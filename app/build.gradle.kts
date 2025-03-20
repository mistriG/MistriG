plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services") // Firebase Services
}

android {
    namespace = "com.example.mistrig"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mistrig"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // 🔹 AndroidX Core Libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // 🔹 Material UI Components
    implementation("com.google.android.material:material:1.11.0")

    // 🔹 RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // 🔥 Firebase BOM (Bill of Materials) - Ensures Latest Versions
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))

    // ✅ Firebase Services
    implementation("com.google.firebase:firebase-analytics") // Analytics
    implementation("com.google.firebase:firebase-auth") // Authentication



    // ✅ Google Sign-In (For Social Login)
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // 🔹 Navigation Components (Latest Stable)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    implementation ("com.google.firebase:firebase-auth:22.1.0")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")



    // ✅ Bottom Navigation
    implementation("np.com.susanthapa:curved_bottom_navigation:0.6.5")

    // 🔹 Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
