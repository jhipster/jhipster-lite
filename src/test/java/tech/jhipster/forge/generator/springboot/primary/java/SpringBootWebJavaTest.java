package tech.jhipster.forge.generator.springboot.primary.java;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.SpringBootWebApplicationService;

@UnitTest
@ExtendWith(SpringExtension.class)
class SpringBootWebJavaTest {

  @Mock
  private SpringBootWebApplicationService springBootWebApplicationService;

  @InjectMocks
  SpringBootWebJava springBootWebJava;

  @Test
  void shouldAddSpringBootWeb() {
    Project project = tmpProject();

    springBootWebJava.addSpringBootWeb(project);

    verify(springBootWebApplicationService).addSpringBootWeb(any(Project.class));
  }
}
