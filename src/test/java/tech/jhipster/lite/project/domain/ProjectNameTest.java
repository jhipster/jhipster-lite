package tech.jhipster.lite.project.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class ProjectNameTest {

  @Test
  void shouldGetForamttedFilename() {
    assertThat(new ProjectName("This is not formatted$").filename()).isEqualTo("this-is-not-formatted.zip");
  }
}
