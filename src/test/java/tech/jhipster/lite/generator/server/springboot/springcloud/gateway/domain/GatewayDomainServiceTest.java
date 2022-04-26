package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GatewayDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringCloudCommonService springCloudCommonService;

  @InjectMocks
  GatewayDomainService gatewayDomainService;

  @Test
  void shouldInit() {
    // Given
    Project project = tmpProject();

    when(buildToolService.getVersion(project, "spring-cloud")).thenReturn(Optional.of("0.0.0"));

    // When
    gatewayDomainService.init(project);

    // Then
    verify(springCloudCommonService).addSpringCloudCommonDependencies(project);
    verify(buildToolService).addDependency(any(Project.class), any(Dependency.class));

    verify(springCloudCommonService)
      .addOrMergeBootstrapProperties(
        project,
        "server/springboot/springcloud/gateway/src",
        "bootstrap.properties",
        "src/main/resources/config"
      );
    verify(springCloudCommonService)
      .addOrMergeBootstrapProperties(
        project,
        "server/springboot/springcloud/gateway/src/test",
        "bootstrap.properties",
        "src/test/resources/config"
      );
  }

  @Test
  void shouldNotAddDependencies() {
    Project project = tmpProjectWithPomXml();

    assertThatThrownBy(() -> gatewayDomainService.addDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }
}
