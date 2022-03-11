package tech.jhipster.lite.generator.docker.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.docker.domain.DockerDomainService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class DockerDomainServiceTest {

  @InjectMocks
  private DockerDomainService dockerDomainService;

  @Test
  void shouldReturnVersion() {
    try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
      String dockerfilePath = "/path/of/dockerfile";
      fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
      fileUtilsMock
        .when(() -> FileUtils.read(dockerfilePath))
        .thenReturn(
          """
            FROM jboss/keycloak:16.1.0
            FROM ubuntu/postgres:12-20.04_beta
            FROM postgres-old:1.0.0
            from Postgres:14.1
          """
        );

      assertThat(dockerDomainService.getVersion("postgres")).contains("14.1");
    }
  }

  @Test
  void shouldNotReturnVersionWhenNotFound() {
    try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
      String dockerfilePath = "/path/of/dockerfile";
      fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
      fileUtilsMock.when(() -> FileUtils.read(dockerfilePath)).thenReturn("""
            FROM postgres:14.1
          """);

      assertThat(dockerDomainService.getVersion("mysql")).isEmpty();
    }
  }

  @Test
  void shouldNotReturnVersionWhenDockerfileIsInvalid() {
    try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
      String dockerfilePath = "/path/of/dockerfile";
      fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
      fileUtilsMock.when(() -> FileUtils.read(dockerfilePath)).thenReturn("""
            FROM postgres:14.1:6
          """);

      assertThat(dockerDomainService.getVersion("postgres")).isEmpty();
    }
  }

  @Test
  void shouldThrowExceptionWhenFilenameIsNull() {
    assertThatThrownBy(() -> dockerDomainService.getVersion(null)).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldThrowExceptionWhenFilenameIsBlank() {
    assertThatThrownBy(() -> dockerDomainService.getVersion("   ")).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldThrowExceptionWhenDockerfileNotFound() {
    try (MockedStatic<FileUtils> fileUtilsMock = Mockito.mockStatic(FileUtils.class)) {
      String dockerfilePath = "/path/of/dockerfile";
      fileUtilsMock.when(() -> FileUtils.getPath("generator", "dependencies", "Dockerfile")).thenReturn(dockerfilePath);
      fileUtilsMock.when(() -> FileUtils.read(dockerfilePath)).thenThrow(IOException.class);

      assertThatThrownBy(() -> dockerDomainService.getVersion("postgres")).isInstanceOf(GeneratorException.class);
    }
  }
}
