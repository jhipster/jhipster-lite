package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.consumer;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.project.domain.Dependency;
import tech.jhipster.forge.generator.project.domain.Project;
import tech.jhipster.forge.generator.project.domain.added.DependencyAdded;

@IntegrationTest
class DependencyAddedConsumerIT {

  @Autowired
  ApplicationEventPublisher publisher;

  @Autowired
  DependencyAddedConsumer consumer;

  @Test
  void shouldAddDependency() throws Exception {
    // Given
    Project project = tmpProjectWithPomXml();
    Dependency dependency = Dependency
      .builder()
      .groupId("org.keycloak")
      .artifactId("keycloak-admin-client")
      .version("\\${keycloak.version}")
      .build();
    DependencyAdded dependencyAdded = DependencyAdded.of(project, dependency);

    // When
    publisher.publishEvent(dependencyAdded);

    // Then
    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.keycloak</groupId>",
        "<artifactId>keycloak-admin-client</artifactId>",
        "<version>${keycloak.version}</version>",
        "</dependency>"
      )
    );
  }
}
