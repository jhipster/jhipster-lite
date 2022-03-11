package tech.jhipster.lite.generator.docker.domain;

import java.util.Optional;

public interface DockerService {
  Optional<String> getImageNameWithVersion(String imageName);

  Optional<String> getImageVersion(String imageName);
}
