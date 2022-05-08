package tech.jhipster.lite.generator.client.angular.security.jwt.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.client.angular.core.domain.Angular.*;
import static tech.jhipster.lite.generator.client.angular.core.domain.Angular.APP_COMPONENT_SPEC;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_WEBAPP;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class AngularJwtDomainService implements AngularJwtService {

  public static final String SOURCE = "client/angular";
  public static final String SOURCE_WEBAPP = "client/angular/src/main/webapp";
  private static final String APP = "src/main/webapp/app/common/primary/app";
  public static final String SOURCE_PRIMARY = getPath(SOURCE, APP);
  public static final String DESTINATION_PRIMARY = APP;

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public AngularJwtDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void addJwtAngular(Project project) {
    addAppJwtFiles(project);
  }

  public void addAppJwtFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);

    addJwtDependencies(project);
    addAngularJwtFiles(project);
    addJwtFiles(project);
    updateAngularFilesForJwt(project);
  }

  public void addJwtDependencies(Project project) {
    AngularJwt.jwtDependencies().forEach(dependency -> addDependency(project, dependency));
  }

  public void updateAngularFilesForJwt(Project project) {
    String oldHtml = "<!-- jhipster-needle-angular-jwt-login-form -->";
    String newHtml =
      """
      <mat-card>
        <mat-card-content>
          <div [ngSwitch]="account !== null">
            <form class="login-form" (ngSubmit)="login()" [formGroup]="loginForm" *ngSwitchCase="false">
              <mat-form-field class="form-field-full-width" appearance="fill">
                <mat-label>Login</mat-label>
                <input type="text" name="username" id="username" formControlName="username" #username matInput placeholder="Login" value="">
              </mat-form-field>
              <mat-form-field class="form-field-full-width" appearance="fill">
                <mat-label>Password</mat-label>
                <input type="password" name="password" id="password" formControlName="password" #password matInput placeholder="Password" value="">
              </mat-form-field>
              <button type="submit" mat-flat-button color="primary">Sign in</button>
            </form>
            <div *ngSwitchCase="true">
              <div>You are logged in as user "{{ account?.login }}".</div>
              <button (click)="logout()" mat-flat-button color="primary">Logout</button>
            </div>
          </div>
        </mat-card-content>
      </mat-card>""";
    projectRepository.replaceText(project, APP, APP_COMPONENT_HTML, oldHtml, newHtml);

    oldHtml = "import \\{ Component, OnInit \\} from '@angular/core';";
    newHtml =
      """
        import { Component, OnDestroy, OnInit } from '@angular/core';
        import { FormBuilder, Validators } from '@angular/forms';
        import { Subject, takeUntil } from 'rxjs';
        import { AccountService } from '../../../auth/account.service';
        import { LoginService } from '../../../login/login.service';
        import { Account } from '../../../auth/account.model';""";
    projectRepository.replaceText(project, APP, APP_COMPONENT, oldHtml, newHtml);

    oldHtml = "export class AppComponent implements OnInit \\{";
    newHtml = "export class AppComponent implements OnInit, OnDestroy {";
    projectRepository.replaceText(project, APP, APP_COMPONENT, oldHtml, newHtml);

    oldHtml = "appName = '';";
    newHtml =
      """
      appName = '';

        private readonly destroy\\$ = new Subject<void>();

        account: Account | null = null;

        loginForm = this.fb.group({
          username: [null, [Validators.required]],
          password: [null, [Validators.required]]
        });

        constructor(
          private accountService: AccountService,
          private loginService: LoginService,
          private fb: FormBuilder
        ) {}
      """;
    projectRepository.replaceText(project, APP, APP_COMPONENT, oldHtml, newHtml);

    oldHtml = """
        }
      }
      """;
    newHtml =
      """
          this.accountService
            .getAuthenticationState()
            .pipe(takeUntil(this.destroy\\$))
            .subscribe(account => this.account = account);
        }

        ngOnDestroy(): void {
          this.destroy\\$.next();
          this.destroy\\$.complete();
        }

        login(): void {
          this.loginService
            .login({
              username: this.loginForm.get('username')!.value,
              password: this.loginForm.get('password')!.value,
            })
            .subscribe();
        }

        logout(): void {
          this.loginService.logout();
        }
      }""";
    projectRepository.replaceText(project, APP, APP_COMPONENT, oldHtml, newHtml);

    oldHtml = "import \\{ NgModule \\} from '@angular/core';";
    newHtml =
      """
        import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
        import { NgModule } from '@angular/core';
        import { ReactiveFormsModule } from '@angular/forms';
        import { NgxWebstorageModule } from 'ngx-webstorage';""";
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);

    oldHtml = "import \\{ AppComponent \\} from './app.component';";
    newHtml =
      """
        import { AppComponent } from './app.component';
        import { AuthInterceptor } from '../../../auth/auth.interceptor';
        """;
    projectRepository.replaceText(project, APP, APP_MODULE, oldHtml, newHtml);

    oldHtml =
      "imports: \\[BrowserAnimationsModule, MatToolbarModule, MatIconModule, MatButtonModule, MatButtonToggleModule, MatFormFieldModule, MatInputModule, MatCardModule, MatDividerModule, BrowserModule, AppRoutingModule\\],";
    newHtml =
      "imports: [BrowserAnimationsModule, MatToolbarModule, MatIconModule, MatButtonModule, MatButtonToggleModule, MatFormFieldModule, MatInputModule, MatCardModule, MatDividerModule, BrowserModule, AppRoutingModule, HttpClientModule, ReactiveFormsModule, NgxWebstorageModule.forRoot()],";
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

    oldHtml =
      """
      import \\{ ComponentFixture, TestBed, waitForAsync \\} from '@angular/core/testing';

      import \\{ AppComponent \\} from './app.component';

      describe\\('App Component', \\(\\) => \\{
        let comp: AppComponent;
        let fixture: ComponentFixture<AppComponent>;

        beforeEach\\(
          waitForAsync\\(\\(\\) => \\{
            TestBed.configureTestingModule\\(\\{
              declarations: \\[AppComponent\\],
            \\}\\)
              .overrideTemplate\\(AppComponent, ''\\)
              .compileComponents\\(\\);
          \\}\\)
        \\);

        beforeEach\\(\\(\\) => \\{
          fixture = TestBed.createComponent\\(AppComponent\\);
          comp = fixture.componentInstance;
        \\}\\);

        describe\\('ngOnInit', \\(\\) => \\{
          it\\('should have appName', \\(\\) => \\{
            // WHEN
            comp.ngOnInit\\(\\);

            // THEN
            expect\\(comp.appName\\).toEqual\\('""";
    newHtml =
      """
        import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
        import { HttpClientTestingModule } from '@angular/common/http/testing';
        import { NgxWebstorageModule } from 'ngx-webstorage';
        import { FormBuilder } from '@angular/forms';
        import { of, Subject } from 'rxjs';
        import { LoginService } from '../../../login/login.service';
        import { AccountService } from '../../../auth/account.service';
        import { Account } from '../../../auth/account.model';

        import { AppComponent } from './app.component';

        describe('App Component', () => {
          let comp: AppComponent;
          let fixture: ComponentFixture<AppComponent>;
          let mockAccountService: AccountService;
          let mockLoginService: LoginService;
          const account: Account = {
            activated: true,
            authorities: [],
            email: '',
            firstName: null,
            langKey: '',
            lastName: null,
            login: 'login',
          };

          beforeEach(
            waitForAsync(() => {
              TestBed.configureTestingModule({
                declarations: [AppComponent],
                imports: [HttpClientTestingModule, NgxWebstorageModule.forRoot()],
                providers: [
                  FormBuilder,
                  AccountService,
                  {
                    provide: LoginService,
                    useValue: {
                      login: jest.fn(() => of({})),
                      logout: jest.fn(() => of({})),
                    },
                  },
                ],
              })
                .overrideTemplate(AppComponent, '')
                .compileComponents();
            })
          );

          beforeEach(() => {
            fixture = TestBed.createComponent(AppComponent);
            comp = fixture.componentInstance;
            mockLoginService = TestBed.inject(LoginService);
            mockAccountService = TestBed.inject(AccountService);
          });

          describe('ngOnInit', () => {
            it('should have appName', () => {
              // GIVEN
              const authenticationState = new Subject<Account | null>();
              mockAccountService.getAuthenticationState = jest.fn(() => authenticationState.asObservable());

              // WHEN
              comp.ngOnInit();

              // THEN
              expect(comp.appName).toEqual('""";
    projectRepository.replaceText(project, APP, APP_COMPONENT_SPEC, oldHtml, newHtml);

    oldHtml = """
          \\}\\);
        \\}\\);

      \\}\\);""";
    newHtml =
      """
              expect(mockAccountService.getAuthenticationState).toHaveBeenCalled();

              // THEN
              expect(comp.account).toBeNull();

              // WHEN
              authenticationState.next(account);

              // THEN
              expect(comp.account).toEqual(account);

              // WHEN
              authenticationState.next(null);

              // THEN
              expect(comp.account).toBeNull();
            });
          });

          describe('login', () => {
            it('should authenticate the user', () => {
              // GIVEN
              const credentials = {
                username: 'admin',
                password: 'admin',
              };

              comp.loginForm.patchValue({
                username: 'admin',
                password: 'admin',
              });

              // WHEN
              comp.login();

              // THEN
              expect(mockLoginService.login).toHaveBeenCalledWith(credentials);
            });
          });

          describe('logout', () => {
            it('should logout the user', () => {
              // WHEN
              comp.logout();

              // THEN
              expect(mockLoginService.logout).toHaveBeenCalled();
            });
          });

        });
        """;
    projectRepository.replaceText(project, APP, APP_COMPONENT_SPEC, oldHtml, newHtml);

    oldHtml = "9000";
    newHtml = """
        9000,
                    "proxyConfig": "proxy.conf.json" """;
    projectRepository.replaceText(project, "", "angular.json", oldHtml, newHtml);
  }

  public void addJwtFiles(Project project) {
    project.addConfig("serverPort", 8080);
    AngularJwt.jwtFiles().forEach((file, path) -> projectRepository.template(project, getPath(SOURCE, path), file, getPath("", path)));
  }

  public void addAngularJwtFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);
    AngularJwt
      .angularJwtFiles()
      .forEach((file, path) -> projectRepository.template(project, getPath(SOURCE_WEBAPP, path), file, getPath(MAIN_WEBAPP, path)));
  }

  private void addDependency(Project project, String dependency) {
    npmService
      .getVersion("angular", dependency)
      .ifPresentOrElse(
        version -> npmService.addDependency(project, dependency, version),
        () -> {
          throw new GeneratorException("Dependency not found: " + dependency);
        }
      );
  }
}
