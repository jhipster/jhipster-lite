package tech.jhipster.lite.module.domain.resource;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;
import tech.jhipster.lite.module.domain.JHipsterSlug;

public class JHipsterLandscape {

  private final JHipsterLandscapeLevels levels;

  private JHipsterLandscape(JHipsterLandscapeLevels levels) {
    this.levels = levels;
  }

  public static JHipsterLandscape from(JHipsterModulesResources resources) {
    assertNoDuplicatedSlug(resources);

    return new JHipsterLandscape(JHipsterLandscapeLevels.builder().resources(resources).build()).withoutNestedDependencies();
  }

  private JHipsterLandscape withoutNestedDependencies() {
    return new JHipsterLandscape(new JHipsterLandscapeLevels(nestedDependenciesFreeLevels()));
  }

  private List<JHipsterLandscapeLevel> nestedDependenciesFreeLevels() {
    return levels.stream().map(toLevelsWithoutNestedDepdendencies()).toList();
  }

  private Function<JHipsterLandscapeLevel, JHipsterLandscapeLevel> toLevelsWithoutNestedDepdendencies() {
    return level -> new JHipsterLandscapeLevel(level.elements().stream().map(toElementWithoutNestedDependencies()).toList());
  }

  @Generated(reason = "Jacoco think there is a missing case")
  private Function<JHipsterLandscapeElement, JHipsterLandscapeElement> toElementWithoutNestedDependencies() {
    return element -> {
      return switch (element.type()) {
        case MODULE -> moduleWithoutNestedDependencies((JHipsterLandscapeModule) element);
        case FEATURE -> element;
      };
    };
  }

  private JHipsterLandscapeModule moduleWithoutNestedDependencies(JHipsterLandscapeModule module) {
    List<JHipsterLandscapeDependency> knownDependencies = knownDependencies(module);

    return JHipsterLandscapeModule
      .builder()
      .module(module.slug())
      .operation(module.operation())
      .dependencies(dependenciesWithoutNested(module, knownDependencies));
  }

  private List<JHipsterLandscapeDependency> knownDependencies(JHipsterLandscapeModule module) {
    return module.dependencies().map(toKnownDependencies()).orElse(List.of());
  }

  private Function<JHipsterLandscapeDependencies, List<JHipsterLandscapeDependency>> toKnownDependencies() {
    return dependencies -> dependencies.stream().flatMap(dependency -> allDependenciesOf(dependency.slug())).toList();
  }

  private List<JHipsterLandscapeDependency> dependenciesWithoutNested(
    JHipsterLandscapeModule module,
    List<JHipsterLandscapeDependency> knownDependencies
  ) {
    return module.dependencies().map(toDependenciesWithoutNested(knownDependencies)).orElse(null);
  }

  private Function<JHipsterLandscapeDependencies, List<JHipsterLandscapeDependency>> toDependenciesWithoutNested(
    List<JHipsterLandscapeDependency> knownDependencies
  ) {
    return dependencies -> dependencies.stream().filter(dependency -> !knownDependencies.contains(dependency)).toList();
  }

  private Stream<JHipsterLandscapeDependency> allDependenciesOf(JHipsterSlug slug) {
    return levels
      .stream()
      .flatMap(level -> level.elements().stream())
      .filter(element -> element.slug().equals(slug))
      .flatMap(element -> element.dependencies().map(dependencies -> dependencies.stream()).orElse(Stream.of()));
  }

  private static void assertNoDuplicatedSlug(JHipsterModulesResources resources) {
    duplicatedSlug(resources).ifPresent(throwForDuplicatedSlug());
  }

  private static Optional<String> duplicatedSlug(JHipsterModulesResources resources) {
    List<String> featureSlugs = resources
      .stream()
      .map(resource -> resource.organization().feature())
      .filter(Optional::isPresent)
      .map(Optional::get)
      .map(JHipsterFeatureSlug::get)
      .toList();

    return resources.stream().map(resource -> resource.slug().get()).filter(featureSlugs::contains).findFirst();
  }

  private static Consumer<String> throwForDuplicatedSlug() {
    return slug -> {
      throw InvalidLandscapeException.duplicatedSlug(slug);
    };
  }

  public JHipsterLandscapeLevels levels() {
    return levels;
  }
}
