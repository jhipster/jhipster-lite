package tech.jhipster.lite.generator.client.angular.security.oauth2.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.generator.client.angular.security.oauth2.domain.AngularOauth2.APP_COMPONENT_HTML_FILE_PATH;
import static tech.jhipster.lite.generator.client.angular.security.oauth2.domain.AngularOauth2.APP_COMPONENT_SPEC_TS_FILE_PATH;
import static tech.jhipster.lite.generator.client.angular.security.oauth2.domain.AngularOauth2.APP_MODULE_TS_FILE_PATH;
import static tech.jhipster.lite.generator.client.angular.security.oauth2.domain.AngularOauth2.ENVIRONMENT_TS_FILE_PATH;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.client.angular.common.domain.AngularCommonService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class AngularOauth2DomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  NpmService npmService;

  @Mock
  AngularCommonService angularCommonService;

  @InjectMocks
  AngularOauth2DomainService angularOauth2DomainService;

  @Test
  void shouldAddOauth2() {
    // Given
    Project project = Project.builder().folder("/project/path").build();
    when(npmService.getVersion("angular", "keycloak-js")).thenReturn(Optional.of("0.0.0"));

    // When
    angularOauth2DomainService.addOauth2(project);

    // Then
    verify(npmService).addDependency(project, "keycloak-js", "0.0.0");
    verifyAddedFiles(project);
    verifyUpdatedFiles(project);
  }

  private void verifyAddedFiles(Project project) {
    verify(projectRepository, times(7)).template(any(Project.class), anyString(), anyString(), anyString());
    ArgumentCaptor<String> fileNameArgCaptor = ArgumentCaptor.forClass(String.class);
    verify(projectRepository, times(4))
      .template(
        eq(project),
        eq("client/angular/security/oauth2/src/main/webapp/app/auth"),
        fileNameArgCaptor.capture(),
        eq("src/main/webapp/app/auth")
      );
    assertThat(fileNameArgCaptor.getAllValues())
      .containsExactlyInAnyOrder(
        "oauth2-auth.service.ts",
        "oauth2-auth.service.spec.ts",
        "http-auth.interceptor.ts",
        "http-auth.interceptor.spec.ts"
      );

    fileNameArgCaptor = ArgumentCaptor.forClass(String.class);
    verify(projectRepository, times(3))
      .template(
        eq(project),
        eq("client/angular/security/oauth2/src/main/webapp/app/login"),
        fileNameArgCaptor.capture(),
        eq("src/main/webapp/app/login")
      );
    assertThat(fileNameArgCaptor.getAllValues())
      .containsExactlyInAnyOrder("login.component.html", "login.component.ts", "login.component.spec.ts");
  }

  @Test
  void shouldThrowExceptionWhenDependencyVersionNotFound() {
    // Given
    Project project = Project.builder().folder("/project/path").build();
    when(npmService.getVersion("angular", "keycloak-js")).thenReturn(Optional.empty());

    // When + Then
    assertThatThrownBy(() -> angularOauth2DomainService.addOauth2(project))
      .isInstanceOf(GeneratorException.class)
      .hasMessageContaining("Dependency not found: keycloak-js");
  }

  private void verifyUpdatedFiles(Project project) {
    verify(angularCommonService, times(2)).addImports(any(Project.class), anyString(), anyString());
    verify(angularCommonService, times(1)).addInExistingImport(any(Project.class), anyString(), anyString(), anyString());
    verify(angularCommonService, times(1)).addConstants(any(Project.class), anyString(), anyString());
    verify(angularCommonService, times(2)).addDeclarations(any(Project.class), anyString(), anyString());
    verify(angularCommonService, times(1)).addProviders(any(Project.class), anyString(), anyString());
    verify(angularCommonService, times(1)).addEnvVariables(any(Project.class), anyString(), anyString());
    verify(angularCommonService, times(1)).addHtml(any(Project.class), anyString(), anyString(), anyString());
    verify(angularCommonService, times(1)).addTest(any(Project.class), anyString(), anyString(), anyString());

    verify(angularCommonService)
      .addImports(
        project,
        APP_MODULE_TS_FILE_PATH,
        """
        import {HTTP_INTERCEPTORS} from '@angular/common/http';
        import {LoginComponent} from './login/login.component';
        import {Oauth2AuthService} from './auth/oauth2-auth.service';
        import {HttpAuthInterceptor} from './auth/http-auth.interceptor';
        """
      );

    verify(angularCommonService).addInExistingImport(project, APP_MODULE_TS_FILE_PATH, " APP_INITIALIZER", "@angular/core");

    verify(angularCommonService)
      .addConstants(
        project,
        APP_MODULE_TS_FILE_PATH,
        """
        const initializeApp = (oauth2AuthService: Oauth2AuthService) => {
          return () => {
            oauth2AuthService.initAuthentication();
          };
        };
        """
      );

    verify(angularCommonService).addDeclarations(project, APP_MODULE_TS_FILE_PATH, """
            LoginComponent,
        """);

    verify(angularCommonService)
      .addProviders(
        project,
        APP_MODULE_TS_FILE_PATH,
        """
            { provide: APP_INITIALIZER, useFactory: initializeApp, multi: true, deps: [Oauth2AuthService] },
            { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
        """
      );

    verify(angularCommonService)
      .addEnvVariables(
        project,
        ENVIRONMENT_TS_FILE_PATH,
        """
        keycloak: {
          url: 'http://localhost:9080/auth',
          realm: 'jhipster',
          client_id: 'web_app'
        }
      """
      );

    verify(angularCommonService)
      .addHtml(
        project,
        APP_COMPONENT_HTML_FILE_PATH,
        """
          <jh-lite-login></jh-lite-login>
        """,
        "(<span.+id=\\\"menu-space-separator\\\".*></span>)"
      );

    verify(angularCommonService)
      .addImports(
        project,
        APP_COMPONENT_SPEC_TS_FILE_PATH,
        """
        import {By} from '@angular/platform-browser';
        import {LoginComponent} from './login/login.component';
        """
      );

    verify(angularCommonService).addDeclarations(project, APP_COMPONENT_SPEC_TS_FILE_PATH, """
                  LoginComponent,
        """);
    verify(angularCommonService)
      .addTest(
        project,
        APP_COMPONENT_SPEC_TS_FILE_PATH,
        """
          it('should display login component',() => {
            // WHEN
            fixture.detectChanges();

            // THEN
            expect(fixture.debugElement.query(By.directive(LoginComponent))).toBeTruthy();
          });
      """,
        "should have appName"
      );
  }
}
