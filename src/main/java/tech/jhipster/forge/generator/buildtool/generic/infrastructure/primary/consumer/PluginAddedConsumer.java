package tech.jhipster.forge.generator.buildtool.generic.infrastructure.primary.consumer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.application.BuildToolApplicationService;
import tech.jhipster.forge.generator.project.domain.added.PluginAdded;

@Component
public class PluginAddedConsumer implements ApplicationListener<PayloadApplicationEvent<PluginAdded>> {

  private final BuildToolApplicationService buildToolApplicationService;

  public PluginAddedConsumer(BuildToolApplicationService buildToolApplicationService) {
    this.buildToolApplicationService = buildToolApplicationService;
  }

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<PluginAdded> event) {
    PluginAdded pluginAdded = event.getPayload();
    buildToolApplicationService.addPlugin(pluginAdded.project(), pluginAdded.plugin());
  }
}
