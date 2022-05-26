package tech.jhipster.lite.generator.server.javatool.base.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.Indentation;
import tech.jhipster.lite.generator.module.domain.JHipsterBasePackage;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectBaseName;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;
import tech.jhipster.lite.generator.project.domain.Project;

public class JavaBaseModuleProperties {

  private final JHipsterProjectFolder project;
  private final Indentation indentation;
  private final JHipsterBasePackage basePackage;
  private final JHipsterProjectBaseName projectBaseName;

  private JavaBaseModuleProperties(JavaBaseModulePropertiesBuilder builder) {
    project = new JHipsterProjectFolder(builder.project);
    indentation = Indentation.from(builder.indentation);
    basePackage = new JHipsterBasePackage(builder.basePackage);
    projectBaseName = new JHipsterProjectBaseName(builder.projectBaseName);
  }

  public static JavaBaseModuleProperties from(Project project) {
    Assert.notNull("project", project);

    return builder()
      .project(project.getFolder())
      .indentation(project.getIntegerConfig("prettierDefaultIndent").orElse(null))
      .basePackage(project.getPackageNamePath().orElse(null))
      .projectBaseName(project.getBaseName().orElse(null))
      .build();
  }

  public static JavaBaseModulePropertiesBuilder builder() {
    return new JavaBaseModulePropertiesBuilder();
  }

  public JHipsterProjectFolder project() {
    return project;
  }

  public Indentation indentation() {
    return indentation;
  }

  public JHipsterBasePackage basePackage() {
    return basePackage;
  }

  public JHipsterProjectBaseName projectBaseName() {
    return projectBaseName;
  }

  public static class JavaBaseModulePropertiesBuilder {

    private String project;
    private Integer indentation;
    private String basePackage;
    private String projectBaseName;

    public JavaBaseModulePropertiesBuilder project(String project) {
      this.project = project;

      return this;
    }

    public JavaBaseModulePropertiesBuilder indentation(Integer indentation) {
      this.indentation = indentation;

      return this;
    }

    public JavaBaseModulePropertiesBuilder basePackage(String basePackage) {
      this.basePackage = basePackage;

      return this;
    }

    public JavaBaseModulePropertiesBuilder projectBaseName(String projectBaseName) {
      this.projectBaseName = projectBaseName;

      return this;
    }

    public JavaBaseModuleProperties build() {
      return new JavaBaseModuleProperties(this);
    }
  }
}
