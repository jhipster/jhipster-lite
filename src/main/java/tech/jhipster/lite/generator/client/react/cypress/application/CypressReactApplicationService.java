package tech.jhipster.lite.generator.client.react.cypress.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.react.cypress.domain.CypressReactService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class CypressReactApplicationService {

  private final CypressReactService CypressReactService;

  public CypressReactApplicationService(CypressReactService CypressReactService) {
    this.CypressReactService = CypressReactService;
  }

  public void init(Project project) {
    CypressReactService.init(project);
  }
}
