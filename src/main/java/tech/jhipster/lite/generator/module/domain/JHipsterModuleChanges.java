package tech.jhipster.lite.generator.module.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.javadependency.command.JavaDependenciesCommands;
import tech.jhipster.lite.generator.module.domain.javaproperties.SpringProperties;
import tech.jhipster.lite.generator.module.domain.postaction.JHipsterModulePostActions;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements;

public class JHipsterModuleChanges {

  private final JHipsterProjectFolder projectFolder;
  private final Indentation indentation;
  private final TemplatedFiles files;
  private final JHipsterModuleReplacements replacements;
  private final JavaDependenciesCommands javaDependencies;
  private final JHipsterModulePreActions preActions;
  private final JHipsterModulePostActions postActions;
  private final SpringProperties springProperties;

  private JHipsterModuleChanges(JHipsterModuleChangesBuilder builder) {
    assertMandatoryFields(builder);

    projectFolder = builder.projectFolder;
    indentation = builder.indentation;
    files = builder.files;
    replacements = builder.replacements;
    javaDependencies = builder.javaDependencies;
    preActions = builder.preActions;
    postActions = builder.postActions;
    springProperties = builder.springProperties;
  }

  private void assertMandatoryFields(JHipsterModuleChangesBuilder builder) {
    Assert.notNull("projectFolder", builder.projectFolder);
    Assert.notNull("indentation", builder.indentation);
    Assert.notNull("files", builder.files);
    Assert.notNull("replacements", builder.replacements);
    Assert.notNull("javaDependencies", builder.javaDependencies);
    Assert.notNull("preActions", builder.preActions);
    Assert.notNull("postActions", builder.postActions);
    Assert.notNull("springProperties", builder.springProperties);
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

  public SpringProperties springProperties() {
    return springProperties;
  }

  public static class JHipsterModuleChangesBuilder
    implements
      JHipsterModuleChangesProjectFolderBuilder,
      JHipsterModuleChangesIndentationBuilder,
      JHipsterModuleChangesFilesBuilder,
      JHipsterModuleChangesReplacementsBuilder,
      JHipsterModuleChangesJavaDependenciesBuilder,
      JHipsterModuleChangesPreActionsBuilder,
      JHipsterModuleChangesPostActionsBuilder,
      JHipsterModuleChangesSpringPropertiesBuilder {

    private JHipsterProjectFolder projectFolder;
    private TemplatedFiles files;
    private JHipsterModuleReplacements replacements;
    private JavaDependenciesCommands javaDependencies;
    private Indentation indentation;
    private JHipsterModulePreActions preActions;
    private JHipsterModulePostActions postActions;
    private SpringProperties springProperties;

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
    public JHipsterModuleChangesSpringPropertiesBuilder postActions(JHipsterModulePostActions postActions) {
      this.postActions = postActions;

      return this;
    }

    @Override
    public JHipsterModuleChanges springProperties(SpringProperties springProperties) {
      this.springProperties = springProperties;

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
    JHipsterModuleChangesSpringPropertiesBuilder postActions(JHipsterModulePostActions postActions);
  }

  public interface JHipsterModuleChangesSpringPropertiesBuilder {
    JHipsterModuleChanges springProperties(SpringProperties springProperties);
  }
}
