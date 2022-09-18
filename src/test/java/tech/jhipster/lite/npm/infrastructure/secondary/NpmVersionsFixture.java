package tech.jhipster.lite.npm.infrastructure.secondary;

import tech.jhipster.lite.npm.domain.NpmVersions;
import tech.jhipster.lite.projectfile.infrastructure.secondary.FileSystemProjectFilesReader;

public final class NpmVersionsFixture {

  private NpmVersionsFixture() {}

  public static NpmVersions npmVersions(FileSystemProjectFilesReader filesReader) {
    return new FileSystemNpmVersions(filesReader);
  }
}
