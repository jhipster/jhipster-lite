package tech.jhipster.forge.generator.project.domain;

import tech.jhipster.forge.error.domain.Assert;

public record Server(ServerFramework framework) {
  public Server {
    Assert.notNull("framework", framework);
  }
}
