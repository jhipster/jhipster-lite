package tech.jhipster.lite.module.infrastructure.secondary.javabuild;

import java.util.Optional;
import org.springframework.stereotype.Component;
import tech.jhipster.lite.module.domain.javabuild.JavaBuildTool;
import tech.jhipster.lite.module.domain.javabuild.ProjectJavaBuildToolRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@Component
public class FileSystemProjectJavaBuildToolRepository implements ProjectJavaBuildToolRepository {

  @Override
  public Optional<JavaBuildTool> detect(JHipsterProjectFolder projectFolder) {
    if (projectFolder.fileExists("pom.xml")) {
      return Optional.of(JavaBuildTool.MAVEN);
    } else if (projectFolder.fileExists("build.gradle.kts")) {
      return Optional.of(JavaBuildTool.GRADLE);
    }
    return Optional.empty();
  }
}
