package tech.jhipster.lite.generator.module.infrastructure.primary;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleFactory;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleSlug;

public class JHipsterModuleResource {

  private final String legacyUrl;
  private final JHipsterModuleSlug slug;
  private final JHipsterModuleApiDoc apiDoc;
  private final JHipsterModuleFactory factory;

  private JHipsterModuleResource(JHipsterModuleResourceBuilder builder) {
    assertMandatoryFields(builder);

    legacyUrl = builder.legacyUrl;
    slug = new JHipsterModuleSlug(builder.slug);
    apiDoc = builder.apiDoc;
    factory = builder.factory;
  }

  private void assertMandatoryFields(JHipsterModuleResourceBuilder builder) {
    Assert.notBlank("legacyUrl", builder.legacyUrl);
    Assert.notNull("apiDoc", builder.apiDoc);
    Assert.notNull("factory", builder.factory);
  }

  public static JHipsterModuleResourceLegacyUrlBuilder builder() {
    return new JHipsterModuleResourceBuilder();
  }

  public String legacyUrl() {
    return legacyUrl;
  }

  JHipsterModuleSlug slug() {
    return slug;
  }

  public JHipsterModuleApiDoc apiDoc() {
    return apiDoc;
  }

  public JHipsterModuleFactory factory() {
    return factory;
  }

  public static class JHipsterModuleResourceBuilder
    implements
      JHipsterModuleResourceLegacyUrlBuilder,
      JHipsterModuleResourceSlugBuilder,
      JHipsterModuleResourceApiDocBuilder,
      JHipsterModuleResourceFactoryBuilder {

    private String legacyUrl;
    private String slug;
    private JHipsterModuleApiDoc apiDoc;
    private JHipsterModuleFactory factory;

    private JHipsterModuleResourceBuilder() {}

    @Override
    public JHipsterModuleResourceSlugBuilder legacyUrl(String legacyUrl) {
      this.legacyUrl = legacyUrl;

      return this;
    }

    @Override
    public JHipsterModuleResourceApiDocBuilder slug(String slug) {
      this.slug = slug;

      return this;
    }

    @Override
    public JHipsterModuleResourceFactoryBuilder apiDoc(JHipsterModuleApiDoc apiDoc) {
      this.apiDoc = apiDoc;

      return this;
    }

    @Override
    public JHipsterModuleResource factory(JHipsterModuleFactory factory) {
      this.factory = factory;

      return new JHipsterModuleResource(this);
    }
  }

  public interface JHipsterModuleResourceLegacyUrlBuilder {
    JHipsterModuleResourceSlugBuilder legacyUrl(String legacyUrl);
  }

  public interface JHipsterModuleResourceSlugBuilder {
    JHipsterModuleResourceApiDocBuilder slug(String slug);
  }

  public interface JHipsterModuleResourceApiDocBuilder {
    JHipsterModuleResourceFactoryBuilder apiDoc(JHipsterModuleApiDoc apiDoc);
  }

  public interface JHipsterModuleResourceFactoryBuilder {
    JHipsterModuleResource factory(JHipsterModuleFactory factory);
  }
}
