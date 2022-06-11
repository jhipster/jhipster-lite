package tech.jhipster.lite.generator.module.infrastructure.primary;

import tech.jhipster.lite.generator.module.domain.JHipsterModuleFactory;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture;

final class JHipsterModulesResourceFixture {

  private JHipsterModulesResourceFixture() {}

  static JHipsterModuleResource defaultModuleResource() {
    return defaultModuleResourceBuilder().build();
  }

  static JHipsterTestModuleResourceBuilder defaultModuleResourceBuilder() {
    return new JHipsterTestModuleResourceBuilder().legacyUrl("/api/legacy").slug("slug").operation("operation").factory(properties -> null);
  }

  static class JHipsterTestModuleResourceBuilder {

    private String legacyUrl;
    private String slug;
    private String operation;
    private JHipsterModuleFactory factory;

    private JHipsterTestModuleResourceBuilder() {}

    public JHipsterTestModuleResourceBuilder legacyUrl(String legacyUrl) {
      this.legacyUrl = legacyUrl;

      return this;
    }

    public JHipsterTestModuleResourceBuilder slug(String slug) {
      this.slug = slug;

      return this;
    }

    public JHipsterTestModuleResourceBuilder operation(String operation) {
      this.operation = operation;

      return this;
    }

    public JHipsterTestModuleResourceBuilder factory(JHipsterModuleFactory factory) {
      this.factory = factory;

      return this;
    }

    public JHipsterModuleResource build() {
      return JHipsterModuleResource
        .builder()
        .legacyUrl(legacyUrl)
        .slug(slug)
        .propertiesDefinition(JHipsterModulesFixture.propertiesDefinition())
        .apiDoc(new JHipsterModuleApiDoc("tag", operation))
        .factory(factory);
    }
  }
}
