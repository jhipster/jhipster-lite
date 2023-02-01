package tech.jhipster.lite.module.domain.javaproperties;

import tech.jhipster.lite.error.domain.Assert;

public class SpringFactory {

  private final PropertyKey key;
  private final PropertyValue value;

  public SpringFactory(SpringFactoryBuilder builder) {
    Assert.notNull("key", builder.key);
    Assert.notNull("value", builder.value);

    key = builder.key;
    value = builder.value;
  }

  public PropertyKey key() {
    return key;
  }

  public PropertyValue value() {
    return value;
  }

  public static class SpringFactoryBuilder implements SpringFactoryKeyBuilder, SpringFactoryValueBuilder {

    private PropertyKey key;
    private PropertyValue value;

    //todo
    private SpringFactoryBuilder() {}

    @Override
    public SpringFactoryKeyBuilder key(PropertyKey key) {
      this.key = key;

      return this;
    }

    @Override
    public SpringFactoryValueBuilder value(PropertyValue value) {
      this.value = value;

      return this;
    }

    // todo implem ?
    public SpringFactory build() {
      return new SpringFactory(this);
    }
  }

  // todo maybe make a common interface ?
  public interface SpringFactoryKeyBuilder {
    SpringFactoryKeyBuilder key(PropertyKey key);
  }

  public interface SpringFactoryValueBuilder {
    SpringFactoryValueBuilder value(PropertyValue value);
  }
}
