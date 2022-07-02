package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain.SpringdocDomainService.WEBFLUX_DEPENDENCY_ID;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencies;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependencies;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringdocDomainServiceTest {

  @Mock
  private ProjectJavaDependenciesRepository projectJavaDependenciesRepository;

  private SpringdocModuleFactory mockSpringdocModuleFactory;

  private MockedConstruction<SpringdocModuleFactory> moduleFactoryMockedConstruction;

  private SpringdocDomainService springdocDomainService;

  @BeforeEach
  void setUp() {
    moduleFactoryMockedConstruction = Mockito.mockConstruction(SpringdocModuleFactory.class);
    springdocDomainService = new SpringdocDomainService(projectJavaDependenciesRepository);
    mockSpringdocModuleFactory = moduleFactoryMockedConstruction.constructed().get(0);
  }

  @AfterEach
  void tearDown() {
    moduleFactoryMockedConstruction.close();
  }

  private ProjectJavaDependencies buildJavaDependenciesWithWebflux() {
    List<JavaDependency> dependencies = List.of(
      JavaDependency.builder().groupId(WEBFLUX_DEPENDENCY_ID.groupId()).artifactId(WEBFLUX_DEPENDENCY_ID.artifactId()).build()
    );
    return new ProjectJavaDependencies(null, new JavaDependencies(dependencies));
  }

  @Nested
  class BuildSpringdocModuleTest {

    @Test
    void shouldBuildForMvc() {
      // Given
      JHipsterModuleProperties moduleProperties = new JHipsterModuleProperties("/path/project", Map.of());
      when(projectJavaDependenciesRepository.get(any(JHipsterProjectFolder.class))).thenReturn(new ProjectJavaDependencies(null, null));

      // When
      springdocDomainService.buildSpringdocModule(moduleProperties);

      // Then
      verify(mockSpringdocModuleFactory).buildModuleForMvc(moduleProperties);
    }

    @Test
    void shouldBuildForWebflux() {
      // Given
      JHipsterModuleProperties moduleProperties = new JHipsterModuleProperties("/path/project", Map.of());
      when(projectJavaDependenciesRepository.get(any(JHipsterProjectFolder.class))).thenReturn(buildJavaDependenciesWithWebflux());

      // When
      springdocDomainService.buildSpringdocModule(moduleProperties);

      // Then
      verify(mockSpringdocModuleFactory).buildModuleForMvc(moduleProperties);
    }
  }

  @Nested
  class BuildSpringdocModuleWithSecurityJWTTest {

    @Test
    void shouldBuildForMvc() {
      // Given
      JHipsterModuleProperties moduleProperties = new JHipsterModuleProperties("/path/project", Map.of());
      when(projectJavaDependenciesRepository.get(any(JHipsterProjectFolder.class))).thenReturn(new ProjectJavaDependencies(null, null));

      // When
      springdocDomainService.buildSpringdocModuleWithSecurityJWT(moduleProperties);

      // Then
      verify(mockSpringdocModuleFactory).buildModuleWithSecurityJwtForMvc(moduleProperties);
    }

    @Test
    void shouldBuildForWebflux() {
      // Given
      JHipsterModuleProperties moduleProperties = new JHipsterModuleProperties("/path/project", Map.of());
      when(projectJavaDependenciesRepository.get(any(JHipsterProjectFolder.class))).thenReturn(buildJavaDependenciesWithWebflux());

      // When
      springdocDomainService.buildSpringdocModuleWithSecurityJWT(moduleProperties);

      // Then
      verify(mockSpringdocModuleFactory).buildModuleWithSecurityJwtForWebflux(moduleProperties);
    }
  }
}
