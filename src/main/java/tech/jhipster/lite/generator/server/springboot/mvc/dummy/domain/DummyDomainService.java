package tech.jhipster.lite.generator.server.springboot.mvc.dummy.domain;

import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class DummyDomainService implements DummyService {

  public static final String SOURCE = "server/springboot/mvc/dummy";
  public static final String PATCH = "dummy.patch";

  private final ProjectRepository projectRepository;

  public DummyDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void applyDummyGitPatch(Project project) {
    projectRepository.gitInit(project);
    projectRepository.add(project, SOURCE, PATCH, ".jhipster");
    projectRepository.gitApplyPatch(project, FileUtils.getPath(project.getFolder(), ".jhipster", PATCH));
  }
}
