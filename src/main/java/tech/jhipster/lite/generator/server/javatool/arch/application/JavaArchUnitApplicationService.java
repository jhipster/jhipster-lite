package tech.jhipster.lite.generator.server.javatool.arch.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.javatool.arch.domain.JavaArchUnitService;

@Service
public class JavaArchUnitApplicationService {

  private final JavaArchUnitService javaArchUnitService;

  public JavaArchUnitApplicationService(JavaArchUnitService javaArchUnitService) {
    this.javaArchUnitService = javaArchUnitService;
  }

  public void init(Project project) {
    javaArchUnitService.init(project);
  }
}
