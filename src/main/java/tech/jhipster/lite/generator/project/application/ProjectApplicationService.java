package tech.jhipster.lite.generator.project.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Service
public class ProjectApplicationService {

  private final ProjectRepository projectRepository;

  public ProjectApplicationService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public byte[] download(Project project) {
    return projectRepository.download(project);
  }
}
