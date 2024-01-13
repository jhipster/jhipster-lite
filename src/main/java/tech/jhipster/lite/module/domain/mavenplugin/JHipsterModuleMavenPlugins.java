package tech.jhipster.lite.module.domain.mavenplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectMavenPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectMavenPlugin.AddDirectMavenPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenPluginManagement.AddMavenPluginManagementOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JHipsterModuleMavenPlugins {

  private final Collection<MavenPlugin> pluginsManagement;
  private final Collection<MavenPlugin> plugins;

  private JHipsterModuleMavenPlugins(JHipsterModuleMavenPluginsBuilder<?> builder) {
    pluginsManagement = builder.pluginsManagement;
    plugins = builder.plugins;
  }

  public static <T> JHipsterModuleMavenPluginsBuilder<T> builder(T parentModuleBuilder) {
    return new JHipsterModuleMavenPluginsBuilder<>(parentModuleBuilder);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    return buildChanges(versions, Optional.empty());
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions, BuildProfileId buildProfile) {
    Assert.notNull("buildProfile", buildProfile);
    return buildChanges(versions, Optional.of(buildProfile));
  }

  private JavaBuildCommands buildChanges(JavaDependenciesVersions versions, Optional<BuildProfileId> buildProfile) {
    Assert.notNull("versions", versions);

    Stream<JavaBuildCommand> managementCommands = pluginsManagement.stream().map(toAddMavenPluginManagement(versions, buildProfile));
    Stream<JavaBuildCommand> pluginsCommands = plugins.stream().map(toAddDirectMavenPlugin(versions, buildProfile));

    return new JavaBuildCommands(Stream.concat(managementCommands, pluginsCommands).toList());
  }

  private static Function<MavenPlugin, AddMavenPluginManagement> toAddMavenPluginManagement(
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    return plugin -> {
      AddMavenPluginManagementOptionalBuilder commandBuilder = AddMavenPluginManagement.builder().plugin(plugin);
      return plugin
        .versionSlug()
        .map(versions::get)
        .map(commandBuilder::pluginVersion)
        .map(builder -> builder.buildProfile(buildProfile.orElse(null)))
        .map(AddMavenPluginManagementOptionalBuilder::build)
        .orElse(commandBuilder.build());
    };
  }

  private static Function<MavenPlugin, AddDirectMavenPlugin> toAddDirectMavenPlugin(
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    return plugin -> {
      AddDirectMavenPluginOptionalBuilder commandBuilder = AddDirectMavenPlugin.builder().plugin(plugin);
      return plugin
        .versionSlug()
        .map(versions::get)
        .map(commandBuilder::pluginVersion)
        .map(builder -> builder.buildProfile(buildProfile.orElse(null)))
        .map(AddDirectMavenPluginOptionalBuilder::build)
        .orElse(commandBuilder.build());
    };
  }

  public static class JHipsterModuleMavenPluginsBuilder<T> {

    private final T parentModuleBuilder;
    private final Collection<MavenPlugin> pluginsManagement = new ArrayList<>();
    private final Collection<MavenPlugin> plugins = new ArrayList<>();

    private JHipsterModuleMavenPluginsBuilder(T parentModuleBuilder) {
      Assert.notNull("parentModuleBuilder", parentModuleBuilder);

      this.parentModuleBuilder = parentModuleBuilder;
    }

    public JHipsterModuleMavenPluginsBuilder<T> pluginManagement(MavenPlugin pluginManagement) {
      Assert.notNull("pluginManagement", pluginManagement);

      pluginsManagement.add(pluginManagement);

      return this;
    }

    public JHipsterModuleMavenPluginsBuilder<T> plugin(MavenPlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public T and() {
      return parentModuleBuilder;
    }

    public JHipsterModuleMavenPlugins build() {
      return new JHipsterModuleMavenPlugins(this);
    }
  }
}
