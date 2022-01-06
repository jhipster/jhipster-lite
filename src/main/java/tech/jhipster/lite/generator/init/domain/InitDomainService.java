package tech.jhipster.lite.generator.init.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.CRLF;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class InitDomainService implements InitService {

  public static final String SOURCE = "init";
  public static final String HUSKY_FOLDER = ".husky";

  private final ProjectRepository projectRepository;

  public InitDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    addReadme(project);
    addGitConfiguration(project);
    addEditorConfiguration(project);
    addPrettier(project);
    addPackageJson(project);
  }

  @Override
  public void addPackageJson(Project project) {
    project.addDefaultConfig(PROJECT_NAME);
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("nodeVersion", Init.getNodeVersion());

    String baseName = project.getBaseName().orElse("");
    project.addConfig("dasherizedBaseName", WordUtils.kebabCase(baseName));

    projectRepository.template(project, SOURCE, PACKAGE_JSON);
  }

  @Override
  public void addReadme(Project project) {
    project.addDefaultConfig(PROJECT_NAME);

    projectRepository.template(project, SOURCE, "README.md");
  }

  @Override
  public void addGitConfiguration(Project project) {
    projectRepository.add(project, SOURCE, "gitignore", ".", ".gitignore");
    projectRepository.add(project, SOURCE, "gitattributes", ".", ".gitattributes");
  }

  @Override
  public void addEditorConfiguration(Project project) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);

    project.addConfig("editorConfigEndOfLine", CRLF.equals(project.getEndOfLine()) ? "crlf" : "lf");
    projectRepository.template(project, SOURCE, ".editorconfig");

    projectRepository.add(project, SOURCE, ".eslintignore");
  }

  @Override
  public void addPrettier(Project project) {
    projectRepository.add(project, SOURCE, ".lintstagedrc.js");
    projectRepository.add(project, SOURCE, ".prettierignore");
    projectRepository.add(project, getPath(SOURCE, HUSKY_FOLDER), "pre-commit", HUSKY_FOLDER);
    projectRepository.setExecutable(project, HUSKY_FOLDER, "pre-commit");

    project.addConfig("prettierEndOfLine", CRLF.equals(project.getEndOfLine()) ? "crlf" : "lf");
    projectRepository.template(project, SOURCE, ".prettierrc");
  }
}
