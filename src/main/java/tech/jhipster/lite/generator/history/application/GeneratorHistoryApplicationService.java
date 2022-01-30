package tech.jhipster.lite.generator.history.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryService;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryValue;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class GeneratorHistoryApplicationService {

  private final GeneratorHistoryService generatorHistoryService;

  public GeneratorHistoryApplicationService(GeneratorHistoryService generatorHistoryService) {
    this.generatorHistoryService = generatorHistoryService;
  }

  public void addHistoryValue(Project project, GeneratorHistoryValue generatorHistoryValue) {
    try {
      generatorHistoryService.addHistoryValue(project, generatorHistoryValue);
    } catch (JsonProcessingException e) {
      throw new GeneratorException("Cannot add history value " + generatorHistoryValue + " : " + e.getMessage());
    }
  }
}
