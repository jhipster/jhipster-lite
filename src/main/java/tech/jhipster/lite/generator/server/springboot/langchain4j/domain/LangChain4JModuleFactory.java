package tech.jhipster.lite.generator.server.springboot.langchain4j.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class LangChain4JModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/langchain4j");
  private static final GroupId LANGCHAIN4J_GROUP_ID = groupId("dev.langchain4j");
  private static final VersionSlug LANGCHAIN4J_VERSION_SLUG = versionSlug("langchain4j");

  private static final String PROPERTIES = "properties";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("Dev tools"), SOURCE.template("langchain4j.md"))
      .javaDependencies()
      .addDependency(LANGCHAIN4J_GROUP_ID,
        artifactId("langchain4j-spring-boot-starter"),
        LANGCHAIN4J_VERSION_SLUG)
      .addDependency(LANGCHAIN4J_GROUP_ID,
        artifactId("langchain4j-open-ai-spring-boot-starter"),
        LANGCHAIN4J_VERSION_SLUG)
      .and()
      .files()
      .and()
      .springMainProperties()
      .set(propertyKey("langchain4j.open-ai.chat-model.api-key"), propertyValue("${OPENAI_API_KEY}"))
      .set(propertyKey("langchain4j.open-ai.chat-model.model-name"), propertyValue("gpt-4o-mini"))
      .set(propertyKey("langchain4j.open-ai.chat-model.log-requests"), propertyValue("true"))
      .set(propertyKey("langchain4j.open-ai.chat-model.log-responses"), propertyValue("true"))
      .and()
      .build();
    //@formatter:on
  }
}
