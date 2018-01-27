import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.flywaydb.gradle.FlywayExtension
import org.gradle.kotlin.dsl.version
import org.jooq.util.GenerationTool
import org.jooq.util.KeepNamesGeneratorStrategy
import org.jooq.util.jaxb.*
import org.jooq.util.jaxb.Target

plugins {
    id("org.jetbrains.kotlin.jvm") version ("1.2.21")
    id("com.github.johnrengelman.shadow") version ("2.0.2")
    id("org.flywaydb.flyway") version ("5.0.6")
}

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.postgresql:postgresql:42.2.1")
        classpath("org.jooq:jooq:3.10.4")
        classpath("org.jooq:jooq-meta:3.10.4")
        classpath("org.jooq:jooq-codegen:3.10.4")
    }
}

configure<FlywayExtension> {
    url = "jdbc:postgresql://172.17.0.2:5432/postgres"
    user = "postgres"
    password = "mysecretpassword"
}

group = "codes.foobar"
version = "1.0"

tasks {
    val buildFrontEnd by creating(Exec::class) {
        workingDir("frontend")
        commandLine("npm", "run", "build")
    }

    val copyFrontEnd by creating(Copy::class) {
        dependsOn(buildFrontEnd)

        from("frontend/dist")
        into("build/resources/main/frontend")
    }

    val jooqCodeGen by creating(DefaultTask::class) {
        dependsOn(tasks["flywayMigrate"])

        val configuration = Configuration().apply {
            jdbc = Jdbc().apply {
                driver = "org.postgresql.Driver"
                url = "jdbc:postgresql://172.17.0.2:5432/postgres"
                user = "postgres"
                password = "mysecretpassword"
            }
            generator = Generator().apply {
                database = Database().apply {
                    name = "org.jooq.util.postgres.PostgresDatabase"
                    includes = ".*"
                    excludes = "flyway_schema_history"
                    inputSchema = "public"
                    isOutputSchemaToDefault = true
                }
                target = Target().apply {
                    packageName = "codes.foobar.wedapp.jooq"
                    directory = "src/main/java"
                }
            }
        }

        GenerationTool.generate(configuration)
    }

    val shadowJar: ShadowJar by tasks
    shadowJar.apply {
        dependsOn(copyFrontEnd, jooqCodeGen)

        manifest {
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = version
            attributes["Main-Class"] = "codes.foobar.wedapp.MainKt"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("com.sparkjava:spark-core:2.7.1")
    compile("org.slf4j:slf4j-simple:1.7.25")
    compile("org.apache.logging.log4j:log4j-api:2.10.0")
    compile("org.apache.logging.log4j:log4j-core:2.10.0")
    compile("org.apache.logging.log4j:log4j-slf4j-impl:2.10.0")
    compile("com.squareup.moshi:moshi:1.5.0")
    compile("com.google.code.gson:gson:2.8.2")
    compile("com.google.guava:guava:23.6-jre")
    compile("com.auth0:java-jwt:3.3.0")
    compile("com.auth0:jwks-rsa:0.3.0")
    compile("com.zaxxer:HikariCP:2.7.6")
    compile("org.jooq:jooq:3.10.4")
    compile("org.flywaydb:flyway-core:5.0.6")
    compile("org.postgresql:postgresql:42.2.1")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.3")
    testCompile("org.assertj:assertj-core:3.9.0")
    testCompile("org.mockito:mockito-core:2.+")
}