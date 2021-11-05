package tech.jhipster.forge.generator.buildtool.generic.application;

import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProjectBuilder;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.project.domain.*;

@UnitTest
@ExtendWith(SpringExtension.class)
class BuildToolApplicationServiceTest {

  @Mock
  BuildToolService buildToolService;

  @InjectMocks
  BuildToolApplicationService buildToolApplicationService;

  @Test
  void shouldAddParent() throws Exception {
    Project project = getMavenProject();
    Parent parent = getParent();

    buildToolApplicationService.addParent(project, parent);

    verify(buildToolService).addParent(project, parent);
  }

  @Test
  void shouldAddDependency() throws Exception {
    Project project = getMavenProject();
    Dependency dependency = getDependency();

    buildToolApplicationService.addDependency(project, dependency);

    verify(buildToolService).addDependency(project, dependency, List.of());
  }

  @Test
  void shouldAddDependencyWithExclusions() throws Exception {
    Project project = getMavenProject();
    Dependency dependency = getDependency();
    Dependency dependencyToExclude = Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-starter-tomcat")
      .build();

    buildToolApplicationService.addDependency(project, dependency, List.of(dependencyToExclude));

    verify(buildToolService).addDependency(project, dependency, List.of(dependencyToExclude));
  }

  @Test
  void shouldAddPlugin() throws Exception {
    Project project = getMavenProject();
    Plugin plugin = getPlugin();

    buildToolApplicationService.addPlugin(project, plugin);

    verify(buildToolService).addPlugin(project, plugin);
  }

  @Test
  void shouldAddProperty() throws Exception {
    Project project = getMavenProject();

    buildToolApplicationService.addProperty(project, "testcontainers", "1.16.0");

    verify(buildToolService).addProperty(project, "testcontainers", "1.16.0");
  }

  private Project getMavenProject() throws IOException {
    Project project = tmpProjectBuilder().buildTool(BuildToolType.MAVEN).build();
    TestUtils.copyPomXml(project);
    return project;
  }

  private Dependency getDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
  }

  private Parent getParent() {
    return Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
  }

  private Plugin getPlugin() {
    return Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
  }
}
