package tech.jhipster.lite.generator.client.angular.security.jwt.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.client.angular.core.domain.Angular.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class AngularJwtDomainService implements AngularJwtService {

  private static final Collection<String> ANGULAR_MODULES = List.of(
    "BrowserAnimationsModule",
    "HttpClientModule",
    "MatMenuModule",
    "MatToolbarModule",
    "MatIconModule",
    "MatButtonModule",
    "MatButtonToggleModule",
    "BrowserModule",
    "AppRoutingModule"
  );

  public static final String SOURCE = "client/angular/security/jwt";
  public static final String SOURCE_WEBAPP = "client/angular/security/jwt/src/main/webapp";
  private static final String APP = "src/main/webapp/app";

  private final ProjectRepository projectRepository;

  public AngularJwtDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addJwtAngular(Project project) {
    addAppJwtFiles(project);
  }

  public void addAppJwtFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);

    addAngularJwtFiles(project);
    updateAngularFilesForJwt(project);
  }

  public void updateAngularFilesForJwt(Project project) {
    String oldHtml = "// jhipster-needle-angular-route";
    String newHtml =
      """
        // jhipster-needle-angular-route
        {
          path: '',
          loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
        },""";
    projectRepository.replaceText(project, APP, APP_ROUTING_MODULE, oldHtml, newHtml);

    oldHtml = "import \\{ NgModule \\} from '@angular/core';";
    newHtml = """
        import { NgModule } from '@angular/core';
        import { ReactiveFormsModule } from '@angular/forms';""";
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);

    oldHtml = "import \\{ AppComponent \\} from './app.component';";
    newHtml =
      """
        import { AppComponent } from './app.component';
        import { AuthInterceptor } from './auth/auth.interceptor';
        """;
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);

    oldHtml = "imports: \\[" + ANGULAR_MODULES.stream().collect(Collectors.joining(", ")) + "\\],";
    newHtml = "imports: \\[" + ANGULAR_MODULES.stream().collect(Collectors.joining(", ")) + ", ReactiveFormsModule\\],";
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);

    oldHtml = "bootstrap: \\[AppComponent\\],";
    newHtml =
      """
        bootstrap: [AppComponent],
          providers: [
            {
              provide: HTTP_INTERCEPTORS,
              useClass: AuthInterceptor,
              multi: true,
            },
          ],""";
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);
  }

  public void addAngularJwtFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);

    List<ProjectFile> files = AngularJwt
      .angularJwtFiles()
      .entrySet()
      .stream()
      .map(entry ->
        ProjectFile
          .forProject(project)
          .withSource(getPath(SOURCE_WEBAPP, entry.getValue()), entry.getKey())
          .withDestinationFolder(getPath(MAIN_WEBAPP, entry.getValue()))
      )
      .toList();
    projectRepository.template(files);
  }
}
