package tech.jhipster.lite.project.domain;

import java.util.Optional;

public interface ProjectsRepository {
  Optional<Project> get(ProjectPath path);
}
