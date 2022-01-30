package tech.jhipster.lite.generator.history.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProject;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GeneratorHistoryDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  GeneratorHistoryDomainService historyDomainService;

  @Test
  void shouldCreateHistoryFile() {
    // Given
    Project project = tmpProject();

    // When
    historyDomainService.createHistoryFile(project);

    // Then
    verify(projectRepository).add(project, "history", "history.json", ".jhipster", "history.json");
  }

  @Test
  void shouldGetHistoryData() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      String historyFilePath = "/path/history.json";
      fileUtils.when(() -> FileUtils.getPath(project.getFolder(), ".jhipster", "history.json")).thenReturn(historyFilePath);
      fileUtils.when(() -> FileUtils.read(historyFilePath)).thenReturn(getFileContent());

      // When
      GeneratorHistoryData generatorHistoryData = historyDomainService.getHistoryData(project);

      // Then
      GeneratorHistoryData expectedGeneratorHistoryData = new GeneratorHistoryData();
      expectedGeneratorHistoryData.getValues().add(new GeneratorHistoryValue().setServiceId("springboot-init"));
      assertThat(generatorHistoryData).usingRecursiveComparison().isEqualTo(expectedGeneratorHistoryData);
    }
  }

  @Test
  void shouldNotGetHistoryDataWhenFileDoesNotExist() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      fileUtils.when(() -> FileUtils.getPath(any())).thenReturn("/path/history.json");
      fileUtils.when(() -> FileUtils.read(anyString())).thenThrow(new IOException());

      // When + Then
      assertThatThrownBy(() -> historyDomainService.getHistoryData(project)).isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Test
  void shouldNotGetHistoryDataWhenJsonIsNotDeserializable() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      fileUtils.when(() -> FileUtils.getPath(any())).thenReturn("/path/history.json");
      fileUtils.when(() -> FileUtils.read(anyString())).thenReturn("invalidJsonContent");

      // When + Then
      assertThatThrownBy(() -> historyDomainService.getHistoryData(project)).isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Test
  void shouldAddHistoryValueInExistingFile() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      String historyFilePath = "/path/history.json";
      fileUtils.when(() -> FileUtils.getPath(project.getFolder(), ".jhipster", "history.json")).thenReturn(historyFilePath);
      fileUtils.when(() -> FileUtils.read(historyFilePath)).thenReturn(getFileContent());

      // When
      GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue().setServiceId("tomcat");
      historyDomainService.addHistoryValue(project, generatorHistoryValue);

      // Then
      fileUtils.verify(() -> FileUtils.read(anyString()), times(1));
      fileUtils.verify(() -> {
        try {
          FileUtils.write(historyFilePath, getExpectedFileContent(), WordUtils.CRLF);
        } catch (IOException e) {
          fail("Unexpected IOException");
        }
      });

      verifyNoInteractions(projectRepository);
    }
  }

  @Test
  void shouldAddHistoryValueInNewFile() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      String historyFilePath = "/path/history.json";
      fileUtils.when(() -> FileUtils.getPath(project.getFolder(), ".jhipster", "history.json")).thenReturn(historyFilePath);
      fileUtils.when(() -> FileUtils.read(historyFilePath)).thenThrow(new IOException()).thenReturn(getFileContent());

      // When
      GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue().setServiceId("tomcat");
      historyDomainService.addHistoryValue(project, generatorHistoryValue);

      // Then
      verify(projectRepository).add(project, "history", "history.json", ".jhipster", "history.json");

      fileUtils.verify(() -> FileUtils.read(anyString()), times(2));
      fileUtils.verify(() -> {
        try {
          FileUtils.write(historyFilePath, getExpectedFileContent(), WordUtils.CRLF);
        } catch (IOException e) {
          fail("Unexpected IOException");
        }
      });
    }
  }

  @Test
  void shouldNotAddHistoryValueWhenJsonSerializationError() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      String historyFilePath = "/path/history.json";
      fileUtils.when(() -> FileUtils.getPath(project.getFolder(), ".jhipster", "history.json")).thenReturn(historyFilePath);
      fileUtils.when(() -> FileUtils.read(historyFilePath)).thenReturn(getFileContent());

      GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue().setServiceId("tomcat");

      // When + Then
      assertThatThrownBy(() -> historyDomainService.addHistoryValue(project, generatorHistoryValue))
        .isExactlyInstanceOf(GeneratorException.class);

      fileUtils.verify(
        () -> {
          try {
            FileUtils.write(anyString(), anyString(), anyString());
          } catch (IOException e) {
            fail("Unexpected IOException");
          }
        },
        never()
      );

      verifyNoInteractions(projectRepository);
    }
  }

  @Test
  void shouldNotAddHistoryValueWheIoExceptionDuringWriting() {
    // Given
    Project project = tmpProject();

    try (MockedStatic<FileUtils> fileUtils = Mockito.mockStatic(FileUtils.class)) {
      String historyFilePath = "/path/history.json";
      fileUtils.when(() -> FileUtils.getPath(project.getFolder(), ".jhipster", "history.json")).thenReturn(historyFilePath);
      fileUtils.when(() -> FileUtils.read(historyFilePath)).thenReturn(getFileContent());
      fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(new IOException());

      // When
      GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue().setServiceId("tomcat");
      assertThatThrownBy(() -> historyDomainService.addHistoryValue(project, generatorHistoryValue)).isInstanceOf(GeneratorException.class);
    }
  }

  private String getFileContent() {
    return """
        {
          "values": [
            { "serviceId": "springboot-init"}
          ]
        }
      """;
  }

  private String getExpectedFileContent() {
    return """
      {
        "values" : [ {
          "serviceId" : "springboot-init"
        }, {
          "serviceId" : "tomcat"
        } ]
      }""";
  }
}
