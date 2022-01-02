package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static tech.jhipster.lite.common.domain.WordUtils.indent;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class NpmDomainService implements NpmService {

  private final CommandRepository commandRepository;
  private final ProjectRepository projectRepository;

  public NpmDomainService(CommandRepository commandRepository, ProjectRepository projectRepository) {
    this.commandRepository = commandRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public void addDependency(Project project, String dependency, String version) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);

    String needle = "\"dependencies\": \\{";
    String newText = needle + System.lineSeparator() + indent(2, indent) + "\"" + dependency + "\": \"" + version + "\"";

    String devDependenciesNeedle = "\"devDependencies\": \\{";
    if (!projectRepository.containsRegexp(project, "", "package.json", needle)) {
      newText =
        newText + System.lineSeparator() + indent(1, indent) + "\\}," + System.lineSeparator() + indent(1, indent) + devDependenciesNeedle;
      projectRepository.replaceText(project, "", "package.json", devDependenciesNeedle, newText);
    } else if (projectRepository.containsRegexp(project, "", "package.json", needle + "\\}")) {
      projectRepository.replaceText(project, "", "package.json", needle, newText + System.lineSeparator() + indent(1, indent));
    } else {
      projectRepository.replaceText(project, "", "package.json", needle, newText + ",");
    }
  }

  @Override
  public void addDevDependency(Project project, String dependency, String version) {
    // Need to be implemented
  }

  @Override
  public void addScript(Project project, String name, String cmd) {
    // Need to be implemented
  }

  @Override
  public void install(Project project) {
    this.commandRepository.npmInstall(project);
  }

  @Override
  public void prettify(Project project) {
    this.commandRepository.npmPrettierFormat(project);
  }
}
