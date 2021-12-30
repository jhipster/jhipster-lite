package tech.jhipster.lite.generator.server.springboot.logstash.tcp.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface LogstashService {
  void init(Project project);

  void addDependencies(Project project);
  void addJavaFiles(Project project);
  void addProperties(Project project);
}
