package tech.jhipster.lite.module.domain.resource;

import tech.jhipster.lite.error.domain.Assert;

public record JHipsterModuleApiDoc(JHipsterModuleTag tag, JHipsterModuleOperation operation) {
  public JHipsterModuleApiDoc(String tag, String operation) {
    this(new JHipsterModuleTag(tag), new JHipsterModuleOperation(operation));
  }

  public JHipsterModuleApiDoc {
    Assert.notNull("tag", tag);
    Assert.notNull("operation", operation);
  }
}
