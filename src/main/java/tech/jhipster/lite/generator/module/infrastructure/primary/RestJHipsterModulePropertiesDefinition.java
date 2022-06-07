package tech.jhipster.lite.generator.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModulePropertiesDefinition;

@Schema(name = "JHipsterModulePropertiesDefinition", description = "Definition for module properties")
class RestJHipsterModulePropertiesDefinition {

  private final Collection<RestJHipsterModulePropertyDefinition> definitions;

  private RestJHipsterModulePropertiesDefinition(Collection<RestJHipsterModulePropertyDefinition> definitions) {
    this.definitions = definitions;
  }

  static RestJHipsterModulePropertiesDefinition from(JHipsterModulePropertiesDefinition propertiesDefinition) {
    return new RestJHipsterModulePropertiesDefinition(
      propertiesDefinition.get().stream().map(RestJHipsterModulePropertyDefinition::from).toList()
    );
  }

  @Schema(description = "Definitions for properties in this module")
  public Collection<RestJHipsterModulePropertyDefinition> getDefinitions() {
    return definitions;
  }
}
