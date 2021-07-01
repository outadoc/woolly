import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("com.diffplug.spotless") version "5.14.0"
    id("com.github.ben-manes.versions") version "0.39.0"
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("com.android.tools.build:gradle:4.2.0")
    }
}

allprojects {
    group = "fr.outadoc.woolly"
    version = "0.1-alpha01"

    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        maven { url = uri("https://nexus.outadoc.fr/repository/public") }
    }
}

configure<SpotlessExtension> {
    format("misc") {
        target(
            "**/*.kt",
            "**/*.kts",
            "**/*.md",
            "**/.gitignore"
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
