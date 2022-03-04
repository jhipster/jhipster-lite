package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.common.domain.CommonSecurityService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class OAuth2SecurityDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @Mock
  CommonSecurityService commonSecurityService;

  @InjectMocks
  OAuth2SecurityDomainService oAuth2SecurityDomainService;

  @Test
  @DisplayName("should add OAuth2")
  void shouldAddOAuth2() {
    Project project = tmpProject();

    oAuth2SecurityDomainService.addOAuth2(project);

    // 3 dependencies
    // 1 dependency for test
    verify(buildToolService, times(4)).addDependency(any(Project.class), any(Dependency.class));

    // 3 files related to docker-compose (docker-compose, realm, users)
    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString(), anyString());

    // 9 files for Java
    // 10 files for Java Test
    verify(projectRepository, times(19)).template(any(Project.class), anyString(), anyString(), anyString());

    // 5 properties, with 1 comment and 1 new line
    verify(springBootCommonService).addPropertiesComment(any(Project.class), anyString());
    verify(springBootCommonService, times(5)).addProperties(any(Project.class), anyString(), anyString());
    verify(springBootCommonService).addPropertiesNewLine(project);

    // 1 property, with 1 comment and 1 new line
    verify(springBootCommonService).addPropertiesTestComment(any(Project.class), anyString());
    verify(springBootCommonService).addPropertiesTest(any(Project.class), anyString(), anyString());
    verify(springBootCommonService).addPropertiesTestNewLine(project);

    verify(commonSecurityService).updateExceptionTranslator(project);
    verify(commonSecurityService).updateIntegrationTestWithMockUser(project);
  }
}
