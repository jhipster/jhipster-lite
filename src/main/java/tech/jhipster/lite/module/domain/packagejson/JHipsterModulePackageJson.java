package tech.jhipster.lite.module.domain.packagejson;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModulePackageJson {

  private final Scripts scripts;
  private final PackageJsonDependencies dependencies;
  private final PackageJsonDependencies dependenciesToRemove;
  private final PackageJsonDependencies devDependencies;
  private final PackageJsonDependencies devDependenciesToRemove;
  private final PackageJsonType type;

  private JHipsterModulePackageJson(JHipsterModulePackageJsonBuilder builder) {
    scripts = new Scripts(builder.scripts);
    dependencies = new PackageJsonDependencies(builder.dependencies);
    dependenciesToRemove = new PackageJsonDependencies(builder.dependenciesToRemove);
    devDependencies = new PackageJsonDependencies(builder.devDependencies);
    devDependenciesToRemove = new PackageJsonDependencies(builder.devDependenciesToRemove);
    type = new PackageJsonType(builder.type);
  }

  public static JHipsterModulePackageJsonBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModulePackageJsonBuilder(module);
  }

  public PackageJsonChanges buildChanges(JHipsterModuleContext context) {
    return new PackageJsonChanges(this, context);
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

  public static final class JHipsterModulePackageJsonBuilder {

    private final JHipsterModuleBuilder parentBuilder;
    private final Collection<Script> scripts = new ArrayList<>();
    private final Collection<PackageJsonDependency> dependencies = new ArrayList<>();
    private final Collection<PackageJsonDependency> devDependencies = new ArrayList<>();
    private final Collection<PackageJsonDependency> dependenciesToRemove = new ArrayList<>();
    private final Collection<PackageJsonDependency> devDependenciesToRemove = new ArrayList<>();
    private String type;

    private JHipsterModulePackageJsonBuilder(JHipsterModuleBuilder parentBuilder) {
      Assert.notNull("module", parentBuilder);

      this.parentBuilder = parentBuilder;
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

    public JHipsterModuleBuilder and() {
      return parentBuilder;
    }

    public JHipsterModulePackageJson build() {
      return new JHipsterModulePackageJson(this);
    }
  }
}
