# EasyMigrateAndroidX
[![license](http://img.shields.io/badge/license-BSD3-brightgreen.svg?style=flat)](https://github.com/emile2013/EasyMigrateAndroidX/tree/master/LICENSE)
[![Release Version](https://jitpack.io/v/emile2013/EasyMigrateAndroidX.svg)](https://jitpack.io/#emile2013/EasyMigrateAndroidX)

辅助Android项目迁移至androidX

> AS Migrate to Androidx 功能在多项目或大项目中迁移存在修改源码错乱以及效率问题，此项目能减少类似错误和提高迁移效率。

[README English Version](README.md)
## 特征
- 支持java,kt,xml源文件中support相关包名修正至androidX包；
- 支持gradle文件中support依赖替换成相应的androidX依赖；

## 原理
>此项目原理是解析migrate.xml文件(来自AS源码)，遍历 `所有项目`(setting.gradle中include的所有项目)中的类文件、资源文件以及gradle文件，并进行内容替换，能加快像repo管理或多项目迁移速度。

## 使用

###### 根项目`build.gradle`添加classpath依赖，切记加入 maven { url 'https://jitpack.io' }依赖

```groovy
buildscript {
    ext.easyandroidx_version='0.1.1'
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

###### App子项目`build.gradle` apply 插件

```groovy
apply plugin: 'com.android.application'
apply plugin: 'com.github.emile2013.migrateandroidx' // add this line
```
tips: 这里只要在一个子项目中审明就行，无需多项目均增加！！

###### 运行任务

```
./gradlew migrateAndroidX

```

###### 手动修改`build.gradle`

> 有场景需要手动再次修改build.gradle依赖，例如原来是传递依赖，不是一级声明，这时可能需要手动添加相应依赖，可能要增加未声明的依赖和版本可参考[artifact-mappings](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings)

## 注意事项
 
- 不支持在单行声明末尾加注释，如以下所示
```aidl
    implementation("com.android.support:support-annotations:${rootProject.ext.google.support}"){ //do not add this comment
    }
```
- 为能顺利迁移和运行androidX,建议将Gradle升至4.6+（例如5.6.2），以及AGP升级至3.2.0+（例如3.5.2）,并将以下属性添加至 `gradle.properties`文件中
```
android.useAndroidX=true
android.enableJetifier=true
```


## 引用
- [adt-tools-base](http://git.jetbrains.org/?p=idea/adt-tools-base.git;a=tree)

## License

ResMonitor is licensed under the [BSD 3-Clause License](./LICENSE).
