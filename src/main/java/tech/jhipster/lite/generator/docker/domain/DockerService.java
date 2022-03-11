package tech.jhipster.lite.generator.docker.domain;

import java.util.Optional;

public interface DockerService {
  Optional<String> getVersion(String imageName);
}
