package tech.jhipster.lite.generator.client.vue.security.oauth2_keycloak.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class VueOAuth2KeycloakModulesFactoryTest {

  private static final VueOAuth2KeycloakModulesFactory factory = new VueOAuth2KeycloakModulesFactory();

  @Test
  void shouldBuildVueOAuth2KeycloakModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhipster")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFile("package.json")
        .containing(nodeDependency("keycloak-js"))
        .and()
      .hasFiles("src/main/webapp/app/auth/application/AuthProvider.ts")
      .hasFiles("src/main/webapp/app/auth/domain/AuthRepository.ts")
      .hasFiles("src/main/webapp/app/auth/domain/AuthenticatedUser.ts")
      .hasFiles("src/main/webapp/app/auth/infrastructure/secondary/KeycloakAuthRepository.ts")
      .hasFiles("src/main/webapp/app/auth/infrastructure/secondary/KeycloakHttp.ts")
      .hasFile("src/main/webapp/app/main.ts")
        .containing("""
          import { provideForAuth } from '@/auth/application/AuthProvider';
          import { KeycloakHttp } from '@/auth/infrastructure/secondary/KeycloakHttp';
          import axios from 'axios';
          import Keycloak from 'keycloak-js';
          // jhipster-needle-main-ts-import\
          """
        )
        .containing("""
          const keycloakHttp = new KeycloakHttp(
            new Keycloak({
              url: 'http://localhost:9080',
              realm: 'jhipster',
              clientId: 'web_app',
            }),
          );

          provideForAuth(keycloakHttp);
          // jhipster-needle-main-ts-provider\
          """
        );
    //@formatter:on
  }
}
