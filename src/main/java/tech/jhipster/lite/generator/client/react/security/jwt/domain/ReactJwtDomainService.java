package tech.jhipster.lite.generator.client.react.security.jwt.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_WEBAPP;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVASCRIPT;

import java.util.List;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class ReactJwtDomainService implements ReactJwtService {

  public static final String SOURCE = "client/react";
  public static final String SOURCE_APP = MAIN_WEBAPP + "/app/common/primary/app";
  public static final String APP = "App.tsx";

  public static final String SOURCE_APP_SERVICES = MAIN_WEBAPP + "/app/common/services";

  public static final String SOURCE_LOGIN_FORM = MAIN_WEBAPP + "/app/login/primary/loginForm";

  public static final String SOURCE_LOGIN_MODAL = MAIN_WEBAPP + "/app/login/primary/loginModal";

  public static final String SOURCE_LOGIN_SERVICES = MAIN_WEBAPP + "/app/login/services";

  public static final String PATH_TEST_LOGIN_FORM = TEST_JAVASCRIPT + "/login/primary/loginForm";

  public static final String PATH_TEST_LOGIN_MODAL = TEST_JAVASCRIPT + "/login/primary/loginModal";

  public static final String PATH_TEST_LOGIN_SERVICES = TEST_JAVASCRIPT + "/login/services";

  public static final String PATH_TEST_APP_SERVICES = TEST_JAVASCRIPT + "/common/services";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public ReactJwtDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void addLoginReact(Project project) {
    addDependencies(project);
    addDevDependencies(project);
    addReactLoginFiles(project);
    updateReactFilesForJWT(project);
  }

  public void addDevDependencies(Project project) {
    ReactJwt
      .devDependencies()
      .forEach(dependency ->
        npmService
          .getVersionInReact(dependency)
          .ifPresentOrElse(
            version -> npmService.addDevDependency(project, dependency, version),
            () -> {
              throw new GeneratorException("DevDependency not found: " + dependency);
            }
          )
      );
  }

  public void addDependencies(Project project) {
    ReactJwt
      .dependencies()
      .forEach(dependency ->
        npmService
          .getVersionInReact(dependency)
          .ifPresentOrElse(
            version -> npmService.addDependency(project, dependency, version),
            () -> {
              throw new GeneratorException("Dependency not found: " + dependency);
            }
          )
      );
  }

  public void updateReactFilesForJWT(Project project) {
    String oldHtml = "import './App.css';";
    String newHtml = """
      import LoginForm from '@/login/primary/loginForm';

      import './App.css';
      """;

    projectRepository.replaceText(project, SOURCE_APP, APP, oldHtml, newHtml);

    oldHtml =
      """
              <p>
                Edit&nbsp;
                <code>src/main/webapp/app/common/primary/app/App.tsx</code> to test hot module replacement.
              </p>
            </div>
      """;
    newHtml =
      """
              <p>
                Edit&nbsp;
                <code>src/main/webapp/app/common/primary/app/App.tsx</code> to test hot module replacement.
              </p>
              <LoginForm />
            </div>
      """;

    projectRepository.replaceText(project, SOURCE_APP, APP, oldHtml, newHtml);

    oldHtml = "  -moz-osx-font-smoothing: grayscale;";
    newHtml =
      """
         -moz-osx-font-smoothing: grayscale;
         display: flex;
         flex-direction: column;
         justify-content:center;
         align-items: center;
      """;

    projectRepository.replaceText(project, SOURCE_APP, "App.css", oldHtml, newHtml);
  }

  public void addReactLoginFiles(Project project) {
    //
    //    List<ProjectFile> files = ReactJwt
    //      .reactJwtFiles()
    //      .entrySet()
    //      .stream()
    //      .map(entry ->
    //        ProjectFile
    //          .forProject(project)
    //          .withSource(getPath(SOURCE, entry.getValue()), entry.getKey())
    //          .withDestination(entry.getValue(), entry.getKey())
    //      )
    //      .toList();
    //
    //    projectRepository.template(files);
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_APP_SERVICES), "storage.ts")
        .withDestination(SOURCE_APP_SERVICES, "storage.ts")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_LOGIN_FORM), "index.tsx")
        .withDestination(SOURCE_LOGIN_FORM, "index.tsx")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_LOGIN_MODAL), "index.tsx")
        .withDestination(SOURCE_LOGIN_MODAL, "index.tsx")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_LOGIN_MODAL), "interface.d.ts")
        .withDestination(SOURCE_LOGIN_MODAL, "interface.d.ts")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_LOGIN_MODAL), "LoginModal.scss")
        .withDestination(SOURCE_LOGIN_MODAL, "LoginModal.scss")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, SOURCE_LOGIN_SERVICES), "login.ts")
        .withDestination(SOURCE_LOGIN_SERVICES, "login.ts")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, PATH_TEST_LOGIN_SERVICES), "login.test.ts")
        .withDestination(PATH_TEST_LOGIN_SERVICES, "login.test.ts")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, PATH_TEST_LOGIN_FORM), "index.test.tsx")
        .withDestination(PATH_TEST_LOGIN_FORM, "index.test.tsx")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, PATH_TEST_LOGIN_MODAL), "index.test.tsx")
        .withDestination(PATH_TEST_LOGIN_MODAL, "index.test.tsx")
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getPath(SOURCE, PATH_TEST_APP_SERVICES), "storage.test.ts")
        .withDestination(PATH_TEST_APP_SERVICES, "storage.test.ts")
    );
  }
}
