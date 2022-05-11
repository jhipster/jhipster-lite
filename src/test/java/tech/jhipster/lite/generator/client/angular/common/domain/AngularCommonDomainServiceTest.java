package tech.jhipster.lite.generator.client.angular.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mockStatic;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class AngularCommonDomainServiceTest {

  @InjectMocks
  AngularCommonDomainService angularCommonDomainService;

  private void assertWrittenFileContent(
    Project project,
    MockedStatic<FileUtils> fileUtils,
    String fullFilePath,
    String expectedNewFileContent
  ) {
    ArgumentCaptor<String> stringArgCaptor = ArgumentCaptor.forClass(String.class);
    fileUtils.verify(() -> FileUtils.write(eq(fullFilePath), stringArgCaptor.capture(), eq(project.getEndOfLine())));
    assertThat(stringArgCaptor.getValue().lines().toList()).isEqualTo(expectedNewFileContent.lines().toList());
  }

  @Nested
  class AddImportsTest {

    @Test
    void shouldAddAfterLastExistingImport() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String imports =
        """
        import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
        import {LoginComponent} from './login/login.component';
        """;
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn(
            """
            import { AppRoutingModule } from './app-routing.module';
            import { AppComponent } from './app.component';
            import {
              HttpRequest,
              HttpHandler,
              HttpEvent,
              HttpInterceptor
            } from '@angular/common/http';
            // import { x } from y;
            """
          );

        // When
        angularCommonDomainService.addImports(project, filePath, imports);

        // Then
        assertWrittenFileContent(
          project,
          fileUtils,
          fullFilePath,
          """
          import { AppRoutingModule } from './app-routing.module';
          import { AppComponent } from './app.component';
          import {
            HttpRequest,
            HttpHandler,
            HttpEvent,
            HttpInterceptor
          } from '@angular/common/http';
          import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
          import {LoginComponent} from './login/login.component';

          // import { x } from y;
          """
        );
      }
    }

    @Test
    void shouldAddInTopOfFile() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String imports =
        """
        import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
        import {LoginComponent} from './login/login.component';
        """;
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn("""
            @Injectable({ providedIn: 'root' })
            export class MyService {
            }
            """);

        // When
        angularCommonDomainService.addImports(project, filePath, imports);

        // Then
        assertWrittenFileContent(
          project,
          fileUtils,
          fullFilePath,
          """
          import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
          import {LoginComponent} from './login/login.component';

          @Injectable({ providedIn: 'root' })
          export class MyService {
          }
          """
        );
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String imports =
        """
        import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
        import {LoginComponent} from './login/login.component';
        """;
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addImports(project, filePath, imports))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String imports =
        """
        import {HttpAuthInterceptor} from '../../../auth/infrastructure/primary/http-auth.interceptor';
        import {LoginComponent} from './login/login.component';
        """;
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("");
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addImports(project, filePath, imports))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }

  @Nested
  class AddConstantsTest {

    private static Stream<Arguments> contentFileInputsProvider() {
      return Stream.of(
        Arguments.of(
          """
            import { AppComponent } from './app.component';

            @NgModule({
              declarations: [AppComponent],
            })
            """,
          """
            import { AppComponent } from './app.component';

            const x = 3;

            @NgModule({
              declarations: [AppComponent],
            })
            """,
          "Before decorated element (multiple lines)"
        ),
        Arguments.of(
          """
            import {Injectable} from '@angular/core';

            @Injectable({ providedIn: 'root' })
            """,
          """
            import {Injectable} from '@angular/core';

            const x = 3;

            @Injectable({ providedIn: 'root' })
            """,
          "Before decorated element (one line)"
        ),
        Arguments.of(
          """
            const fn1 = () => 'ok';
            """,
          """
            const fn1 = () => 'ok';

            const x = 3;
            """,
          "In the end of file, when no decorator"
        )
      );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("contentFileInputsProvider")
    void shouldAddConstant(String inputFile, String expectedContentFile, @SuppressWarnings("unused") String testName) {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String constants = """
        const x = 3;
        """;
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn(inputFile);

        // When
        angularCommonDomainService.addConstants(project, filePath, constants);

        // Then
        assertWrittenFileContent(project, fileUtils, fullFilePath, expectedContentFile);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String constants = "const a = 10;";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addConstants(project, filePath, constants))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String constants = "const a = 10;";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("");
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addConstants(project, filePath, constants))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }

  @Nested
  class AddDeclarationsTest {

    private static Stream<Arguments> contentFileInputsProvider() {
      return Stream.of(
        Arguments.of(
          """
            @NgModule({
              declarations: [AppComponent],
            })
            """,
          """
            @NgModule({
              declarations: [AppComponent,
                LoginComponent,
              ],
            })
            """,
          "Declarations in one line"
        ),
        Arguments.of(
          """
            @NgModule({
              declarations: [
                AppComponent
              ],
            })
            """,
          """
            @NgModule({
              declarations: [
                AppComponent,
                LoginComponent,
              ],
            })
            """,
          "Declaration in multiple lines"
        )
      );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("contentFileInputsProvider")
    void shouldAddDeclarations(String inputFile, String expectedContentFile, @SuppressWarnings("unused") String testName) {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String declarations = "    LoginComponent,";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn(inputFile);

        // When
        angularCommonDomainService.addDeclarations(project, filePath, declarations);

        // Then
        assertWrittenFileContent(project, fileUtils, fullFilePath, expectedContentFile);
      }
    }

    @Test
    void shouldNotAddWhenDeclarationArrayNotFound() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String declarations = "LoginComponent";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("");
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addDeclarations(project, filePath, declarations))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String declarations = "LoginComponent";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addDeclarations(project, filePath, declarations))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String declarations = "LoginComponent";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn(
            """
          @NgModule({
              declarations: [
                AppComponent
              ],
            })
          """
          );
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addDeclarations(project, filePath, declarations))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }

  @Nested
  class AddProvidersTest {

    private static Stream<Arguments> contentFileInputsProvider() {
      return Stream.of(
        Arguments.of(
          """
          @NgModule({
            declarations: [AppComponent],
          })
          """,
          """
          @NgModule({
            declarations: [AppComponent],
            providers: [
              { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
            ],
          })
          """,
          "New providers array after declarations (and existing comma)"
        ),
        Arguments.of(
          """
          @NgModule({
            declarations: [AppComponent]
          })
          """,
          """
          @NgModule({
            declarations: [AppComponent],
            providers: [
              { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
            ]
          })
          """,
          "New providers array after declarations (without comma)"
        ),
        Arguments.of(
          """
          @NgModule({
            declarations: [
              AppComponent
            ],
            providers: [
              { provide: 'providerName', useClass: MyProviderClass, multi: true, },
            ]
          })
          """,
          """
          @NgModule({
            declarations: [
              AppComponent
            ],
            providers: [
              { provide: 'providerName', useClass: MyProviderClass, multi: true, },
              { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
            ]
          })
          """,
          "Provider in existing providers array with values"
        ),
        Arguments.of(
          """
          @NgModule({
            declarations: [
              AppComponent
            ],
            providers: [],
          })
          """,
          """
          @NgModule({
            declarations: [
              AppComponent
            ],
            providers: [
              { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, },
            ],
          })
          """,
          "Provider in existing empty providers array"
        )
      );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("contentFileInputsProvider")
    void shouldAddProviders(String inputFile, String expectedContentFile, @SuppressWarnings("unused") String testName) {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String providers = "    { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, }, ";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn(inputFile);

        // When
        angularCommonDomainService.addProviders(project, filePath, providers);

        // Then
        assertWrittenFileContent(project, fileUtils, fullFilePath, expectedContentFile);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String providers = "{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, }";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addProviders(project, filePath, providers))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String providers = "{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true, }";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn(
            """
          @NgModule({
              declarations: [
                AppComponent
              ],
            })
          """
          );
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addDeclarations(project, filePath, providers))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }

  @Nested
  class AddEnvVariablesTest {

    private static Stream<Arguments> contentFileInputsProvider() {
      return Stream.of(
        Arguments.of(
          """
          export const environment = {
            production: false
          };
          """,
          """
          export const environment = {
            production: false,
            value1: true,
          };
          """,
          "Add after key / value"
        ),
        Arguments.of(
          """
          export const environment = {
            production: false,
            objet: {
              key: 'value',
            },
          };
          """,
          """
          export const environment = {
            production: false,
            objet: {
              key: 'value',
            },
            value1: true,
          };
          """,
          "After an object"
        )
      );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("contentFileInputsProvider")
    void shouldAddEnvVariables(String inputFile, String expectedContentFile, @SuppressWarnings("unused") String testName) {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String envVariables = "  value1: true,";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn(inputFile);

        // When
        angularCommonDomainService.addEnvVariables(project, filePath, envVariables);

        // Then
        assertWrittenFileContent(project, fileUtils, fullFilePath, expectedContentFile);
      }
    }

    @Test
    void shouldNotAddWhenEnvVariablePrefixNotFound() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String envVariables = "  value1: true,";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("");

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addEnvVariables(project, filePath, envVariables))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String envVariables = "  value1: true,";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addEnvVariables(project, filePath, envVariables))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String envVariables = "  value1: true,";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn("""
          export const environment = {
            production: false,
          };
          """);
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.addEnvVariables(project, filePath, envVariables))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }

  @Nested
  class PrependHtmlTest {

    @Test
    void shouldAddHtmlTag() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String tagRegex = "(<div.+id=\\\"app\\\".*>)";
      String htmlTag = "  <app-login></app-login>";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils
          .when(() -> FileUtils.read(fullFilePath))
          .thenReturn(
            """
          <div id="elem1"></div>
          <div id="app">
            <div>content</div>
          </div>
          """
          );

        // When
        angularCommonDomainService.prependHtml(project, filePath, htmlTag, tagRegex);

        // Then
        String expectedNewFileContent =
          """
           <div id="elem1"></div>
           <div id="app">
             <app-login></app-login>
             <div>content</div>
           </div>
           """;
        assertWrittenFileContent(project, fileUtils, fullFilePath, expectedNewFileContent);
      }
    }

    @Test
    void shouldNotAddWhenTagToReplaceNotFound() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String tagRegex = "(<div.+id=\\\"app\\\".*>)";
      String htmlTag = "  <app-login></app-login>";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("""
          <div></div>
          """);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.prependHtml(project, filePath, htmlTag, tagRegex))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeRead() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String tagRegex = "(<div.+id=\\\"app\\\".*>)";
      String htmlTag = "  <app-login></app-login>";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.prependHtml(project, filePath, htmlTag, tagRegex))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }

    @Test
    void shouldNotAddWhenFileCannotBeWritten() {
      // Given
      Project project = Project.builder().folder("/project/path").build();
      String filePath = "file/path";
      String tagRegex = "(<div>)";
      String htmlTag = "  <app-login></app-login>";
      try (MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
        String fullFilePath = "/project/path/file/path";
        fileUtils.when(() -> FileUtils.getPath("/project/path", filePath)).thenReturn(fullFilePath);
        fileUtils.when(() -> FileUtils.read(fullFilePath)).thenReturn("<div></div>");
        fileUtils.when(() -> FileUtils.write(anyString(), anyString(), anyString())).thenThrow(IOException.class);

        // When + Then
        assertThatThrownBy(() -> angularCommonDomainService.prependHtml(project, filePath, htmlTag, tagRegex))
          .isInstanceOf(GeneratorException.class)
          .hasMessageContaining(fullFilePath);
      }
    }
  }
}
