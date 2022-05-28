package tech.jhipster.lite.generator.server.springboot.banner.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.lite.TestUtils.tmpProjectBuilder;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
class BannerModulePropertiesTest {

  @Test
  void shouldConvertFromProject() {
    Project project = tmpProjectBuilder().build();
    BannerModuleProperties properties = BannerModuleProperties.from(project);

    assertThat(properties.project()).isEqualTo(new JHipsterProjectFolder(project.getFolder()));
  }

  @Test
  void shouldNotConvertFromNullProject() {
    assertThatThrownBy(() -> BannerModuleProperties.from(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }
}
