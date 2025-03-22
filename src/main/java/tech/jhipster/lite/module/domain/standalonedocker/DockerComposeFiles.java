package tech.jhipster.lite.module.domain.standalonedocker;

import java.util.Collection;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;
import tech.jhipster.lite.shared.error.domain.Assert;

public record DockerComposeFiles(Collection<DockerComposeFile> files) {
  public DockerComposeFiles {
    Assert.field("files", files).notNull().noNullElement();
  }

  public Collection<DockerComposeFile> get() {
    return files();
  }
}
