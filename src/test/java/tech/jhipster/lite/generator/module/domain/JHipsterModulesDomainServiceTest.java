package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.generator.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.generator.module.domain.javadependency.GroupId;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencies;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.ProjectJavaDependencies;
import tech.jhipster.lite.generator.module.domain.javadependency.ProjectJavaDependenciesRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class JHipsterModulesDomainServiceTest {

  @Mock
  ProjectJavaDependenciesRepository projectDependencies;

  @InjectMocks
  JHipsterModulesDomainService jHipsterModulesDomainService;

  @Nested
  class GetDependencyTest {

    @Test
    void shouldReturnDependency() {
      // Given
      JavaDependency javaDependency = JavaDependency
        .builder()
        .groupId("org.springframework.boot")
        .artifactId("spring-boot-starter-actuator")
        .build();

      when(projectDependencies.get(any(JHipsterProjectFolder.class)))
        .thenReturn(new ProjectJavaDependencies(JavaDependenciesVersions.EMPTY, new JavaDependencies(List.of(javaDependency))));

      // When
      DependencyId dependencyId = new DependencyId(new GroupId("org.springframework.boot"), new ArtifactId("spring-boot-starter-actuator"));
      Optional<JavaDependency> dependency = jHipsterModulesDomainService.getDependency("/tmp/foo", dependencyId);

      // Then
      assertThat(dependency).contains(javaDependency);

      ArgumentCaptor<JHipsterProjectFolder> jHipsterProjectFolderArgCaptor = ArgumentCaptor.forClass(JHipsterProjectFolder.class);
      verify(projectDependencies).get(jHipsterProjectFolderArgCaptor.capture());
      assertThat(jHipsterProjectFolderArgCaptor.getValue().folder()).isEqualTo("/tmp/foo");
    }

    @Test
    void shouldNotReturnDependencyWhenNotFound() {
      // Given
      when(projectDependencies.get(any(JHipsterProjectFolder.class)))
        .thenReturn(new ProjectJavaDependencies(JavaDependenciesVersions.EMPTY, new JavaDependencies(List.of())));

      // When
      DependencyId dependencyId = new DependencyId(new GroupId("groupId"), new ArtifactId("artifactId"));
      Optional<JavaDependency> dependency = jHipsterModulesDomainService.getDependency("/tmp/foo", dependencyId);

      // Then
      assertThat(dependency).isEmpty();
    }
  }
}
