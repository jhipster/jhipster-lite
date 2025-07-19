package tech.jhipster.lite.generator.typescript.sonar.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.nodejs.JHLiteNodePackagesVersionSource.COMMON;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class SonarTypescriptModuleFactory {

  private static final JHipsterSource SOURCE = from("typescript/sonar");
  private static final JHipsterDestination SONAR_PROPERTIES_DESTINATION = to("sonar-project.properties");
  private static final String SONARQUBE = "sonarqube";

  private final DockerImages dockerImages;

  public SonarTypescriptModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    // @formatter:off
    return moduleBuilder(properties)
      .context()
        .put("sonarqubeDockerImage", dockerImages.get(SONARQUBE).fullName())
        .and()
      .packageJson()
        .addDevDependency(packageName("@sonar/scan"), COMMON)
        .and()
      .documentation(documentationTitle("sonar"), SOURCE.template("sonar.md"))
        .files()
        .add(SOURCE.template("sonar.yml"), toSrcMainDocker().append("sonar.yml"))
        .and()
      .files()
        .add(SOURCE.append("Dockerfile"), toSrcMainDocker().append("sonar/Dockerfile"))
        .and()
      .files()
        .add(SOURCE.append("sonar_generate_token.sh"), toSrcMainDocker().append("sonar/sonar_generate_token.sh"))
        .add(SOURCE.template("sonar-project.properties"), SONAR_PROPERTIES_DESTINATION)
        .and()
      .build();
    // @formatter:on
  }
}
