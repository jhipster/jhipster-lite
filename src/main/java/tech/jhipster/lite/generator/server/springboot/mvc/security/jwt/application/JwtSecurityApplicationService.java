package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityService;

@Service
public class JwtSecurityApplicationService {

  private final JwtSecurityService jwtSecurityService;

  public JwtSecurityApplicationService(JwtSecurityService jwtSecurityService) {
    this.jwtSecurityService = jwtSecurityService;
  }

  public void initBasicAuth(Project project) {
    jwtSecurityService.initBasicAuth(project);
  }
}
