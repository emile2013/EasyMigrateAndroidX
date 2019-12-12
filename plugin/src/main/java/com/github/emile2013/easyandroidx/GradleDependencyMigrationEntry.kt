package com.github.emile2013.easyandroidx

/**
 * like [org.jetbrains.android.refactoring.AppCompatMigrationEntry#GradleDependencyMigrationEntry] but simple
 *
 * @author y.huang
 * @since 2019-12-12
 */
class GradleDependencyMigrationEntry {

     var oldGroupName: String

     var oldArtifactName: String


     var newGroupName: String

     var newArtifactName: String

     var newBaseVersion: String

    constructor(
        oldGroupName: String,
        oldArtifactName: String,
        newGroupName: String,
        newArtifactName: String,
        newBaseVersion: String
    ) {
        this.oldGroupName = oldGroupName;
        this.oldArtifactName = oldArtifactName
        this.newGroupName = newGroupName
        this.newArtifactName = newArtifactName
        this.newBaseVersion = newBaseVersion
    }
}