package tech.jhipster.lite.generator.typescript.domain;

import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class TypescriptDomainService implements TypescriptService {

  public static final String SOURCE = "typescript";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public TypescriptDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void init(Project project) {
    npmService.addDependency(project, "@types/jest", "^26.0.22");
    npmService.addDependency(project, "@typescript-eslint/eslint-plugin", "^4.21.0");
    npmService.addDependency(project, "@typescript-eslint/parser", "^4.21.0");
    npmService.addDependency(project, "eslint", "^7.23.0");
    npmService.addDependency(project, "eslint-import-resolver-typescript", "^2.4.0");
    npmService.addDependency(project, "eslint-plugin-import", "^2.22.1");
    npmService.addDependency(project, "eslint-plugin-prettier", "^3.3.1");
    npmService.addDependency(project, "jest", "^26.6.3");
    npmService.addDependency(project, "prettier", "^2.2.1");
    npmService.addDependency(project, "ts-jest", "^26.5.4");
    npmService.addDependency(project, "typescript", "^4.2.4");

    npmService.addScript(project, "test", "jest");
    npmService.addScript(project, "test:watch", "jest --watch");
    npmService.addScript(project, "test:watch:all", "jest --watchAll");
    npmService.addScript(project, "eslint:ci", "eslint './**/*.{ts,js}'");
    npmService.addScript(project, "eslint", "eslint './**/*.{ts,js}' --fix");

    projectRepository.add(project, SOURCE, "tsconfig.json");
    projectRepository.add(project, SOURCE, "jest.config.js");
    projectRepository.add(project, SOURCE, ".eslintrc.js");
  }
}
