package tech.jhipster.lite.module.domain.gradleprofile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.gradleprofile.JHipsterModuleGradleProfile.JHipsterModuleGradleProfileBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradleProfile;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradleProfiles {

  private final Collection<JHipsterModuleGradleProfile> profiles;

  private JHipsterModuleGradleProfiles(JHipsterModuleGradleProfilesBuilder builder) {
    this.profiles = builder.profiles.values().stream().map(JHipsterModuleGradleProfileBuilder::build).toList();
  }

  public static JHipsterModuleGradleProfilesBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleGradleProfilesBuilder(module);
  }

  public JavaBuildCommands buildChanges() {
    Stream<JavaBuildCommand> addProfileCommands = profiles.stream().map(toAddProfileCommands());

    Collection<JavaBuildCommand> commands = Stream.of(addProfileCommands).flatMap(Function.identity()).toList();

    return new JavaBuildCommands(commands);
  }

  private Function<JHipsterModuleGradleProfile, JavaBuildCommand> toAddProfileCommands() {
    return profile -> new AddGradleProfile(profile.id(), profile.activation());
  }

  public static final class JHipsterModuleGradleProfilesBuilder {

    private final JHipsterModuleBuilder module;
    private final Map<GradleProfileId, JHipsterModuleGradleProfileBuilder> profiles = new HashMap<>();

    private JHipsterModuleGradleProfilesBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleGradleProfileBuilder addProfile(GradleProfileId gradleProfileId) {
      Assert.notNull("gradleProfileId", gradleProfileId);

      return profiles.computeIfAbsent(gradleProfileId, id -> JHipsterModuleGradleProfile.builder(this, gradleProfileId));
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleGradleProfiles build() {
      return new JHipsterModuleGradleProfiles(this);
    }
  }
}
