package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application.OAuth2SecurityApplicationService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.primary.dto.OAuth2ClientDTO;

@RestController
@RequestMapping("/api/servers/spring-boot/mvc/security")
@Tag(name = "Spring Boot - Security")
class OAuth2SecurityResource {

  private final OAuth2SecurityApplicationService oauth2SecurityApplicationService;

  public OAuth2SecurityResource(OAuth2SecurityApplicationService oauth2SecurityApplicationService) {
    this.oauth2SecurityApplicationService = oauth2SecurityApplicationService;
  }

  @Operation(summary = "Add a Spring Security OAuth2-OIDC Client")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding a Spring Security OAuth2-OIDC Client")
  @PostMapping("/oauth2/add-client")
  public void addClient(@RequestBody OAuth2ClientDTO oAuth2ClientDTO) {
    Project project = ProjectDTO.toProject(oAuth2ClientDTO);
    oauth2SecurityApplicationService.addClient(project, oAuth2ClientDTO.getProvider(), oAuth2ClientDTO.getIssuerUri());
  }

  @Operation(summary = "Add Spring Security default login with OAuth2")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security default login with OAuth2")
  @PostMapping("/oauth2/default")
  public void addDefault(@RequestBody OAuth2ClientDTO oAuth2ClientDTO) {
    Project project = ProjectDTO.toProject(oAuth2ClientDTO);
    oauth2SecurityApplicationService.addDefault(project, oAuth2ClientDTO.getProvider(), oAuth2ClientDTO.getIssuerUri());
  }
}
