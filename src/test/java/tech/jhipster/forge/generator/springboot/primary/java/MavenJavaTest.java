package tech.jhipster.forge.generator.springboot.primary.java;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.MavenApplicationService;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;

@UnitTest
@ExtendWith(SpringExtension.class)
class MavenJavaTest {

  @Mock
  MavenApplicationService mavenApplicationService;

  @InjectMocks
  MavenJava mavenJava;

  @Test
  void shouldInitPomXml() {
    Project project = tmpProject();

    mavenJava.initPomXml(project);

    verify(mavenApplicationService).initPomXml(any(Project.class));
  }

  @Test
  void shouldAddMavenWrapper() {
    Project project = tmpProject();

    mavenJava.addMavenWrapper(project);

    verify(mavenApplicationService).addMavenWrapper(any(Project.class));
  }

  @Test
  void shouldAddParent() throws Exception {
    Project project = tmpProjectWithPomXml();
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();

    mavenJava.addParent(project, parent);

    verify(mavenApplicationService).addParent(any(Project.class), any(Parent.class));
  }

  @Test
  void shouldAddDependency() throws Exception {
    Project project = tmpProjectWithPomXml();
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();

    mavenJava.addDependency(project, dependency);

    verify(mavenApplicationService).addDependency(any(Project.class), any(Dependency.class));
  }

  @Test
  void shouldAddPlugin() throws Exception {
    Project project = tmpProjectWithPomXml();
    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();

    mavenJava.addPlugin(project, plugin);

    verify(mavenApplicationService).addPlugin(any(Project.class), any(Plugin.class));
  }
}
