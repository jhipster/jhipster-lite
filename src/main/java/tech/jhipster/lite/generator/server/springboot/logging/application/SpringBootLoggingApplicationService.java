package tech.jhipster.lite.generator.server.springboot.logging.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;

@Service
public class SpringBootLoggingApplicationService {

  private final SpringBootLoggingService springBootLoggingService;

  public SpringBootLoggingApplicationService(SpringBootLoggingService springBootLoggingService) {
    this.springBootLoggingService = springBootLoggingService;
  }

  public void addLog(Project project, String packageName, String level) {
    springBootLoggingService.addLogger(project, packageName, level);
  }

  public void addLogTest(Project project, String packageName, String level) {
    springBootLoggingService.addLoggerTest(project, packageName, level);
  }
}
