package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.gradleprofile.GradleProfileActivation;
import tech.jhipster.lite.module.domain.gradleprofile.GradleProfileId;
import tech.jhipster.lite.shared.error.domain.Assert;

public record AddGradleProfile(GradleProfileId buildProfileId, Optional<GradleProfileActivation> activation) implements JavaBuildCommand {
  public AddGradleProfile {
    Assert.notNull("buildProfileId", buildProfileId);
    Assert.notNull("activation", activation);
  }

  public AddGradleProfile(GradleProfileId buildProfileId) {
    this(buildProfileId, Optional.empty());
  }

  public AddGradleProfile(GradleProfileId buildProfileId, GradleProfileActivation activation) {
    this(buildProfileId, Optional.of(activation));
  }
}
