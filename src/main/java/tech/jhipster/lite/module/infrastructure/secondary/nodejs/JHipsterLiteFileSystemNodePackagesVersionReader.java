package tech.jhipster.lite.module.infrastructure.secondary.nodejs;

import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.nodejs.JHLiteNodePackagesVersionSource;
import tech.jhipster.lite.module.domain.nodejs.NodePackagesVersions;

@Order
@Repository
public class JHipsterLiteFileSystemNodePackagesVersionReader implements NodePackagesVersionsReader {

  private final FileSystemNodePackagesVersionReader reader;

  public JHipsterLiteFileSystemNodePackagesVersionReader(ProjectFiles projectFiles) {
    reader = new FileSystemNodePackagesVersionReader(
      projectFiles,
      List.of(JHLiteNodePackagesVersionSource.values()),
      "/generator/dependencies/"
    );
  }

  @Override
  public NodePackagesVersions get() {
    return reader.get();
  }
}
