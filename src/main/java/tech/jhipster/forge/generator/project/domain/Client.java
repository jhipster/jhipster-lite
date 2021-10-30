package tech.jhipster.forge.generator.project.domain;

import tech.jhipster.forge.error.domain.Assert;

public record Client(ClientFramework framework) {
  public Client {
    Assert.notNull("framework", framework);
  }
}
