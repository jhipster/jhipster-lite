package tech.jhipster.lite.module.domain.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;
import tech.jhipster.lite.module.domain.JHipsterSlug;

public record JHipsterLandscapeLevels(Collection<JHipsterLandscapeLevel> levels) {
  public JHipsterLandscapeLevels(Collection<JHipsterLandscapeLevel> levels) {
    Assert.notEmpty("levels", levels);

    this.levels = Collections.unmodifiableCollection(levels);
  }

  static JHipsterLandscapeLevelsBuilder builder() {
    return new JHipsterLandscapeLevelsBuilder();
  }

  public Collection<JHipsterLandscapeLevel> get() {
    return levels();
  }

  public Stream<JHipsterLandscapeLevel> stream() {
    return levels().stream();
  }

  static class JHipsterLandscapeLevelsBuilder {

    private final Map<JHipsterFeatureSlug, Collection<JHipsterLandscapeModule>> features = new ConcurrentHashMap<>();
    private final Collection<JHipsterLandscapeModule> modules = new ArrayList<>();

    JHipsterLandscapeLevelsBuilder resources(JHipsterModulesResources resources) {
      Assert.notNull("resources", resources);

      resources.stream().forEach(this::append);

      return this;
    }

    private JHipsterLandscapeLevelsBuilder append(JHipsterModuleResource resource) {
      Assert.notNull("resource", resource);

      resource.organization().feature().ifPresentOrElse(addFeature(resource), addModule(resource));

      return this;
    }

    private Consumer<JHipsterFeatureSlug> addFeature(JHipsterModuleResource resource) {
      return feature -> features.computeIfAbsent(feature, newArrayList()).add(landscapeModule(resource));
    }

    private Function<JHipsterFeatureSlug, Collection<JHipsterLandscapeModule>> newArrayList() {
      return key -> new ArrayList<>();
    }

    private Runnable addModule(JHipsterModuleResource resource) {
      return () -> modules.add(landscapeModule(resource));
    }

    private static JHipsterLandscapeModule landscapeModule(JHipsterModuleResource resource) {
      return JHipsterLandscapeModule
        .builder()
        .module(resource.slug())
        .operation(resource.apiDoc().operation())
        .dependencies(resource.organization().dependencies());
    }

    public JHipsterLandscapeLevels build() {
      List<JHipsterLandscapeElement> elements = Stream.concat(features.entrySet().stream().map(toFeature()), modules.stream()).toList();
      JHipsterLandscapeLevelsDispatcher dispatcher = new JHipsterLandscapeLevelsDispatcher(elements);

      dispatcher.buildRoot();

      while (dispatcher.hasRemainingElements()) {
        dispatcher.dispatchNextLevel();
      }

      return new JHipsterLandscapeLevels(dispatcher.displayedLevels());
    }

    private Function<Entry<JHipsterFeatureSlug, Collection<JHipsterLandscapeModule>>, JHipsterLandscapeFeature> toFeature() {
      return entry -> new JHipsterLandscapeFeature(entry.getKey(), entry.getValue());
    }
  }

  private static class JHipsterLandscapeLevelsDispatcher {

    private final List<JHipsterSortableLandscapeLevel> levels = new ArrayList<>();
    private List<JHipsterLandscapeElement> elementsToDispatch;

    public JHipsterLandscapeLevelsDispatcher(List<JHipsterLandscapeElement> elements) {
      elementsToDispatch = elements;
    }

    public void buildRoot() {
      List<JHipsterLandscapeElement> rootElements = levelElements(withoutDependencies());

      if (rootElements.isEmpty()) {
        throw InvalidLandscapeException.missingRootElement();
      }

      appendLevel(rootElements);
    }

    private Predicate<JHipsterLandscapeElement> withoutDependencies() {
      return element -> element.dependencies().isEmpty();
    }

    public boolean hasRemainingElements() {
      return !elementsToDispatch.isEmpty();
    }

    public void dispatchNextLevel() {
      Set<JHipsterSlug> knownSlugs = knownSlugs();
      List<JHipsterLandscapeElement> levelElements = levelElements(withAllKnownDependencies(knownSlugs));

      if (levelElements.isEmpty()) {
        throw InvalidLandscapeException.unknownDepdencency(
          knownSlugs,
          elementsToDispatch.stream().map(JHipsterLandscapeElement::slug).toList()
        );
      }

      appendLevel(levelElements);
    }

    private List<JHipsterLandscapeElement> levelElements(Predicate<JHipsterLandscapeElement> condition) {
      return elementsToDispatch.stream().filter(condition).toList();
    }

    private Predicate<JHipsterLandscapeElement> withAllKnownDependencies(Set<JHipsterSlug> knownSlugs) {
      return element ->
        knownSlugs.containsAll(
          element
            .dependencies()
            .stream()
            .flatMap(dependencies -> dependencies.get().stream())
            .map(JHipsterLandscapeDependency::slug)
            .toList()
        );
    }

    private Set<JHipsterSlug> knownSlugs() {
      return levels.stream().flatMap(JHipsterSortableLandscapeLevel::slugs).collect(Collectors.toSet());
    }

    private void appendLevel(List<JHipsterLandscapeElement> elements) {
      updateElementsToDispatch(elements);

      JHipsterSortableLandscapeLevel addedLevel = new JHipsterSortableLandscapeLevel(elements);
      levels.forEach(level -> level.added(addedLevel));
      levels.add(addedLevel);
    }

    private void updateElementsToDispatch(List<JHipsterLandscapeElement> elements) {
      elementsToDispatch = elementsToDispatch.stream().filter(element -> !elements.contains(element)).toList();
    }

    public Collection<JHipsterLandscapeLevel> displayedLevels() {
      return levels.stream().map(JHipsterSortableLandscapeLevel::toDisplayedLevels).toList();
    }
  }

  private static class JHipsterSortableLandscapeLevel {

    private static final Comparator<JHipsterSortableLandscapeElement> DEPENDENT_ELEMENT_COMPARATOR = Comparator.comparing(
      JHipsterSortableLandscapeElement::dependentElementsCount
    );

    private final List<JHipsterSortableLandscapeElement> elements;

    public JHipsterSortableLandscapeLevel(List<JHipsterLandscapeElement> elements) {
      this.elements = elements.stream().map(JHipsterSortableLandscapeElement::new).toList();
    }

    public void added(JHipsterSortableLandscapeLevel addedLevel) {
      elements.forEach(element -> element.added(addedLevel));
    }

    public Stream<JHipsterSlug> slugs() {
      return elements.stream().flatMap(JHipsterSortableLandscapeElement::slugs);
    }

    public JHipsterLandscapeLevel toDisplayedLevels() {
      return new JHipsterLandscapeLevel(
        elements.stream().sorted(DEPENDENT_ELEMENT_COMPARATOR).map(JHipsterSortableLandscapeElement::element).toList()
      );
    }
  }

  private static class JHipsterSortableLandscapeElement {

    private final JHipsterLandscapeElement element;

    private long dependentElementsCount = 0;

    public JHipsterSortableLandscapeElement(JHipsterLandscapeElement element) {
      this.element = element;
    }

    public void added(JHipsterSortableLandscapeLevel addedLevel) {
      dependentElementsCount += addedLevel.elements.stream().mapToLong(toDependentElementsCount()).sum();
    }

    private ToLongFunction<JHipsterSortableLandscapeElement> toDependentElementsCount() {
      return sortableElement ->
        sortableElement
          .element()
          .dependencies()
          .stream()
          .flatMap(dependencies -> dependencies.dependencies().stream())
          .filter(dependency -> dependency.slug().equals(slug()))
          .count();
    }

    public JHipsterLandscapeElementType type() {
      return element.type();
    }

    @Generated(reason = "Jacoco think there is a missing case")
    public Stream<JHipsterSlug> slugs() {
      return switch (type()) {
        case MODULE -> Stream.of(slug());
        case FEATURE -> featureSlugs();
      };
    }

    private Stream<JHipsterSlug> featureSlugs() {
      return Stream.concat(Stream.of(slug()), ((JHipsterLandscapeFeature) element).modules().stream().map(JHipsterLandscapeModule::slug));
    }

    public JHipsterSlug slug() {
      return element.slug();
    }

    public long dependentElementsCount() {
      return dependentElementsCount;
    }

    public JHipsterLandscapeElement element() {
      return element;
    }
  }
}
