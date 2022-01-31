package tech.jhipster.lite.generator.history.application;

import org.springframework.stereotype.Service;
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
    generatorHistoryService.addHistoryValue(project, generatorHistoryValue);
  }
}
