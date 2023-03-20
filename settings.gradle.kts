pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://maven.springframework.org/release")
        }
        maven {
            url = uri("https://maven.restlet.com")
        }
    }
}

rootProject.name = "blog"
include("apps:blog-api")
include("libs:adapter-http")
include("libs:adapter-persistence")
include("libs:application")