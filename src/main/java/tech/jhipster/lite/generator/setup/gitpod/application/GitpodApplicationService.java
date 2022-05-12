package tech.jhipster.lite.generator.setup.gitpod.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.setup.gitpod.domain.GitpodService;

@Service
public class GitpodApplicationService {

  private final GitpodService gitpodService;

  public GitpodApplicationService(GitpodService gitpodService) {
    this.gitpodService = gitpodService;
  }

  public void init(Project project) {
    gitpodService.init(project);
  }
}
