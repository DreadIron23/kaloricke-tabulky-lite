@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "janmokry.kaloricketabulkylite.feature.foodexplorer"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "janmokry.kaloricketabulkylite.core.testing.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core-ui"))
    androidTestImplementation(project(":core-testing"))

    // Core Android dependencies
    implementation(libs.androidx.recyclerview)

    implementation(libs.kotlinx.coroutines.android)

    // Arch Components
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // View
    implementation (libs.viewbindingpropertydelegate.noreflection)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.android.material)
    implementation(libs.coil)
    implementation(libs.coil.transformations)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.tikxml.annotation)
    implementation(libs.tikxml.core)
    kapt(libs.tikxml.processor)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Hilt and instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    // Hilt and Robolectric tests.
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)

    // Local tests: jUnit, coroutines, Android runner
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumented tests: jUnit rules and runners
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
}
