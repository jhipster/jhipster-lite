# JHipster Light ⚡

[![Build Status][github-actions-jhlight-image]][github-actions-url]
[![Coverage Status][codecov-image]][codecov-url]

## Description

JHipster Light will help you to start your project:

- init the project
- add Maven Java as Build Tool
- add Java Base classes and Domain Error
- add Spring Boot as Server Framework
  - add Spring Boot MVC
    - add Tomcat or Undertow
    - add Spring Security with JWT
  - add Database
    - add PostgreSQL
    - add Liquibase as Database Migration Tool

## Prerequisites

### Java

You need to have Java 17 :

- [JDK 17](https://openjdk.java.net/projects/jdk/17/)

### Node.js and NPM

This part is needed if you want to contribute to the project.

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

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

Then navigate to http://localhost:7471/swagger-ui.html in your browser.

## Generate your project

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

### Init your project

Use the `Init` API.

It will generate the default files, needed by all projects:

- `.editorconfig`
- `.gitattributes`
- `.gitignore`
- `package.json` with prettier configuration
- etc

### Maven

Then, you can use `Maven` API to add Maven with Java:

- default `pom.xml` with Java
- Maven Wrapper

### Java Base

This API is needed as it will contain all classes used by other following options. It will create `error/domain` package.

### Spring Boot

This API will add Spring Boot to your project:

- dependencies
- main class
- properties

### Spring Boot MVC

To be completed

### Database

To be completed

[github-actions-jhlight-image]: https://github.com/pascalgrimaud/jhipster-light/workflows/build/badge.svg
[github-actions-url]: https://github.com/pascalgrimaud/jhipster-light/actions
[codecov-image]: https://codecov.io/gh/pascalgrimaud/jhipster-light/branch/main/graph/badge.svg?token=TGYTFIF15C
[codecov-url]: https://codecov.io/gh/pascalgrimaud/jhipster-light

<!-- jhipster-needle-readme -->
