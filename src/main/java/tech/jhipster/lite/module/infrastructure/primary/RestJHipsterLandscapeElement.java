package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeElement;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeElementType;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeFeature;
import tech.jhipster.lite.module.domain.resource.JHipsterLandscapeModule;

@Schema(name = "JHipsterLandscapeElement", description = "An element in a landscape, can be either a module or a feature")
sealed interface RestJHipsterLandscapeElement permits RestJHipsterLandscapeModule, RestJHipsterLandscapeFeature {
  JHipsterLandscapeElementType getType();

  @Generated(reason = "Jacococ think there is a missed case here")
  static RestJHipsterLandscapeElement from(JHipsterLandscapeElement element) {
    return switch (element.type()) {
      case MODULE -> RestJHipsterLandscapeModule.fromModule((JHipsterLandscapeModule) element);
      case FEATURE -> RestJHipsterLandscapeFeature.fromFeature((JHipsterLandscapeFeature) element);
    };
  }
}
