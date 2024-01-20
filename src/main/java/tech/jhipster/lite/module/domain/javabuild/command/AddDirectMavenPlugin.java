package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class AddDirectMavenPlugin implements JavaBuildCommand, AddMavenPlugin {

  private final MavenPlugin plugin;
  private final Optional<JavaDependencyVersion> pluginVersion;
  private final Optional<BuildProfileId> buildProfile;
  private final Collection<JavaDependencyVersion> dependenciesVersions;

  private AddDirectMavenPlugin(AddDirectMavenPluginBuilder builder) {
    Assert.notNull("plugin", builder.plugin);
    Assert.notNull("dependenciesVersions", builder.dependenciesVersions);
    this.plugin = builder.plugin;
    this.dependenciesVersions = builder.dependenciesVersions;
    this.pluginVersion = Optional.ofNullable(builder.pluginVersion);
    this.buildProfile = Optional.ofNullable(builder.buildProfile);
  }

  @Override
  public MavenPlugin plugin() {
    return plugin;
  }

  public Optional<BuildProfileId> buildProfile() {
    return buildProfile;
  }

  @Override
  public Optional<JavaDependencyVersion> pluginVersion() {
    return pluginVersion;
  }

  public Collection<JavaDependencyVersion> dependenciesVersions() {
    return dependenciesVersions;
  }

  public static AddDirectMavenPluginPluginBuilder builder() {
    return new AddDirectMavenPluginBuilder();
  }

  private static class AddDirectMavenPluginBuilder implements AddDirectMavenPluginPluginBuilder, AddDirectMavenPluginOptionalBuilder {

    private MavenPlugin plugin;
    private JavaDependencyVersion pluginVersion;
    private BuildProfileId buildProfile;
    private final Collection<JavaDependencyVersion> dependenciesVersions = new HashSet<>();

    private AddDirectMavenPluginBuilder() {}

    @Override
    public AddDirectMavenPluginBuilder plugin(MavenPlugin plugin) {
      this.plugin = plugin;
      return this;
    }

    @Override
    public AddDirectMavenPluginOptionalBuilder pluginVersion(JavaDependencyVersion pluginVersion) {
      this.pluginVersion = pluginVersion;
      return this;
    }

    @Override
    public AddDirectMavenPluginOptionalBuilder buildProfile(BuildProfileId buildProfile) {
      this.buildProfile = buildProfile;
      return this;
    }

    @Override
    public AddDirectMavenPluginOptionalBuilder addDependencyVersion(JavaDependencyVersion version) {
      this.dependenciesVersions.add(version);
      return this;
    }

    public AddDirectMavenPlugin build() {
      return new AddDirectMavenPlugin(this);
    }
  }

  public interface AddDirectMavenPluginPluginBuilder {
    AddDirectMavenPluginOptionalBuilder plugin(MavenPlugin javaBuildPlugin);
  }

  public interface AddDirectMavenPluginOptionalBuilder {
    AddDirectMavenPluginOptionalBuilder pluginVersion(JavaDependencyVersion version);
    AddDirectMavenPluginOptionalBuilder buildProfile(BuildProfileId buildProfile);
    AddDirectMavenPluginOptionalBuilder addDependencyVersion(JavaDependencyVersion version);

    AddDirectMavenPlugin build();
  }
}
