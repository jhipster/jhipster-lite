package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.shared.error.domain.ErrorKey;

enum NodeErrorKey implements ErrorKey {
  UNKNOWN_PACKAGE("unknown-node-package");

  private final String key;

  NodeErrorKey(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
