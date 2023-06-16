object Versions {
    val compileSdk = 33
    val minSdk = 21
    val targetSdk = 33
    val versionCode = 1
    val versionName = "1.0.0"
    val java = 15
    val androidxCore = "1.10.0"
    val androidxComposeActivity = "1.7.1"
    val androidxLifecycle = "2.6.1"
    val composeBom = "2023.04.01"
    val composeNavigation = "2.5.3"
    val composeCompiler = "1.4.4"
    val composeMaterial3 = "1.2.0-alpha02"
    val junit_jupiter = "5.9.2"
    val espresso = "3.5.1"
    val retrofit = "2.9.0"
    val coroutines = "1.6.4"
    val hilt = "2.45"
    val hiltComposeNavigation = "1.0.0"
    val androidGradle = "7.4.2"
    val kotlin = "1.8.10"
    val gson = "2.10.1"
    val gsonConverter = "2.9.0"
}

object Dependencies {
    val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    val androidxComposeActivity = "androidx.activity:activity-compose:${Versions.androidxComposeActivity}"
    val androidxLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    val composeAnimation = "androidx.compose.animation:animation"
    val composeAnimationCore = "androidx.compose.animation:animation-core"
    val composeFoundation = "androidx.compose.foundation:foundation"
    val composeFoundationLayout = "androidx.compose.foundation:foundation-layout"
    val composeMaterial = "androidx.compose.material:material"
    val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    val composeTooling = "androidx.compose.ui:ui-tooling"
    val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val composeCompiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    val junit_jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val hiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
}

object Plugins {
    val androidApp = "com.android.application"
    val androidLibrary = "com.android.library"
    val kotlinAndroid = "org.jetbrains.kotlin.android"
    val kotlinKapt = "org.jetbrains.kotlin.kapt"
    val hilt = "com.google.dagger.hilt.android"
}