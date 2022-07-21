package tech.jhipster.lite.project.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.project.domain.Project;
import tech.jhipster.lite.project.domain.ProjectPath;
import tech.jhipster.lite.project.domain.ProjectsDownloader;
import tech.jhipster.lite.project.domain.ProjectsRepository;
import tech.jhipster.lite.projectfolder.domain.ProjectFolder;

@Service
public class ProjectsApplicationService {

  private final ProjectsDownloader downloader;

  public ProjectsApplicationService(ProjectFolder projectFolder, ProjectsRepository projects) {
    downloader = new ProjectsDownloader(projectFolder, projects);
  }

  public Project get(ProjectPath path) {
    return downloader.download(path);
  }
}
