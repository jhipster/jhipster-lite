package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradleProfilePlugin implements GradlePlugin {

  private final GradlePluginDependency dependency;
  private final Optional<GradlePluginConfiguration> configuration;
  private final Optional<VersionSlug> versionSlug;

  private GradleProfilePlugin(GradleProfilePluginBuilder builder) {
    Assert.notNull("dependencyId", builder.dependency);

    this.dependency = builder.dependency;
    this.configuration = Optional.ofNullable(builder.configuration);
    this.versionSlug = Optional.ofNullable(builder.versionSlug);
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

  public static GradleProfilePluginDependencyBuilder builder() {
    return new GradleProfilePluginBuilder();
  }

  private static final class GradleProfilePluginBuilder
    implements GradleProfilePluginDependencyBuilder, GradleProfilePluginOptionalBuilder {

    private GradlePluginDependency dependency;
    private GradlePluginConfiguration configuration;
    private VersionSlug versionSlug;

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

  public interface GradleProfilePluginDependencyBuilder {
    GradleProfilePluginOptionalBuilder dependency(GradlePluginDependency dependency);

    default GradleProfilePluginOptionalBuilder dependency(String groupId, String artifactId) {
      return dependency(new GradlePluginDependency(JavaDependency.builder().groupId(groupId).artifactId(artifactId).build()));
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
