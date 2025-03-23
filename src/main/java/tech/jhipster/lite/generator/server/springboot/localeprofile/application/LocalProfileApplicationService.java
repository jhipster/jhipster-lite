package tech.jhipster.lite.generator.server.springboot.localeprofile.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.localeprofile.domain.LocalProfileModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class LocalProfileApplicationService {

  private final LocalProfileModuleFactory localProfile;

  public LocalProfileApplicationService() {
    localProfile = new LocalProfileModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return localProfile.buildModule(properties);
  }
}
