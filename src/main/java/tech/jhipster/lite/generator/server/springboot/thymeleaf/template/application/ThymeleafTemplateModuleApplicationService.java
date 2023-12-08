package tech.jhipster.lite.generator.server.springboot.thymeleaf.template.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.thymeleaf.template.domain.ThymeleafTemplateModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class ThymeleafTemplateModuleApplicationService {

  private final ThymeleafTemplateModuleFactory thymeleafTemplateFactory;

  public ThymeleafTemplateModuleApplicationService() {
    thymeleafTemplateFactory = new ThymeleafTemplateModuleFactory();
  }

  public JHipsterModule buildThymeleafTemplateModule(JHipsterModuleProperties properties) {
    return thymeleafTemplateFactory.buildModule(properties);
  }
}
