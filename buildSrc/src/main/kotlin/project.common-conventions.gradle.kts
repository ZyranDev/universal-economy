plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    mavenCentral()
}

tasks {
    java {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(
                    "${findProperty("java")}"
                )
            )
        }
    }

    compileJava {
        options.compilerArgs.add("-parameters")
    }
}