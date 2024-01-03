plugins {
  java
  checkstyle
  // jhipster-needle-gradle-plugin
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
    vendor.set(JvmVendorSpec.ADOPTIUM)
  }
}

checkstyle {
  toolVersion = libs.versions.checkstyle.get()
}

// jhipster-needle-gradle-project-extension-plugin-configuration

repositories {
  mavenCentral()
  // jhipster-needle-gradle-repositories
}

group = "tech.jhipster.lite"
version = "0.0.1-SNAPSHOT"

ext {
  // jhipster-needle-gradle-properties
}

dependencies {
  // jhipster-needle-gradle-dependencies
  // jhipster-needle-gradle-test-dependencies
}
