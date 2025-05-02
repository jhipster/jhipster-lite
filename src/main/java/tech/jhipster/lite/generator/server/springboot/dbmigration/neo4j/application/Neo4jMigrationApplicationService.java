package tech.jhipster.lite.generator.server.springboot.dbmigration.neo4j.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.dbmigration.neo4j.domain.Neo4jMigrationModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class Neo4jMigrationApplicationService {

  private final Neo4jMigrationModuleFactory neo4jMigrations;

  public Neo4jMigrationApplicationService() {
    neo4jMigrations = new Neo4jMigrationModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return neo4jMigrations.buildModule(properties);
  }
}
