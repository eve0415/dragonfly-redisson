group = "net.eve0415"
version = "debug"

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.redisson:redisson:3.18.0")
}

application {
    mainClass.set("MainKt")
}
