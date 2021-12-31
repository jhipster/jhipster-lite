package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface OAuth2SecurityService {
  void init(Project project, OAuth2Provider provider, String issuerUri);
  void addDefault(Project project);
  void addJwt(Project project);
  void addOpaqueToken(Project project);
  void addAccount(Project project);
}
