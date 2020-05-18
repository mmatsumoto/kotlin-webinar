```
    group 'Example'
    version '1.0-SNAPSHOT'
    
    buildscript {
        ext.kotlin_version = '1.3.70'
    
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        }
    }
    
    apply plugin: 'java'
    apply plugin: 'kotlin'
    
    sourceCompatibility = 1.8
    
    repositories {
        jcenter()
    }
    
    dependencies {
        compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        testCompile group: 'junit', name: 'junit', version: '4.12'
    }
```