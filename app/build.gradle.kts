plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "hr.riteh.oop.weatherapp"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}


dependencies {
    val composeBom = platform(Dependencies.composeBom)

    implementation(composeBom)
    implementation(Dependencies.androidxCore)
    implementation(Dependencies.androidxLifecycle)
    implementation(Dependencies.androidxComposeActivity)
    implementation(Dependencies.composeAnimation)
    implementation(Dependencies.composeAnimationCore)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeFoundationLayout)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeCompiler)
    implementation(Dependencies.espresso)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.coroutines)

    androidTestImplementation(Dependencies.junit_jupiter)
    androidTestImplementation(composeBom)

    debugImplementation(Dependencies.composeToolingPreview)
}