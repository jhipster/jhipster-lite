package tech.jhipster.lite.module.domain.packagejson;

import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class PackageJsonChanges {

  private final Scripts scripts;
  private final PackageJsonDependencies dependencies;
  private final PackageJsonDependencies dependenciesToRemove;
  private final PackageJsonDependencies devDependencies;
  private final PackageJsonDependencies devDependenciesToRemove;
  private final PackageJsonType type;
  private final JHipsterModuleContext context;

  public PackageJsonChanges(JHipsterModulePackageJson packageJson, JHipsterModuleContext context) {
    Assert.notNull("packageJson", packageJson);
    Assert.notNull("context", context);

    this.scripts = packageJson.scripts();
    this.dependencies = packageJson.dependencies();
    this.dependenciesToRemove = packageJson.dependenciesToRemove();
    this.devDependencies = packageJson.devDependencies();
    this.devDependenciesToRemove = packageJson.devDependenciesToRemove();
    this.type = packageJson.type();
    this.context = context;
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

  public JHipsterModuleContext context() {
    return context;
  }
}
