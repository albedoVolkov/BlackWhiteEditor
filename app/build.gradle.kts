plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.albedo.blackwhiteeditor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.albedo.blackwhiteeditor"
        minSdk = 28
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
    // Allow references to generated code
    kapt{
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    val hilt = "2.48"
    val lifecycle = "2.7.0"
    val kotlinxCoroutinesVersion = "1.7.1"
    val roomVersion =  "2.6.1"
    val okhttp3Version =  "4.7.2"
    val retrofitVersion =  "2.9.0"

    // Testing
//    androidTestImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation "androidx.arch.core:core-testing:2.2.0"
//    androidTestImplementation "androidx.test.ext:junit-ktx:1.1.5"
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
//    androidTestImplementation 'com.google.truth:truth:0.43'




    //Kotlin Library
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")

    //Android UI Layout Library
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //Life Cycle Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle")

    //Kotlin Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion")

    //Android Unit Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Okhttp3
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3Version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp3Version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:3.7.0")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")// library "Room"
    implementation("androidx.room:room-ktx:$roomVersion")// additionally for Kotlin Coroutines, Kotlin Flows
    androidTestImplementation("androidx.room:room-testing:$roomVersion")//testing
    kapt("androidx.room:room-compiler:$roomVersion")// code-generator

//    //Firebase
//    implementation("com.google.firebase:firebase-bom:32.3.1")
//    implementation("com.google.firebase:firebase-firestore-ktx")
//    implementation("com.google.firebase:firebase-storage-ktx")
//    implementation("com.google.firebase:firebase-crashlytics")
//    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-auth-ktx")

    //Dagger/Hilt
    implementation("com.google.dagger:hilt-android:$hilt")
    kapt("com.google.dagger:hilt-android-compiler:$hilt")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    //Gson
    implementation ("com.google.code.gson:gson:2.10.1")

}