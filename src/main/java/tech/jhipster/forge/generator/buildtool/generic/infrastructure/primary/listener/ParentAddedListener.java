package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.ParentAdded;

@Component
public class ParentAddedListener implements ApplicationListener<PayloadApplicationEvent<ParentAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public ParentAddedListener(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<ParentAdded> event) {
    ParentAdded parentAdded = event.getPayload();
    buildToolApplicationService.addParent(parentAdded.project(), parentAdded.parent());
  }
}
