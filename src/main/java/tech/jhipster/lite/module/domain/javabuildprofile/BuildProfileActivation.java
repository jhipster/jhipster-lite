package tech.jhipster.lite.module.domain.javabuildprofile;

import tech.jhipster.lite.shared.error.domain.Assert;

public record BuildProfileActivation(String activation) {
  public BuildProfileActivation {
    Assert.notBlank("activation", activation);
  }

  public String get() {
    return activation();
  }
}
