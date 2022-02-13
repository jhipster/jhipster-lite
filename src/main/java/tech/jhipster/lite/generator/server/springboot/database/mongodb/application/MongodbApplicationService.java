package tech.jhipster.lite.generator.server.springboot.database.mongodb.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.database.mongodb.domain.MongodbService;

@Service
public class MongodbApplicationService {

  private final MongodbService mongodbService;

  public MongodbApplicationService(MongodbService mongodbService) {
    this.mongodbService = mongodbService;
  }

  public void init(Project project) {
    mongodbService.init(project);
  }

  public void addSpringDataMongodb(Project project) {
    mongodbService.addSpringDataMongodb(project);
  }

  public void addMongodbDriver(Project project) {
    mongodbService.addMongodbDriver(project);
  }

  public void addDockerCompose(Project project) {
    mongodbService.addDockerCompose(project);
  }

  public void addJavaFiles(Project project) {
    mongodbService.addJavaFiles(project);
  }

  public void addConfigurationFiles(Project project) {
    mongodbService.addConfigurationFiles(project);
  }

  public void addProperties(Project project) {
    mongodbService.addProperties(project);
  }

  public void addLogger(Project project) {
    mongodbService.addLoggerInConfiguration(project);
  }

  public void addTestContainers(Project project) {
    mongodbService.addTestcontainers(project);
  }
}
