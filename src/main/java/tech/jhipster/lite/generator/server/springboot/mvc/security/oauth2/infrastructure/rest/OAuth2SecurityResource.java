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
@RequestMapping("/api/servers/spring-boot/security")
@Tag(name = "Spring Boot - Security")
class OAuth2SecurityResource {

  private final OAuth2SecurityApplicationService oauth2SecurityApplicationService;

  public OAuth2SecurityResource(OAuth2SecurityApplicationService oauth2SecurityApplicationService) {
    this.oauth2SecurityApplicationService = oauth2SecurityApplicationService;
  }

  @Operation(summary = "Add a Spring Security OAuth2-OIDC Client")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding a Spring Security OAuth2-OIDC Client")
  @PostMapping("/oauth2")
  public void init(@RequestBody OAuth2ClientDTO oAuth2ClientDTO) {
    Project project = ProjectDTO.toProject(oAuth2ClientDTO.getProjectDTO());
    oauth2SecurityApplicationService.init(project, oAuth2ClientDTO.getProvider(), oAuth2ClientDTO.getIssuerUri());
  }

  @Operation(summary = "Add Spring Security default login with OAuth2")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security default login with OAuth2")
  @PostMapping("/oauth2/default")
  public void addDefault(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    oauth2SecurityApplicationService.addDefault(project);
  }

  @Operation(summary = "Add Spring Security JWT over OAuth2")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security JWT over OAuth2")
  @PostMapping("/oauth2/jwt")
  public void addJwt(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    oauth2SecurityApplicationService.addJwt(project);
  }

  @Operation(summary = "Add Spring Security opaque token over OAuth2")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Security opaque token over OAuth2")
  @PostMapping("/oauth2/opaque-token")
  public void addOpaqueToken(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    oauth2SecurityApplicationService.addOpaqueToken(project);
  }

  @Operation(summary = "Add Account Management")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding account management")
  @PostMapping("/oauth2/account")
  public void addAccount(@RequestBody ProjectDTO projectDTO) {
    Project project = ProjectDTO.toProject(projectDTO);
    oauth2SecurityApplicationService.addAccount(project);
  }
}
