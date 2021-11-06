package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.listener;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.project.domain.Parent;
import tech.jhipster.forge.generator.project.domain.Project;
import tech.jhipster.forge.generator.project.domain.added.ParentAdded;

@IntegrationTest
class ParentAddedListenerIT {

  @Autowired
  ApplicationEventPublisher publisher;

  @Test
  void shouldAddParent() throws Exception {
    Project project = tmpProjectWithPomXml();
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
    ParentAdded parentAdded = ParentAdded.of(project, parent);

    publisher.publishEvent(parentAdded);

    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<parent>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-parent</artifactId>",
        "<version>2.5.3</version>",
        "<relativePath/>",
        "</parent>"
      )
    );
  }
}
