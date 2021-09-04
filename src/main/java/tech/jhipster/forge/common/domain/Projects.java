package tech.jhipster.forge.common.domain;

public interface Projects {
  void create(Project project);

  void add(Project project, String source, String sourceFilename);
  void add(Project project, String source, String sourceFilename, String destination);
  void add(Project project, String source, String sourceFilename, String destination, String destinationFilename);

  void template(Project project, String source, String sourceFilename);
  void template(Project project, String source, String sourceFilename, String destination);
  void template(Project project, String source, String sourceFilename, String destination, String destinationFilename);
}
