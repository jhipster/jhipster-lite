package tech.jhipster.lite.generator.client.angular.common.application;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.angular.core.application.AngularApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class AngularCommonApplicationServiceIT {

  private static final String APP_MODULE_TS_FILE_PATH = "src/main/webapp/app/common/primary/app/app.module.ts";
  private static final String ENVIRONMENT_TS_FILE_PATH = "src/main/webapp/environments/environment.ts";
  private static final String APP_COMPONENT_HTML_FILE_PATH = "src/main/webapp/app/common/primary/app/app.component.html";

  @Autowired
  AngularCommonApplicationService angularCommonApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  AngularApplicationService angularApplicationService;

  @Test
  void shouldAddImports() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String imports =
      """
      import {Oauth2AuthService} from '../../../auth/infrastructure/primary/oauth2-auth.service';
      import {HTTP_INTERCEPTORS} from '@angular/common/http';
      """;
    angularCommonApplicationService.addImports(project, APP_MODULE_TS_FILE_PATH, imports);

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), APP_MODULE_TS_FILE_PATH)));
    List<String> expectedContentLines =
      """
        import { NgModule } from '@angular/core';
        import { MatDividerModule } from '@angular/material/divider';
        import {Oauth2AuthService} from '../../../auth/infrastructure/primary/oauth2-auth.service';
        import {HTTP_INTERCEPTORS} from '@angular/common/http';
        """.lines()
        .toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  @Test
  void shouldAddConstants() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String constants = """
      const myFn = () => {
        return 'hello world';
      };
      """;
    angularCommonApplicationService.addConstants(project, APP_MODULE_TS_FILE_PATH, constants);

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), APP_MODULE_TS_FILE_PATH)));
    List<String> expectedContentLines =
      """
        import { NgModule } from '@angular/core';
        import { MatDividerModule } from '@angular/material/divider';

        const myFn = () => {
          return 'hello world';
        };

        @NgModule({
        """.lines()
        .toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  @Test
  void shouldAddDeclarations() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String declarations = """
          MyComponent1, MyComponent2,
      """;
    angularCommonApplicationService.addDeclarations(project, APP_MODULE_TS_FILE_PATH, declarations);

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), APP_MODULE_TS_FILE_PATH)));
    List<String> expectedContentLines =
      """
        @NgModule({
          declarations: [AppComponent,
            MyComponent1, MyComponent2,
          ],
        """.lines()
        .toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  @Test
  void shouldAddProviders() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String providers = """
          { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
      """;
    angularCommonApplicationService.addProviders(project, APP_MODULE_TS_FILE_PATH, providers);

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), APP_MODULE_TS_FILE_PATH)));
    List<String> expectedContentLines =
      """
          providers: [
            { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
          ],
        """.lines()
        .toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  @Test
  void shouldAddEnvVariables() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String envVariables = """
        config1: {
          key1: "value1"
        }
      """;
    angularCommonApplicationService.addEnvVariables(project, ENVIRONMENT_TS_FILE_PATH, envVariables);

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), ENVIRONMENT_TS_FILE_PATH)));
    List<String> expectedContentLines =
      """
        export const environment = {
          production: false,
          config1: {
            key1: "value1"
          }
        };
        """.lines()
        .toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  @Test
  void shouldPrependHtml() throws IOException {
    // Given
    Project project = initAngularProject();

    // When
    String htmlToAdd = """
        <div>
          <h1>Hello World</h1>
        </div>
      """;
    angularCommonApplicationService.prependHtml(project, APP_COMPONENT_HTML_FILE_PATH, htmlToAdd, "(<div id=\"footer\">)");

    // Then
    List<String> allLinesFile = Files.readAllLines(Path.of(getPath(project.getFolder(), APP_COMPONENT_HTML_FILE_PATH)));
    List<String> expectedContentLines =
      """
      <div id="footer">
        <div>
          <h1>Hello World</h1>
        </div>
      """.lines().toList();

    assertThat(allLinesFile).containsAll(expectedContentLines);
  }

  private Project initAngularProject() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");
    initApplicationService.init(project);
    angularApplicationService.addAngular(project);
    return project;
  }
}
