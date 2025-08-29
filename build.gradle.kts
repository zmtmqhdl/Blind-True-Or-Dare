plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // hilt
    alias(libs.plugins.hilt) apply false

    // ksp
    alias(libs.plugins.ksp) apply false

    id("com.google.gms.google-services") version "4.4.3" apply false
}

extra.apply {
    set("compileSdk", 36)
    set("targetSdk", 36)
    set("minSdk", 35)
}