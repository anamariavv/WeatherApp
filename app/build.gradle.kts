plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_15
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_15
    }
}

dependencies {
    val composeBom = platform(Dependencies.composeBom)

    implementation(composeBom)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.androidxCore)
    implementation(Dependencies.androidxLifecycle)
    implementation(Dependencies.androidxComposeActivity)
    implementation(Dependencies.composeAnimation)
    implementation(Dependencies.composeAnimationCore)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeFoundationLayout)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeCompiler)
    implementation(Dependencies.espresso)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.hilt)

    kapt(Dependencies.hiltCompiler)

    androidTestImplementation(Dependencies.junit_jupiter)
    androidTestImplementation(composeBom)

    debugImplementation(Dependencies.composeToolingPreview)

    implementation(project(":domain"))
}
