package tech.jhipster.lite.generator.history.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public class GeneratorHistoryDomainService implements GeneratorHistoryService {

  private final GeneratorHistoryRepository generatorHistoryRepository;

  public GeneratorHistoryDomainService(GeneratorHistoryRepository generatorHistoryRepository) {
    this.generatorHistoryRepository = generatorHistoryRepository;
  }

  @Override
  public GeneratorHistoryData getHistoryData(Project project) {
    return generatorHistoryRepository.getHistoryData(project);
  }

  @Override
  public void addHistoryValue(Project project, GeneratorHistoryValue generatorHistoryValue) {
    generatorHistoryRepository.addHistoryValue(project, generatorHistoryValue);
  }
}
