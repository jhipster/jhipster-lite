package tech.jhipster.lite.generator.history.domain;

public class GeneratorHistoryValue {

  private String serviceId;

  public String getServiceId() {
    return serviceId;
  }

  public GeneratorHistoryValue setServiceId(String serviceId) {
    this.serviceId = serviceId;
    return this;
  }

  @Override
  public String toString() {
    return "GeneratorHistoryValue{" + "serviceId='" + serviceId + '\'' + '}';
  }
}
