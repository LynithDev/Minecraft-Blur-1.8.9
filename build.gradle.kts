plugins {
    java
    kotlin("jvm") version("2.0.0")
    val dgtVersion = "2.2.3"
    id("dev.deftu.gradle.tools") version(dgtVersion)
    id("dev.deftu.gradle.tools.resources") version(dgtVersion)
    id("dev.deftu.gradle.tools.minecraft.loom") version(dgtVersion)
}

group = property("group") as String? ?: "dev.lynith"
version = property("version") as String? ?: "UNKNOWN"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
}