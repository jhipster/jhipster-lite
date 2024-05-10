package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradleCorePlugin implements GradleMainBuildPlugin, GradleProfilePlugin {

  private final GradlePluginId id;
  private final Optional<GradlePluginImports> imports;
  private final Optional<GradlePluginConfiguration> configuration;
  private final Optional<VersionSlug> toolVersionSlug;

  private GradleCorePlugin(GradleCorePluginBuilder builder) {
    Assert.notNull("id", builder.id);
    id = builder.id;
    imports = Optional.ofNullable(builder.imports);
    configuration = Optional.ofNullable(builder.configuration);
    toolVersionSlug = Optional.ofNullable(builder.toolVersionSlug);
  }

  public GradlePluginId id() {
    return id;
  }

  @Override
  public Optional<GradlePluginImports> imports() {
    return imports;
  }

  @Override
  public Optional<GradlePluginConfiguration> configuration() {
    return configuration;
  }

  public Optional<VersionSlug> toolVersionSlug() {
    return toolVersionSlug;
  }

  public static GradleCorePluginIdBuilder builder() {
    return new GradleCorePluginBuilder();
  }

  private static final class GradleCorePluginBuilder implements GradleCorePluginIdBuilder, GradleCorePluginOptionalBuilder {

    private GradlePluginId id;
    private GradlePluginImports imports;
    private GradlePluginConfiguration configuration;
    private VersionSlug toolVersionSlug;

    @Override
    public GradleCorePluginOptionalBuilder id(GradlePluginId id) {
      this.id = id;

      return this;
    }

    @Override
    public GradleCorePluginOptionalBuilder imports(GradlePluginImports imports) {
      this.imports = imports;

      return this;
    }

    @Override
    public GradleCorePluginOptionalBuilder configuration(GradlePluginConfiguration configuration) {
      this.configuration = configuration;

      return this;
    }

    @Override
    public GradleCorePluginOptionalBuilder toolVersionSlug(VersionSlug versionSlug) {
      this.toolVersionSlug = versionSlug;

      return this;
    }

    @Override
    public GradleCorePlugin build() {
      return new GradleCorePlugin(this);
    }
  }

  public interface GradleCorePluginIdBuilder {
    GradleCorePluginOptionalBuilder id(GradlePluginId id);

    default GradleCorePluginOptionalBuilder id(String id) {
      return id(new GradlePluginId(id));
    }
  }

  public interface GradleCorePluginOptionalBuilder {
    GradleCorePluginOptionalBuilder imports(GradlePluginImports imports);

    default GradleCorePluginOptionalBuilder imports(GradlePluginImport... imports) {
      return imports(GradlePluginImports.of(imports));
    }

    GradleCorePluginOptionalBuilder configuration(GradlePluginConfiguration configuration);

    default GradleCorePluginOptionalBuilder configuration(String configuration) {
      return configuration(new GradlePluginConfiguration(configuration));
    }

    GradleCorePluginOptionalBuilder toolVersionSlug(VersionSlug versionSlug);

    default GradleCorePluginOptionalBuilder toolVersionSlug(String versionSlug) {
      return toolVersionSlug(new VersionSlug(versionSlug));
    }

    GradleCorePlugin build();
  }
}
