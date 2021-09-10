package tech.jhipster.forge.generator.maven.secondary;

import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import org.springframework.stereotype.Repository;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.secondary.ProjectRepository;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.generator.maven.domain.Mavens;

@Repository
public class MavenRepository extends ProjectRepository implements Mavens {

  public static final String SOURCE = "maven";

  @Override
  public void initPomXml(Project project) {
    project.addDefaultConfig("packageName");
    project.addDefaultConfig("projectName");
    project.addDefaultConfig("baseName");

    String baseName = project.getConfig("baseName").orElse("");
    project.addConfig("dasherizedBaseName", WordUtils.kebabCase(baseName));

    template(project, SOURCE, "pom.xml");
  }

  @Override
  public void addMavenWrapper(Project project) {
    add(project, SOURCE, "mvnw");
    add(project, SOURCE, "mvnw.cmd");

    String sourceWrapper = getPath(SOURCE, ".mvn", "wrapper");
    String destinationWrapper = getPath(".mvn", "wrapper");

    add(project, sourceWrapper, "MavenWrapperDownloader.java", destinationWrapper);
    add(project, sourceWrapper, "maven-wrapper.jar", destinationWrapper);
    add(project, sourceWrapper, "maven-wrapper.properties", destinationWrapper);
  }
}
