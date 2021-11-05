package tech.jhipster.forge.generator.project.infrastructure.secondary;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.project.domain.*;
import tech.jhipster.forge.generator.project.domain.added.DependencyAdded;
import tech.jhipster.forge.generator.project.domain.added.ParentAdded;
import tech.jhipster.forge.generator.project.domain.added.PluginAdded;
import tech.jhipster.forge.generator.project.domain.added.PropertyAdded;

@Component
public class BuildToolProducer implements BuildToolRepository {

  private final ApplicationEventPublisher publisher;

  public BuildToolProducer(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void addParent(Project project, Parent parent) {
    publisher.publishEvent(ParentAdded.of(project, parent));
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    addDependency(project, dependency, List.of());
  }

  @Override
  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    publisher.publishEvent(DependencyAdded.of(project, dependency, exclusions));
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    publisher.publishEvent(PluginAdded.of(project, plugin));
  }

  @Override
  public void addProperty(Project project, String key, String version) {
    publisher.publishEvent(PropertyAdded.of(project, key, version));
  }
}
