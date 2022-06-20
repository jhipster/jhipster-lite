package tech.jhipster.lite.generator.module.domain.packagejson;

import tech.jhipster.lite.error.domain.Assert;

public record ScriptKey(String key) {
  public ScriptKey {
    Assert.notBlank("key", key);
  }

  public String get() {
    return key();
  }
}
