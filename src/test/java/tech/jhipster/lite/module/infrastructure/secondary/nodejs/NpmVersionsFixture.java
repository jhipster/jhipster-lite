package tech.jhipster.lite.module.infrastructure.secondary.nodejs;

import java.util.Collection;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class NpmVersionsFixture {

  private NpmVersionsFixture() {}

  public static NodeVersions npmVersions(ProjectFiles filesReader, Collection<NodePackagesVersionsReader> customReaders) {
    Assert.notNull("customReaders", customReaders);

    return new JHipsterNodeVersions(
      Stream.concat(customReaders.stream(), Stream.of(new JHipsterLiteFileSystemNodePackagesVersionReader(filesReader))).toList()
    );
  }
}
