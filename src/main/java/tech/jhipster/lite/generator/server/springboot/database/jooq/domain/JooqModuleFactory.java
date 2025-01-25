package tech.jhipster.lite.generator.server.springboot.database.jooq.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.jooqModuleCodegenConfiguration;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JooqModuleFactory {

  public static final String GENERATE = "generate";
  public static final String JOOQ_CODEGEN = "jooq-codegen";
  public static final String JOOQ_CODEGEN_MAVEN = "jooq-codegen-maven";
  public static final String ORG_JOOQ = "org.jooq";
  public static final String MSSQL_PASSWORD = "yourStrong(!)Password";

  public JHipsterModule buildPostgresql(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(
      properties
      )
      .mavenPlugins()
        .plugin(mavenPlugin()
          .groupId(ORG_JOOQ)
          .artifactId(JOOQ_CODEGEN_MAVEN)
          .versionSlug("jooq")
          .addExecution(pluginExecution().goals(GENERATE).id(JOOQ_CODEGEN).phase(MavenBuildPhase.GENERATE_RESOURCES))
          .configuration(jooqModuleCodegenConfiguration()
            .database(tech.jhipster.lite.module.domain.jooqplugin.DatabaseType.POSTGRESQL)
            .databaseUrl("jdbc:postgresql://localhost:5432/" + properties.projectBaseName().name())
            .user(properties.projectBaseName().name())
            .inputSchema("public")
            .build()
            .getConfiguration())
          .build())
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMariaDB(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(
      properties
      )
      .mavenPlugins()
      .plugin(mavenPlugin()
        .groupId(ORG_JOOQ)
        .artifactId(JOOQ_CODEGEN_MAVEN)
        .versionSlug("jooq")
        .addExecution(pluginExecution().goals(GENERATE).id(JOOQ_CODEGEN).phase(MavenBuildPhase.GENERATE_RESOURCES))
        .configuration(jooqModuleCodegenConfiguration()
          .database(tech.jhipster.lite.module.domain.jooqplugin.DatabaseType.MARIADB)
          .databaseUrl("jdbc:mariadb://localhost:3306/" + properties.projectBaseName().name())
            .user("root")
            .inputSchema(properties.projectBaseName().name())
            .build()
            .getConfiguration())
        .build())
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMySQL(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(
      properties
      )
      .mavenPlugins()
      .plugin(mavenPlugin()
        .groupId(ORG_JOOQ)
        .artifactId(JOOQ_CODEGEN_MAVEN)
        .versionSlug("jooq")
        .addExecution(pluginExecution().goals(GENERATE).id(JOOQ_CODEGEN).phase(MavenBuildPhase.GENERATE_RESOURCES))
        .configuration(jooqModuleCodegenConfiguration()
          .database(tech.jhipster.lite.module.domain.jooqplugin.DatabaseType.MYSQL)
          .databaseUrl("jdbc:mysql://localhost:3306/" + properties.projectBaseName().name())
          .user("root")
          .inputSchema(properties.projectBaseName().name())
          .build()
          .getConfiguration())
        .build())
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMsSQL(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(
      properties
      )
      .mavenPlugins()
      .plugin(mavenPlugin()
        .groupId(ORG_JOOQ)
        .artifactId(JOOQ_CODEGEN_MAVEN)
        .versionSlug("jooq")
        .addExecution(pluginExecution().goals(GENERATE).id(JOOQ_CODEGEN).phase(MavenBuildPhase.GENERATE_RESOURCES))
        .configuration(jooqModuleCodegenConfiguration()
          .database(tech.jhipster.lite.module.domain.jooqplugin.DatabaseType.MSSQL)
          .databaseUrl("jdbc:sqlserver://localhost:1433;database=" + properties.projectBaseName().name() + ";trustServerCertificate=true")
          .user("SA")
          .inputSchema("model")
          .password(MSSQL_PASSWORD)
          .getConfiguration())
        .build())
      .and()
      .build();
    //@formatter:on
  }

  public static JHipsterModule.JHipsterModuleBuilder commonModuleBuilder(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(groupId("org.springframework.boot"), artifactId("spring-boot-starter-jooq"))
      .and();
  }
}
