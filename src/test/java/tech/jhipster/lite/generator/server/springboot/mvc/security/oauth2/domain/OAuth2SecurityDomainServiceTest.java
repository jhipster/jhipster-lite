package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.docker.domain.DockerImage;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectFilesAsserter;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.common.domain.CommonSecurityService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class OAuth2SecurityDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @Mock
  private BuildToolService buildToolService;

  @Mock
  private SpringBootCommonService springBootCommonService;

  @Mock
  private CommonSecurityService commonSecurityService;

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private OAuth2SecurityDomainService oAuth2SecurityDomainService;

  @Test
  @DisplayName("should add OAuth2")
  void shouldAddOAuth2() {
    Project project = tmpProject();
    when(dockerImages.get("jboss/keycloak")).thenReturn(new DockerImage("jboss/keycloak", "0.0.0"));

    oAuth2SecurityDomainService.addOAuth2(project);

    // 3 dependencies
    // 1 dependency for test
    verify(buildToolService, times(4)).addDependency(any(Project.class), any(Dependency.class));

    // 3 files related to docker-compose (docker-compose, realm, users)
    verify(projectRepository, times(3)).template(any(ProjectFile.class));

    // 10 files for Java
    // 11 files for Java Test
    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(10));
    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(11));

    // 5 properties, with 1 comment and 1 new line
    verify(springBootCommonService).addPropertiesComment(any(Project.class), anyString());
    verify(springBootCommonService, times(5)).addProperties(any(Project.class), anyString(), anyString());
    verify(springBootCommonService).addPropertiesNewLine(project);

    // 2 properties, with 1 comment and 1 new line
    verify(springBootCommonService).addPropertiesTestComment(any(Project.class), anyString());
    verify(springBootCommonService, times(2)).addPropertiesTest(any(Project.class), anyString(), anyString());
    verify(springBootCommonService).addPropertiesTestNewLine(project);

    verify(commonSecurityService).updateExceptionTranslator(project);
    verify(commonSecurityService).updateIntegrationTestWithMockUser(project);

    verify(projectRepository, times(7)).replaceText(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddAccountContext() {
    Project project = tmpProject();

    oAuth2SecurityDomainService.addAccountContext(project);

    // 3 java files and 4 for tests
    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(3));
    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(4));
  }
}
