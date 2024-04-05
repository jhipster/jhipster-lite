package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradleCommunityProfilePlugin implements GradleProfilePlugin {

  private final GradlePluginId id;
  private final GradlePluginDependency dependency;
  private final Optional<GradlePluginConfiguration> configuration;
  private final Optional<VersionSlug> versionSlug;

  private GradleCommunityProfilePlugin(GradleCommunityProfilePluginBuilder builder) {
    Assert.notNull("id", builder.id);
    Assert.notNull("dependencyId", builder.dependency);

    id = builder.id;
    this.dependency = builder.dependency;
    this.configuration = Optional.ofNullable(builder.configuration);
    this.versionSlug = Optional.ofNullable(builder.versionSlug);
  }

  @Override
  public GradlePluginId id() {
    return id;
  }

  @Override
  public Optional<GradlePluginConfiguration> configuration() {
    return configuration;
  }

  public GradlePluginDependency dependency() {
    return dependency;
  }

  public Optional<VersionSlug> versionSlug() {
    return versionSlug;
  }

  public static GradleCommunityProfilePluginIdBuilder builder() {
    return new GradleCommunityProfilePluginBuilder();
  }

  private static final class GradleCommunityProfilePluginBuilder
    implements GradleCommunityProfilePluginIdBuilder, GradleCommunityPluginDependencyBuilder, GradleCommunityPluginOptionalBuilder {

    private GradlePluginId id;
    private GradlePluginDependency dependency;
    private GradlePluginConfiguration configuration;
    private VersionSlug versionSlug;

    @Override
    public GradleCommunityPluginDependencyBuilder id(GradlePluginId id) {
      this.id = id;

      return this;
    }

    @Override
    public GradleCommunityPluginOptionalBuilder dependency(GradlePluginDependency dependency) {
      this.dependency = dependency;

      return this;
    }

    @Override
    public GradleCommunityPluginOptionalBuilder configuration(GradlePluginConfiguration configuration) {
      this.configuration = configuration;

      return this;
    }

    @Override
    public GradleCommunityPluginOptionalBuilder versionSlug(VersionSlug versionSlug) {
      this.versionSlug = versionSlug;

      return this;
    }

    @Override
    public GradleCommunityProfilePlugin build() {
      return new GradleCommunityProfilePlugin(this);
    }
  }

  public interface GradleCommunityProfilePluginIdBuilder {
    GradleCommunityPluginDependencyBuilder id(GradlePluginId id);

    default GradleCommunityPluginDependencyBuilder id(String id) {
      return id(new GradlePluginId(id));
    }
  }

  public interface GradleCommunityPluginDependencyBuilder {
    GradleCommunityPluginOptionalBuilder dependency(GradlePluginDependency dependency);

    default GradleCommunityPluginOptionalBuilder dependency(GroupId groupId, ArtifactId artifactId) {
      return dependency(new GradlePluginDependency(groupId, artifactId));
    }
  }

  public interface GradleCommunityPluginOptionalBuilder {
    GradleCommunityPluginOptionalBuilder configuration(GradlePluginConfiguration configuration);

    default GradleCommunityPluginOptionalBuilder configuration(String configuration) {
      return configuration(new GradlePluginConfiguration(configuration));
    }

    GradleCommunityPluginOptionalBuilder versionSlug(VersionSlug versionSlug);

    default GradleCommunityPluginOptionalBuilder versionSlug(String versionSlug) {
      return versionSlug(new VersionSlug(versionSlug));
    }

    GradleCommunityProfilePlugin build();
  }
}
