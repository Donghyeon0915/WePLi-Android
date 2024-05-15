@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

/**
 * 아래와 같이 선언하고 필요한 모듈에서 id로 추가해주면 됨
 * ```gradle
 * plugins {
 *      id("wepli.plugin.hilt")
 * }
 * ```
 */
gradlePlugin {

    // 자주 쓰이는 의존성들을 플러그인 형태로 만들어두는 것
    // 다른 모듈에선 이 플러그인만 가져다 쓰면 해당하는 의존성이 바로 적용됨
    plugins {
        register("android-application") {
            id = "wepli.plugin.android.application" // 컨벤션 플러그인의 이름 (직접 지정)
            implementationClass = "ApplicationConventionPlugin" // id에 매칭되는 클래스명
        }

        register("android-feature") {
            id = "wepli.plugin.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }

        register("android-hilt") {
            id = "wepli.plugin.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("kotlin-serialization") {
            id = "wepli.plugin.kotlin.serialization"
            implementationClass = "KotlinSerializationPlugin"
        }
    }
}