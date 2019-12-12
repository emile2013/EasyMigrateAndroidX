package com.github.emile2013.easyandroidx

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File


/**
 *
 * Migrate androidX Task
 *
 * @author y.huang
 * @since 2019-12-12
 */
open class MigrateTask : DefaultTask() {


    @TaskAction
    fun doAction() {

        val classEntries = mutableListOf<ClassMigrationEntry>()
        val packageEntries = mutableListOf<ClassMigrationEntry>()
        val gradleDependencyEntries = mutableListOf<GradleDependencyMigrationEntry>()

        parseMigrationFile(object : MigrationParserVisitor {

            override fun visitClass(old: String, new: String) {
                classEntries.add(ClassMigrationEntry(old, new))
            }

            override fun visitPackage(old: String, new: String) {
                packageEntries.add(ClassMigrationEntry(old, new))
            }

            override fun visitGradleCoordinate(
                    oldGroupName: String,
                    oldArtifactName: String,
                    newGroupName: String,
                    newArtifactName: String,
                    newBaseVersion: String
            ) {
                gradleDependencyEntries.add(
                        GradleDependencyMigrationEntry(
                                oldGroupName,
                                oldArtifactName,
                                newGroupName,
                                newArtifactName,
                                newBaseVersion
                        )
                )
            }

            override fun visitGradleCoordinateUpgrade(
                    groupName: String,
                    artifactName: String,
                    newBaseVersion: String
            ) {
                //do nothing
            }
        })

        migrateNow(classEntries, packageEntries, gradleDependencyEntries)

    }

    private fun migrateNow(
            classEntries: MutableList<ClassMigrationEntry>,
            packageEntries: MutableList<ClassMigrationEntry>,
            gradleDependencyEntries: MutableList<GradleDependencyMigrationEntry>
    ) {


        var walk = project.rootDir.walk()
        walk.filter { !isBuildDir(it) }
                .filter { it.isFile }
                .filter { !it.isHidden }
                .filter { it.extension == "xml" || it.extension == "java" || it.extension == "kt" || it.extension == "gradle" }
                .forEach {
                    if (it.extension == "gradle") {
                        migrateDSL(it, gradleDependencyEntries)
                    } else {
                        migrateSource(it, classEntries, packageEntries)
                    }
                }


    }

    private fun isBuildDir(file: File): Boolean {

        project.childProjects.forEach {
            if (file.absolutePath.startsWith(it.value.buildDir.absolutePath)) {
                return true
            }
        }
        return false
    }

    private fun migrateDSL(gradle: File, gradleDependencyEntries: MutableList<GradleDependencyMigrationEntry>) {

        var text = gradle.readText()

        var rewrite = false
        gradle.forEachLine {

            for (entry in gradleDependencyEntries) {

                if (it.contains("${entry.oldGroupName}:${entry.oldArtifactName}")) {

                    var newValue = it.replace(
                            "${entry.oldGroupName}:${entry.oldArtifactName}",
                            "${entry.newGroupName}:${entry.newArtifactName}"
                    )
                    newValue = newValue.replaceAfterLast(":", "${entry.newBaseVersion}\"").replace("'", "\"")
                    text = text.replace(it, newValue)
                    rewrite = true
                }
            }
        }
        if (rewrite) {
            gradle.writeText(text)
        }
    }

    private fun migrateSource(it: File, classEntries: MutableList<ClassMigrationEntry>, packageEntries: MutableList<ClassMigrationEntry>) {
        var text = it.readText()
        var rewrite = false

        //first find target class
        for (entry in classEntries) {
            if (text.contains(entry.oldName)) {
                text = text.replace(entry.oldName, entry.newName)
                rewrite = true
            }
        }
        //second find target package
        for (entry in packageEntries) {
            if (text.contains(entry.oldName)) {
                text = text.replace(entry.oldName, entry.newName)
                rewrite = true
            }
        }
        if (rewrite) {
            it.writeText(text)
        }
    }


}