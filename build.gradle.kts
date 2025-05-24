plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // firebase
    alias(libs.plugins.firebase) apply false

    // hilt
    alias(libs.plugins.hilt) apply false

    // ksp
    alias(libs.plugins.ksp) apply false
}