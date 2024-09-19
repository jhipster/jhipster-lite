package tech.jhipster.lite.generator.client.vue.security.oauth2_keycloak.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.npm.JHLiteNpmVersionSource.*;

import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class VueOAuth2KeycloakModulesFactory {

  private static final JHipsterSource SOURCE = from("client/vue");
  private static final JHipsterSource APP_SOURCE = from("client/vue/webapp/app");
  private static final JHipsterSource DOCUMENTATION_SOURCE = SOURCE.append("documentation");

  private static final JHipsterDestination MAIN_DESTINATION = to("src/main/webapp/app");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    Indentation indentation = properties.indentation();

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("Vue Authentication Components"),
                      DOCUMENTATION_SOURCE.file("vue-authentication-components.md"))
      .packageJson()
        .addDependency(packageName("keycloak-js"), COMMON)
        .and()
      .files()
        .batch(APP_SOURCE.append("auth"), MAIN_DESTINATION.append("auth"))
          .addTemplate("application/AuthProvider.ts")
          .addTemplate("domain/AuthRepository.ts")
          .addTemplate("domain/AuthenticatedUser.ts")
          .addTemplate("infrastructure/secondary/KeycloakAuthRepository.ts")
          .addTemplate("infrastructure/secondary/KeycloakHttp.ts")
          .and()
        .and()
      .mandatoryReplacements()
        .in(path("src/main/webapp/app/main.ts"))
          .add(lineBeforeText("// jhipster-needle-main-ts-import"),
            """
            import { provideForAuth } from '@/auth/application/AuthProvider';
            import { KeycloakHttp } from '@/auth/infrastructure/secondary/KeycloakHttp';
            import axios from 'axios';
            import Keycloak from 'keycloak-js';\
            """
          )
          .add(lineBeforeText("// jhipster-needle-main-ts-provider"),
            """
            const keycloakHttp = new KeycloakHttp(
            %snew Keycloak({
            %surl: 'http://localhost:9080',
            %srealm: 'jhipster',
            %sclientId: 'web_app',
            %s}),
            );

            provideForAuth(keycloakHttp);\
            """.formatted(indentation.spaces(),
                          indentation.times(2),
                          indentation.times(2),
                          indentation.times(2),
                          indentation.spaces())
          )
          .and()
        .and()
      .build();
    //@formatter:on
  }
}
