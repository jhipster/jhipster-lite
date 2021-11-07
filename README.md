# JHipster Forge 🗡🛡

[![Build Status][github-actions-jhforge-image]][github-actions-url]
[![Coverage Status][codecov-image]][codecov-url]

## Description

JHipster Forge will help you to start your project:

- init the project
- add Maven as Build Tool
- add Spring Boot as Server Framework
- add Tomcat or Undertow as Server
- add PostgreSQL as Database
- add Liquibase as Database Migration Tool

## Prerequisites

### Node.js and NPM

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

### Java

You need to have Java 17 :

- [JDK 17](https://openjdk.java.net/projects/jdk/17/)

## Test the project

To launch tests:

```
./mvnw clean test
```

To launch tests and integration tests:

```
./mvnw clean verify
```

## Run the project

You can run the project using Maven:

```
./mvnw spring-boot:run
```

Or, first, you can package as jar:

```
./mvnw package
```

Then, run:

```
java -jar target/*.jar
```

Then navigate to http://localhost:8080/swagger-ui/ in your browser.

You can use this JSON to generate project:

<!-- prettier-ignore-start -->
```yaml
{
  "folder": "/tmp/beer",
  "generator-jhipster": {
    "projectName": "Beer Project",
    "baseName": "beer",
    "prettierDefaultIndent": 2,
    "packageName": "tech.jhipster.beer"
  }
}
```
<!-- prettier-ignore-end -->

[github-actions-jhforge-image]: https://github.com/pascalgrimaud/jhipster-forge/workflows/build/badge.svg
[github-actions-url]: https://github.com/pascalgrimaud/jhipster-forge/actions
[codecov-image]: https://codecov.io/gh/pascalgrimaud/jhipster-forge/branch/main/graph/badge.svg?token=TGYTFIF15C
[codecov-url]: https://codecov.io/gh/pascalgrimaud/jhipster-forge

<!-- jhipster-needle-readme -->
