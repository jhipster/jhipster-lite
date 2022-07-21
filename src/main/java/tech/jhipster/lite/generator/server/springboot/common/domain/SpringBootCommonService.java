package tech.jhipster.lite.generator.server.springboot.common.domain;

import java.util.Optional;
import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootCommonService {
  Optional<String> getProperty(Project project, String key);

  boolean isSetWithMySQLOrMariaDBDatabase(Project project);

  boolean isDatabaseUseSequences(Project project);
}
