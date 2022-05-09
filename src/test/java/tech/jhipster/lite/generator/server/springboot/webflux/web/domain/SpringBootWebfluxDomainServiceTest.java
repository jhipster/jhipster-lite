package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootWebfluxDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @InjectMocks
  @Spy
  SpringBootWebfluxDomainService springBootWebfluxDomainService;

  @Test
  void shouldInit() {
    Map<String, Object> config = new HashMap<>(Map.of("packageName", "beer"));
    Project project = Project.builder().folder("/folder").config(config).build();

    when(buildToolService.getVersion(project, "problem-spring")).thenReturn(Optional.of("0.0.0"));

    springBootWebfluxDomainService.init(project);

    verify(springBootWebfluxDomainService).addSpringBootWebflux(project);
    verify(springBootWebfluxDomainService).addExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootWebflux() {
    Project project = Project.builder().folder("/folder").config(Map.of("serverPort", 8081)).build();

    springBootWebfluxDomainService.addSpringBootWebflux(project);

    ArgumentCaptor<Dependency> dependencyArgCaptor = ArgumentCaptor.forClass(Dependency.class);
    verify(buildToolService, times(2)).addDependency(eq(project), dependencyArgCaptor.capture());
    assertThat(dependencyArgCaptor.getAllValues())
      .extracting(Dependency::getGroupId, Dependency::getArtifactId, Dependency::getScope)
      .contains(tuple("org.springframework.boot", "spring-boot-starter-webflux", Optional.empty()), Index.atIndex(0))
      .contains(tuple("io.projectreactor", "reactor-test", Optional.of("test")), Index.atIndex(1));

    verify(springBootCommonService).addPropertiesComment(project, "Spring Boot Webflux");
    verify(springBootCommonService).addProperties(project, "server.port", 8081);
    verify(springBootCommonService).addPropertiesTest(project, "server.port", 0);
    verify(springBootCommonService).addPropertiesNewLine(project);
  }

  @Nested
  class AddExceptionHandlerTest {

    @Test
    void shouldAddExceptionHandler() {
      Map<String, Object> config = new HashMap<>(Map.of("packageName", "beer"));
      Project project = Project.builder().folder("/folder").config(config).build();

      when(buildToolService.getVersion(project, "problem-spring")).thenReturn(Optional.of("0.0.0"));

      springBootWebfluxDomainService.addExceptionHandler(project);

      verify(buildToolService, times(2)).addProperty(any(Project.class), anyString(), anyString());
      verify(buildToolService).addProperty(project, "problem-spring.version", "0.0.0");
      verify(buildToolService).addProperty(project, "problem-spring-webflux.version", "\\${problem-spring.version}");

      ArgumentCaptor<Dependency> dependencyArgCaptor = ArgumentCaptor.forClass(Dependency.class);
      verify(buildToolService, times(2)).addDependency(any(Project.class), dependencyArgCaptor.capture());
      List<Dependency> allValues = dependencyArgCaptor.getAllValues();
      assertThat(allValues.get(0))
        .usingRecursiveComparison()
        .isEqualTo(Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-validation").build());
      assertThat(allValues.get(1))
        .usingRecursiveComparison()
        .isEqualTo(
          Dependency
            .builder()
            .groupId("org.zalando")
            .artifactId("problem-spring-webflux")
            .version("\\${problem-spring-webflux.version}")
            .build()
        );

      verify(springBootCommonService, times(2)).addProperties(any(Project.class), anyString(), anyString());
      verify(springBootCommonService).addProperties(project, "application.exception.details", "false");
      verify(springBootCommonService).addProperties(project, "application.exception.package", "org.,java.,net.,javax.,com.,io.,de.,beer");

      verify(springBootCommonService, times(1)).addPropertiesTest(any(Project.class), anyString(), anyString());
      verify(springBootCommonService).addPropertiesTest(project, "application.exception.package", "org.,java.");

      verify(projectRepository, times(12)).template(any(Project.class), anyString(), anyString(), anyString());

      String expectedSourcePath = "server/springboot/webflux/web/src";
      String expectedDestinationSourcePath = "src/main/java/beer/technical/infrastructure/primary/exception";
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("ProblemConfiguration.java"), eq(expectedDestinationSourcePath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("HeaderUtil.java"), eq(expectedDestinationSourcePath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("BadRequestAlertException.java"), eq(expectedDestinationSourcePath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("ErrorConstants.java"), eq(expectedDestinationSourcePath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("ExceptionTranslator.java"), eq(expectedDestinationSourcePath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedSourcePath), eq("FieldErrorDTO.java"), eq(expectedDestinationSourcePath));

      String expectedTestPath = "server/springboot/webflux/web/test";
      String expectedDestinationTestPath = "src/test/java/beer/technical/infrastructure/primary/exception";
      verify(projectRepository)
        .template(any(Project.class), eq(expectedTestPath), eq("HeaderUtilTest.java"), eq(expectedDestinationTestPath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedTestPath), eq("BadRequestAlertExceptionTest.java"), eq(expectedDestinationTestPath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedTestPath), eq("ExceptionTranslatorIT.java"), eq(expectedDestinationTestPath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedTestPath), eq("ExceptionTranslatorTestController.java"), eq(expectedDestinationTestPath));
      verify(projectRepository)
        .template(any(Project.class), eq(expectedTestPath), eq("FieldErrorDTOTest.java"), eq(expectedDestinationTestPath));

      verify(projectRepository).template(any(Project.class), eq(expectedTestPath), eq("TestUtil.java"), eq("src/test/java/beer"));
    }

    @Test
    void shouldNotAddWhenDependencyVersionNotFound() {
      Map<String, Object> config = new HashMap<>(Map.of("packageName", "beer"));
      Project project = Project.builder().folder("/folder").config(config).build();

      when(buildToolService.getVersion(project, "problem-spring")).thenReturn(Optional.empty());

      assertThatThrownBy(() -> springBootWebfluxDomainService.addExceptionHandler(project))
        .isInstanceOf(GeneratorException.class)
        .hasMessageContaining("Problem Spring version not found");
    }
  }
}
