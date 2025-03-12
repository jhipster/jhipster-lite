package tech.jhipster.lite.module.domain.resource;

import tech.jhipster.lite.module.domain.JHipsterModuleSlug;

public interface JHipsterModuleSlugFactory {
  String get();

  JHipsterModuleRank rank();

  default JHipsterModuleSlug build() {
    return new JHipsterModuleSlug(get());
  }
}
