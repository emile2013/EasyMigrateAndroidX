# EasyMigrateAndroidX
[![license](http://img.shields.io/badge/license-BSD3-brightgreen.svg?style=flat)](https://github.com/emile2013/EasyMigrateAndroidX/tree/master/LICENSE)
[![Release Version](https://jitpack.io/v/emile2013/ResMonitor.svg)](https://jitpack.io/#emile2013/ResMonitor)

Help Android Program Migrate To AndroidX

> AS Migrate to Androidx 功能在多项目或大项目中迁移存在修改源码错乱以及效率问题，此项目能减少类似错误和提高迁移效率。
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
tips: 这里只要在一个子项目中审明就行，无需多项目均增加！！

###### Run task

```
./gradlew migrateAndroidX

```

###### Modify manually build.gradle [手动修改build.gradle]

> 有场景需要手动再次修改build.gradle依赖，例如原来是传递依赖，不是一级审明，这时需要手动主动添加相应依赖，可能要增加未审明的依赖和版本可参考[artifact-mappings](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings)

> You maybe need to add not declare dependency in build.gradle , so after running, modify the [newest](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings) artifact-mappings for androidX

## Tips
 
 此项目原理是解析migrate.xml文件(来自AS源码)，遍历 `所有项目`(setting.gradle中include的所有项目)中的类文件、资源文件以及gradle文件，并进行内容替换，能加快像repo管理或多项目迁移速度；

## Reference
- [adt-tools-base](http://git.jetbrains.org/?p=idea/adt-tools-base.git;a=tree)

## License

ResMonitor is licensed under the [BSD 3-Clause License](./LICENSE).
