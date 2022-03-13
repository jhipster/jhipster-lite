package tech.jhipster.lite.generator.history.infrastructure.primary.dto;

import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryValue;

@UnitTest
public class HistoryDTOTest {

  @Test
  void shouldHaveEmptyListFromNullDomain() {
    assertThat(HistoryDTO.from(null).getServiceIds()).isEmpty();
  }

  @Test
  void shouldConvertFromHistory() {
    HistoryDTO history = defaultHistory();

    assertThat(history.getServiceIds()).isEqualTo(List.of("service51", "service1664", "service33"));
  }

  @Test
  void shouldSerializeToJson() throws JsonProcessingException {
    assertThat(JsonMapper.builder().build().writeValueAsString(defaultHistory())).isEqualTo(defaultJson());
  }

  private HistoryDTO defaultHistory() {
    return HistoryDTO.from(values());
  }

  public static List<GeneratorHistoryValue> values() {
    return serviceIds();
  }

  private static List<GeneratorHistoryValue> serviceIds() {
    return List.of(
      new GeneratorHistoryValue("service51"),
      new GeneratorHistoryValue("service1664"),
      new GeneratorHistoryValue("service33")
    );
  }

  public static String defaultJson() {
    return ("{\"serviceIds\":[\"service51\",\"service1664\",\"service33\"]}");
  }
}
