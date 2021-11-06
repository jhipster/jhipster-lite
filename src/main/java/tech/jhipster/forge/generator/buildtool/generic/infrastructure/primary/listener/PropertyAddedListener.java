package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.PropertyAdded;

@Component
public class PropertyAddedListener implements ApplicationListener<PayloadApplicationEvent<PropertyAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public PropertyAddedListener(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<PropertyAdded> event) {
    PropertyAdded propertyAdded = event.getPayload();
    buildToolApplicationService.addProperty(propertyAdded.project(), propertyAdded.key(), propertyAdded.version());
  }
}
