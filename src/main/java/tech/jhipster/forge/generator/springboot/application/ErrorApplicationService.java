package tech.jhipster.forge.generator.springboot.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.usecase.ErrorService;

@Service
public class ErrorApplicationService {

  private final ErrorService errorService;

  public ErrorApplicationService(ErrorService errorService) {
    this.errorService = errorService;
  }

  public void init(Project project) {
    errorService.init(project);
  }
}
