import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.vanniktech.maven.publish)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "top.wkbin.spanny"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("io.github.wkbin", "spanny", "1.0.0")

    pom {
        name = "spanny"
        description = "一个 SpannableString 的封装库"
        url = "https://github.com/wkbin/Spanny"
        licenses {
            license {
                name = "Apache-2.0"
                url = "https://spdx.org/licenses/Apache-2.0.html"
            }
        }

        developers {
            developer {
                id = "wkbin/Spanny"
                name = "xxxx"
                url = "https://github.wkbin.io"
            }
        }
        scm {
            url = "https://github.com/wkbin/Spanny"
            connection = "scm:git:git@github.com:wkbin/Spanny.git"
            developerConnection = "scm:git:ssh://git@github.com:wkbin/Spanny.git"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.register<Copy>("extractJarFromAar") {
    val aarFile = file("$buildDir/outputs/aar/${project.name}-release.aar")
    from(zipTree(aarFile))
    include("classes.jar")
    into("$buildDir/outputs/jar")
    rename("classes.jar", "${project.name}.jar")

    dependsOn("bundleReleaseAar")
}