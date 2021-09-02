package tech.jhipster.forge.common.domain;

public interface Projects {
  void create(Project project);

  void add(Project project, String source, String filename);
  void add(Project project, String source, String filename, String destination);
  void add(Project project, String source, String filename, String destination, String destinationFilename);

  void template(Project project, String source, String file);
}
