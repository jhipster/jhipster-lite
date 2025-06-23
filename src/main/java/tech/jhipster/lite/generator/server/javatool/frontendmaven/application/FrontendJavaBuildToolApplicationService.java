package tech.jhipster.lite.generator.server.javatool.frontendmaven.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.frontendmaven.domain.FrontendJavaBuildToolModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class FrontendJavaBuildToolApplicationService {

  private final FrontendJavaBuildToolModuleFactory frontendJavaBuildTool;

  public FrontendJavaBuildToolApplicationService(NodeVersions nodeVersions) {
    frontendJavaBuildTool = new FrontendJavaBuildToolModuleFactory(nodeVersions);
  }

  public JHipsterModule buildFrontendMavenModule(JHipsterModuleProperties properties) {
    return frontendJavaBuildTool.buildFrontendMavenModule(properties);
  }

  public JHipsterModule buildFrontendGradleModule(JHipsterModuleProperties properties) {
    return frontendJavaBuildTool.buildFrontendGradleModule(properties);
  }

  public JHipsterModule buildFrontendMavenCacheModule(JHipsterModuleProperties properties) {
    return frontendJavaBuildTool.buildFrontendMavenCacheModule(properties);
  }

  public JHipsterModule buildMergeCypressCoverageModule(JHipsterModuleProperties properties) {
    return frontendJavaBuildTool.buildMergeCypressCoverageModule(properties);
  }
}
