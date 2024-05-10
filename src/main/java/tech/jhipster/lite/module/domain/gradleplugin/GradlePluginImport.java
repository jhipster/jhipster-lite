package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginImport(String pluginImport) {
  public GradlePluginImport {
    Assert.notBlank("pluginImport", pluginImport);
  }

  public String get() {
    return pluginImport();
  }
}
