# JHipster Forge

[![Build Status][github-actions-jhforge-image]][github-actions-url]

## Node.js and NPM

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

## Maven

To launch tests:

```
./mvnw clean test
```

To launch tests and integration tests:

```
./mvnw clean verify
```

[github-actions-jhforge-image]: https://github.com/pascalgrimaud/jhipster-forge/workflows/Application%20CI/badge.svg
[github-actions-url]: https://github.com/pascalgrimaud/jhipster-forge/actions

<!-- jhipster-needle-readme -->
