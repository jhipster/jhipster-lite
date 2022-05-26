package tech.jhipster.lite.generator.module.domain;

import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;

import java.util.List;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.generator.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.generator.module.domain.javadependency.CurrentJavaDependenciesVersions;
import tech.jhipster.lite.generator.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.generator.module.domain.javadependency.GroupId;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency.JavaDependencyOptionalValueBuilder;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.generator.module.domain.javadependency.command.AddJavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.command.JavaDependenciesCommands;
import tech.jhipster.lite.generator.module.domain.javadependency.command.RemoveJavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.command.SetJavaDependencyVersion;

public final class JHipsterModulesFixture {

  private JHipsterModulesFixture() {}

  public static JHipsterModule module() {
    // @formatter:off
   return moduleForProject(new JHipsterProjectFolder(FileUtils.tmpDirForTest()))
    .context()
      .put("packageName", "com.test.myapp")
      .and()
    .files()
      .add(from("init/gitignore"), to(".gitignore"))
      .batch(from("server/javatool/base"), to("src/main/java/com/company/myapp/errors"))
        .add("Assert.java.mustache")
        .add("AssertionException.java.mustache")
        .and()
      .add(from("server/springboot/core/MainApp.java.mustache"), to("src/main/java/com/company/myapp/MyApp.java"))
      .and()
    .javaDependencies()
      .add(groupId("org.springframework.boot"), artifactId("spring-boot-starter"))
      .add(groupId("org.zalando"), artifactId("problem-spring-web"), versionSlug("problem-spring"))
      .add(optionalTestDependency())
      .add(groupId("io.jsonwebtoken"), artifactId("jjwt-api"), versionSlug("jjwt.version"))
      .and()
    .build();
    // @formatter:on
  }

  public static JavaDependency defaultVersionDependency() {
    return javaDependency().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
  }

  public static JavaDependency dependencyWithVersion() {
    return javaDependency().groupId("org.zalando").artifactId("problem-spring-web").versionSlug("problem-spring").build();
  }

  public static JavaDependenciesCommands javaDependenciesCommands() {
    SetJavaDependencyVersion setVersion = new SetJavaDependencyVersion(springBootVersion());
    RemoveJavaDependency remove = new RemoveJavaDependency(new DependencyId(new GroupId("spring-boot"), new ArtifactId("1.2.3")));
    AddJavaDependency add = new AddJavaDependency(optionalTestDependency());

    return new JavaDependenciesCommands(List.of(setVersion, remove, add));
  }

  public static JavaDependency optionalTestDependency() {
    return optionalTestDependencyBuilder().build();
  }

  public static JavaDependencyOptionalValueBuilder optionalTestDependencyBuilder() {
    return javaDependency()
      .groupId("org.junit.jupiter")
      .artifactId("junit-jupiter-engine")
      .versionSlug("spring-boot")
      .scope(JavaDependencyScope.TEST)
      .optional();
  }

  public static DependencyId jsonWebTokenDependencyId() {
    return new DependencyId(new GroupId("io.jsonwebtoken"), new ArtifactId("jjwt-api"));
  }

  public static JHipsterModuleContext context() {
    return JHipsterModuleContext.builder(emptyModuleBuilder()).put("packageName", "com.test.myapp").build();
  }

  public static JHipsterModuleBuilder emptyModuleBuilder() {
    return moduleForProject(new JHipsterProjectFolder(FileUtils.tmpDirForTest()));
  }

  public static CurrentJavaDependenciesVersions currentJavaDependenciesVersion() {
    return new CurrentJavaDependenciesVersions(List.of(springBootVersion(), problemVersion()));
  }

  private static JavaDependencyVersion problemVersion() {
    return new JavaDependencyVersion("problem-spring", "3.4.5");
  }

  public static JavaDependencyVersion springBootVersion() {
    return new JavaDependencyVersion("spring-boot", "1.2.3");
  }
}
