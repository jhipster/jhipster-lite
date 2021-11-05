package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.consumer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.PropertyAdded;

@Component
public class PropertyAddedConsumer implements ApplicationListener<PayloadApplicationEvent<PropertyAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public PropertyAddedConsumer(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<PropertyAdded> event) {
    PropertyAdded propertyAdded = event.getPayload();
    buildToolApplicationService.addProperty(propertyAdded.project(), propertyAdded.key(), propertyAdded.version());
  }
}
