    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.google.gms.google.services)
    //    id("com.google.gms.google-services")

    }

    android {
        namespace = "com.example.plantwise"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.plantwise"
            minSdk = 24
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures{
            viewBinding=true
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
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = "11"
        }
        buildToolsVersion = "35.0.0"

    //    configurations.all {
    //        resolutionStrategy.force("com.google.android.gms:play-services-auth:20.7.0")
    //    }


    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.firebase.auth)
        implementation(libs.androidx.credentials)
        implementation(libs.androidx.credentials.play.services.auth)
        implementation(libs.googleid)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        implementation("com.google.firebase:firebase-auth-ktx:23.2.0")
    //    implementation("com.google.firebase:firebase-auth-ktx:21.0.3")
    //    implementation("com.google.android.gms:play-services-auth:20.7.0")




    }
    //
    //apply(plugin = "com.google.gms.google-services")

