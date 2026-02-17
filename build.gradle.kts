plugins {
    id("java")
    application
}

group = "org.wwss"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.13.2")
}

application {
    mainClass.set("org.wwss.Main")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.wwss.Main"
        attributes["Implementation-Title"] = "MSM"
        attributes["Implementation-Version"] = version
    }
    // 创建 fat jar，包含所有依赖
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
