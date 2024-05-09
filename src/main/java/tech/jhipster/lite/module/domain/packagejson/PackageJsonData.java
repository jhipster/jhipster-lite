package tech.jhipster.lite.module.domain.packagejson;

record PackageJsonData(
  Scripts scripts,
  PackageJsonDependencies dependencies,
  PackageJsonDependencies dependenciesToRemove,
  PackageJsonDependencies devDependencies,
  PackageJsonDependencies devDependenciesToRemove,
  PackageJsonType type
) {}
