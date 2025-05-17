package tech.jhipster.lite.generator.server.springboot.thymeleaf.init.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.thymeleaf.init.domain.SpringBootThymeleafModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SpringBootThymeleafApplicationService {

  private final SpringBootThymeleafModuleFactory springBootThymeleaf;

  public SpringBootThymeleafApplicationService() {
    springBootThymeleaf = new SpringBootThymeleafModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return springBootThymeleaf.buildModule(properties);
  }
}
