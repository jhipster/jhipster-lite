package tech.jhipster.lite.module.domain.packagejson;

import tech.jhipster.lite.module.domain.npm.NpmVersionSource;
import tech.jhipster.lite.module.domain.npm.NpmVersionSourceFactory;

@Deprecated(forRemoval = true)
public enum VersionSource implements NpmVersionSourceFactory {
  COMMON,
  ANGULAR,
  REACT,
  SVELTE,
  VUE;

  @Override
  public NpmVersionSource build() {
    return new NpmVersionSource(name().toLowerCase());
  }
}
