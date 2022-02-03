package tech.jhipster.lite.generator.history.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneratorHistoryData {

  private final List<GeneratorHistoryValue> values = new ArrayList<>();

  public List<GeneratorHistoryValue> getValues() {
    return values;
  }

  @Override
  public String toString() {
    return "GeneratorHistoryData{" + "values=" + values + '}';
  }
}
