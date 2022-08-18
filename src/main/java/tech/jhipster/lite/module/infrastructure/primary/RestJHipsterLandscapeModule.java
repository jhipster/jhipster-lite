package tech.jhipster.lite.module.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeElementType;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeModule;

@JsonPropertyOrder({ "type", "slug", "operation", "properties", "dependencies" })
@Schema(name = "JHipsterLandscapeModule", description = "Module in a landscape")
final class RestJHipsterLandscapeModule implements RestJHipsterLandscapeElement {

  private final String slug;
  private final String operation;
  private final RestJHipsterModulePropertiesDefinition properties;
  private final Collection<String> dependencies;

  private RestJHipsterLandscapeModule(RestJHipsterLandscapeModuleBuilder builder) {
    slug = builder.slug;
    operation = builder.operation;
    properties = builder.properties;
    dependencies = builder.dependencies;
  }

  static RestJHipsterLandscapeModule fromModule(JHipsterLandscapeModule module) {
    return new RestJHipsterLandscapeModuleBuilder()
      .slug(module.slug().get())
      .operation(module.operation().get())
      .properties(RestJHipsterModulePropertiesDefinition.from(module.propertiesDefinition()))
      .dependencies(buildDependencies(module))
      .build();
  }

  private static List<String> buildDependencies(JHipsterLandscapeModule module) {
    return module
      .dependencies()
      .map(dependencies -> dependencies.stream().map(dependency -> dependency.slug().get()).toList())
      .orElse(null);
  }

  @Override
  @Schema(description = "Type of this landscape element", required = true)
  public JHipsterLandscapeElementType getType() {
    return JHipsterLandscapeElementType.MODULE;
  }

  @Schema(description = "Unique slug of this module", required = true)
  public String getSlug() {
    return slug;
  }

  @Schema(description = "Operation done by this module", required = true)
  public String getOperation() {
    return operation;
  }

  @Schema(description = "Definition of properties for this module", required = true)
  public RestJHipsterModulePropertiesDefinition getProperties() {
    return properties;
  }

  @Schema(description = "Slug of the dependencies for this module")
  public Collection<String> getDependencies() {
    return dependencies;
  }

  private static class RestJHipsterLandscapeModuleBuilder {

    private String slug;
    private String operation;
    private RestJHipsterModulePropertiesDefinition properties;
    private List<String> dependencies;

    public RestJHipsterLandscapeModuleBuilder slug(String slug) {
      this.slug = slug;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder operation(String operation) {
      this.operation = operation;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder properties(RestJHipsterModulePropertiesDefinition properties) {
      this.properties = properties;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder dependencies(List<String> dependencies) {
      this.dependencies = dependencies;

      return this;
    }

    public RestJHipsterLandscapeModule build() {
      return new RestJHipsterLandscapeModule(this);
    }
  }
}
