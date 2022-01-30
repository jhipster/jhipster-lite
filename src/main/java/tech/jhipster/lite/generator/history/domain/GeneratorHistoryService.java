package tech.jhipster.lite.generator.history.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import tech.jhipster.lite.generator.project.domain.Project;

public interface GeneratorHistoryService {
  void createHistoryFile(Project project);

  GeneratorHistoryData getHistoryData(Project project);

  void addHistoryValue(Project project, GeneratorHistoryValue generatorHistoryValue) throws JsonProcessingException;
}
