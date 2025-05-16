plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // firebase
    alias(libs.plugins.firebase)

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
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "MyWorld (Dev)")
            buildConfigField("Boolean", "IS_DEV", "true")
        }
        create("prod") {
            dimension = "env"
            resValue("string", "app_name", "MyWorld")
            buildConfigField("Boolean", "IS_DEV", "false")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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

    // firebase
    implementation(platform(libs.firebase.bom))

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