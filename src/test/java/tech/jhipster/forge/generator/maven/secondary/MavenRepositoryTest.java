package tech.jhipster.forge.generator.maven.secondary;

import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;

@UnitTest
@ExtendWith(SpringExtension.class)
class MavenRepositoryTest {

  @InjectMocks
  MavenRepository mavenRepository;

  @Test
  void shouldInitPomXml() {
    Project project = tmpProject();

    mavenRepository.initPomXml(project);

    assertFileExist(project, "pom.xml");
  }

  @Test
  void shouldAddMavenWrapper() {
    Project project = tmpProject();

    mavenRepository.addMavenWrapper(project);

    assertFileExist(project, "mvnw");
    assertFileExist(project, "mvnw.cmd");
    assertFileExist(project, ".mvn", "wrapper", "MavenWrapperDownloader.java");
    assertFileExist(project, ".mvn", "wrapper", "maven-wrapper.jar");
    assertFileExist(project, ".mvn", "wrapper", "maven-wrapper.properties");
  }
}
