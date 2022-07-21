package tech.jhipster.lite.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.lite.generator.project.domain.Project;

public interface BuildToolService {
  Optional<String> getVersion(Project project, String name);

  Optional<String> getGroup(Project project);

  Optional<String> getName(Project project);
}
