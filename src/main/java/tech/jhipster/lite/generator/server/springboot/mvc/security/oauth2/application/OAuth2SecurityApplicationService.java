package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2SecurityService;

@Service
public class OAuth2SecurityApplicationService {

  private final OAuth2SecurityService oauth2SecurityService;

  public OAuth2SecurityApplicationService(OAuth2SecurityService oauth2SecurityService) {
    this.oauth2SecurityService = oauth2SecurityService;
  }

  public void init(Project project, String issuerUri) {
    oauth2SecurityService.init(project, issuerUri);
  }

  public void addDefault(Project project) {
    oauth2SecurityService.addDefault(project);
  }

  public void addJwt(Project project) {
    oauth2SecurityService.addJwt(project);
  }

  public void addOpaqueToken(Project project) {
    oauth2SecurityService.addOpaqueToken(project);
  }

  public void addAccount(Project project) {
    oauth2SecurityService.addAccount(project);
  }
}
