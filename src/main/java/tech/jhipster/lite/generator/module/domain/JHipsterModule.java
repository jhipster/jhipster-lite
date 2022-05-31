package tech.jhipster.lite.generator.module.domain;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleContext.JHipsterModuleContextBuilder;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleFiles.JHipsterModuleFilesBuilder;
import tech.jhipster.lite.generator.module.domain.JHipsterModulePreActions.JHipsterModulePreActionsBuilder;
import tech.jhipster.lite.generator.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.generator.module.domain.javadependency.GroupId;
import tech.jhipster.lite.generator.module.domain.javadependency.JHipsterModuleJavaDependencies;
import tech.jhipster.lite.generator.module.domain.javadependency.JHipsterModuleJavaDependencies.JHipsterModuleJavaDependenciesBuilder;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency.JavaDependencyGroupIdBuilder;
import tech.jhipster.lite.generator.module.domain.javadependency.VersionSlug;
import tech.jhipster.lite.generator.module.domain.postaction.JHipsterModulePostActions;
import tech.jhipster.lite.generator.module.domain.postaction.JHipsterModulePostActions.JHipsterModulePostActionsBuilder;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements;
import tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements.JHipsterModuleReplacementsBuilder;
import tech.jhipster.lite.generator.module.domain.replacement.RegexMatcher;
import tech.jhipster.lite.generator.module.domain.replacement.TextMatcher;

public class JHipsterModule {

  private final JHipsterProjectFolder projectFolder;
  private final Collection<JHipsterModuleFile> files;
  private final JHipsterModuleReplacements replacements;
  private final JHipsterModuleContext context;
  private final JHipsterModuleJavaDependencies javaDependencies;
  private final JHipsterModulePreActions preActions;
  private final JHipsterModulePostActions postActions;

  private JHipsterModule(JHipsterModuleBuilder builder) {
    projectFolder = builder.projectFolder;

    files = builder.files.build().get();
    replacements = builder.replacements.build();
    context = builder.context.build();
    javaDependencies = builder.javaDependencies.build();
    preActions = builder.preActions.build();
    postActions = builder.postActions.build();
  }

  public static JHipsterModuleBuilder moduleForProject(JHipsterModuleProperties properties) {
    return new JHipsterModuleBuilder(properties);
  }

  public static JavaDependencyGroupIdBuilder javaDependency() {
    return JavaDependency.builder();
  }

  public static JHipsterSource from(String source) {
    Assert.notBlank("source", source);

    return new JHipsterSource(Paths.get("/generator", source));
  }

  public static JHipsterDestination to(String destination) {
    return new JHipsterDestination(destination);
  }

  public static JHipsterDestination toSrcMainJava() {
    return JHipsterDestination.SRC_MAIN_JAVA;
  }

  public static JHipsterDestination toSrcTestJava() {
    return JHipsterDestination.SRC_TEST_JAVA;
  }

  public static GroupId groupId(String groupId) {
    return new GroupId(groupId);
  }

  public static ArtifactId artifactId(String artifactId) {
    return new ArtifactId(artifactId);
  }

  public static VersionSlug versionSlug(String versionSlug) {
    return new VersionSlug(versionSlug);
  }

  public static TextMatcher text(String text) {
    return new TextMatcher(text);
  }

  public static RegexMatcher regex(String regex) {
    return new RegexMatcher(regex);
  }

  public JHipsterProjectFolder projectFolder() {
    return projectFolder;
  }

  public Indentation indentation() {
    return context.indentation();
  }

  public TemplatedFiles templatedFiles() {
    List<TemplatedFile> templatedFiles = files.stream().map(file -> TemplatedFile.builder().file(file).context(context).build()).toList();

    return new TemplatedFiles(templatedFiles);
  }

  public JHipsterModuleReplacements replacements() {
    return replacements;
  }

  public JHipsterModuleJavaDependencies javaDependencies() {
    return javaDependencies;
  }

  public JHipsterModulePreActions preActions() {
    return preActions;
  }

  public JHipsterModulePostActions postActions() {
    return postActions;
  }

  public static class JHipsterModuleBuilder {

    private final JHipsterProjectFolder projectFolder;
    private final JHipsterModuleProperties properties;
    private final JHipsterModuleContextBuilder context;
    private final JHipsterModuleFilesBuilder files = JHipsterModuleFiles.builder(this);
    private final JHipsterModuleReplacementsBuilder replacements = JHipsterModuleReplacements.builder(this);
    private final JHipsterModuleJavaDependenciesBuilder javaDependencies = JHipsterModuleJavaDependencies.builder(this);
    private final JHipsterModulePreActionsBuilder preActions = JHipsterModulePreActions.builder(this);
    private final JHipsterModulePostActionsBuilder postActions = JHipsterModulePostActions.builder(this);

    private JHipsterModuleBuilder(JHipsterModuleProperties properties) {
      Assert.notNull("properties", properties);

      this.projectFolder = properties.projectFolder();
      this.properties = properties;
      context = JHipsterModuleContext.builder(this);
    }

    JHipsterModuleProperties properties() {
      return properties;
    }

    public JHipsterModuleContextBuilder context() {
      return context;
    }

    public JHipsterModuleFilesBuilder files() {
      return files;
    }

    public JHipsterModuleReplacementsBuilder replacements() {
      return replacements;
    }

    public JHipsterModuleJavaDependenciesBuilder javaDependencies() {
      return javaDependencies;
    }

    public JHipsterModulePreActionsBuilder preActions() {
      return preActions;
    }

    public JHipsterModulePostActionsBuilder postActions() {
      return postActions;
    }

    public JHipsterModule build() {
      return new JHipsterModule(this);
    }
  }
}
