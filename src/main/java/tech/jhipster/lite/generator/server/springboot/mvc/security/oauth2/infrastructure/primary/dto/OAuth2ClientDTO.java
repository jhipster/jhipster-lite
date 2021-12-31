package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Provider;

@Schema(description = "OAuth2 Client DTO")
public class OAuth2ClientDTO {

  @JsonProperty("project")
  @Schema(description = "project", required = true)
  private ProjectDTO projectDTO;

  @JsonProperty(value = "provider", defaultValue = "KEYCLOAK")
  @Schema(description = "OAuth2-OIDC provider", nullable = true, defaultValue = "KEYCLOAK")
  private OAuth2Provider provider;

  @JsonProperty("issuerUri")
  @Schema(description = "your custom issuer URI for KEYCLOAK, OKTA, AUTH0 or OTHER", nullable = true)
  private String issuerUri;

  public ProjectDTO getProjectDTO() {
    return projectDTO;
  }

  public void setProjectDTO(ProjectDTO projectDTO) {
    this.projectDTO = projectDTO;
  }

  public OAuth2Provider getProvider() {
    return provider;
  }

  public void setProvider(OAuth2Provider provider) {
    this.provider = provider;
  }

  public String getIssuerUri() {
    return issuerUri;
  }

  public void setIssuerUri(String issuerUri) {
    this.issuerUri = issuerUri;
  }
}
