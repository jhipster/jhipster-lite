package tech.jhipster.lite.generator.module.domain.packagejson;

import tech.jhipster.lite.error.domain.Assert;

public record Script(ScriptKey key, ScriptCommand command) {
  public Script {
    Assert.notNull("key", key);
    Assert.notNull("command", command);
  }
}
