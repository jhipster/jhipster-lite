package tech.jhipster.lite.generator.history.infrastructure.primary;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.history.application.GeneratorHistoryApplicationService;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryValue;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@Aspect
@Configuration
public class GeneratorHistoryInterceptor {

  private final GeneratorHistoryApplicationService generatorHistoryApplicationService;

  public GeneratorHistoryInterceptor(GeneratorHistoryApplicationService generatorHistoryApplicationService) {
    this.generatorHistoryApplicationService = generatorHistoryApplicationService;
  }

  @AfterReturning(value = "@annotation(generatorStep)")
  public void addInHistory(JoinPoint joinPoint, GeneratorStep generatorStep) {
    String serviceId = generatorStep.id();
    ProjectDTO projectDTO = (ProjectDTO) joinPoint.getArgs()[0];
    Project project = ProjectDTO.toProject(projectDTO);
    GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue(serviceId);
    generatorHistoryApplicationService.addHistoryValue(project, generatorHistoryValue);
  }
}
