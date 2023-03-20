import com.linecorp.support.project.multi.recipe.configureByLabels

plugins {
    java
    id("io.spring.dependency-management") version Versions.springDependencyManagementPlugin apply false
    id("org.springframework.boot") version Versions.springBoot apply false
    id("io.freefair.lombok") version Versions.lombokPlugin apply false
    id("com.linecorp.build-recipe-plugin") version Versions.lineRecipePlugin

    kotlin("jvm") version Versions.kotlin apply false
    kotlin("kapt") version Versions.kotlin apply false
    kotlin("plugin.spring") version Versions.kotlin apply false
    kotlin("plugin.jpa") version Versions.kotlin apply false
}

allprojects {
    group = "sample.wooni.blog"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.restlet.com")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

subprojects {
    apply(plugin = "idea")
}

configureByLabels("java") {
    apply(plugin = "org.gradle.java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")
//    apply(plugin = "com.coditory.integration-test")

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:${Versions.kotlin}")
            mavenBom("com.google.guava:guava-bom:${Versions.guava}")
        }

        dependencies {
            dependency("org.apache.commons:commons-lang3:${Versions.apacheCommonsLang}")
            dependency("com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonCore}")
        }
    }

    dependencies {
        val implementation by configurations

        implementation("com.google.guava:guava")
        implementation("org.apache.commons:commons-lang3")
    }
}

configureByLabels("boot") {
    apply(plugin = "org.springframework.boot")

    tasks.getByName<Jar>("jar") {
        enabled = false
    }

    tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = true
        archiveClassifier.set("boot")
    }
}

configureByLabels("library") {
    apply(plugin = "java-library")

    tasks.getByName<Jar>("jar") {
        enabled = true
    }
}