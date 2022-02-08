package tech.jhipster.lite.generator.server.javatool.frontendmaven.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class FrontendMavenDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @InjectMocks
  FrontendMavenDomainService frontendMavenDomainService;

  @Test
  void shouldAddFrontendMavenPlugin() {
    Project project = tmpProjectWithPomXml();
    when(buildToolService.getVersion(any(Project.class), anyString())).thenReturn(Optional.of("0.0.0"));

    frontendMavenDomainService.addFrontendMavenPlugin(project);

    verify(buildToolService, times(5)).addProperty(any(Project.class), anyString(), anyString());
    verify(buildToolService, times(3)).addPlugin(any(Project.class), any(Plugin.class));
  }

  @Test
  void shouldNotAddFrontendMavenPlugin() {
    Project project = tmpProjectWithPomXml();

    Assertions
      .assertThatThrownBy(() -> frontendMavenDomainService.addFrontendMavenPlugin(project))
      .isExactlyInstanceOf(GeneratorException.class);
  }
}
