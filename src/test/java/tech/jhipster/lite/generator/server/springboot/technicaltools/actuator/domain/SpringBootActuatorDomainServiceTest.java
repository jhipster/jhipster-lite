package tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleOnProjectWithDefaultPom;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootActuatorDomainServiceTest {

  private static final SpringBootActuatorModuleFactory factory = new SpringBootActuatorModuleFactory();

  @Test
  void shouldCreateSpringBootActuatorModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(FileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleOnProjectWithDefaultPom(module)
      .createFile("pom.xml")
      .containing("<groupId>org.springframework.boot</groupId>")
      .containing("<artifactId>spring-boot-starter-actuator</artifactId>")
      .and()
      .createFile("src/main/resources/config/application.properties")
      .containing("management.endpoints.web.base-path=/management")
      .containing("management.endpoints.web.exposure.include=configprops, env, health, info, logfile, loggers, threaddump")
      .containing("management.endpoint.health.probes.enabled=true")
      .containing("spring.security.oauth2.client.registration.oidc.scope=openid,profile,email")
      .containing("management.endpoint.health.show-details=always");
  }
}
