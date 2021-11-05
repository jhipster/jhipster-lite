package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.consumer;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.project.domain.Project;
import tech.jhipster.forge.generator.project.domain.added.PropertyAdded;

@IntegrationTest
class PropertyAddedConsumerIT {

  @Autowired
  ApplicationEventPublisher publisher;

  @Test
  void shouldAddProperty() throws Exception {
    Project project = tmpProjectWithPomXml();
    PropertyAdded propertyAdded = PropertyAdded.of(project, "testcontainers", "1.16.0");

    publisher.publishEvent(propertyAdded);

    assertFileContent(project, "pom.xml", "<testcontainers.version>1.16.0</testcontainers.version>");
  }
}
