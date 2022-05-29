package tech.jhipster.lite.generator.server.springboot.banner.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;
import tech.jhipster.lite.generator.project.domain.Project;

public class BannerModuleProperties {

  private final JHipsterProjectFolder project;

  private BannerModuleProperties(BannerModulePropertiesBuilder builder) {
    this.project = new JHipsterProjectFolder(builder.project);
  }

  public static BannerModuleProperties from(Project project) {
    Assert.notNull("project", project);

    return builder().project(project.getFolder()).build();
  }

  public static BannerModulePropertiesBuilder builder() {
    return new BannerModulePropertiesBuilder();
  }

  public JHipsterProjectFolder project() {
    return project;
  }

  public static class BannerModulePropertiesBuilder {

    private String project;

    public BannerModulePropertiesBuilder project(String project) {
      this.project = project;
      return this;
    }

    public BannerModuleProperties build() {
      return new BannerModuleProperties(this);
    }
  }
}
