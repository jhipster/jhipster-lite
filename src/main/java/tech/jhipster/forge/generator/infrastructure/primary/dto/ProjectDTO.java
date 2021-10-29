package tech.jhipster.forge.generator.infrastructure.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.generator.domain.core.Project;

@ApiModel(value = "ProjectDTO", description = "Project DTO")
public class ProjectDTO {

  @JsonProperty("path")
  @ApiModelProperty(value = "path", example = "/tmp/jhforge/jhipster", required = true)
  private String path;

  @JsonProperty("generator-jhipster")
  @ApiModelProperty(value = "generator-jhipster configuration", required = true)
  private Map<String, Object> generatorJhipster;

  public String getPath() {
    return path;
  }

  public Map<String, Object> getGeneratorJhipster() {
    return generatorJhipster;
  }

  public static Project toProject(ProjectDTO projectDTO) {
    Assert.notNull("projectDTO", projectDTO);

    return Project.builder().path(projectDTO.getPath()).config(projectDTO.getGeneratorJhipster()).build();
  }

  public ProjectDTO path(String path) {
    this.path = path;
    return this;
  }

  public ProjectDTO generatorJhipster(Map<String, Object> generatorJhipster) {
    this.generatorJhipster = generatorJhipster;
    return this;
  }
}
