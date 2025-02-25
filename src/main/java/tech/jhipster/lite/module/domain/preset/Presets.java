package tech.jhipster.lite.module.domain.preset;

import java.util.Collection;
import java.util.Comparator;
import tech.jhipster.lite.shared.error.domain.Assert;

public record Presets(Collection<Preset> presets) {
  public Presets {
    Assert.notNull("presets", presets);
  }

  public static Presets from(Collection<Preset> presets) {
    return new Presets(sortByAlphabeticalOrder(presets));
  }

  private static Collection<Preset> sortByAlphabeticalOrder(Collection<Preset> presets) {
    return presets.stream().sorted(alphabeticalOrder()).toList();
  }

  private static Comparator<Preset> alphabeticalOrder() {
    return Comparator.comparing(preset -> preset.name().name());
  }
}
