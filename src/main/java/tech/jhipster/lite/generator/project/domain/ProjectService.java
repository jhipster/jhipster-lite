package tech.jhipster.lite.generator.project.domain;

import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

public interface ProjectService {
  ProjectDTO getProjectDetails(String folderPath);
}
