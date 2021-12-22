package tech.jhipster.lite.generator.server.springboot.core.domain;

public class SpringBoot {

  public static final String SPRING_BOOT_VERSION = "2.6.2";
  public static final String NEEDLE_APPLICATION_PROPERTIES = "# jhipster-needle-application-properties";
  public static final String NEEDLE_APPLICATION_FAST_PROPERTIES = "# jhipster-needle-application-fast-properties";
  public static final String NEEDLE_APPLICATION_TEST_PROPERTIES = "# jhipster-needle-application-test-properties";
  public static final String NEEDLE_LOGBACK_LOGGER = "<!-- jhipster-needle-logback-add-log -->";
  public static final String APPLICATION_PROPERTIES = "application.properties";
  public static final String APPLICATION_FAST_PROPERTIES = "application-fast.properties";
  public static final String LOGGING_CONFIGURATION = "logback-spring.xml";
  public static final String LOGGING_TEST_CONFIGURATION = "logback.xml";

  private SpringBoot() {}

  public static String getVersion() {
    return SPRING_BOOT_VERSION;
  }
}
