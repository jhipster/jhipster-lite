package tech.jhipster.forge.generator.project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.forge.generator.project.domain.BuildToolType.MAVEN;
import static tech.jhipster.forge.generator.project.domain.Language.JAVA;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.refacto.domain.core.FileUtils;

@UnitTest
class ProjectTest {

  @Test
  void shouldBuildMinimalProject() {
    String folder = FileUtils.tmpDir();

    Project project = Project.builder().folder(folder).build();

    assertThat(project.getFolder()).isEqualTo(folder);

    assertThat(project.getLanguage()).isEmpty();
    assertThat(project.getBuildTool()).isEmpty();
    assertThat(project.getServer()).isEmpty();
    assertThat(project.getClient()).isEmpty();
  }

  @Test
  void shouldBuildFullProject() {
    String folder = FileUtils.tmpDir();

    Project project = Project
      .builder()
      .folder(folder)
      .language(JAVA)
      .buildTool(MAVEN)
      .server(new Server(ServerFramework.SPRING))
      .client(new Client(ClientFramework.ANGULAR))
      .build();

    assertThat(project.getFolder()).isEqualTo(folder);
    assertThat(project.getLanguage()).contains(JAVA);
    assertThat(project.getBuildTool()).contains(MAVEN);
    assertThat(project.getServer()).contains(new Server(ServerFramework.SPRING));
    assertThat(project.getClient()).contains(new Client(ClientFramework.ANGULAR));
  }
}
