package tech.jhipster.forge.common.domain;

import tech.jhipster.forge.error.domain.Assert;

public class Project {

  private final String path;

  private Project(ProjectBuilder builder) {
    Assert.notBlank("path", builder.path);

    this.path = builder.path;
  }

  public static ProjectBuilder builder() {
    return new ProjectBuilder();
  }

  public String getPath() {
    return path;
  }

  public static class ProjectBuilder {

    private String path;

    public Project build() {
      return new Project(this);
    }

    public ProjectBuilder path(String path) {
      this.path = path;
      return this;
    }
  }
}
