package tech.jhipster.lite.generator.project.domain;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.lite.error.domain.UnauthorizedValueException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class ProjectDomainService implements ProjectService {

  private final NpmService npmService;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public ProjectDomainService(NpmService npmService, BuildToolService buildToolService, SpringBootCommonService springBootCommonService) {
    this.npmService = npmService;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public ProjectDTO getProjectDetails(String folderPath) {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.folder(folderPath);
    Project project = ProjectDTO.toProject(projectDTO);

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(PROJECT_NAME, npmService.getDescription(projectDTO.getFolder()));
    configMap.put(BASE_NAME, npmService.getName(projectDTO.getFolder()));

    if (project.isMavenProject()) {
      configMap.put(PACKAGE_NAME, buildToolService.getGroup(project));
      configMap.put(BASE_NAME, buildToolService.getName(project));
    } else if (project.isGradleProject()) {
      configMap.put(PACKAGE_NAME, buildToolService.getGroup(project));
    }

    Optional<String> serverPort = springBootCommonService.getProperty(project, "server.port");
    if (serverPort.isPresent()) {
      configMap.put("serverPort", Integer.parseInt(serverPort.get()));
    }

    projectDTO.generatorJhipster(configMap);
    return projectDTO;
  }
}
