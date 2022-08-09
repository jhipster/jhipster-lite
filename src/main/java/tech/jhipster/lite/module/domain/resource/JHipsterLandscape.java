package tech.jhipster.lite.module.domain.resource;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;

public class JHipsterLandscape {

  private final JHipsterLandscapeLevels levels;

  private JHipsterLandscape(JHipsterLandscapeLevels levels) {
    this.levels = levels;
  }

  public static JHipsterLandscape from(JHipsterModulesResources resources) {
    assertNoDuplicatedSlug(resources);

    return new JHipsterLandscape(JHipsterLandscapeLevels.builder().resources(resources).build());
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
