package tech.jhipster.lite.project.infrastructure.secondary;

import static org.mockito.Mockito.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import tech.jhipster.lite.module.infrastructure.secondary.FileSystemProjectFiles;

@TestConfiguration
public class FakedFileSystemProjectFilesConfiguration {

  @Bean
  @Primary
  FileSystemProjectFiles fileSystemProjectFiles() {
    FileSystemProjectFiles fileSystemProjectFiles = Mockito.spy(new FileSystemProjectFiles());

    mockPresetJson(fileSystemProjectFiles);

    return fileSystemProjectFiles;
  }

  private static void mockPresetJson(FileSystemProjectFiles fileSystemProjectFiles) {
    String presetJsonContent = """
      {
        "presets": [
          {
            "name": "test preset one",
            "modules": ["test-module-one", "test-module-two"]
          },
          {
            "name": "test preset two",
            "modules": ["test-module-three", "test-module-four"]
          }
        ]
      }
      """;

    lenient().when(fileSystemProjectFiles.findRecursivelyInPath("/presets")).thenReturn(List.of("/presets/preset-maven.json"));
    lenient()
      .when(fileSystemProjectFiles.readBytes("/presets/preset-maven.json"))
      .thenReturn(presetJsonContent.getBytes(StandardCharsets.UTF_8));
  }
}
