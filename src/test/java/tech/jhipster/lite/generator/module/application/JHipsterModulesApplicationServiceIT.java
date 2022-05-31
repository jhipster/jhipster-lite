package tech.jhipster.lite.generator.module.application;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.generator.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.generator.module.domain.javadependency.GroupId;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class JHipsterModulesApplicationServiceIT {

  @Autowired
  JHipsterModulesApplicationService jHipsterModulesApplicationService;

  @Test
  void shouldGetDependency() {
    Project project = tmpProjectWithPomXml();

    DependencyId dependencyId = new DependencyId(new GroupId("org.assertj"), new ArtifactId("assertj-core"));
    Optional<JavaDependency> dependency = jHipsterModulesApplicationService.getDependency(project.getFolder(), dependencyId);

    assertThat(dependency).isNotEmpty();
    assertThat(dependency.get().artifactId().artifactId()).isEqualTo("assertj-core");
  }
}
