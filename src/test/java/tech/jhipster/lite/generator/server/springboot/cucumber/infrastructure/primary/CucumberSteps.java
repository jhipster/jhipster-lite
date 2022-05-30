package tech.jhipster.lite.generator.server.springboot.cucumber.infrastructure.primary;

import io.cucumber.java.en.When;
import tech.jhipster.lite.generator.ModulesSteps;

public class CucumberSteps extends ModulesSteps {

  @When("I add cucumber to default project with maven file")
  public void addCucumberModule() {
    applyModuleForDefaultProjectWithMavenFile("/api/servers/spring-boot/component-tests/cucumber");
  }
}
