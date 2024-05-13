package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradlePluginImports {

  private final Collection<GradlePluginImport> imports;

  public GradlePluginImports(GradlePluginImportsBuilder<?> builder) {
    this.imports = builder.imports;
  }

  public Stream<GradlePluginImport> stream() {
    return imports.stream();
  }

  public static <T> GradlePluginImportsBuilder<T> builder(T parentBuilder) {
    return new GradlePluginImportsBuilder<>(parentBuilder);
  }

  public static final class GradlePluginImportsBuilder<T> {

    private final T parentBuilder;
    private final Collection<GradlePluginImport> imports = new ArrayList<>();

    private GradlePluginImportsBuilder(T parentBuilder) {
      Assert.notNull("parentBuilder", parentBuilder);

      this.parentBuilder = parentBuilder;
    }

    public GradlePluginImportsBuilder<T> gradleImport(String gradleImport) {
      imports.add(new GradlePluginImport(gradleImport));

      return this;
    }

    public T and() {
      return parentBuilder;
    }

    public Optional<GradlePluginImports> buildOptional() {
      return imports.isEmpty() ? Optional.empty() : Optional.of(build());
    }

    private GradlePluginImports build() {
      return new GradlePluginImports(this);
    }
  }
}
