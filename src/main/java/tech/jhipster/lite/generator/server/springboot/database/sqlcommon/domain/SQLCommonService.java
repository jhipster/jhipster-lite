package tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain;

import java.util.Map;
import tech.jhipster.lite.generator.project.domain.Project;

public interface SQLCommonService {
  void addTestcontainers(Project project, String database, Map<String, Object> properties);
}
