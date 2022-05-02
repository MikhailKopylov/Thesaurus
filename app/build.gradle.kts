import Dependensies.APPCOMPAT
import Dependensies.COIL
import Dependensies.CONSTRAINT
import Dependensies.CORE
import Dependensies.CORE_KTX
import Dependensies.KOIN_ANDROID
import Dependensies.KOIN_ANDROID_COMPAT
import Dependensies.KOIN_CORE
import Dependensies.KOTLIN_STDLIB
import Dependensies.MATERIAL
import Dependensies.ROOM_COMPILE
import Dependensies.ROOM_RUNTIME
import Dependensies.SPLASH_SCREEN
import Dependensies.TEST
import Dependensies.TEST_ESPRESSO
import Dependensies.TEST_JUNIT

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "ru.amk.tesaurus"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}
dependencies {

    implementation(project(":translate"))
    implementation(project(":core"))

    implementation(CORE)
    implementation(MATERIAL)
    implementation(CONSTRAINT)
    implementation(APPCOMPAT)

    //Kotlin
    implementation(KOTLIN_STDLIB)
    implementation(CORE_KTX)
    // Test
    androidTestImplementation(TEST)
    androidTestImplementation(TEST_ESPRESSO)
    testImplementation(TEST_JUNIT)
    //Koin
    implementation(KOIN_CORE)
    implementation(KOIN_ANDROID)
    implementation(KOIN_ANDROID_COMPAT)
    //Room
    implementation(ROOM_RUNTIME)
    kapt(ROOM_COMPILE)
    //Coil
    implementation(COIL)

    implementation(SPLASH_SCREEN)
}

