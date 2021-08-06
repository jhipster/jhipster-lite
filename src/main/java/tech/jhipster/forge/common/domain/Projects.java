package tech.jhipster.forge.common.domain;

public interface Projects {
  void create(Project project);
  void add(Project project, String source, String file);
}
