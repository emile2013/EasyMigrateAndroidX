# EasyMigrateAndroidX
[![license](http://img.shields.io/badge/license-BSD3-brightgreen.svg?style=flat)](https://github.com/emile2013/EasyMigrateAndroidX/tree/master/LICENSE)
[![Release Version](https://jitpack.io/v/emile2013/ResMonitor.svg)](https://jitpack.io/#emile2013/ResMonitor)

Help Android Program Migrate To AndroidX

> Android Studio `Migrate to Androidx` make lots of source file such as java content not exactly right, result project compile error. This repository can fast migrate to androidX,without such issue.

## Features
- support java,xml,kt extension file replace;
- support gradle extension file replace;

[README 中文版](README.zh-CN.md)

## Getting Started 

###### Edit root project build.gradle file, append plugin in  `buildscript`  classpath ，and do not forget add maven { url 'https://jitpack.io' } too.

```groovy
buildscript {
    ext.easyandroidx_version='0.1.0'
    repositories {
        maven { url 'https://jitpack.io' } //add this line
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath "com.github.emile2013:EasyMigrateAndroidX:$easyandroidx_version" //add this line
    }
}

allprojects {
    repositories {
      maven { url 'https://jitpack.io' }  //add this line
      google()
      mavenCentral()
      jcenter()
    }
}
```

###### App module build.gradle file  add  dependency

```groovy
apply plugin: 'com.android.application'
apply plugin: 'com.github.emile2013.migrateandroidx' // add this line
```
tips: just  add in one child module , no need add in all projects

###### Run task

```
./gradlew migrateAndroidX

```

###### Modify manually build.gradle [手动修改build.gradle]

> You maybe need to add not declare dependency in build.gradle , so after running, modify the [newest](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings) artifact for androidX

## Tips
 
-  Repository do not support append comment in `dependency declare line` , such as  :
```aidl
    implementation("com.android.support:support-annotations:${rootProject.ext.google.support}"){ //do not add this comment
    }
```
- androidX Prerequisites such as gradle version 4.6+,AGP 3.2.0+, and both of them are set to true in your gradle.properties file:
```
android.useAndroidX=true
android.enableJetifier=true
```

## Reference
- [adt-tools-base](http://git.jetbrains.org/?p=idea/adt-tools-base.git;a=tree)

## License

ResMonitor is licensed under the [BSD 3-Clause License](./LICENSE).
