package tech.jhipster.lite.module.domain.preset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;
import tech.jhipster.lite.shared.error.domain.Assert;

public record Presets(Collection<Preset> presets) {
  public Presets(Collection<Preset> presets) {
    Assert.notNull("presets", presets);

    this.presets = JHipsterCollections.immutable(sortByAlphabeticalOrder(presets));
  }

  private static Collection<Preset> sortByAlphabeticalOrder(Collection<Preset> presets) {
    return new ArrayList<>(presets).stream().sorted(alphabeticalOrder()).toList();
  }

  private static Comparator<Preset> alphabeticalOrder() {
    return Comparator.comparing(preset -> preset.name().name());
  }

  public Stream<Preset> stream() {
    return presets.stream();
  }
}
