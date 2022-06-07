package tech.jhipster.lite.generator.server.springboot.database.postgresql.infrastructure.primary;

import static tech.jhipster.lite.generator.server.springboot.database.postgresql.infrastructure.primary.PostgresqlModuleConfiguration.URL_POSTGRESQL_MODULE;

import io.cucumber.java.en.When;
import tech.jhipster.lite.generator.ModulesSteps;

public class PostqresqlSteps extends ModulesSteps {

  @When("I add postgresql to default project with maven file")
  public void addCucumberModule() {
    applyModuleForDefaultProjectWithMavenFile(URL_POSTGRESQL_MODULE);
  }
}
