package tech.jhipster.lite.module.domain.javabuildplugin;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javabuild.command.AddBuildPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;

@UnitTest
class JHipsterModuleJavaBuildPluginTest {

  @Test
  void shouldAddNewDependencyManagement() {
    JavaBuildCommands changes = JHipsterModuleJavaBuildPlugin
      .builder(emptyModuleBuilder())
      .pluginManagement(mavenEnforcerPluginManagement())
      .build()
      .buildChanges(currentJavaDependenciesVersion());

    assertThat(changes.get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(AddBuildPluginManagement.builder().plugin(mavenEnforcerPluginManagement()).pluginVersion(mavenEnforcerVersion()));
  }

  @Test
  void shouldAddNewDependency() {
    JavaBuildCommands changes = JHipsterModuleJavaBuildPlugin
      .builder(emptyModuleBuilder())
      .plugin(mavenEnforcerPlugin())
      .build()
      .buildChanges(currentJavaDependenciesVersion());

    assertThat(changes.get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(AddDirectJavaBuildPlugin.builder().javaBuildPlugin(mavenEnforcerPlugin()).build());
  }
}
