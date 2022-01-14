package tech.jhipster.lite.generator.server.springboot.mvc.dummy.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.domain.DummyService;

@Service
public class DummyApplicationService {

  private final DummyService dummyService;

  public DummyApplicationService(DummyService dummyService) {
    this.dummyService = dummyService;
  }

  public void applyDummyGitPatch(Project project) {
    dummyService.applyDummyGitPatch(project);
  }
}
