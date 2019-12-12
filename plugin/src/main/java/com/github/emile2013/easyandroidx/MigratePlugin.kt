package com.github.emile2013.easyandroidx

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Migrate androidX Plugin
 * @author y.huang
 * @since 2019-12-12
 */
class MigratePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        with(project) {

            tasks.create(
                "migrateAndroidX",
                MigrateTask::class.java
            )

        }
    }
}