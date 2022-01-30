package tech.jhipster.lite.generator.history.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneratorHistoryData {

  private List<GeneratorHistoryValue> values = new ArrayList<>();

  public List<GeneratorHistoryValue> getValues() {
    return values;
  }

  public GeneratorHistoryData setValues(List<GeneratorHistoryValue> values) {
    this.values = values;
    return this;
  }

  @Override
  public String toString() {
    return "GeneratorHistoryData{" + "values=" + values + '}';
  }
}
