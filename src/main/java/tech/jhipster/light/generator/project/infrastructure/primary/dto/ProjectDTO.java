package tech.jhipster.light.generator.project.infrastructure.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import tech.jhipster.light.error.domain.Assert;
import tech.jhipster.light.generator.project.domain.Project;

@Schema(description = "Project DTO")
public class ProjectDTO {

  @JsonProperty("folder")
  @Schema(description = "folder", example = "/tmp/jhlight/jhipster", required = true)
  private String folder;

  @JsonProperty("generator-jhipster")
  @Schema(description = "generator-jhipster configuration", required = true)
  private Map<String, Object> generatorJhipster;

  public String getFolder() {
    return folder;
  }

  public Map<String, Object> getGeneratorJhipster() {
    return generatorJhipster;
  }

  public static Project toProject(ProjectDTO projectDTO) {
    Assert.notNull("projectDTO", projectDTO);

    return Project.builder().folder(projectDTO.getFolder()).config(projectDTO.getGeneratorJhipster()).build();
  }

  public ProjectDTO folder(String path) {
    this.folder = path;
    return this;
  }

  public ProjectDTO generatorJhipster(Map<String, Object> generatorJhipster) {
    this.generatorJhipster = generatorJhipster;
    return this;
  }
}
