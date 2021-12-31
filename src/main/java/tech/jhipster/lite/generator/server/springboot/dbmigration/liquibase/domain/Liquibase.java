package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain;

public class Liquibase {

  public static final String NEEDLE_LIQUIBASE = "<!-- jhipster-needle-liquibase-add-changelog -->";

  private Liquibase() {}

  public static String getIncludeLine(String path, String fileName) {
    return new StringBuilder()
      .append("<include file=\"config/liquibase/changelog/")
      .append(path)
      .append("/")
      .append(fileName)
      .append("\" relativeToChangelogFile=\"false\"/>")
      .toString();
  }
}
