package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuild.command.SetVersion;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JHipsterModuleGradlePlugin {

  private final Collection<GradlePlugin> plugins;

  private JHipsterModuleGradlePlugin(JHipsterModuleGradlePluginBuilder builder) {
    Assert.notNull("plugins", builder.plugins);
    plugins = builder.plugins;
  }

  public static JHipsterModuleGradlePluginBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleGradlePluginBuilder(module);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    Assert.notNull("versions", versions);

    Stream<JavaBuildCommand> pluginsCommands = plugins.stream().map(AddGradlePlugin::new);
    Stream<JavaBuildCommand> toolVersionCommands = plugins
      .stream()
      .filter(GradleCorePlugin.class::isInstance)
      .map(GradleCorePlugin.class::cast)
      .map(GradleCorePlugin::toolVersionSlug)
      .flatMap(Optional::stream)
      .map(toSetVersionCommand(versions));

    return new JavaBuildCommands(Stream.concat(pluginsCommands, toolVersionCommands).toList());
  }

  private static Function<VersionSlug, JavaBuildCommand> toSetVersionCommand(JavaDependenciesVersions versions) {
    return versionSlug -> new SetVersion(versions.get(versionSlug));
  }

  public static class JHipsterModuleGradlePluginBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<GradlePlugin> plugins = new ArrayList<>();

    private JHipsterModuleGradlePluginBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleGradlePluginBuilder plugin(GradlePlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleGradlePlugin build() {
      return new JHipsterModuleGradlePlugin(this);
    }
  }
}
