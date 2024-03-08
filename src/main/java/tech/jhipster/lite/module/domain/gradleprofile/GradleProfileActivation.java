package tech.jhipster.lite.module.domain.gradleprofile;

import java.util.Optional;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class GradleProfileActivation {

  private final Optional<Boolean> activeByDefault;

  public GradleProfileActivation(GradleProfileActivationBuilder builder) {
    this.activeByDefault = Optional.ofNullable(builder.activeByDefault);
  }

  public Optional<Boolean> activeByDefault() {
    return activeByDefault;
  }

  public static GradleProfileActivationBuilder builder() {
    return new GradleProfileActivationBuilder();
  }

  public static final class GradleProfileActivationBuilder {

    private Boolean activeByDefault;

    private GradleProfileActivationBuilder() {}

    public GradleProfileActivationBuilder activeByDefault() {
      return activeByDefault(true);
    }

    public GradleProfileActivationBuilder activeByDefault(Boolean activeByDefault) {
      Assert.notNull("activeByDefault", activeByDefault);
      this.activeByDefault = activeByDefault;
      return this;
    }

    public GradleProfileActivation build() {
      return new GradleProfileActivation(this);
    }
  }
}
