import java.io.FileInputStream
import java.util.Properties

val projectProperties = Properties().apply {
    load(FileInputStream(rootProject.file("project.properties")))
}

val test = projectProperties["isTest"].toString().toBoolean()

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // hilt
    alias(libs.plugins.hilt)

    // kps
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.myWorld"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myWorld"
        minSdk = 35
        targetSdk = 35
        versionCode = projectProperties["versionCode"].toString().toInt()
        versionName = "${projectProperties["major"]}.${projectProperties["minor"]}.${projectProperties["patch"]}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isShrinkResources = false
            isMinifyEnabled = false
            if (test) {
                resValue("string", "app_name", "myWorld (Dev)")
            } else {
                resValue("string", "app_name", "myWorld")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            if (test) {
                resValue("string", "app_name", "myWorld (Dev)")
            } else {
                resValue("string", "app_name", "myWorld")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)

    // module
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    // hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}