package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.domain.MongockService;

@Service
public class MongockApplicationService {

  private final MongockService mongockService;

  public MongockApplicationService(MongockService mongockService) {
    this.mongockService = mongockService;
  }

  public void init(Project project) {
    mongockService.init(project);
  }
}
