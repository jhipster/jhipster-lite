package tech.jhipster.lite.module.infrastructure.secondary.javadependency.maven;

import static org.joox.JOOX.$;
import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.maven.model.Activation;
import org.apache.maven.model.Build;
import org.apache.maven.model.Extension;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.joox.JOOX;
import org.joox.Match;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.buildproperties.BuildProperty;
import tech.jhipster.lite.module.domain.buildproperties.PropertyKey;
import tech.jhipster.lite.module.domain.buildproperties.PropertyValue;
import tech.jhipster.lite.module.domain.javabuild.MavenBuildExtension;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javabuild.command.AddBuildPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaBuildProfile;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenBuildExtension;
import tech.jhipster.lite.module.domain.javabuild.command.RemoveDirectJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.RemoveJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javabuild.command.SetBuildProperty;
import tech.jhipster.lite.module.domain.javabuild.command.SetVersion;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPluginAdditionalElements;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileActivation;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyClassifier;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.JavaDependenciesCommandHandler;
import tech.jhipster.lite.shared.enumeration.domain.Enums;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public class MavenCommandHandler implements JavaDependenciesCommandHandler {

  private static final String FORMATTED_LINE_END = "> *" + LINE_BREAK;
  private static final String RESULTING_LINE_END = ">" + LINE_BREAK;
  private static final Pattern SPACES_ONLY_LINE = Pattern.compile("^\\s+$", Pattern.MULTILINE);
  private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + LINE_BREAK;
  private static final String COMMAND = "command";
  private static final String GROUP_ID = "groupId";
  private static final String ARTIFACT_ID = "artifactId";
  private static final String VERSION = "version";
  private static final String CLASSIFIER = "classifier";

  private static final String PARENT = "parent";
  private static final String PACKAGING = "packaging";
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";
  private static final String PROPERTIES = "properties";
  private static final String BUILD = "build";
  private static final String DEPENDENCY_MANAGEMENT = "dependencyManagement";
  private static final String DEPENDENCIES = "dependencies";
  private static final String PLUGINS = "plugins";

  private static final String[] DEPENDENCIES_ANCHORS = new String[] {
    DEPENDENCY_MANAGEMENT,
    PROPERTIES,
    PARENT,
    PACKAGING,
    DESCRIPTION,
    NAME,
    VERSION,
    ARTIFACT_ID,
  };

  private static final String[] BUILD_ANCHORS = new String[] {
    DEPENDENCIES,
    DEPENDENCY_MANAGEMENT,
    PROPERTIES,
    PARENT,
    PACKAGING,
    DESCRIPTION,
    NAME,
    VERSION,
    ARTIFACT_ID,
  };

  private static final String[] PROFILES_ANCHORS = new String[] {
    BUILD,
    DEPENDENCIES,
    DEPENDENCY_MANAGEMENT,
    PROPERTIES,
    PARENT,
    PACKAGING,
    DESCRIPTION,
    NAME,
    VERSION,
    ARTIFACT_ID,
  };

  private final Indentation indentation;
  private final Path pomPath;
  private final Match document;
  private final Model pomModel;

  public MavenCommandHandler(Indentation indentation, Path pomPath) {
    Assert.notNull("indentation", indentation);
    Assert.notNull("pomPath", pomPath);

    this.indentation = indentation;
    this.pomPath = pomPath;
    document = readDocument(pomPath);
    pomModel = readModel(pomPath);
  }

  private Model readModel(Path pomPath) {
    try (InputStream input = Files.newInputStream(pomPath)) {
      MavenXpp3Reader reader = new MavenXpp3Reader();
      return reader.read(input);
    } catch (IOException | XmlPullParserException e) {
      throw GeneratorException.technicalError("Error reading pom file: " + e.getMessage(), e);
    }
  }

  private Match readDocument(Path pomPath) {
    try (InputStream input = Files.newInputStream(pomPath)) {
      return $(input);
    } catch (IOException | SAXException e) {
      throw GeneratorException.technicalError("Error reading pom content: " + e.getMessage(), e);
    }
  }

  @Override
  public void handle(SetVersion command) {
    Assert.notNull(COMMAND, command);

    BuildProperty property = new BuildProperty(new PropertyKey(command.property()), new PropertyValue(command.dependencyVersion()));
    handle(new SetBuildProperty(property));
  }

  @Override
  public void handle(SetBuildProperty command) {
    Assert.notNull(COMMAND, command);

    if (command.buildProfile().isPresent()) {
      BuildProfileId buildProfileId = command.buildProfile().orElseThrow();
      Profile mavenProfile = pomModel
        .getProfiles()
        .stream()
        .filter(profileMatch(buildProfileId))
        .findFirst()
        .orElseThrow(() -> new MissingMavenProfileException(buildProfileId));
      mavenProfile.addProperty(command.property().key().get(), command.property().value().get());
    } else {
      pomModel.addProperty(command.property().key().get(), command.property().value().get());
    }

    writePomFromModel();
  }

  private static Predicate<Profile> profileMatch(BuildProfileId buildProfileId) {
    return profile -> profile.getId().equals(buildProfileId.value());
  }

  @Override
  public void handle(AddJavaBuildProfile command) {
    Assert.notNull(COMMAND, command);

    List<Profile> profiles = pomModel.getProfiles();
    if (profiles.stream().noneMatch(profileMatch(command.buildProfileId()))) {
      Profile profile = toMavenProfile(command);
      pomModel.addProfile(profile);
    }

    writePomFromModel();
  }

  private static Profile toMavenProfile(AddJavaBuildProfile command) {
    Profile profile = new Profile();
    profile.setId(command.buildProfileId().value());
    command.activation().ifPresent(activation -> profile.setActivation(toMavenActivation(activation)));

    return profile;
  }

  private static Activation toMavenActivation(BuildProfileActivation activation) {
    Activation mavenActivation = new Activation();
    activation.activeByDefault().ifPresent(mavenActivation::setActiveByDefault);
    return mavenActivation;
  }

  @Override
  public void handle(RemoveJavaDependencyManagement command) {
    Assert.notNull(COMMAND, command);

    removeDependency("project > dependencyManagement > dependencies > dependency", command.dependency());
  }

  @Override
  public void handle(RemoveDirectJavaDependency command) {
    Assert.notNull(COMMAND, command);

    removeDependency("project > dependencies > dependency", command.dependency());
  }

  private void removeDependency(String rootPath, DependencyId dependency) {
    document.find(rootPath).each().stream().filter(dependencyMatch(dependency)).forEach(Match::remove);

    writePom();
  }

  @Override
  public void handle(AddMavenBuildExtension command) {
    Assert.notNull(COMMAND, command);

    projectBuild().addExtension(toMavenExtension(command.buildExtension()));

    writePomFromModel();
  }

  private Build projectBuild() {
    if (pomModel.getBuild() == null) {
      pomModel.setBuild(new Build());
    }
    return pomModel.getBuild();
  }

  private static Extension toMavenExtension(MavenBuildExtension mavenBuildExtension) {
    Extension extension = new Extension();
    extension.setArtifactId(mavenBuildExtension.artifactId().get());
    extension.setGroupId(mavenBuildExtension.groupId().get());
    mavenBuildExtension.versionSlug().map(VersionSlug::mavenVariable).ifPresent(extension::setVersion);
    return extension;
  }

  @Override
  public void handle(AddJavaDependencyManagement command) {
    Assert.notNull(COMMAND, command);

    Match dependencies = document.find("project > dependencyManagement > dependencies");
    if (dependencies.isEmpty()) {
      appendDependenciesManagement(command);
    } else {
      appendDependencyManagement(command, dependencies);
    }

    writePom();
  }

  private void appendDependenciesManagement(AddJavaDependencyManagement command) {
    Match dependencies = $(DEPENDENCY_MANAGEMENT)
      .append(LINE_BREAK)
      .append(indentation.times(2))
      .append(
        $(DEPENDENCIES)
          .append(LINE_BREAK)
          .append(indentation.times(3))
          .append(dependencyNode(command, 3))
          .append(LINE_BREAK)
          .append(indentation.times(2))
      )
      .append(LINE_BREAK)
      .append(indentation.spaces());

    findFirst(DEPENDENCIES_ANCHORS).after(dependencies);

    document.find("project > dependencyManagement").before(LINE_BREAK).before(LINE_BREAK).before(indentation.spaces());
  }

  private void appendDependencyManagement(AddJavaDependency command, Match dependencies) {
    appendNotTestDependency(command, dependencies, 3);
  }

  @Override
  public void handle(AddDirectJavaDependency command) {
    Assert.notNull(COMMAND, command);

    Match dependencies = document.find("project > dependencies");
    if (dependencies.isEmpty()) {
      appendDependencies(command);
    } else {
      appendDependency(command, dependencies);
    }

    writePom();
  }

  private void appendDependencies(AddDirectJavaDependency command) {
    Match dependencies = $(DEPENDENCIES)
      .append(LINE_BREAK)
      .append(indentation.times(2))
      .append(dependencyNode(command, 2))
      .append(LINE_BREAK)
      .append(indentation.spaces());

    findFirst(DEPENDENCIES_ANCHORS).after(dependencies);

    document.find("project > dependencies").before(LINE_BREAK).before(LINE_BREAK).before(indentation.spaces());
  }

  private void appendDependency(AddDirectJavaDependency command, Match dependencies) {
    if (command.scope() == JavaDependencyScope.TEST) {
      appendDependencyInLastPosition(command, dependencies, 2);
    } else {
      appendNotTestDependency(command, dependencies, 2);
    }
  }

  private void appendNotTestDependency(AddJavaDependency command, Match dependencies, int level) {
    dependencies
      .children()
      .each()
      .stream()
      .filter(testDependency())
      .findFirst()
      .ifPresentOrElse(appendBeforeFirstTestDependency(command, level), () -> appendDependencyInLastPosition(command, dependencies, level));
  }

  private Consumer<Match> appendBeforeFirstTestDependency(AddJavaDependency command, int level) {
    return firstTestDependency -> {
      Match dependencyNode = dependencyNode(command, level);
      firstTestDependency.before(dependencyNode);

      document
        .find("project > dependencies > dependency")
        .each()
        .stream()
        .filter(dependencyMatch(command.dependencyId()))
        .findFirst()
        .ifPresent(node -> node.after(indentation.times(level)).after(LINE_BREAK).after(LINE_BREAK));
    };
  }

  private Predicate<Match> dependencyMatch(DependencyId dependency) {
    return dependencyNode -> {
      boolean sameGroupId = dependencyNode.child(GROUP_ID).text().equals(dependency.groupId().get());
      boolean sameArtifactId = dependencyNode.child(ARTIFACT_ID).text().equals(dependency.artifactId().get());

      return sameGroupId && sameArtifactId;
    };
  }

  private Predicate<Match> testDependency() {
    return dependency -> "test".equals(dependency.child("scope").text());
  }

  private void appendDependencyInLastPosition(AddJavaDependency command, Match dependencies, int level) {
    dependencies
      .append(LINE_BREAK)
      .append(indentation.times(level))
      .append(dependencyNode(command, level))
      .append(LINE_BREAK)
      .append(indentation.times(level - 1));
  }

  private Match dependencyNode(AddJavaDependency command, int level) {
    Match dependency = buildDependencyNode(command, level);

    appendVersion(command.version(), dependency, level);
    appendClassifier(command.classifier(), dependency, level);
    appendScope(command, dependency, level);
    appendOptional(command, dependency, level);
    appendType(command, dependency, level);
    appendExclusions(command, dependency, level);

    dependency.append(LINE_BREAK).append(indentation.times(level));

    return dependency;
  }

  private Match buildDependencyNode(AddJavaDependency command, int level) {
    return appendDependencyId($("dependency"), command.dependencyId(), level);
  }

  private void appendScope(AddJavaDependency command, Match dependency, int level) {
    if (command.scope() != JavaDependencyScope.COMPILE) {
      dependency
        .append(LINE_BREAK)
        .append(indentation.times(level + 1))
        .append($("scope", Enums.map(command.scope(), MavenScope.class).key()));
    }
  }

  private void appendOptional(AddJavaDependency command, Match dependency, int level) {
    if (command.optional()) {
      dependency.append(LINE_BREAK).append(indentation.times(level + 1)).append($("optional", "true"));
    }
  }

  private void appendType(AddJavaDependency command, Match dependency, int level) {
    command
      .dependencyType()
      .ifPresent(type ->
        dependency.append(LINE_BREAK).append(indentation.times(level + 1)).append($("type", Enums.map(type, MavenType.class).key()))
      );
  }

  private void appendExclusions(AddJavaDependency command, Match dependency, int level) {
    Collection<DependencyId> exclusions = command.exclusions();
    if (exclusions.isEmpty()) {
      return;
    }

    dependency.append(LINE_BREAK).append(indentation.times(level + 1)).append(buildExclusionsNode(level, exclusions));
  }

  private Match buildExclusionsNode(int level, Collection<DependencyId> exclusions) {
    Match exclusionsNode = $("exclusions");

    exclusions.stream().map(toExclusionNode(level)).forEach(appendExclusionNode(level, exclusionsNode));

    exclusionsNode.append(LINE_BREAK).append(indentation.times(level + 1));

    return exclusionsNode;
  }

  private Function<DependencyId, Match> toExclusionNode(int level) {
    return exclusion -> appendDependencyId($("exclusion"), exclusion, level + 2).append(LINE_BREAK).append(indentation.times(level + 2));
  }

  private Consumer<Match> appendExclusionNode(int level, Match exclusionsNode) {
    return exclusionNode -> exclusionsNode.append(LINE_BREAK).append(indentation.times(level + 2)).append(exclusionNode);
  }

  @Override
  public void handle(AddBuildPluginManagement command) {
    Assert.notNull(COMMAND, command);

    Match pluginNode = pluginNode(command, 4);

    Match buildNode = findBuildNode();
    if (buildNode.isEmpty()) {
      appendBuildNode(pluginManagementNode(pluginNode));
    } else {
      appendPluginManagementInBuildNode(pluginNode, buildNode);
    }

    writePom();
  }

  private void appendPluginManagementInBuildNode(Match pluginNode, Match buildNode) {
    Match pluginManagementNode = buildNode.child("pluginManagement");

    if (pluginManagementNode.isEmpty()) {
      appendPluginManagement(pluginNode, buildNode);
    } else {
      appendInPluginManagement(pluginNode, pluginManagementNode);
    }
  }

  private Match appendPluginManagement(Match pluginNode, Match buildNode) {
    return buildNode.append(indentation.times(1)).append(pluginManagementNode(pluginNode)).append(LINE_BREAK).append(indentation.times(1));
  }

  private void appendInPluginManagement(Match pluginNode, Match pluginManagementNode) {
    Match pluginsNode = pluginManagementNode.child(PLUGINS);

    if (pluginsNode.isEmpty()) {
      appendPluginsNode(pluginManagementNode, pluginNode, 4);
    } else {
      appendPluginNode(pluginsNode, pluginNode, 4);
    }
  }

  private Match pluginManagementNode(Match pluginNode) {
    return $("pluginManagement")
      .append(LINE_BREAK)
      .append(indentation.times(3))
      .append(pluginsNode(pluginNode, 4))
      .append(LINE_BREAK)
      .append(indentation.times(2));
  }

  @Override
  public void handle(AddDirectJavaBuildPlugin command) {
    Assert.notNull(COMMAND, command);

    Match pluginNode = pluginNode(command, 3);

    Match buildNode = findBuildNode();
    if (buildNode.isEmpty()) {
      appendBuildNode(pluginsNode(pluginNode, 3));
    } else {
      appendPluginInBuildNode(pluginNode, buildNode);
    }

    writePom();
  }

  private void appendPluginInBuildNode(Match pluginNode, Match buildNode) {
    Match pluginsNode = buildNode.child(PLUGINS);

    if (pluginsNode.isEmpty()) {
      appendPluginsNode(buildNode, pluginNode, 3);
    } else {
      appendPluginNode(pluginsNode, pluginNode, 3);
    }
  }

  private Match appendPluginsNode(Match parent, Match pluginNode, int level) {
    return parent
      .append(indentation.times(1))
      .append(pluginsNode(pluginNode, level))
      .append(LINE_BREAK)
      .append(indentation.times(level - 2));
  }

  private Match pluginsNode(Match pluginNode, int level) {
    return $(PLUGINS)
      .append(LINE_BREAK)
      .append(indentation.times(level))
      .append(pluginNode.append(indentation.times(level)))
      .append(LINE_BREAK)
      .append(indentation.times(level - 1));
  }

  private void appendBuildNode(Match innerNode) {
    Match build = $(BUILD)
      .append(LINE_BREAK)
      .append(indentation.times(2))
      .append(innerNode)
      .append(LINE_BREAK)
      .append(indentation.times(1));

    findFirst(BUILD_ANCHORS).after(build);

    findBuildNode().before(LINE_BREAK).before(LINE_BREAK).before(indentation.spaces());
  }

  private Match findBuildNode() {
    return document.find("project > build");
  }

  private Match appendPluginNode(Match pluginsNode, Match pluginNode, int level) {
    return pluginsNode
      .append(indentation.times(1))
      .append(pluginNode.append(indentation.times(level)))
      .append(LINE_BREAK)
      .append(indentation.times(level - 1));
  }

  private Match pluginNode(AddJavaBuildPlugin command, int level) {
    Match pluginNode = appendDependencyId($("plugin"), command.dependencyId(), level);

    appendVersion(command.versionSlug(), pluginNode, level);

    command.additionalElements().ifPresent(appendAdditionalElements(pluginNode, level));

    return pluginNode.append(LINE_BREAK);
  }

  private Match appendDependencyId(Match node, DependencyId dependency, int level) {
    return node
      .append(LINE_BREAK)
      .append(indentation.times(level + 1))
      .append($(GROUP_ID, dependency.groupId().get()))
      .append(LINE_BREAK)
      .append(indentation.times(level + 1))
      .append($(ARTIFACT_ID, dependency.artifactId().get()));
  }

  private void appendVersion(Optional<VersionSlug> versionSlug, Match node, int level) {
    versionSlug.ifPresent(version ->
      node.append(LINE_BREAK).append(indentation.times(level + 1)).append($(VERSION, version.mavenVariable()))
    );
  }

  private void appendClassifier(Optional<JavaDependencyClassifier> classifier, Match node, int level) {
    classifier.ifPresent(depClassifier ->
      node.append(LINE_BREAK).append(indentation.times(level + 1)).append($(CLASSIFIER, depClassifier.get()))
    );
  }

  private Consumer<JavaBuildPluginAdditionalElements> appendAdditionalElements(Match pluginNode, int level) {
    return additionalElements ->
      pluginNode.append(LINE_BREAK).append(indentation.times(level + 1)).append(formatAdditionalElements(additionalElements, level + 1));
  }

  private String formatAdditionalElements(JavaBuildPluginAdditionalElements additionalElements, int level) {
    try (StringWriter stringWriter = new StringWriter()) {
      org.dom4j.Document additionalElementsDocument = DocumentHelper.parseText("<root>" + additionalElements.get() + "</root>");
      XMLWriter writer = new XMLWriter(stringWriter, buildFormat());
      writer.write(additionalElementsDocument);

      String result = stringWriter.toString();

      return result
        .substring(10, result.length() - 10)
        .replaceAll(FORMATTED_LINE_END, RESULTING_LINE_END)
        .indent((level - 1) * indentation.spacesCount());
    } catch (DocumentException | IOException e) {
      throw new MalformedAdditionalInformationException(e);
    }
  }

  private OutputFormat buildFormat() {
    OutputFormat format = OutputFormat.createPrettyPrint();

    format.setIndentSize(indentation.spacesCount());
    format.setSuppressDeclaration(true);
    format.setEncoding("UTF-8");

    return format;
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "The exception handling is hard to test and an implementation detail")
  private void writePom() {
    try (Writer writer = Files.newBufferedWriter(pomPath, StandardCharsets.UTF_8)) {
      writer.write(HEADER);

      for (Element e : document) {
        String element = JOOX.$(e).toString().replace("\r\n", LINE_BREAK).replace(" xmlns=\"\"", "");

        element = SPACES_ONLY_LINE.matcher(element).replaceAll("");

        writer.write(element);
      }
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error writing pom: " + e.getMessage(), e);
    }
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "The exception handling is hard to test and an implementation detail")
  private void writePomFromModel() {
    MavenXpp3Writer mavenWriter = new MavenXpp3Writer();
    try (Writer writer = Files.newBufferedWriter(pomPath, StandardCharsets.UTF_8)) {
      mavenWriter.write(writer, pomModel);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error writing pom: " + e.getMessage(), e);
    }
  }

  private Match findFirst(String... elements) {
    return Stream
      .of(elements)
      .map(element -> "project > " + element)
      .map(document::find)
      .filter(Match::isNotEmpty)
      .findFirst()
      .orElseThrow(InvalidPomException::new);
  }
}
