package tech.jhipster.lite.generator.server.springboot.logging.logstash.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Logstash {

  private static final String LOGSTASH_LOGBACK_ENCODER_VERSION = "7.0.1";

  private Logstash() {}

  public static Dependency logstashLogbackEncoderDependency() {
    return Dependency
      .builder()
      .groupId("net.logstash.logback")
      .artifactId("logstash-logback-encoder")
      .version("\\${logstash-logback-encoder.version}")
      .build();
  }

  public static String getLogstashLogbackEncoderVersion() {
    return LOGSTASH_LOGBACK_ENCODER_VERSION;
  }
}
