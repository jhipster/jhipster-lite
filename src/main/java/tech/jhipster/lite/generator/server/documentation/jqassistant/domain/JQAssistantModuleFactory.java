package tech.jhipster.lite.generator.server.documentation.jqassistant.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.VERIFY;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin.MavenPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JQAssistantModuleFactory {

  private static final String JQASSISTANT_CONFIGURATION_FILE = ".jqassistant.yml";
  private static final String SOURCE = "server/documentation/jqassistant";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(from(SOURCE).append(JQASSISTANT_CONFIGURATION_FILE), to(JQASSISTANT_CONFIGURATION_FILE))
        .and()
      .javaDependencies()
        .addDependencyManagement(jQAssistantContextMapperDependency())
        .and()
      .mavenPlugins()
        .pluginManagement(jQAssistantPluginManagement())
        .plugin(jQAssistantPluginBuilder().build())
        .pluginManagement(asciidoctorPluginManagement())
        .plugin(asciidoctorPluginBuilder().build())
        .and()
      .build();
    //@formatter:on
  }

  private static JavaDependency jQAssistantContextMapperDependency() {
    return JavaDependency.builder()
      .groupId("org.jqassistant.plugin")
      .artifactId("jqassistant-context-mapper-plugin")
      .versionSlug("jqassistant-context-mapper-plugin")
      .build();
  }

  private static MavenPluginOptionalBuilder jQAssistantPluginBuilder() {
    return mavenPlugin().groupId("com.buschmais.jqassistant").artifactId("jqassistant-maven-plugin");
  }

  private static MavenPlugin jQAssistantPluginManagement() {
    return jQAssistantPluginBuilder()
      .versionSlug("jqassistant")
      .addExecution(pluginExecution().goals("scan", "analyze").id("default-cli"))
      .build();
  }

  private static MavenPluginOptionalBuilder asciidoctorPluginBuilder() {
    return mavenPlugin().groupId("org.asciidoctor").artifactId("asciidoctor-maven-plugin");
  }

  private static MavenPlugin asciidoctorPluginManagement() {
    return asciidoctorPluginBuilder()
      .versionSlug("asciidoctor-maven-plugin")
      .configuration(
        """
        <backend>html5</backend>
        <attributes>
          <jqassistant-report-path>${project.build.directory}/jqassistant/jqassistant-report.xml</jqassistant-report-path>
        </attributes>
        """
      )
      .addExecution(pluginExecution().goals("process-asciidoc").id("html").phase(VERIFY))
      .addDependency(jqassistantAsciidoctorjExtensions())
      .build();
  }

  private static JavaDependency jqassistantAsciidoctorjExtensions() {
    return javaDependency()
      .groupId("org.jqassistant.tooling.asciidoctorj")
      .artifactId("jqassistant-asciidoctorj-extensions")
      .versionSlug("jqassistant-asciidoctorj-extensions")
      .build();
  }
}
