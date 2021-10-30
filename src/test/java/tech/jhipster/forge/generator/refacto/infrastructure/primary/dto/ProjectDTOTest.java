package tech.jhipster.forge.generator.refacto.infrastructure.primary.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.forge.generator.refacto.domain.core.DefaultConfig.BASE_NAME;

import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;
import tech.jhipster.forge.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.forge.generator.refacto.domain.core.Project;

@UnitTest
class ProjectDTOTest {

  @Test
  void shouldBuild() {
    ProjectDTO projectDTO = buildProjectDTO();

    assertThat(projectDTO.getPath()).isEqualTo("/tmp/chips");
    assertThat(projectDTO.getGeneratorJhipster()).containsEntry(BASE_NAME, "chips");
  }

  @Test
  void shouldToProject() {
    ProjectDTO projectDTO = buildProjectDTO();

    Project project = ProjectDTO.toProject(projectDTO);

    assertThat(project).isNotNull();
    assertThat(project.getPath()).isEqualTo("/tmp/chips");
    assertThat(project.getConfig()).containsEntry(BASE_NAME, "chips");
  }

  @Test
  void shouldNotToProjectWithNull() {
    assertThatThrownBy(() -> ProjectDTO.toProject(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("projectDTO");
  }

  @Test
  void shouldToProjectWithNullPath() {
    ProjectDTO projectDTO = buildProjectDTO().path(null);

    assertThatThrownBy(() -> ProjectDTO.toProject(projectDTO))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("path");
  }

  private ProjectDTO buildProjectDTO() {
    return new ProjectDTO().path("/tmp/chips").generatorJhipster(Map.of(BASE_NAME, "chips"));
  }
}
