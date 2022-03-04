package tech.jhipster.lite.generator.server.springboot.logging.logstash.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Logstash {

  private Logstash() {}

  public static Dependency logstashLogbackEncoderDependency() {
    return Dependency
      .builder()
      .groupId("net.logstash.logback")
      .artifactId("logstash-logback-encoder")
      .version("\\${logstash-logback-encoder.version}")
      .build();
  }
}
