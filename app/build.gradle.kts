plugins {
        id("com.android.application")
            id("org.jetbrains.kotlin.android")
                id("com.google.dagger.hilt.android")
                    id("com.google.devtools.ksp")
}

android {
        namespace = "com.example.portfolio"
            compileSdk = 34

                defaultConfig {
                            applicationId = "com.example.portfolio"
                                    minSdk = 24
                                            targetSdk = 34
                                                    versionCode = 1
                                                            versionName = "1.0"
                }

                    buildFeatures {
                                compose = true
                    }
                        composeOptions {
                                    kotlinCompilerExtensionVersion = "1.5.4"
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
        // UI Compose
            implementation("androidx.activity:activity-compose:1.8.2")
                implementation("androidx.compose.material3:material3:1.2.0")
                    implementation("androidx.compose.ui:ui:1.6.2")
                        
                            // Room Database
                                val roomVersion = "2.6.1"
                                    implementation("androidx.room:room-runtime:$roomVersion")
                                        implementation("androidx.room:room-ktx:$roomVersion")
                                            ksp("androidx.room:room-compiler:$roomVersion")

                                                // ViewModel and Lifecycle Compose
                                                    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
                                                        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
                                                            
                                                                // Hilt for Dependency Injection
                                                                    implementation("com.google.dagger:hilt-android:2.51.1")
                                                                        ksp("com.google.dagger:hilt-android-compiler:2.51.1")
                                                                            implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
}
