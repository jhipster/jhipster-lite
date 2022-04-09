package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootWebfluxDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @InjectMocks
  SpringBootWebfluxDomainService springBootWebfluxDomainService;

  @Test
  void shouldAddSpringBootWebflux() {
    Project project = Project.builder().folder("/folder").build();

    springBootWebfluxDomainService.addSpringBootWebflux(project);

    ArgumentCaptor<Dependency> dependencyArgCaptor = ArgumentCaptor.forClass(Dependency.class);
    verify(buildToolService).addDependency(eq(project), dependencyArgCaptor.capture());
    assertThat(dependencyArgCaptor.getValue())
      .usingRecursiveComparison()
      .isEqualTo(Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-webflux").build());
  }
}
