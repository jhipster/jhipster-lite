package tech.jhipster.lite.generator.server.javatool.frontendmaven.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.frontendmaven.domain.FrontendServerModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.npm.NpmVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class FrontendServerApplicationService {

  private final FrontendServerModuleFactory factory;

  public FrontendServerApplicationService(NpmVersions npmVersions) {
    factory = new FrontendServerModuleFactory(npmVersions);
  }

  public JHipsterModule buildFrontendMavenModule(JHipsterModuleProperties properties) {
    return factory.buildFrontendMavenModule(properties);
  }

  public JHipsterModule buildFrontendGradleModule(JHipsterModuleProperties properties) {
    return factory.buildFrontendGradleModule(properties);
  }
}
