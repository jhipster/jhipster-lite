package tech.jhipster.lite.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.lite.common.domain.OptionalUtils;
import tech.jhipster.lite.error.domain.Assert;

public class Repository {

  private final String id;
  private final String url;
  private final Optional<String> name;
  private final Optional<String> additionalElements;

  private Repository(Repository.RepositoryBuilder builder) {
    Assert.notBlank("id", builder.id);
    Assert.notBlank("url", builder.url);

    this.id = builder.id;
    this.url = builder.url;
    this.name = OptionalUtils.notBlank(builder.name);
    this.additionalElements = OptionalUtils.notBlank(builder.additionalElements);
  }

  public static Repository.RepositoryBuilder builder() {
    return new Repository.RepositoryBuilder();
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public Optional<String> getName() {
    return name;
  }

  public Optional<String> getAdditionalElements() {
    return additionalElements;
  }

  public static class RepositoryBuilder {

    private String id;
    private String url;
    private String name;
    private String additionalElements;

    public Repository.RepositoryBuilder id(String id) {
      this.id = id;
      return this;
    }

    public Repository.RepositoryBuilder url(String url) {
      this.url = url;
      return this;
    }

    public Repository.RepositoryBuilder name(String name) {
      this.name = name;
      return this;
    }

    public Repository.RepositoryBuilder additionalElements(String additionalElements) {
      this.additionalElements = additionalElements;
      return this;
    }

    public Repository build() {
      return new Repository(this);
    }
  }
}
