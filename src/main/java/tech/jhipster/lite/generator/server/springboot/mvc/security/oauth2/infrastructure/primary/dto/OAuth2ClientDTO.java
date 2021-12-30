package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.primary.dto;

import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.DEFAULT_ISSUER_URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@Schema(description = "OAuth2 Client DTO")
public class OAuth2ClientDTO {

  @JsonProperty("project")
  @Schema(description = "project", required = true)
  private ProjectDTO projectDTO;

  @JsonProperty("issuerUri")
  @Schema(description = "issuer URI", defaultValue = DEFAULT_ISSUER_URI)
  private String issuerUri;

  public ProjectDTO getProjectDTO() {
    return projectDTO;
  }

  public void setProjectDTO(ProjectDTO projectDTO) {
    this.projectDTO = projectDTO;
  }

  public String getIssuerUri() {
    return issuerUri;
  }

  public void setIssuerUri(String issuerUri) {
    this.issuerUri = issuerUri;
  }
}
