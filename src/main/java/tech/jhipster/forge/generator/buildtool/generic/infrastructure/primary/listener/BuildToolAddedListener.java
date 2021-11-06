package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.BuildToolAdded;

@Component
public class BuildToolAddedListener implements ApplicationListener<PayloadApplicationEvent<BuildToolAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public BuildToolAddedListener(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<BuildToolAdded> event) {
    BuildToolAdded buildToolAdded = event.getPayload();
    buildToolApplicationService.init(buildToolAdded.project());
  }
}
