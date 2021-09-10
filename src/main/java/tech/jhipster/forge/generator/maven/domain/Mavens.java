package tech.jhipster.forge.generator.maven.domain;

import tech.jhipster.forge.common.domain.Project;

public interface Mavens {
  void initPomXml(Project project);
  void addMavenWrapper(Project project);
}
