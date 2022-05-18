package tech.jhipster.lite.common.domain;

import java.util.Collections;
import java.util.Map;

public final class JHipsterCollections {

  private JHipsterCollections() {}

  public static <K, V> Map<K, V> immutable(Map<K, V> map) {
    if (map == null) {
      return Map.of();
    }

    return Collections.unmodifiableMap(map);
  }
}
