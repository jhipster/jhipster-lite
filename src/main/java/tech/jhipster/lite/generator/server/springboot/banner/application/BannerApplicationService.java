package tech.jhipster.lite.generator.server.springboot.banner.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.JHipsterModules;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.server.springboot.banner.domain.BannerModuleFactory;
import tech.jhipster.lite.generator.server.springboot.banner.domain.BannerModuleProperties;

@Service
public class BannerApplicationService {

  private final JHipsterModules modules;
  private final BannerModuleFactory factory;

  public BannerApplicationService(JHipsterModules modules) {
    this.modules = modules;
    this.factory = new BannerModuleFactory();
  }

  public void addBannerJHipsterV7(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerJHipsterV7(properties);
    modules.apply(module);
  }

  public void addBannerJHipsterV7React(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerJHipsterV7React(properties);
    modules.apply(module);
  }

  public void addBannerJHipsterV7Vue(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerJHipsterV7Vue(properties);
    modules.apply(module);
  }

  public void addBannerJHipsterV2(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerJHipsterV2(properties);
    modules.apply(module);
  }

  public void addBannerJHipsterV3(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerJHipsterV3(properties);
    modules.apply(module);
  }

  public void addBannerIppon(BannerModuleProperties properties) {
    JHipsterModule module = factory.buildModuleBannerIppon(properties);
    modules.apply(module);
  }
}
