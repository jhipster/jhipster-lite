package tech.jhipster.lite.generator.server.springboot.customjhlite.domain;

import static tech.jhipster.lite.generator.server.springboot.cucumbercommon.domain.CucumbersModules.cucumberModuleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javadependency.*;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency.JavaDependencyOptionalValueBuilder;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class CustomJHLiteModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/custom-jhlite");
  private static final JHipsterSource MAIN_SOURCE = SOURCE.append("main");
  private static final JHipsterSource SLUG_SOURCE = MAIN_SOURCE.append("shared").append("slug");
  private static final JHipsterSource NPM_SOURCE = MAIN_SOURCE.append("shared").append("npm");
  private static final JHipsterSource TEST_SOURCE = SOURCE.append("test");
  private static final JHipsterSource CUCUMBER_SOURCE = from("server/springboot/cucumber");

  private static final String SRC_MAIN_JAVA = "src/main/java";
  private static final String DOMAIN = "domain";

  private static final PropertyKey SERVER_PORT_KEY = propertyKey("server.port");
  private static final PropertyKey JACKSON_INCLUSION_KEY = propertyKey("spring.jackson.default-property-inclusion");

  private static final PropertyKey HIDDEN_SLUGS_PROPERTY_KEY = propertyKey("jhlite-hidden-resources.slugs");
  private static final PropertyKey BEAN_DEFINITION_OVERRIDING_PROPERTY_KEY = propertyKey("spring.main.allow-bean-definition-overriding");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    String baseName = properties.projectBaseName().capitalized();
    JHipsterDestination slugDestination = toSrcMainJava().append(packagePath).append("shared").append("slug");
    JHipsterDestination npmDestination = toSrcMainJava().append(packagePath).append("shared").append("npm");
    JHipsterDestination cucumberDestination = toSrcTestJava().append(packagePath).append("cucumber");

    //@formatter:off
    return cucumberModuleBuilder(properties)
      .context()
        .put("baseName", properties.projectBaseName().capitalized())
        .put("baseNameUpperCased", properties.projectBaseName().upperCased())
        .put("baseNameKebabCased", properties.projectBaseName().kebabCase())
        .and()
      .documentation(documentationTitle("Module creation"), SOURCE.template("module-creation.md"))
      .documentation(documentationTitle("Cucumber"), CUCUMBER_SOURCE.template("cucumber.md"))
      .javaDependencies()
        .addDependency(jhipsterLiteDependency())
        .addDependency(jhipsterLiteTestDependency())
      .and()
      .mandatoryReplacements()
        .in(mainClassFile(properties))
          .add(text("@SpringBootApplication"), springBootApplicationWithJHLite(properties))
          .add(lineBeforeText("import org.springframework.boot.SpringApplication;"), "import tech.jhipster.lite.JHLiteApp;")
        .and()
      .and()
      .springMainProperties()
        .set(SERVER_PORT_KEY, propertyValue(properties.serverPort().get()))
        .set(JACKSON_INCLUSION_KEY, propertyValue("non_null"))
        .comment(HIDDEN_SLUGS_PROPERTY_KEY, comment("Disable the modules and its dependencies by slugs"))
        .set(HIDDEN_SLUGS_PROPERTY_KEY, propertyValue("custom-jhlite"))
        .and()
      .springTestProperties()
        .set(SERVER_PORT_KEY, propertyValue(0))
        .set(BEAN_DEFINITION_OVERRIDING_PROPERTY_KEY, propertyValue(true))
        .and()
      .files()
        .add(SLUG_SOURCE.template("package-info.java"), slugDestination.append("package-info.java"))
        .add(SLUG_SOURCE.append(DOMAIN).template("FeatureSlug.java"), slugDestination.append(DOMAIN).append(baseName + "FeatureSlug.java"))
        .add(SLUG_SOURCE.append(DOMAIN).template("ModuleSlug.java"), slugDestination.append(DOMAIN).append(baseName + "ModuleSlug.java"))
        .add(NPM_SOURCE.template("package-info.java"), npmDestination.append("package-info.java"))
        .add(NPM_SOURCE.append(DOMAIN).template("NpmVersionSource.java"), npmDestination.append(DOMAIN).append(baseName + "NpmVersionSource.java"))
        .add(NPM_SOURCE.append("infrastructure").append("secondary").template("FileSystemNpmVersionReader.java"), npmDestination.append("infrastructure").append("secondary").append(baseName + "FileSystemNpmVersionReader.java"))
        .add(SOURCE.file("package.json"), toSrcMainResources().append("generator").append(properties.projectBaseName().kebabCase() + "-dependencies").append("package.json"))
        .batch(TEST_SOURCE, cucumberDestination)
          .addTemplate("CucumberTest.java")
          .addTemplate("CucumberConfiguration.java")
        .and()
        .add(CUCUMBER_SOURCE.append("rest").template("CucumberRestTemplate.java"), cucumberDestination.append("rest").append("CucumberRestTemplate.java"))
        .add(CUCUMBER_SOURCE.file("gitkeep"), to("src/test/features/.gitkeep"))
        .batch(SOURCE.append("tests-ci"),to("tests-ci"))
          .addExecutableTemplate("generate.sh")
          .addTemplate("modulePayload.json")
          .addExecutableTemplate("start.sh")
          .addExecutable("stop.sh")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  private JavaDependency jhipsterLiteDependency() {
    return jhLiteDependencyBuilder().build();
  }

  private JavaDependency jhipsterLiteTestDependency() {
    return jhLiteDependencyBuilder().classifier("tests").scope(JavaDependencyScope.TEST).type(JavaDependencyType.TEST_JAR).build();
  }

  private JavaDependencyOptionalValueBuilder jhLiteDependencyBuilder() {
    return javaDependency().groupId("tech.jhipster.lite").artifactId("jhlite").versionSlug("jhlite");
  }

  private String springBootApplicationWithJHLite(JHipsterModuleProperties properties) {
    return "@SpringBootApplication(scanBasePackageClasses = { JHLiteApp.class, " + mainClassName(properties) + ".class })";
  }

  private JHipsterProjectFilePath mainClassFile(JHipsterModuleProperties properties) {
    return path(SRC_MAIN_JAVA).append(properties.packagePath()).append(mainClassName(properties) + ".java");
  }

  private String mainClassName(JHipsterModuleProperties properties) {
    return properties.projectBaseName().capitalized() + "App";
  }
}
