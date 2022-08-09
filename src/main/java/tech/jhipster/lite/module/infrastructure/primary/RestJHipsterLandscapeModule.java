package tech.jhipster.lite.module.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeElementType;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeModule;

@JsonPropertyOrder({ "type", "slug", "operation", "dependencies" })
@Schema(name = "JHipsterLandscapeModule", description = "Module in a landscape")
final class RestJHipsterLandscapeModule implements RestJHipsterLandscapeElement {

  private final String slug;
  private final String operation;
  private final Collection<String> dependencies;

  private RestJHipsterLandscapeModule(String slug, String operation, Collection<String> dependencies) {
    this.slug = slug;
    this.operation = operation;
    this.dependencies = dependencies;
  }

  static RestJHipsterLandscapeModule fromModule(JHipsterLandscapeModule module) {
    return new RestJHipsterLandscapeModule(module.slug().get(), module.operation().get(), buildDependencies(module));
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

  @Schema(description = "Slug of the dependencies for this module")
  public Collection<String> getDependencies() {
    return dependencies;
  }
}
