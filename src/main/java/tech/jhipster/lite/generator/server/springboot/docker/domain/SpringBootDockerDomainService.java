package tech.jhipster.lite.generator.server.springboot.docker.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class SpringBootDockerDomainService implements SpringBootDockerService {

  public static final String SOURCE = "server/springboot/docker/jib";

  private final ProjectRepository projectRepository;
  private final SpringBootPropertiesService springBootPropertiesService;
  private final BuildToolService buildToolService;

  public SpringBootDockerDomainService(
    ProjectRepository projectRepository,
    SpringBootPropertiesService springBootPropertiesService,
    BuildToolService buildToolService
  ) {
    this.projectRepository = projectRepository;
    this.springBootPropertiesService = springBootPropertiesService;
    this.buildToolService = buildToolService;
  }

  @Override
  public void addJib(Project project) {
    Plugin jibPlugin = Plugin
      .builder()
      .groupId("com.google.cloud.tools")
      .artifactId("jib-maven-plugin")
      .version("\\${jib-maven-plugin.version}")
      .additionalElements(
        """
           <from>
             <image>eclipse-temurin:11-jre-focal</image>
             <platforms>
               <platform>
                 <architecture>${jib-maven-plugin.architecture}</architecture>
                 <os>linux</os>
               </platform>
             </platforms>
           </from>
           <to>
             <image>jhipster:latest</image>
           </to>
           <container>
             <entrypoint>
               <shell>bash</shell>
               <option>-c</option>
               <arg>/entrypoint.sh</arg>
             </entrypoint>
             <ports>
               <port>8081</port>
             </ports>
             <environment>
               <SPRING_OUTPUT_ANSI_ENABLED>ALWAYS</SPRING_OUTPUT_ANSI_ENABLED>
               <JHIPSTER_SLEEP>0</JHIPSTER_SLEEP>
             </environment>
             <creationTime>USE_CURRENT_TIMESTAMP</creationTime>
             <user>1000</user>
           </container>
           <extraDirectories>
             <paths>src/main/docker/jib</paths>
             <permissions>
               <permission>
                 <file>/entrypoint.sh</file>
                 <mode>755</mode>
               </permission>
             </permissions>
           </extraDirectories>"""
      )
      .build();
    this.buildToolService.addProperty(project, "jib-maven-plugin", "3.1.4");
    this.buildToolService.addPlugin(project, jibPlugin);
  }
}
