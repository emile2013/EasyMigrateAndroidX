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
        classpath "com.github.emile2013:easyandroidx:$easyandroidx_version" //add this line
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

###### Run task

```
./gradlew migrateAndroidX

```

###### Modify manually build.gradle [手动修改build.gradle]

>考虑到androidx版本一直变更，此项目没有把androidX依赖版本替换，所以需要手动修改build.gradle文件中相应依赖版本号，以及可能要增加未审明的依赖，相应依赖可参考[artifact-mappings](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings)

>This Repository just replace dependency groupName and artifactName without version, so after running, modify the [newest](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings) version for androidX 

## Tips
 
 此项目原理是解析migrate.xml文件(来自AS源码)，遍历所有项目中的类文件、资源文件以及gradle文件，并进行内容替换，能加快像repo管理或多项目迁移速度；

## Reference
- [adt-tools-base](http://git.jetbrains.org/?p=idea/adt-tools-base.git;a=tree)

## License

ResMonitor is licensed under the [BSD 3-Clause License](./LICENSE).
