package tech.jhipster.lite.generator.server.sonar.domain;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PROJECT_NAME;
import static tech.jhipster.lite.generator.server.sonar.domain.Sonar.getMavenPluginVersion;

import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SonarDomainService implements SonarService {

  public static final String SOURCE = "server/sonar";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public SonarDomainService(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Override
  public void addSonarJavaBackend(Project project) {
    addPropertiesPlugin(project);
    addSonarScannerPluginManagement(project);
    addPropertiesFile(project);
    addDockerCompose(project);
  }

  private void addPropertiesPlugin(Project project) {
    Plugin plugin = Plugin
      .builder()
      .groupId("org.codehaus.mojo")
      .artifactId("properties-maven-plugin")
      .version("\\${properties-maven-plugin.version}")
      .additionalElements(
        """
        <executions>
          <execution>
            <phase>initialize</phase>
            <goals>
              <goal>read-project-properties</goal>
            </goals>
            <configuration>
              <files>
                <file>sonar-project.properties</file>
              </files>
            </configuration>
          </execution>
        </executions>"""
      )
      .build();
    buildToolService.addProperty(project, "properties-maven-plugin.version", "1.0.0");
    buildToolService.addPlugin(project, plugin);
  }

  private void addSonarScannerPluginManagement(Project project) {
    Plugin plugin = Plugin
      .builder()
      .groupId("org.sonarsource.scanner.maven")
      .artifactId("sonar-maven-plugin")
      .version("\\${sonar-maven-plugin.version}")
      .build();
    buildToolService.addProperty(project, "sonar-maven-plugin.version", getMavenPluginVersion());
    buildToolService.addPluginManagement(project, plugin);
  }

  private void addPropertiesFile(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addDefaultConfig(PROJECT_NAME);
    projectRepository.template(project, SOURCE, "sonar-project.properties");
  }

  private void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("sonarqubeDockerImage", Sonar.getSonarqubeDockerImage());
    projectRepository.template(project, SOURCE, "sonar.yml", "src/main/docker", "sonar.yml");
  }
}
