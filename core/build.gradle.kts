import Dependensies.APPCOMPAT
import Dependensies.CONSTRAINT
import Dependensies.CORE
import Dependensies.CORE_KTX
import Dependensies.COROUTINES_ANDROID
import Dependensies.COROUTINES_CORE
import Dependensies.KOIN_ANDROID
import Dependensies.KOIN_ANDROID_COMPAT
import Dependensies.KOIN_CORE
import Dependensies.KOTLIN_STDLIB
import Dependensies.LIFECYCLE_LIVEDATA
import Dependensies.LIFECYCLE_RUNTIME
import Dependensies.LIFECYCLE_VIEW_MODEL
import Dependensies.MATERIAL
import Dependensies.RETROFIT
import Dependensies.RETROFIT_COROUTINES
import Dependensies.RETROFIT_GSON
import Dependensies.RETROFIT_OKHHTP
import Dependensies.RETROFIT_RXJAVA2
import Dependensies.ROOM_COMPILE
import Dependensies.ROOM_RUNTIME
import Dependensies.TEST
import Dependensies.TEST_ESPRESSO
import Dependensies.TEST_JUNIT

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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
}

dependencies {

    implementation(CORE)
    implementation(MATERIAL)
    implementation(CONSTRAINT)
    implementation(APPCOMPAT)

    //Kotlin
    implementation(KOTLIN_STDLIB)
    implementation(CORE_KTX)
    // Retrofit 2
    implementation(RETROFIT)
    implementation(RETROFIT_GSON)
    implementation(RETROFIT_OKHHTP)
    implementation(RETROFIT_RXJAVA2)
    implementation(RETROFIT_COROUTINES)
    //Room
    implementation(ROOM_RUNTIME)
    kapt(ROOM_COMPILE)
    //Koin
    implementation(KOIN_CORE)
    implementation(KOIN_ANDROID)
    implementation(KOIN_ANDROID_COMPAT)
    //Coroutine
    api(COROUTINES_ANDROID)
    api(COROUTINES_CORE)
    api(LIFECYCLE_VIEW_MODEL)
    api(LIFECYCLE_RUNTIME)
    api(LIFECYCLE_LIVEDATA)
    //Kotlin
    implementation(KOTLIN_STDLIB)
    implementation(CORE_KTX)
    // Test
    androidTestImplementation(TEST)
    androidTestImplementation(TEST_ESPRESSO)
    testImplementation(TEST_JUNIT)

}
