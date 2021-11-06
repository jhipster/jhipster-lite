package tech.jhipster.forge.generator.project.infrastructure.secondary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.common.domain.FileUtils;
import tech.jhipster.forge.generator.project.domain.*;
import tech.jhipster.forge.generator.project.domain.added.*;

@UnitTest
@ExtendWith(SpringExtension.class)
class BuildToolPublisherTest {

  @Mock
  ApplicationEventPublisher publisher;

  @InjectMocks
  BuildToolPublisher buildToolPublisher;

  @Test
  void shouldAddParent() {
    buildToolPublisher.addParent(tmpProject(), getParent());

    verify(publisher).publishEvent(any(ParentAdded.class));
  }

  @Test
  void shouldAddDependency() {
    buildToolPublisher.addDependency(tmpProject(), getDependency());

    verify(publisher).publishEvent(any(DependencyAdded.class));
  }

  @Test
  void shouldAddDependencyWithExclusions() {
    buildToolPublisher.addDependency(tmpProject(), getDependency(), getExclusions());

    verify(publisher).publishEvent(any(DependencyAdded.class));
  }

  @Test
  void shouldAddPlugin() {
    buildToolPublisher.addPlugin(tmpProject(), getPlugin());

    verify(publisher).publishEvent(any(PluginAdded.class));
  }

  @Test
  void shouldAddProperty() {
    buildToolPublisher.addProperty(tmpProject(), "testcontainers", "1.16.0");

    verify(publisher).publishEvent(any(PropertyAdded.class));
  }

  @Test
  void shouldInit() {
    Project project = Project.builder().folder(FileUtils.tmpDirForTest()).buildTool(BuildToolType.MAVEN).build();
    buildToolPublisher.init(project);

    verify(publisher).publishEvent(any(BuildToolAdded.class));
  }

  private Parent getParent() {
    return Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
  }

  private Dependency getDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
  }

  private List<Dependency> getExclusions() {
    return List.of(Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-tomcat").build());
  }

  private Plugin getPlugin() {
    return Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
  }
}
