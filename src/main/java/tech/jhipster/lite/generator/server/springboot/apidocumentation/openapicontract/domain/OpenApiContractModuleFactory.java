package tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin.MavenPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class OpenApiContractModuleFactory {

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .pluginManagement(openApiPluginManagement(properties))
        .plugin(openApiPlugin().build())
        .and()
      .build();
    //@formatter:on
  }

  private MavenPluginOptionalBuilder openApiPlugin() {
    return mavenPlugin().groupId("io.github.kbuntrock").artifactId("openapi-maven-plugin");
  }

  private MavenPlugin openApiPluginManagement(JHipsterModuleProperties properties) {
    return openApiPlugin()
      .versionSlug("openapi-maven-plugin")
      .addExecution(pluginExecution().goals("documentation").id("generate-openapi-contract"))
      .configuration(
        """
        <apiConfiguration>
          <library>SPRING_MVC</library>
        </apiConfiguration>
        <apis>
          <api>
            <filename>openapi-contract.yml</filename>
            <locations>
              <location>%s</location>
            </locations>
            <tag>
              <substitutions>
                <sub>
                  <regex>Resource$</regex>
                  <substitute />
                </sub>
              </substitutions>
            </tag>
          </api>
        </apis>\
        """.formatted(properties.basePackage())
      )
      .build();
  }
}
