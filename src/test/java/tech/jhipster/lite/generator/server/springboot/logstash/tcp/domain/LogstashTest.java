package tech.jhipster.lite.generator.server.springboot.logstash.tcp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class LogstashTest {

  @Test
  void shouldLogstashLogbackEncoder() {
    Dependency dependency = Logstash.logstashLogbackEncoderDependency();

    assertThat(dependency.getGroupId()).isEqualTo("net.logstash.logback");
    assertThat(dependency.getArtifactId()).isEqualTo("logstash-logback-encoder");
    assertThat(dependency.getVersion()).hasValue("\\${logstash-logback-encoder.version}");
  }
}
