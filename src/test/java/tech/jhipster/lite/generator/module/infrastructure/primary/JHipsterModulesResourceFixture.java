package tech.jhipster.lite.generator.module.infrastructure.primary;

import static org.mockito.Mockito.*;

import java.util.List;
import tech.jhipster.lite.JsonHelper;
import tech.jhipster.lite.generator.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleFactory;

final class JHipsterModulesResourceFixture {

  private JHipsterModulesResourceFixture() {}

  static JHipsterModulesHandlerMapping jhipsterModuleMapping(JHipsterModuleResource... modules) {
    return new JHipsterModulesHandlerMapping(JsonHelper.jsonMapper(), List.of(modules), mock(JHipsterModulesApplicationService.class));
  }

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
        .apiDoc(new JHipsterModuleApiDoc("tag", operation))
        .factory(factory);
    }
  }
}
