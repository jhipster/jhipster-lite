package tech.jhipster.lite.module.domain.packagejson;

import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class PackageJsonChanges {

  private final PackageJsonData data;
  private final JHipsterModuleContext context;

  public PackageJsonChanges(JHipsterModulePackageJson packageJson, JHipsterModuleContext context) {
    Assert.notNull("packageJson", packageJson);
    Assert.notNull("context", context);

    this.data = packageJson.getData();
    this.context = context;
  }

  public boolean isEmpty() {
    return (
      data.scripts().isEmpty() &&
      data.dependencies().isEmpty() &&
      data.devDependencies().isEmpty() &&
      data.dependenciesToRemove().isEmpty() &&
      data.devDependenciesToRemove().isEmpty()
    );
  }

  public Scripts scripts() {
    return data.scripts();
  }

  public PackageJsonDependencies dependencies() {
    return data.dependencies();
  }

  public PackageJsonDependencies devDependencies() {
    return data.devDependencies();
  }

  public PackageJsonDependencies dependenciesToRemove() {
    return data.dependenciesToRemove();
  }

  public PackageJsonDependencies devDependenciesToRemove() {
    return data.devDependenciesToRemove();
  }

  public PackageJsonType type() {
    return data.type();
  }

  public JHipsterModuleContext context() {
    return context;
  }
}
