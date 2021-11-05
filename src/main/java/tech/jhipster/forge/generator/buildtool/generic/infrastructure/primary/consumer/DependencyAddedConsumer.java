package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.consumer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.DependencyAdded;

@Component
public class DependencyAddedConsumer implements ApplicationListener<PayloadApplicationEvent<DependencyAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public DependencyAddedConsumer(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<DependencyAdded> event) {
    DependencyAdded dependencyAdded = event.getPayload();
    buildToolApplicationService.addDependency(dependencyAdded.project(), dependencyAdded.dependency(), dependencyAdded.exclusions());
  }
}
