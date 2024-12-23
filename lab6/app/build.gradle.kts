plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")

}



android {
    namespace = "com.example.lab6"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.lab6"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}

dependencies {

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //val roomVersion = "2.5.0" // Проверьте наличие последней версии
    //implementation("androidx.room:room-runtime:$roomVersion") // Основная библиотека Room
    //kapt("androidx.room:room-compiler:$roomVersion") // Компилятор аннотаций
    //implementation("androidx.room:room-ktx:$roomVersion") // Расширения для Kotlin

    implementation("androidx.recyclerview:recyclerview:1.2.1")
//    implementation("androidx.room:room-runtime:2.5.0")
//    kapt("androidx.room:room-compiler:2.5.0")
//    implementation("androidx.room:room-ktx:2.5.0")

}