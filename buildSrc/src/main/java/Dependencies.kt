object Versions {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val java = 15
    const val androidxCore = "1.10.0"
    const val androidxComposeActivity = "1.7.1"
    const val androidxLifecycle = "2.6.1"
    const val composeBom = "2023.04.01"
    const val composeNavigation = "2.5.3"
    const val composeCompiler = "1.4.4"
    const val composeMaterial3 = "1.2.0-alpha02"
    const val junit_jupiter = "5.9.2"
    const val espresso = "3.5.1"
    const val retrofit = "2.9.0"
    const val coroutines = "1.6.4"
    const val hilt = "2.45"
    const val hiltComposeNavigation = "1.0.0"
    const val androidGradle = "7.4.2"
    const val kotlin = "1.8.10"
    const val gson = "2.10.1"
    const val gsonConverter = "2.9.0"
    const val httpLoggingInterceptor = "4.11.0"
    const val room = "2.5.2"
    const val playServicesLocation = "21.0.1"
    const val coroutinesPlayServices = "1.7.3"
    const val datastore = "1.0.0"

}

object Dependencies {
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxComposeActivity = "androidx.activity:activity-compose:${Versions.androidxComposeActivity}"
    const val androidxLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeAnimation = "androidx.compose.animation:animation"
    const val composeAnimationCore = "androidx.compose.animation:animation-core"
    const val composeFoundation = "androidx.compose.foundation:foundation"
    const val composeFoundationLayout = "androidx.compose.foundation:foundation-layout"
    const val composeMaterial = "androidx.compose.material:material"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeTooling = "androidx.compose.ui:ui-tooling"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    const val junit_jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLoggingInterceptor}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKotlin = "androidx.room:room-ktx:${Versions.room}"
    const val playServicesLocation = "com.google.android.gms:play-services-location:${Versions.playServicesLocation}"
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServices}"
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
}

object Plugins {
    const val androidApp = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
    const val hilt = "com.google.dagger.hilt.android"
}