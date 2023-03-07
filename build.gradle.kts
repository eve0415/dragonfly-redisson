group = "net.eve0415"
version = "debug"

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.redisson:redisson:3.20.0")
}

application {
    mainClass.set("MainKt")
}
