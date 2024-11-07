package tech.jhipster.lite.generator.server.springboot.langchain4j.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.langchain4j.domain.LangChain4JModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class LangChain4JApplicationService {

  private final LangChain4JModuleFactory factory;

  public LangChain4JApplicationService() {
    factory = new LangChain4JModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
