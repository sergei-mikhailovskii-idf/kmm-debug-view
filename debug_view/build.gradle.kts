plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("io.realm.kotlin") version "1.11.0"
    id("maven-publish")
}

group = "com.idfinance.kmm"
version = "0.0.2"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    ios()
    iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "DebugView"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(libs.realm)
                implementation(libs.decomposeCompose)
                implementation(libs.decompose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.activity.compose)
            }
        }
    }
}

android {
    namespace = "com.idfinance.debugview"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

publishing {
    publications {
        matching { it.name in listOf("iosArm64", "iosX64", "kotlinMultiplatform") }.all {
            val targetPublication = this@all
            tasks.withType<AbstractPublishToMaven>()
                .matching { it.publication == targetPublication }
                .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
        }
    }
    repositories {
        maven {
            name = "kmm-debug-view"
            url = uri("https://maven.pkg.github.com/sergei-mikhailovskii-idf/kmm-debug-view")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_API_KEY")
            }
        }
    }
}