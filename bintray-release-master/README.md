# bintray-release [![](https://ci.novoda.com/buildStatus/icon?job=bintray-release)](https://ci.novoda.com/job/bintray-release/lastBuild/console) [![Download](https://api.bintray.com/packages/novoda-oss/maven/bintray-release/images/download.svg) ](https://bintray.com/novoda-oss/maven/bintray-release/_latestVersion) [![](https://raw.githubusercontent.com/novoda/novoda/master/assets/btn_apache_lisence.png)](LICENSE.txt)

Super duper easy way to release your Android and other artifacts to bintray.


## Description

This is a helper for releasing libraries to bintray. It is intended to help configuring stuff related to maven and bintray.
At the moment it works with Android Library projects, plain Java and plain Groovy projects, but our focus is to mainly support Android projects.

## Adding to project

To publish a library to bintray using this plugin, add these dependencies to the `build.gradle` of the module that will be published:

```groovy
apply plugin: 'com.novoda.bintray-release' // must be applied after your artifact generating plugin (eg. java / com.android.library)

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.novoda:bintray-release:<latest-version>'
    }
}
```

## Simple usage

Use the `publish` closure to set the info of your package:

```groovy
publish {
    userOrg = 'novoda'
    groupId = 'com.novoda'
    artifactId = 'bintray-release'
    publishVersion = '0.6.1'
    desc = 'Oh hi, this is a nice description for a project, right?'
    website = 'https://github.com/novoda/bintray-release'
}
```

If you use [Kotlin DSL](https://github.com/gradle/kotlin-dsl) use:

```kotlin
import com.novoda.gradle.release.PublishExtension

configure<PublishExtension> {
  userOrg = "novoda"
  groupId = "com.novoda"
  artifactId = "bintray-release"
  publishVersion = "0.6.1"
  desc = "Oh hi, this is a nice description for a project, right?"
  website = "https://github.com/novoda/bintray-release"
}
```

Finally, use the task `bintrayUpload` to publish (make sure you build the project first!):

```bash
$ ./gradlew clean build bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false
```

More info on the available properties and other usages in the [Github Wiki](https://github.com/novoda/bintray-release/wiki).

## Gradle compatibility

The plugin officially supports only Gradle 4.0+

## Snapshots
[![CI status](https://ci.novoda.com/buildStatus/icon?job=bintray-release-snapshot)](https://ci.novoda.com/job/bintray-release-snapshot/lastBuild/console) [![Download from Bintray](https://api.bintray.com/packages/novoda-oss/snapshots/bintray-release/images/download.svg)](https://bintray.com/novoda-oss/snapshots/bintray-release/_latestVersion)

Snapshot builds from [`develop`](https://github.com/novoda/bintray-release/compare/master...develop) are automatically deployed to a [repository](https://bintray.com/novoda-oss/snapshots/bintray-release/_latestVersion) that is **not** synced with JCenter.
To consume a snapshot build add an additional maven repo as follows:
```
repositories {
    maven {
        url 'https://dl.bintray.com/novoda-oss/snapshots/'
    }
}
```

You can find the latest snapshot version following this [link](https://bintray.com/novoda-oss/snapshots/bintray-release/_latestVersion).

## Links

Here are a list of useful links:

 * We always welcome people to contribute new features or bug fixes, [here is how](https://github.com/novoda/novoda/blob/master/CONTRIBUTING.md)
 * If you have a problem check the [Issues Page](https://github.com/novoda/bintray-release/issues) first to see if we are working on it
 * For further usage or to delve more deeply checkout the [Project Wiki](https://github.com/novoda/bintray-release/wiki)
 * Looking for community help, browse the already asked [Stack Overflow Questions](http://stackoverflow.com/questions/tagged/support-bintray-release) or use the tag: `support-bintray-release` when posting a new question  
