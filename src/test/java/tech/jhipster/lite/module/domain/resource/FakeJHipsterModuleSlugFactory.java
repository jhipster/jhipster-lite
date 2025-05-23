package tech.jhipster.lite.module.domain.resource;

record FakeJHipsterModuleSlugFactory(String slug, JHipsterModuleRank rank) implements JHipsterModuleSlugFactory {
  @Override
  public String get() {
    return slug;
  }

  @Override
  public JHipsterModuleRank rank() {
    return rank;
  }
}
