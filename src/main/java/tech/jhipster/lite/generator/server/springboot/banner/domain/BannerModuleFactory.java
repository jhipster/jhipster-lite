package tech.jhipster.lite.generator.server.springboot.banner.domain;

import static tech.jhipster.lite.generator.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterDestination;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterSource;

public class BannerModuleFactory {

  private static final String SOURCE_FOLDER = "server/springboot/banner";
  private static final String PROPERTIES = "properties";

  public JHipsterModule buildModuleBannerJHipsterV7(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-jhipster-v7.txt");
  }

  public JHipsterModule buildModuleBannerJHipsterV7React(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-jhipster-v7-react.txt");
  }

  public JHipsterModule buildModuleBannerJHipsterV7Vue(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-jhipster-v7-vue.txt");
  }

  public JHipsterModule buildModuleBannerJHipsterV2(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-jhipster-v2.txt");
  }

  public JHipsterModule buildModuleBannerJHipsterV3(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-jhipster-v3.txt");
  }

  public JHipsterModule buildModuleBannerIppon(BannerModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);
    return buildModuleBanner(properties, "banner-ippon.txt");
  }

  private JHipsterModule buildModuleBanner(BannerModuleProperties properties, String file) {
    // @formatter:off
    return JHipsterModule
      .moduleForProject(properties.project())
      .files()
        .add(source().file(file), destination())
        .and()
      .build();
    // @formatter:on
  }

  private JHipsterSource source() {
    return from(SOURCE_FOLDER);
  }

  private JHipsterDestination destination() {
    return new JHipsterDestination(MAIN_RESOURCES).append("banner.txt");
  }
}
