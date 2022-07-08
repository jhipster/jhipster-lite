# Guides

You can follow this guide to generate your new project with JHipster Lite.

First, you need to start JHipster Lite. In the `jhipster-lite` folder, just launch: `./mvnw`

Check if you can access to [http://localhost:7471](http://localhost:7471)

Then, you need to fill the different fields, before generating something:

![fill_information](01-fill_information.png)

You can init your project, adding git configuration, editor config and a simple `package.json` file:

![init](02-init.png)

Let's try to generate a Maven project, by adding a Maven wrapper and a default `pom.xml`:

![maven](03-maven.png)

Let's add the Java base and all asserts classes:

![java_base](04-java_base.png)

We need to select the Spring Boot section:

![spring_boot_section](05-spring_boot_section.png)

Now, maybe we want to add Spring Boot:

![spring_boot](06-spring_boot.png)

Let's add an application server, using Tomcat:

![spring_boot_mvc](07-spring_boot_mvc.png)

We should add a simple front, using Vue 3:

![vue](08-vue.png)

To make the frontend working ith the backend, we need to add Frontend Maven Plugin:

![frontend-maven-plugin](09-frontend_maven_plugin.png)

Finally, we can download the project:

![download](10-download.png)
