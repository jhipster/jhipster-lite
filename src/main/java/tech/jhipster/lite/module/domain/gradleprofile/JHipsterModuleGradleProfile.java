package tech.jhipster.lite.module.domain.gradleprofile;

import java.util.Optional;
import tech.jhipster.lite.module.domain.gradleprofile.GradleProfileActivation.GradleProfileActivationBuilder;
import tech.jhipster.lite.module.domain.gradleprofile.JHipsterModuleGradleProfiles.JHipsterModuleGradleProfilesBuilder;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradleProfile {

  private final GradleProfileId gradleProfileId;
  private final Optional<GradleProfileActivation> activation;

  private JHipsterModuleGradleProfile(JHipsterModuleGradleProfileBuilder builder) {
    Assert.notNull("gradleProfileId", builder.gradleProfileId);
    this.gradleProfileId = builder.gradleProfileId;
    this.activation = Optional.ofNullable(builder.activation);
  }

  public static JHipsterModuleGradleProfileBuilder builder(JHipsterModuleGradleProfilesBuilder profiles, GradleProfileId gradleProfileId) {
    return new JHipsterModuleGradleProfileBuilder(profiles, gradleProfileId);
  }

  public GradleProfileId id() {
    return gradleProfileId;
  }

  public Optional<GradleProfileActivation> activation() {
    return activation;
  }

  public static final class JHipsterModuleGradleProfileBuilder {

    private final JHipsterModuleGradleProfilesBuilder profiles;
    private final GradleProfileId gradleProfileId;
    private GradleProfileActivation activation;

    private JHipsterModuleGradleProfileBuilder(JHipsterModuleGradleProfilesBuilder profiles, GradleProfileId gradleProfileId) {
      Assert.notNull("profiles", profiles);
      Assert.notNull("gradleProfileId", gradleProfileId);

      this.profiles = profiles;
      this.gradleProfileId = gradleProfileId;
    }

    public JHipsterModuleGradleProfilesBuilder and() {
      return profiles;
    }

    public JHipsterModuleGradleProfile build() {
      return new JHipsterModuleGradleProfile(this);
    }

    public JHipsterModuleGradleProfileBuilder activation(GradleProfileActivation activation) {
      Assert.notNull("activation", activation);
      this.activation = activation;

      return this;
    }

    public JHipsterModuleGradleProfileBuilder activation(GradleProfileActivationBuilder activationBuilder) {
      Assert.notNull("activationBuilder", activationBuilder);

      return activation(activationBuilder.build());
    }
  }
}
