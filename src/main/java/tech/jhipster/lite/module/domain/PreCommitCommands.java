package tech.jhipster.lite.module.domain;

import tech.jhipster.lite.shared.error.domain.Assert;

public record PreCommitCommands(String commands) {
  public PreCommitCommands {
    Assert.notBlank("commands", commands);
  }

  public String get() {
    return commands;
  }
}
