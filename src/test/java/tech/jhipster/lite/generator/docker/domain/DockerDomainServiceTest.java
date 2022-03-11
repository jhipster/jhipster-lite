package tech.jhipster.lite.generator.docker.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;

@UnitTest
@ExtendWith(MockitoExtension.class)
class DockerDomainServiceTest {

  @InjectMocks
  private DockerDomainService dockerDomainService;

  @Nested
  class GetImageVersionTest {

    @Test
    void shouldReturnImageVersion() {
      try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
        String dockerfilePath = "/path/of/dockerfile";
        fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
        fileUtilsMock
          .when(() -> FileUtils.readLinesInClasspath(dockerfilePath))
          .thenReturn(
            """
                FROM jboss/keycloak:16.1.0
                FROM ubuntu/postgres:12-20.04_beta
                FROM postgres-old:1.0.0
                from Postgres:14.1
              """.lines()
              .collect(Collectors.toList())
          );

        assertThat(dockerDomainService.getImageVersion("postgres")).contains("14.1");
      }
    }

    @Test
    void shouldNotReturnImageVersionWhenNotFound() {
      try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
        String dockerfilePath = "/path/of/dockerfile";
        fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
        fileUtilsMock.when(() -> FileUtils.readLinesInClasspath(dockerfilePath)).thenReturn(List.of("FROM postgres:14.1"));

        assertThat(dockerDomainService.getImageVersion("mysql")).isEmpty();
      }
    }

    @Test
    void shouldNotReturnImageVersionWhenDockerfileIsInvalid() {
      try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
        String dockerfilePath = "/path/of/dockerfile";
        fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
        fileUtilsMock.when(() -> FileUtils.readLinesInClasspath(dockerfilePath)).thenReturn(List.of("FROM postgres:14.1:6"));

        assertThat(dockerDomainService.getImageVersion("postgres")).isEmpty();
      }
    }

    @Test
    void shouldThrowExceptionWhenFilenameIsNull() {
      assertThatThrownBy(() -> dockerDomainService.getImageVersion(null)).isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    void shouldThrowExceptionWhenFilenameIsBlank() {
      assertThatThrownBy(() -> dockerDomainService.getImageVersion("   ")).isInstanceOf(MissingMandatoryValueException.class);
    }
  }
}
