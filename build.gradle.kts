plugins {
  kotlin("jvm") version "1.8.21"
  kotlin("plugin.jpa") version "1.8.21"
}

group = "com.github.cthiebault"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val hibernateVersion = "6.2.4.Final"

dependencies {
  implementation("org.hibernate.orm:hibernate-core:$hibernateVersion")

  testImplementation(kotlin("test"))
  testImplementation("org.hibernate.orm:hibernate-testing:$hibernateVersion")
  testImplementation("com.h2database:h2:2.1.214")
  testImplementation("org.slf4j:slf4j-simple:2.0.7")
  testImplementation("org.jboss.slf4j:slf4j-jboss-logging:1.2.1.Final")
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(17)
}
