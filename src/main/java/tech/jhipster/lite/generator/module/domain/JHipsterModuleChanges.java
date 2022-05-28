package tech.jhipster.lite.generator.module.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.javadependency.command.JavaDependenciesCommands;
import tech.jhipster.lite.generator.module.domain.postaction.JHipsterModulePostActions;
import tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements;

public class JHipsterModuleChanges {

  private final JHipsterProjectFolder projectFolder;
  private final Indentation indentation;
  private final TemplatedFiles files;
  private final JHipsterModuleReplacements replacements;
  private final JavaDependenciesCommands javaDependencies;
  private final JHipsterModulePreActions preActions;
  private final JHipsterModulePostActions postActions;

  private JHipsterModuleChanges(JHipsterModuleChangesBuilder builder) {
    assertMandatoryFields(builder);

    projectFolder = builder.projectFolder;
    indentation = builder.indentation;
    files = builder.files;
    replacements = builder.replacements;
    javaDependencies = builder.javaDependencies;
    preActions = builder.preActions;
    postActions = builder.postActions;
  }

  private void assertMandatoryFields(JHipsterModuleChangesBuilder builder) {
    Assert.notNull("projectFolder", builder.projectFolder);
    Assert.notNull("indentation", builder.indentation);
    Assert.notNull("files", builder.files);
    Assert.notNull("replacements", builder.replacements);
    Assert.notNull("javaDependencies", builder.javaDependencies);
    Assert.notNull("preActions", builder.preActions);
    Assert.notNull("postActions", builder.postActions);
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

  public JHipsterModuleReplacements replacements() {
    return replacements;
  }

  public JavaDependenciesCommands javaDependenciesCommands() {
    return javaDependencies;
  }

  public JHipsterModulePreActions preActions() {
    return preActions;
  }

  public JHipsterModulePostActions postActions() {
    return postActions;
  }

  public static class JHipsterModuleChangesBuilder
    implements
      JHipsterModuleChangesProjectFolderBuilder,
      JHipsterModuleChangesIndentationBuilder,
      JHipsterModuleChangesFilesBuilder,
      JHipsterModuleChangesReplacementsBuilder,
      JHipsterModuleChangesJavaDependenciesBuilder,
      JHipsterModuleChangesPreActionsBuilder,
      JHipsterModuleChangesPostActionsBuilder {

    private JHipsterProjectFolder projectFolder;
    private TemplatedFiles files;
    private JHipsterModuleReplacements replacements;
    private JavaDependenciesCommands javaDependencies;
    private Indentation indentation;
    private JHipsterModulePreActions preActions;
    private JHipsterModulePostActions postActions;

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
    public JHipsterModuleChangesReplacementsBuilder files(TemplatedFiles files) {
      this.files = files;

      return this;
    }

    @Override
    public JHipsterModuleChangesJavaDependenciesBuilder replacements(JHipsterModuleReplacements replacements) {
      this.replacements = replacements;

      return this;
    }

    @Override
    public JHipsterModuleChangesPreActionsBuilder javaDependencies(JavaDependenciesCommands javaDependencies) {
      this.javaDependencies = javaDependencies;

      return this;
    }

    @Override
    public JHipsterModuleChangesPostActionsBuilder preActions(JHipsterModulePreActions preActions) {
      this.preActions = preActions;

      return this;
    }

    @Override
    public JHipsterModuleChanges postActions(JHipsterModulePostActions postActions) {
      this.postActions = postActions;

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
    JHipsterModuleChangesReplacementsBuilder files(TemplatedFiles files);
  }

  public interface JHipsterModuleChangesReplacementsBuilder {
    JHipsterModuleChangesJavaDependenciesBuilder replacements(JHipsterModuleReplacements replacements);
  }

  public interface JHipsterModuleChangesJavaDependenciesBuilder {
    JHipsterModuleChangesPreActionsBuilder javaDependencies(JavaDependenciesCommands buildDependenciesChanges);
  }

  public interface JHipsterModuleChangesPreActionsBuilder {
    JHipsterModuleChangesPostActionsBuilder preActions(JHipsterModulePreActions preActions);
  }

  public interface JHipsterModuleChangesPostActionsBuilder {
    JHipsterModuleChanges postActions(JHipsterModulePostActions postActions);
  }
}
