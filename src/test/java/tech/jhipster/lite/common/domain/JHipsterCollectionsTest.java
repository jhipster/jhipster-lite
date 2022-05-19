package tech.jhipster.lite.common.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterCollectionsTest {

  @Nested
  @DisplayName("Map")
  class JHipsterCollectionsMapTest {

    @Test
    void shouldGetEmptyImmutableMapFromNullMap() {
      Map<String, String> source = null;
      Map<String, String> map = JHipsterCollections.immutable(source);

      assertThat(map).isEmpty();
      assertThatThrownBy(() -> map.clear()).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldGetmmutableMapFromMap() {
      Map<String, String> source = new HashMap<>();
      source.put("key", "value");
      Map<String, String> map = JHipsterCollections.immutable(source);

      assertThat(map).containsExactly(entry("key", "value"));
      assertThatThrownBy(() -> map.clear()).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
  }
}
