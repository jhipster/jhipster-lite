package tech.jhipster.lite.generator.history.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryRepository;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryValue;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class GeneratorHistoryApplicationService {

  private final GeneratorHistoryRepository generatorHistoryRepository;

  public GeneratorHistoryApplicationService(GeneratorHistoryRepository generatorHistoryRepository) {
    this.generatorHistoryRepository = generatorHistoryRepository;
  }

  public void addHistoryValue(Project project, GeneratorHistoryValue generatorHistoryValue) {
    generatorHistoryRepository.addHistoryValue(project, generatorHistoryValue);
  }
}
