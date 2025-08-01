package tech.jhipster.lite.generator.client.angular.admin.health.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class AngularHealthModuleFactoryTest {

  private static final AngularHealthModuleFactory factory = new AngularHealthModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, appRouting(), appComponent(), appRoutingSpec(), appMainFile())
      .hasPrefixedFiles("src/main/webapp/app/admin", "admin.routes.ts", "admin.routes.spec.ts")
      .hasPrefixedFiles("src/main/webapp/app/config", "application-config.service.spec.ts", "application-config.service.ts")
      .hasPrefixedFiles(
        "src/main/webapp/app/admin/health",
        "health.css",
        "health.html",
        "health.ts",
        "health.spec.ts",
        "health.model.ts",
        "health.service.spec.ts",
        "health.service.ts"
      )
      .hasPrefixedFiles(
        "src/main/webapp/app/admin/health/modal",
        "health-modal.css",
        "health-modal.html",
        "health-modal.ts",
        "health-modal.spec.ts"
      )
      .hasFile("src/main/webapp/app/app.route.ts")
      .containing(
        """
          {
            path: 'admin',
            loadChildren: () => import('./admin/admin.routes'),
          },
        """
      )
      .and()
      .hasFile("src/main/webapp/app/app.html")
      .containing("<a routerLink=\"admin/health\" mat-menu-item><span>Health</span></a>")
      .and()
      .hasFile("src/main/webapp/app/app.route.spec.ts")
      .containing(
        """
          it('should navigate on admin endpoint', () => {
            router.navigateByUrl('/admin');
          });
        """
      );
  }

  private ModuleFile appRouting() {
    return file("src/test/resources/projects/angular/app.route.ts", "src/main/webapp/app/app.route.ts");
  }

  private ModuleFile appComponent() {
    return file("src/test/resources/projects/angular/app.html", "src/main/webapp/app/app.html");
  }

  private ModuleFile appRoutingSpec() {
    return file("src/test/resources/projects/angular/app.route.spec.ts", "src/main/webapp/app/app.route.spec.ts");
  }

  private static ModuleFile appMainFile() {
    return file("src/test/resources/projects/angular/main.ts", "src/main/webapp/main.ts");
  }
}
