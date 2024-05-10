package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginImports(Collection<GradlePluginImport> pluginImports) {
  public GradlePluginImports {
    Assert.field("pluginImports", pluginImports).notNull().noNullElement();
  }

  public static GradlePluginImports of(GradlePluginImport... pluginImports) {
    Assert.field("pluginImports", pluginImports).notNull();
    return new GradlePluginImports(List.of(pluginImports));
  }

  public Stream<GradlePluginImport> stream() {
    return pluginImports.stream();
  }
}
