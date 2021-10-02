package tech.jhipster.forge.generator.springboot.domain.usecase;

import tech.jhipster.forge.common.domain.Project;

public interface SpringBootService {
  void addSpringBoot(Project project);
  void addSpringBootParent(Project project);
  void addSpringBootDependencies(Project project);
  void addSpringBootMavenPlugin(Project project);
  void addMainApp(Project project);
  void addApplicationProperties(Project project);

  void addProperties(Project project, String key, Object value);
}
