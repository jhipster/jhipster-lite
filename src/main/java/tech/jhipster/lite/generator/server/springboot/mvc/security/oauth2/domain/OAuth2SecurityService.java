package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface OAuth2SecurityService {
  void addClient(Project project, OAuth2Provider provider, String issuerUri);
  void addDefault(Project project, OAuth2Provider provider, String issuerUri);
  void addResourceServerJwt(Project project);
  void addKeycloakDocker(Project project);
}
