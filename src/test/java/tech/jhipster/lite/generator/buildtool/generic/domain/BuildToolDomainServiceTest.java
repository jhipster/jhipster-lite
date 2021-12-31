package tech.jhipster.lite.generator.buildtool.generic.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.*;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.lite.generator.project.domain.BuildToolType;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class BuildToolDomainServiceTest {

  @Mock
  MavenService mavenService;

  @InjectMocks
  BuildToolDomainService buildToolDomainService;

  @Nested
  class MavenTest {

    @Test
    void shouldAddParent() {
      Project project = tmpProjectWithPomXml();
      Parent parent = getParent();

      buildToolDomainService.addParent(project, parent);

      verify(mavenService).addParent(project, parent);
    }

    @Test
    void shouldAddDependency() {
      Project project = tmpProjectWithPomXml();
      Dependency dependency = getDependency();

      buildToolDomainService.addDependency(project, dependency);

      verify(mavenService).addDependency(project, dependency);
    }

    @Test
    void shouldAddDependencyWithExclusion() {
      Project project = tmpProjectWithPomXml();
      Dependency dependency = getDependency();
      List<Dependency> exclusions = getExclusions();

      buildToolDomainService.addDependency(project, dependency, exclusions);

      verify(mavenService).addDependency(project, dependency, exclusions);
    }

    @Test
    void shouldAddDependencyManagement() {
      Project project = tmpProjectWithPomXml();
      Dependency dependency = getDependency();

      buildToolDomainService.addDependencyManagement(project, dependency);

      verify(mavenService).addDependencyManagement(project, dependency);
    }

    @Test
    void shouldAddPlugin() {
      Project project = tmpProjectWithPomXml();
      Plugin plugin = getPlugin();

      buildToolDomainService.addPlugin(project, plugin);

      verify(mavenService).addPlugin(project, plugin);
    }

    @Test
    void shouldAddPluginManagement() {
      Project project = tmpProjectWithPomXml();
      Plugin plugin = getPlugin();

      buildToolDomainService.addPluginManagement(project, plugin);

      verify(mavenService).addPluginManagement(project, plugin);
    }

    @Test
    void shouldAddProperty() {
      Project project = tmpProjectWithPomXml();

      buildToolDomainService.addProperty(project, "testcontainers", "1.16.0");

      verify(mavenService).addProperty(project, "testcontainers", "1.16.0");
    }

    @Test
    void shouldInit() {
      Project project = tmpProjectWithPomXml();

      buildToolDomainService.init(project, BuildToolType.MAVEN);

      verify(mavenService).init(project);
    }

    @Test
    void shouldInitWihoutExistingPomXml() {
      Project project = tmpProject();

      buildToolDomainService.init(project, BuildToolType.MAVEN);

      verify(mavenService).init(project);
    }
  }

  @Nested
  class GradleTest {

    @Test
    void shouldNotInit() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.init(project, BuildToolType.GRADLE)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotInitWithExistingGradle() {
      Project project = tmpProjectWithBuildGradle();

      assertThatThrownBy(() -> buildToolDomainService.init(project, BuildToolType.GRADLE)).isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class NoBuildToolTest {

    @Test
    void shouldNotAddParent() {
      Project project = tmpProject();
      Parent parent = Parent
        .builder()
        .groupId("org.springframework.boot")
        .artifactId("spring-boot-starter-parent")
        .version("2.5.3")
        .build();

      assertThatThrownBy(() -> buildToolDomainService.addParent(project, parent)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddDependency() {
      Project project = tmpProject();
      Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();

      assertThatThrownBy(() -> buildToolDomainService.addDependency(project, dependency)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddDependencyWithExclusion() {
      Project project = tmpProject();
      Dependency dependency = getDependency();
      List<Dependency> exclusions = getExclusions();

      assertThatThrownBy(() -> buildToolDomainService.addDependency(project, dependency, exclusions))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddPlugin() {
      Project project = tmpProject();
      Plugin plugin = getPlugin();

      assertThatThrownBy(() -> buildToolDomainService.addPlugin(project, plugin)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddPluginManagement() {
      Project project = tmpProject();
      Plugin plugin = getPlugin();

      assertThatThrownBy(() -> buildToolDomainService.addPluginManagement(project, plugin)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddProperty() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.addProperty(project, "testcontainers", "1.16.0"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
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
