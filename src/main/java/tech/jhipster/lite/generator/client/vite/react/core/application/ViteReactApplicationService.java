package tech.jhipster.lite.generator.client.vite.react.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vite.react.core.domain.ViteReactService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class ViteReactApplicationService {

  private final ViteReactService viteReactService;

  public ViteReactApplicationService(ViteReactService viteReactService) {
    this.viteReactService = viteReactService;
  }

  public void init(Project project) {
    viteReactService.init(project);
  }
}
