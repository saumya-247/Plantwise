//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("io.realm.kotlin") version "1.11.0" apply false
}
//dependencies {
//    classpath("com.google.gms:google-services:4.4.2")
//}
