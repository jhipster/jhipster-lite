package tech.jhipster.lite.generator.server.sonar.domain;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PROJECT_NAME;
import static tech.jhipster.lite.generator.server.sonar.domain.Sonar.getMavenPluginVersion;

import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQL;

public class SonarDomainService implements SonarService {

  public static final String SOURCE = "server/sonar";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public SonarDomainService(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Override
  public void init(Project project) {
    addPropertiesPlugin(project);
    addSonarScannerPluginManagement(project);
    addPropertiesFile(project);
    addDockerCompose(project);
  }

  @Override
  public void addPropertiesPlugin(Project project) {
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
    buildToolService.addProperty(project, "properties-maven-plugin", "1.0.0");
    buildToolService.addPlugin(project, plugin);
  }

  @Override
  public void addSonarScannerPluginManagement(Project project) {
    Plugin plugin = Plugin
      .builder()
      .groupId("org.sonarsource.scanner.maven")
      .artifactId("sonar-maven-plugin")
      .version("\\${sonar-maven-plugin.version}")
      .build();
    buildToolService.addProperty(project, "sonar-maven-plugin", getMavenPluginVersion());
    buildToolService.addPluginManagement(project, plugin);
  }

  @Override
  public void addPropertiesFile(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addDefaultConfig(PROJECT_NAME);
    projectRepository.template(project, SOURCE, "sonar-project.properties");
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("dockerImageName", Sonar.getSonarqubeDockerVersion());
    projectRepository.template(project, SOURCE, "sonar.yml", "src/main/docker", "sonar.yml");
  }
}
