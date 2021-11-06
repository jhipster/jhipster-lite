package tech.jhipster.forge.generator.buildtool.generic.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.TestUtils.tmpProjectBuilder;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.project.domain.*;

@UnitTest
@ExtendWith(MockitoExtension.class)
class BuildToolDomainServiceTest {

  @Mock
  MavenService mavenService;

  @InjectMocks
  BuildToolDomainService buildToolDomainService;

  @Nested
  class NoBuildTool {

    @Test
    void shouldNotAddParent() {
      Project project = tmpProject();
      Parent parent = getParent();

      assertThatThrownBy(() -> buildToolDomainService.addParent(project, parent)).isExactlyInstanceOf(GeneratorException.class);

      verify(mavenService, times(0)).addParent(any(Project.class), any(Parent.class));
    }

    @Test
    void shouldNotAddDependency() {
      Project project = tmpProject();
      Dependency dependency = getDependency();

      assertThatThrownBy(() -> buildToolDomainService.addDependency(project, dependency)).isExactlyInstanceOf(GeneratorException.class);

      verify(mavenService, times(0)).addDependency(any(Project.class), any(Dependency.class));
    }

    @Test
    void shouldNotAddPlugin() {
      Project project = tmpProject();
      Plugin plugin = getPlugin();

      assertThatThrownBy(() -> buildToolDomainService.addPlugin(project, plugin)).isExactlyInstanceOf(GeneratorException.class);

      verify(mavenService, times(0)).addPlugin(any(Project.class), any(Plugin.class));
    }

    @Test
    void shouldNotAddProperty() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.addProperty(project, "testcontainers", "1.16.0"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotInit() {
      assertThatThrownBy(() -> buildToolDomainService.init(tmpProject())).isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class Maven {

    @Test
    void shouldAddParent() throws Exception {
      Project project = getMavenProject();
      Parent parent = getParent();

      buildToolDomainService.addParent(project, parent);

      verify(mavenService).addParent(project, parent);
    }

    @Test
    void shouldAddDependency() throws Exception {
      Project project = getMavenProject();
      Dependency dependency = getDependency();

      buildToolDomainService.addDependency(project, dependency);

      verify(mavenService).addDependency(project, dependency, List.of());
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

      buildToolDomainService.addDependency(project, dependency, List.of(dependencyToExclude));

      verify(mavenService).addDependency(project, dependency, List.of(dependencyToExclude));
    }

    @Test
    void shouldAddPlugin() throws Exception {
      Project project = getMavenProject();
      Plugin plugin = getPlugin();

      buildToolDomainService.addPlugin(project, plugin);

      verify(mavenService).addPlugin(project, plugin);
    }

    @Test
    void shouldAddProperty() throws Exception {
      Project project = getMavenProject();

      buildToolDomainService.addProperty(project, "testcontainers", "1.16.0");

      verify(mavenService).addProperty(project, "testcontainers", "1.16.0");
    }

    @Test
    void shouldInit() {
      Project project = tmpProjectBuilder().buildTool(BuildToolType.MAVEN).build();

      buildToolDomainService.init(project);

      verify(mavenService).init(project);
    }

    private Project getMavenProject() throws IOException {
      Project project = tmpProjectBuilder().buildTool(BuildToolType.MAVEN).build();
      TestUtils.copyPomXml(project);
      return project;
    }
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
