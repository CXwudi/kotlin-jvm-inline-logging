/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.1.1/userguide/building_java_projects.html
 */

plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm") version "1.5.21"

  // Apply the java-library plugin for API and implementation separation.
  `java-library`

  `maven-publish`
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

version = "1.0.0"
group = "mikufan.cx.inlinelogging"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = java.sourceCompatibility

dependencies {
  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("org.slf4j:slf4j-api:1.7.32")

  // Use the Kotlin test library.
  testImplementation("io.kotest:kotest-runner-junit5-jvm:4.6.1")

  // logging enabled only in test cases
  testImplementation("ch.qos.logback:logback-classic:1.2.4")
  // non-inline logger to be compared
  testImplementation("io.github.microutils:kotlin-logging-jvm:2.0.10")

}

tasks.withType<Jar> {
  manifest {
    attributes(
      mapOf(
        "Implementation-Title" to project.name,
        "Implementation-Version" to project.version
      )
    )
  }
}

java {
  withSourcesJar()
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

publishing {
  publications {
    create<MavenPublication>("maven-kotlin") {
      from(components["java"])
    }
  }
}