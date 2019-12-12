package com.github.emile2013.easyandroidx

/**
 *
 *  like [org.jetbrains.android.refactoring.AppCompatMigrationEntry] but simple
 *
 * @author y.huang
 * @since 2019-12-12
 */
class ClassMigrationEntry {

    var oldName: String

     var newName: String


    constructor(oldName: String, newName: String) {
        this.oldName = oldName
        this.newName = newName
    }

}