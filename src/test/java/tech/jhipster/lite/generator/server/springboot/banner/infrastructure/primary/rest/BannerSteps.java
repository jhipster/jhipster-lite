package tech.jhipster.lite.generator.server.springboot.banner.infrastructure.primary.rest;

import io.cucumber.java.en.When;
import tech.jhipster.lite.generator.ModulesSteps;

public class BannerSteps extends ModulesSteps {

  @When("I add banner JHipster v7")
  public void addBannerJHipsterV7() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/jhipster-v7");
  }

  @When("I add banner JHipster v7 for React")
  public void addBannerJHipsterV7React() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/jhipster-v7-react");
  }

  @When("I add banner JHipster v7 for Vue")
  public void addBannerJHipsterV7Vue() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/jhipster-v7-vue");
  }

  @When("I add banner JHipster v2")
  public void addBannerJHipsterV2() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/jhipster-v2");
  }

  @When("I add banner JHipster v3")
  public void addBannerJHipsterV3() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/jhipster-v3");
  }

  @When("I add banner Ippon")
  public void addBannerIppon() {
    applyModuleForDefaultProject("/api/servers/spring-boot/banners/ippon");
  }
}
