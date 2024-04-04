package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradleProfilePlugin implements GradlePlugin {

  private final GradlePluginId id;
  private final GradlePluginDependency dependency;
  private final Optional<GradlePluginConfiguration> configuration;
  private final Optional<VersionSlug> versionSlug;

  private GradleProfilePlugin(GradleProfilePluginBuilder builder) {
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

  public static GradleProfilePluginIdBuilder builder() {
    return new GradleProfilePluginBuilder();
  }

  private static final class GradleProfilePluginBuilder
    implements GradleProfilePluginIdBuilder, GradleProfilePluginDependencyBuilder, GradleProfilePluginOptionalBuilder {

    private GradlePluginId id;
    private GradlePluginDependency dependency;
    private GradlePluginConfiguration configuration;
    private VersionSlug versionSlug;

    @Override
    public GradleProfilePluginDependencyBuilder id(GradlePluginId id) {
      this.id = id;

      return this;
    }

    @Override
    public GradleProfilePluginOptionalBuilder dependency(GradlePluginDependency dependency) {
      this.dependency = dependency;

      return this;
    }

    @Override
    public GradleProfilePluginOptionalBuilder configuration(GradlePluginConfiguration configuration) {
      this.configuration = configuration;

      return this;
    }

    @Override
    public GradleProfilePluginOptionalBuilder versionSlug(VersionSlug versionSlug) {
      this.versionSlug = versionSlug;

      return this;
    }

    @Override
    public GradleProfilePlugin build() {
      return new GradleProfilePlugin(this);
    }
  }

  public interface GradleProfilePluginIdBuilder {
    GradleProfilePluginDependencyBuilder id(GradlePluginId id);

    default GradleProfilePluginDependencyBuilder id(String id) {
      return id(new GradlePluginId(id));
    }
  }

  public interface GradleProfilePluginDependencyBuilder {
    GradleProfilePluginOptionalBuilder dependency(GradlePluginDependency dependency);

    default GradleProfilePluginOptionalBuilder dependency(GroupId groupId, ArtifactId artifactId) {
      return dependency(new GradlePluginDependency(groupId, artifactId));
    }
  }

  public interface GradleProfilePluginOptionalBuilder {
    GradleProfilePluginOptionalBuilder configuration(GradlePluginConfiguration configuration);

    default GradleProfilePluginOptionalBuilder configuration(String configuration) {
      return configuration(new GradlePluginConfiguration(configuration));
    }

    GradleProfilePluginOptionalBuilder versionSlug(VersionSlug versionSlug);

    default GradleProfilePluginOptionalBuilder versionSlug(String versionSlug) {
      return versionSlug(new VersionSlug(versionSlug));
    }

    GradleProfilePlugin build();
  }
}
