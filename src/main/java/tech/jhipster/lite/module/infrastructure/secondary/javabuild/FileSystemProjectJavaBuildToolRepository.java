package tech.jhipster.lite.module.infrastructure.secondary.javabuild;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Component;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterFileToMove;
import tech.jhipster.lite.module.domain.file.JHipsterFilesToMove;
import tech.jhipster.lite.module.domain.file.JHipsterModuleFile;
import tech.jhipster.lite.module.domain.file.JHipsterModuleFiles;
import tech.jhipster.lite.module.domain.javabuild.JavaBuildTool;
import tech.jhipster.lite.module.domain.javabuild.ProjectJavaBuildToolRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@Component
public class FileSystemProjectJavaBuildToolRepository implements ProjectJavaBuildToolRepository {

  private static final String POM_XML = "pom.xml";
  private static final String BUILD_GRADLE_KTS = "build.gradle.kts";

  @Override
  public Optional<JavaBuildTool> detect(JHipsterProjectFolder projectFolder) {
    if (projectFolder.fileExists(POM_XML)) {
      return Optional.of(JavaBuildTool.MAVEN);
    } else if (projectFolder.fileExists(BUILD_GRADLE_KTS)) {
      return Optional.of(JavaBuildTool.GRADLE);
    }
    return Optional.empty();
  }

  @Override
  public Optional<JavaBuildTool> detect(JHipsterModuleFiles moduleFiles) {
    if (hasDestinationFile(moduleFiles.filesToAdd(), POM_XML) || hasDestinationFile(moduleFiles.filesToMove(), POM_XML)) {
      return Optional.of(JavaBuildTool.MAVEN);
    } else if (
      hasDestinationFile(moduleFiles.filesToAdd(), BUILD_GRADLE_KTS) || hasDestinationFile(moduleFiles.filesToMove(), BUILD_GRADLE_KTS)
    ) {
      return Optional.of(JavaBuildTool.GRADLE);
    }
    return Optional.empty();
  }

  private static boolean hasDestinationFile(JHipsterFilesToMove filesToMove, String fileName) {
    return filesToMove.stream().map(JHipsterFileToMove::destination).map(JHipsterDestination::get).anyMatch(fileName::equals);
  }

  private static boolean hasDestinationFile(Collection<JHipsterModuleFile> filesToAdd, String fileName) {
    return filesToAdd.stream().map(JHipsterModuleFile::destination).map(JHipsterDestination::get).anyMatch(fileName::equals);
  }
}
