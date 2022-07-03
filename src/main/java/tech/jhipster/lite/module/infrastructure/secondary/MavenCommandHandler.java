package tech.jhipster.lite.module.infrastructure.secondary;

import static org.joox.JOOX.*;
import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.joox.JOOX;
import org.joox.Match;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import tech.jhipster.lite.common.domain.Enums;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.javadependency.command.AddDirectJavaDependency;
import tech.jhipster.lite.module.domain.javadependency.command.AddJavaDependency;
import tech.jhipster.lite.module.domain.javadependency.command.AddJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javadependency.command.RemoveDirectJavaDependency;
import tech.jhipster.lite.module.domain.javadependency.command.RemoveJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javadependency.command.SetJavaDependencyVersion;

class MavenCommandHandler {

  private static final Pattern SPACES_ONLY_LINE = Pattern.compile("^\\s+$", Pattern.MULTILINE);
  private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + LINE_BREAK;
  private static final String COMMAND = "command";
  private static final String GROUP_ID = "groupId";
  private static final String ARTIFACT_ID = "artifactId";
  private static final String VERSION = "version";
  private static final String[] PROPERTIES_ANCHOR = new String[] { "parent", "packaging", "description", "name", VERSION, ARTIFACT_ID };

  private static final String[] DEPENDENCIES_ANCHOR = new String[] {
    "dependencyManagement",
    "properties",
    "parent",
    "packaging",
    "description",
    "name",
    VERSION,
    ARTIFACT_ID,
  };
  private final Indentation indentation;
  private final Path pomPath;
  private final Match document;

  MavenCommandHandler(Indentation indentation, Path pomPath) {
    Assert.notNull("indentation", indentation);
    Assert.notNull("pomPath", pomPath);

    this.indentation = indentation;
    this.pomPath = pomPath;
    document = readDocument(pomPath);
  }

  private Match readDocument(Path pomPath) {
    try (InputStream input = Files.newInputStream(pomPath)) {
      return $(input);
    } catch (IOException | SAXException e) {
      throw new GeneratorException("Error reading pom content: " + e.getMessage(), e);
    }
  }

  public void handle(SetJavaDependencyVersion command) {
    Assert.notNull(COMMAND, command);

    Match properties = document.find("project > properties");
    if (properties.isEmpty()) {
      appendProperties(command);
    } else {
      appendPropertyLine(properties, command);
    }

    writePom();
  }

  private void appendProperties(SetJavaDependencyVersion command) {
    Match properties = $("properties").append(LINE_BREAK).append(indentation.spaces());

    appendPropertyLine(properties, command);

    findFirst(PROPERTIES_ANCHOR).after(properties);

    document.find("project properties").before(LINE_BREAK).before(LINE_BREAK).before(indentation.spaces());
  }

  private void appendPropertyLine(Match properties, SetJavaDependencyVersion command) {
    Match propertyNode = properties.children().filter(command.property());

    if (propertyNode.isNotEmpty()) {
      propertyNode.text(command.dependencyVersion());
    } else {
      properties
        .append(indentation.spaces())
        .append($(command.property(), command.dependencyVersion()))
        .append(LINE_BREAK)
        .append(indentation.spaces());
    }
  }

  public void handle(RemoveJavaDependencyManagement command) {
    Assert.notNull(COMMAND, command);

    removeDependency("project > dependencyManagement > dependencies > dependency", command.dependency());
  }

  public void handle(RemoveDirectJavaDependency command) {
    Assert.notNull(COMMAND, command);

    removeDependency("project > dependencies > dependency", command.dependency());
  }

  private void removeDependency(String rootPath, DependencyId dependency) {
    document.find(rootPath).each().stream().filter(dependencyMatch(dependency)).forEach(Match::remove);

    writePom();
  }

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
    Match dependencies = $("dependencyManagement")
      .append(LINE_BREAK)
      .append(indentation.times(2))
      .append(
        $("depdendencies")
          .append(LINE_BREAK)
          .append(indentation.times(3))
          .append(dependencyNode(command, 3))
          .append(LINE_BREAK)
          .append(indentation.times(2))
      )
      .append(LINE_BREAK)
      .append(indentation.spaces());

    findFirst(DEPENDENCIES_ANCHOR).after(dependencies);

    document.find("project > dependencyManagement").before(LINE_BREAK).before(LINE_BREAK).before(indentation.spaces());
  }

  private void appendDependencyManagement(AddJavaDependency command, Match dependencies) {
    appendNotTestDependency(command, dependencies, 3);
  }

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
    Match dependencies = $("dependencies")
      .append(LINE_BREAK)
      .append(indentation.times(2))
      .append(dependencyNode(command, 2))
      .append(LINE_BREAK)
      .append(indentation.spaces());

    findFirst(DEPENDENCIES_ANCHOR).after(dependencies);

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

    appendVersion(command, dependency, level);
    appendScope(command, dependency, level);
    appendOptional(command, dependency, level);
    appendType(command, dependency, level);

    dependency.append(LINE_BREAK).append(indentation.times(level));

    return dependency;
  }

  private Match buildDependencyNode(AddJavaDependency command, int level) {
    return $("dependency")
      .append(LINE_BREAK)
      .append(indentation.times(level + 1))
      .append($(GROUP_ID, command.groupId().get()))
      .append(LINE_BREAK)
      .append(indentation.times(level + 1))
      .append($(ARTIFACT_ID, command.artifactId().get()));
  }

  private void appendVersion(AddJavaDependency command, Match dependency, int level) {
    command
      .version()
      .ifPresent(version -> dependency.append(LINE_BREAK).append(indentation.times(level + 1)).append($(VERSION, version.mavenVariable())));
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

  @Generated
  private void writePom() {
    try (Writer writer = Files.newBufferedWriter(pomPath, StandardCharsets.UTF_8)) {
      writer.write(HEADER);

      for (Element e : document) {
        String element = JOOX.$(e).toString().replace("\r\n", LINE_BREAK);

        element = SPACES_ONLY_LINE.matcher(element).replaceAll("");

        writer.write(element);
      }
    } catch (IOException e) {
      throw new GeneratorException("Error writing pom: " + e.getMessage(), e);
    }
  }

  private Match findFirst(String... elements) {
    return Stream.of(elements).map(document::find).filter(Match::isNotEmpty).findFirst().orElseThrow(InvalidPomException::new);
  }
}
