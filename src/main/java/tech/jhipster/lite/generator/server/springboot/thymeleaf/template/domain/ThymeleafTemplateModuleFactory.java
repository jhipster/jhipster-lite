package tech.jhipster.lite.generator.server.springboot.thymeleaf.template.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class ThymeleafTemplateModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/thymeleaf/template/src/main/resources");
  private static final String TEMPLATES = "templates";
  private static final String TEMPLATES_LAYOUT = "templates/layout";
  private static final String STATIC_CSS = "static/css";
  private static final JHipsterProjectFilePath MAIN_RESOURCES_PATH = path("src/main/resources");
  private static final JHipsterDestination DESTINATION = to(MAIN_RESOURCES_PATH.get());

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(SOURCE.append(TEMPLATES).template("index.html"), toSrcMainResources().append(TEMPLATES).append("index.html"))
        .add(SOURCE.append(TEMPLATES_LAYOUT).template("main.html"), toSrcMainResources().append(TEMPLATES_LAYOUT).append("main.html"))
        .add(SOURCE.append(STATIC_CSS).template("application.css"), DESTINATION.append(STATIC_CSS).append("application.css"))
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildHtmxWebjarsModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mandatoryReplacements()
      .in(MAIN_RESOURCES_PATH.append(TEMPLATES_LAYOUT).append("main.html"))
      .add(lineBeforeText("<!-- jhipster-needle-thymeleaf-main-script -->"), "<script type=\"text/javascript\" th:src=\"@{/webjars/htmx.org/dist/htmx.min.js}\"></script>")
      .and()
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildAlpineWebjarsModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mandatoryReplacements()
      .in(MAIN_RESOURCES_PATH.append(TEMPLATES_LAYOUT).append("main.html"))
      .add(lineBeforeText("<!-- jhipster-needle-thymeleaf-main-script -->"), "<script type=\"text/javascript\" th:src=\"@{/webjars/alpinejs/dist/cdn.js}\"></script>")
      .and()
      .and()
      .build();
    //@formatter:on
  }
}
