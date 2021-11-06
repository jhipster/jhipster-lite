package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.listener;

import static tech.jhipster.forge.TestUtils.assertFileExist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.generator.project.domain.BuildToolType;
import tech.jhipster.forge.generator.project.domain.Project;
import tech.jhipster.forge.generator.project.domain.added.BuildToolAdded;

@IntegrationTest
class BuildToolAddedListenerIT {

  @Autowired
  ApplicationEventPublisher publisher;

  @Test
  void shouldInit() {
    Project project = Project.builder().folder(FileUtils.tmpDirForTest()).buildTool(BuildToolType.MAVEN).build();
    BuildToolAdded buildToolAdded = BuildToolAdded.of(project, BuildToolType.MAVEN);

    publisher.publishEvent(buildToolAdded);

    assertFileExist(project, "pom.xml");
    assertFileExist(project, "mvnw");
    assertFileExist(project, "mvnw.cmd");
    assertFileExist(project, ".mvn/wrapper/MavenWrapperDownloader.java");
    assertFileExist(project, ".mvn/wrapper/maven-wrapper.jar");
    assertFileExist(project, ".mvn/wrapper/maven-wrapper.properties");
  }
}
