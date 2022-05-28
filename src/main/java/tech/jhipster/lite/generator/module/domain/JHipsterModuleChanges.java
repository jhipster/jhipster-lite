package tech.jhipster.lite.generator.module.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.javadependency.command.JavaDependenciesCommands;

public class JHipsterModuleChanges {

  private final JHipsterProjectFolder projectFolder;
  private final Indentation indentation;
  private final TemplatedFiles files;
  private final JavaDependenciesCommands javaDependencies;

  private JHipsterModuleChanges(JHipsterModuleChangesBuilder builder) {
    assertMandatoryFields(builder);

    projectFolder = builder.projectFolder;
    indentation = builder.indentation;
    files = builder.files;
    javaDependencies = builder.javaDependencies;
  }

  private void assertMandatoryFields(JHipsterModuleChangesBuilder builder) {
    Assert.notNull("projectFolder", builder.projectFolder);
    Assert.notNull("indentation", builder.indentation);
    Assert.notNull("files", builder.files);
    Assert.notNull("javaDependencies", builder.javaDependencies);
  }

  public static JHipsterModuleChangesProjectFolderBuilder builder() {
    return new JHipsterModuleChangesBuilder();
  }

  public JHipsterProjectFolder projectFolder() {
    return projectFolder;
  }

  public Indentation indentation() {
    return indentation;
  }

  public TemplatedFiles files() {
    return files;
  }

  public JavaDependenciesCommands javaDependenciesCommands() {
    return javaDependencies;
  }

  public static class JHipsterModuleChangesBuilder
    implements
      JHipsterModuleChangesProjectFolderBuilder,
      JHipsterModuleChangesIndentationBuilder,
      JHipsterModuleChangesFilesBuilder,
      JHipsterModuleChangesJavaDependenciesBuilder {

    private JHipsterProjectFolder projectFolder;
    private TemplatedFiles files;
    private JavaDependenciesCommands javaDependencies;
    private Indentation indentation;

    private JHipsterModuleChangesBuilder() {}

    @Override
    public JHipsterModuleChangesIndentationBuilder projectFolder(JHipsterProjectFolder projectFolder) {
      this.projectFolder = projectFolder;

      return this;
    }

    @Override
    public JHipsterModuleChangesFilesBuilder indentation(Indentation indentation) {
      this.indentation = indentation;

      return this;
    }

    @Override
    public JHipsterModuleChangesJavaDependenciesBuilder files(TemplatedFiles files) {
      this.files = files;

      return this;
    }

    @Override
    public JHipsterModuleChanges javaDependencies(JavaDependenciesCommands javaDependencies) {
      this.javaDependencies = javaDependencies;

      return new JHipsterModuleChanges(this);
    }
  }

  public interface JHipsterModuleChangesProjectFolderBuilder {
    JHipsterModuleChangesIndentationBuilder projectFolder(JHipsterProjectFolder projectFolder);
  }

  public interface JHipsterModuleChangesIndentationBuilder {
    JHipsterModuleChangesFilesBuilder indentation(Indentation indentation);
  }

  public interface JHipsterModuleChangesFilesBuilder {
    JHipsterModuleChangesJavaDependenciesBuilder files(TemplatedFiles files);
  }

  public interface JHipsterModuleChangesJavaDependenciesBuilder {
    JHipsterModuleChanges javaDependencies(JavaDependenciesCommands buildDependenciesChanges);
  }
}
