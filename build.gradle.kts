val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.dokka") version "1.5.30"
    id("org.jetbrains.kotlinx.kover") version "0.3.0"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("santa.server.ApplicationKt")
}

repositories {
    mavenCentral()
}

tasks.create("stage") {
    dependsOn("installDist")
}

kover {
    intellijEngineVersion.set("1.0.612")
}
tasks.test {
    extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
        verificationRule {
            name = "The project doesn't have to be big"
            maxValue = 100000
            valueType = kotlinx.kover.api.VerificationValueType.COVERED_LINES_COUNT
        }
        verificationRule {
            // rule without custom name
            minValue = 1
            maxValue = 1000
            valueType = kotlinx.kover.api.VerificationValueType.MISSED_LINES_COUNT
        }
        verificationRule {
            name = "Minimal line coverage rate in percents"
            minValue = 50
            // valueType is kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE by default
        }
    }
}
dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}