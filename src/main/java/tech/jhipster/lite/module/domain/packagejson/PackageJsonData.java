package tech.jhipster.lite.module.domain.packagejson;

interface PackageJsonData {
  Scripts scripts();
  PackageJsonDependencies dependencies();
  PackageJsonDependencies devDependencies();
  PackageJsonDependencies dependenciesToRemove();
  PackageJsonDependencies devDependenciesToRemove();
  PackageJsonType type();
}
