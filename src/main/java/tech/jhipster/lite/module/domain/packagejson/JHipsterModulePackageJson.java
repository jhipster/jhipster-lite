package tech.jhipster.lite.module.domain.packagejson;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModulePackageJson {

  private final Scripts scripts;
  private final PackageJsonDependencies dependencies;
  private final PackageJsonDependencies dependenciesToRemove;
  private final PackageJsonDependencies devDependencies;
  private final PackageJsonDependencies devDependenciesToRemove;
  private final PackageJsonType type;
  private final Optional<JHipsterModuleContext> context;

  private JHipsterModulePackageJson(JHipsterModulePackageJsonBuilder builder) {
    scripts = new Scripts(builder.scripts);
    dependencies = new PackageJsonDependencies(builder.dependencies);
    dependenciesToRemove = new PackageJsonDependencies(builder.dependenciesToRemove);
    devDependencies = new PackageJsonDependencies(builder.devDependencies);
    devDependenciesToRemove = new PackageJsonDependencies(builder.devDependenciesToRemove);
    type = new PackageJsonType(builder.type);
    context = Optional.ofNullable(builder.context);
  }

  public static JHipsterModulePackageJsonBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModulePackageJsonBuilder(module);
  }

  public JHipsterModulePackageJson buildChanges(JHipsterModuleContext context, JHipsterModuleProperties properties) {
    JHipsterModulePackageJsonBuilder builder = JHipsterModulePackageJson.builder(moduleBuilder(properties))
      .addType(type.type())
      .addContext(context);

    scripts.stream().forEach(script -> builder.addScript(script.key(), script.command()));
    dependencies
      .stream()
      .forEach(
        dependency ->
          dependency
            .versionPackageName()
            .ifPresentOrElse(
              versionPackageName -> builder.addDependency(dependency.packageName(), dependency.versionSource(), versionPackageName),
              () -> builder.addDependency(dependency.packageName(), dependency.versionSource())
            )
      );
    dependenciesToRemove.stream().forEach(dependency -> builder.removeDependency(dependency.packageName(), dependency.versionSource()));
    devDependencies
      .stream()
      .forEach(
        dependency ->
          dependency
            .versionPackageName()
            .ifPresentOrElse(
              versionPackageName -> builder.addDevDependency(dependency.packageName(), dependency.versionSource(), versionPackageName),
              () -> builder.addDevDependency(dependency.packageName(), dependency.versionSource())
            )
      );
    devDependenciesToRemove
      .stream()
      .forEach(dependency -> builder.removeDevDependency(dependency.packageName(), dependency.versionSource()));

    return builder.build();
  }

  public boolean isEmpty() {
    return (
      scripts.isEmpty() &&
      dependencies.isEmpty() &&
      devDependencies.isEmpty() &&
      dependenciesToRemove.isEmpty() &&
      devDependenciesToRemove.isEmpty()
    );
  }

  public Scripts scripts() {
    return scripts;
  }

  public PackageJsonDependencies devDependencies() {
    return devDependencies;
  }

  public PackageJsonDependencies dependencies() {
    return dependencies;
  }

  public PackageJsonDependencies devDependenciesToRemove() {
    return devDependenciesToRemove;
  }

  public PackageJsonDependencies dependenciesToRemove() {
    return dependenciesToRemove;
  }

  public PackageJsonType type() {
    return type;
  }

  public Optional<JHipsterModuleContext> context() {
    return context;
  }

  public static final class JHipsterModulePackageJsonBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<Script> scripts = new ArrayList<>();
    private final Collection<PackageJsonDependency> dependencies = new ArrayList<>();
    private final Collection<PackageJsonDependency> devDependencies = new ArrayList<>();
    private final Collection<PackageJsonDependency> dependenciesToRemove = new ArrayList<>();
    private final Collection<PackageJsonDependency> devDependenciesToRemove = new ArrayList<>();
    private String type;
    private JHipsterModuleContext context;

    private JHipsterModulePackageJsonBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModulePackageJsonBuilder addScript(ScriptKey key, ScriptCommand command) {
      scripts.add(new Script(key, command));

      return this;
    }

    public JHipsterModulePackageJsonBuilder addDependency(PackageName packageName, VersionSource versionSource) {
      dependencies.add(PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).build());

      return this;
    }

    public JHipsterModulePackageJsonBuilder addDependency(
      PackageName packageName,
      VersionSource versionSource,
      PackageName versionPackageName
    ) {
      dependencies.add(
        PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).versionPackageName(versionPackageName).build()
      );

      return this;
    }

    public JHipsterModulePackageJsonBuilder removeDependency(PackageName packageName, VersionSource versionSource) {
      dependenciesToRemove.add(PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).build());
      return this;
    }

    public JHipsterModulePackageJsonBuilder addDevDependency(PackageName packageName, VersionSource versionSource) {
      devDependencies.add(PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).build());

      return this;
    }

    public JHipsterModulePackageJsonBuilder addDevDependency(
      PackageName packageName,
      VersionSource versionSource,
      PackageName versionPackageName
    ) {
      devDependencies.add(
        PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).versionPackageName(versionPackageName).build()
      );

      return this;
    }

    public JHipsterModulePackageJsonBuilder removeDevDependency(PackageName packageName, VersionSource versionSource) {
      devDependenciesToRemove.add(PackageJsonDependency.builder().packageName(packageName).versionSource(versionSource).build());
      return this;
    }

    public JHipsterModulePackageJsonBuilder addType(String t) {
      type = t;

      return this;
    }

    public JHipsterModulePackageJsonBuilder addContext(JHipsterModuleContext context) {
      this.context = context;

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModulePackageJson build() {
      return new JHipsterModulePackageJson(this);
    }
  }
}
