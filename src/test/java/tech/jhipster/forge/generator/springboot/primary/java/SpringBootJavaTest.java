package tech.jhipster.forge.generator.springboot.primary.java;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.SpringBootApplicationService;

@UnitTest
@ExtendWith(SpringExtension.class)
class SpringBootJavaTest {

  @Mock
  SpringBootApplicationService springBootApplicationService;

  @InjectMocks
  SpringBootJava springBootJava;

  @Test
  void shouldAddSpringBoot() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addSpringBoot(project);

    verify(springBootApplicationService).addSpringBoot(any(Project.class));
  }

  @Test
  void shouldAddSpringBootParent() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addSpringBootParent(project);

    verify(springBootApplicationService).addSpringBootParent(any(Project.class));
  }

  @Test
  void shouldAddSpringBootDependencies() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addSpringBootDependencies(project);

    verify(springBootApplicationService).addSpringBootDependencies(any(Project.class));
  }

  @Test
  void shouldAddSpringBootMavenPlugin() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addSpringBootMavenPlugin(project);

    verify(springBootApplicationService).addSpringBootMavenPlugin(any(Project.class));
  }

  @Test
  void shouldAddMainApp() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addMainApp(project);

    verify(springBootApplicationService).addMainApp(any(Project.class));
  }

  @Test
  void shouldAddApplicationProperties() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootJava.addApplicationProperties(project);

    verify(springBootApplicationService).addApplicationProperties(any(Project.class));
  }
}
