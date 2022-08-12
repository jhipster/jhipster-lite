package tech.jhipster.lite.module.domain.resource;

import java.util.Collection;
import java.util.Optional;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;

public final class JHipsterLandscapeModule implements JHipsterLandscapeElement {

  private final JHipsterModuleSlug module;
  private final JHipsterModuleOperation operation;
  private final Optional<JHipsterLandscapeDependencies> dependencies;

  private JHipsterLandscapeModule(JHipsterLandscapeModuleBuilder builder) {
    Assert.notNull("module", builder.module);
    Assert.notNull("operation", builder.operation);

    module = builder.module;
    operation = builder.operation;
    dependencies = JHipsterLandscapeDependencies.of(builder.dependencies);
  }

  public static JHipsterLandscapeModuleSlugBuilder builder() {
    return new JHipsterLandscapeModuleBuilder();
  }

  @Override
  public JHipsterLandscapeElementType type() {
    return JHipsterLandscapeElementType.MODULE;
  }

  @Override
  public JHipsterModuleSlug slug() {
    return module;
  }

  public JHipsterModuleOperation operation() {
    return operation;
  }

  @Override
  public Optional<JHipsterLandscapeDependencies> dependencies() {
    return dependencies;
  }

  @Override
  @Generated
  public int hashCode() {
    return new HashCodeBuilder().append(module).hashCode();
  }

  @Override
  @Generated
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    JHipsterLandscapeModule other = (JHipsterLandscapeModule) obj;

    return new EqualsBuilder().append(module, other.module).isEquals();
  }

  public static class JHipsterLandscapeModuleBuilder
    implements JHipsterLandscapeModuleSlugBuilder, JHipsterLandscapeModuleOperationBuilder, JHipsterLandscapeModuleDependenciesBuilder {

    private JHipsterModuleSlug module;
    private JHipsterModuleOperation operation;
    private Collection<JHipsterLandscapeDependency> dependencies;

    private JHipsterLandscapeModuleBuilder() {}

    @Override
    public JHipsterLandscapeModuleOperationBuilder module(JHipsterModuleSlug module) {
      this.module = module;

      return this;
    }

    @Override
    public JHipsterLandscapeModuleDependenciesBuilder operation(JHipsterModuleOperation operation) {
      this.operation = operation;

      return this;
    }

    @Override
    public JHipsterLandscapeModule dependencies(Collection<JHipsterLandscapeDependency> dependencies) {
      this.dependencies = dependencies;

      return new JHipsterLandscapeModule(this);
    }
  }

  public interface JHipsterLandscapeModuleSlugBuilder {
    JHipsterLandscapeModuleOperationBuilder module(JHipsterModuleSlug module);

    default JHipsterLandscapeModuleOperationBuilder module(String module) {
      return module(new JHipsterModuleSlug(module));
    }
  }

  public interface JHipsterLandscapeModuleOperationBuilder {
    JHipsterLandscapeModuleDependenciesBuilder operation(JHipsterModuleOperation operation);

    default JHipsterLandscapeModuleDependenciesBuilder operation(String operation) {
      return operation(new JHipsterModuleOperation(operation));
    }
  }

  public interface JHipsterLandscapeModuleDependenciesBuilder {
    JHipsterLandscapeModule dependencies(Collection<JHipsterLandscapeDependency> dependencies);

    default JHipsterLandscapeModule withoutDependencies() {
      return dependencies(null);
    }
  }
}
