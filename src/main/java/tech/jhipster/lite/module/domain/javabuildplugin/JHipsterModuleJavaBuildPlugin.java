package tech.jhipster.lite.module.domain.javabuildplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddBuildPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddBuildPluginManagement.AddBuildPluginManagementOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaBuildPlugin.AddDirectJavaBuildPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JHipsterModuleJavaBuildPlugin {

  private final Collection<JavaBuildPlugin> pluginsManagement;
  private final Collection<JavaBuildPlugin> plugins;

  private JHipsterModuleJavaBuildPlugin(JHipsterModuleJavaBuildPluginBuilder builder) {
    pluginsManagement = builder.pluginsManagement;
    plugins = builder.plugins;
  }

  public static JHipsterModuleJavaBuildPluginBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleJavaBuildPluginBuilder(module);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    Assert.notNull("versions", versions);

    Stream<JavaBuildCommand> managementCommands = pluginsManagement.stream().map(toAddBuildPluginManagement(versions));
    Stream<JavaBuildCommand> pluginsCommands = plugins.stream().map(toAddDirectJavaBuildPlugin(versions));

    return new JavaBuildCommands(Stream.concat(managementCommands, pluginsCommands).toList());
  }

  private static Function<JavaBuildPlugin, AddBuildPluginManagement> toAddBuildPluginManagement(JavaDependenciesVersions versions) {
    return plugin -> {
      AddBuildPluginManagementOptionalBuilder commandBuilder = AddBuildPluginManagement.builder().plugin(plugin);
      return plugin.versionSlug().map(versions::get).map(commandBuilder::pluginVersion).orElse(commandBuilder.build());
    };
  }

  private static Function<JavaBuildPlugin, AddDirectJavaBuildPlugin> toAddDirectJavaBuildPlugin(JavaDependenciesVersions versions) {
    return plugin -> {
      AddDirectJavaBuildPluginOptionalBuilder commandBuilder = AddDirectJavaBuildPlugin.builder().javaBuildPlugin(plugin);
      return plugin.versionSlug().map(versions::get).map(commandBuilder::pluginVersion).orElse(commandBuilder.build());
    };
  }

  public static class JHipsterModuleJavaBuildPluginBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<JavaBuildPlugin> pluginsManagement = new ArrayList<>();
    private final Collection<JavaBuildPlugin> plugins = new ArrayList<>();

    private JHipsterModuleJavaBuildPluginBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleJavaBuildPluginBuilder pluginManagement(JavaBuildPlugin pluginManagement) {
      Assert.notNull("pluginManagement", pluginManagement);

      pluginsManagement.add(pluginManagement);

      return this;
    }

    public JHipsterModuleJavaBuildPluginBuilder plugin(JavaBuildPlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleJavaBuildPlugin build() {
      return new JHipsterModuleJavaBuildPlugin(this);
    }
  }
}
