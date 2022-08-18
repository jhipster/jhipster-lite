package tech.jhipster.lite.module.domain.resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
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

  private static void assertNoDuplicatedSlug(JHipsterModulesResources resources) {
    duplicatedSlug(resources).ifPresent(throwForDuplicatedSlug());
  }

  private static Optional<String> duplicatedSlug(JHipsterModulesResources resources) {
    List<String> featureSlugs = allFeatureSlugs(resources);

    return resources.stream().map(resource -> resource.slug().get()).filter(featureSlugs::contains).findFirst();
  }

  private static List<String> allFeatureSlugs(JHipsterModulesResources resources) {
    return resources.stream().flatMap(resource -> resource.organization().feature().stream()).map(JHipsterFeatureSlug::get).toList();
  }

  private static Consumer<String> throwForDuplicatedSlug() {
    return slug -> {
      throw InvalidLandscapeException.duplicatedSlug(slug);
    };
  }

  private JHipsterLandscape withoutNestedDependencies() {
    return new JHipsterLandscape(new JHipsterLandscapeLevels(nestedDependenciesFreeLevels()));
  }

  private List<JHipsterLandscapeLevel> nestedDependenciesFreeLevels() {
    return levels.stream().map(toLevelsWithoutNestedDepdendencies()).toList();
  }

  private Function<JHipsterLandscapeLevel, JHipsterLandscapeLevel> toLevelsWithoutNestedDepdendencies() {
    return level -> new JHipsterLandscapeLevel(level.elements().stream().map(this::toElementWithoutNestedDependencies).toList());
  }

  @Generated(reason = "Jacoco think there is a missing case")
  private JHipsterLandscapeElement toElementWithoutNestedDependencies(JHipsterLandscapeElement element) {
    return switch (element.type()) {
      case MODULE -> moduleWithoutNestedDependencies((JHipsterLandscapeModule) element);
      case FEATURE -> element;
    };
  }

  private JHipsterLandscapeModule moduleWithoutNestedDependencies(JHipsterLandscapeModule module) {
    List<JHipsterLandscapeDependency> knownDependencies = knownDependencies(module);

    return JHipsterLandscapeModule
      .builder()
      .module(module.slug())
      .operation(module.operation())
      .propertiesDefinition(module.propertiesDefinition())
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
      .flatMap(element -> element.dependencies().map(JHipsterLandscapeDependencies::stream).orElse(Stream.of()));
  }

  public Collection<JHipsterModuleSlug> sort(Collection<JHipsterModuleSlug> modules) {
    return levels.stream().flatMap(toLevelModules(modules)).toList();
  }

  private Function<JHipsterLandscapeLevel, Stream<JHipsterModuleSlug>> toLevelModules(Collection<JHipsterModuleSlug> modules) {
    return level -> modules.stream().filter(inLevel(level));
  }

  private Predicate<JHipsterModuleSlug> inLevel(JHipsterLandscapeLevel level) {
    return module ->
      level
        .elements()
        .stream()
        .flatMap(JHipsterLandscapeElement::allModules)
        .map(JHipsterLandscapeElement::slug)
        .anyMatch(levelElement -> levelElement.equals(module));
  }

  public JHipsterLandscapeLevels levels() {
    return levels;
  }
}
