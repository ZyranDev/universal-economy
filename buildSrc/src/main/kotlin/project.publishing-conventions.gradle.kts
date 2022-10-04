plugins {
    id("project.common-conventions")
    `maven-publish`
}

val snapshotRepository: String by project
val releaseRepository: String by project

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "${rootProject.name}-${project.name}"
            from(components["java"])
        }
    }
}