package tech.jhipster.lite.module.domain;

public interface JHipsterProjectFolderFactory {
  boolean isValid(String folderPath);

  String generatePath();
}
