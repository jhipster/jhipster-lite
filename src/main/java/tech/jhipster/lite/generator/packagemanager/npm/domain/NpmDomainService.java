package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PRETTIER_DEFAULT_INDENT;

import java.util.Optional;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class NpmDomainService implements NpmService {

  public static final String VERSION = DQ + "version" + DQ;
  public static final String DEPENDENCIES = DQ + "dependencies" + DQ;
  public static final String DEV_DEPENDENCIES = DQ + "devDependencies" + DQ;
  public static final String SCRIPTS = DQ + "scripts" + DQ;

  private final NpmRepository npmRepository;
  private final ProjectRepository projectRepository;

  public NpmDomainService(NpmRepository npmRepository, ProjectRepository projectRepository) {
    this.npmRepository = npmRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public void addDependency(Project project, String dependency, String version) {
    addInformationToPackageJson(project, DEPENDENCIES, dependency, version);
  }

  @Override
  public void addDevDependency(Project project, String dependency, String version) {
    addInformationToPackageJson(project, DEV_DEPENDENCIES, dependency, version);
  }

  @Override
  public void addScript(Project project, String name, String cmd) {
    addInformationToPackageJson(project, SCRIPTS, name, cmd);
  }

  private void addInformationToPackageJson(Project project, String section, String key, String value) {
    project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);

    String needle = section + ": " + OB;
    String newText = needle + LF + indent(2, indent) + DQ + key + DQ + ": " + DQ + value + DQ;

    // no section in package.json
    if (!projectRepository.containsRegexp(project, "", PACKAGE_JSON, needle)) {
      newText = newText + LF + indent(1, indent) + CB + "," + LF + indent(1, indent) + VERSION;
      projectRepository.replaceText(project, "", PACKAGE_JSON, VERSION, newText);
      // section empty
    } else if (projectRepository.containsRegexp(project, "", PACKAGE_JSON, needle + CB)) {
      projectRepository.replaceText(project, "", PACKAGE_JSON, needle, newText + LF + indent(1, indent));
    } else {
      projectRepository.replaceText(project, "", PACKAGE_JSON, needle, newText + ",");
    }
  }

  @Override
  public void install(Project project) {
    this.npmRepository.npmInstall(project);
  }

  @Override
  public void prettify(Project project) {
    this.npmRepository.npmPrettierFormat(project);
  }

  @Override
  public Optional<String> getVersion(String name) {
    return FileUtils
      .readLine(getPath(MAIN_RESOURCES, TEMPLATE_FOLDER, "dependencies", PACKAGE_JSON), name)
      .map(readValue -> {
        String[] result = readValue.split(":");
        if (result.length == 2) {
          return result[1].replace(",", "").replace(DQ, "").replace(" ", "");
        }
        return null;
      });
  }
}
