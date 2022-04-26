import Dependensies.APPCOMPAT
import Dependensies.COIL
import Dependensies.CONSTRAINT
import Dependensies.CORE
import Dependensies.CORE_KTX
import Dependensies.KOTLIN_STDLIB
import Dependensies.MATERIAL
import Dependensies.TEST
import Dependensies.TEST_ESPRESSO
import Dependensies.TEST_JUNIT

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(project(":core"))

    implementation(CORE)
    implementation(MATERIAL)
    implementation(CONSTRAINT)
    implementation(APPCOMPAT)

    //Coil
    implementation(COIL)
    //Kotlin
    implementation(KOTLIN_STDLIB)
    implementation(CORE_KTX)
    // Test
    androidTestImplementation(TEST)
    androidTestImplementation(TEST_ESPRESSO)
    testImplementation(TEST_JUNIT)
}
