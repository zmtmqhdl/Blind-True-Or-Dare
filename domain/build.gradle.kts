plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)

    // ksp
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        proguardFiles("proguard-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.runtime)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)

    // hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}