package tech.jhipster.lite.generator.server.springboot.banner.domain;

import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;

@UnitTest
class BannerModuleFactoryTest {

  private static final BannerModuleFactory factory = new BannerModuleFactory();
  private static final String BANNER_TXT = "src/main/resources/banner.txt";

  @Test
  @DisplayName("JHipster Banner V7")
  void shouldCreateModuleJHipsterBannerV7() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerJHipsterV7(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }

  @Test
  @DisplayName("JHipster Banner V7 React")
  void shouldCreateModuleJHipsterBannerV7React() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerJHipsterV7React(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }

  @Test
  @DisplayName("JHipster Banner V7 Vue")
  void shouldCreateModuleJHipsterBannerV7Vue() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerJHipsterV7Vue(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }

  @Test
  @DisplayName("JHipster Banner V2")
  void shouldCreateModuleJHipsterBannerV2() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerJHipsterV2(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }

  @Test
  @DisplayName("JHipster Banner V3")
  void shouldCreateModuleJHipsterBannerV3() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerJHipsterV3(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }

  @Test
  @DisplayName("Ippon Banner")
  void shouldCreateModuleIpponBanner() {
    BannerModuleProperties properties = BannerModuleProperties.builder().project(FileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModuleBannerIppon(properties);

    assertThatModule(module).createFiles(BANNER_TXT);
  }
}
