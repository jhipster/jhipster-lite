package tech.jhipster.lite.generator.server.springboot.database.mongodb.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Mongodb {

  public static final String MONGODB_DOCKER_IMAGE_NAME = "mongo";

  private Mongodb() {}

  public static String getMongodbDockerImageName() {
    return MONGODB_DOCKER_IMAGE_NAME;
  }

  public static Dependency mongodbDriver() {
    return Dependency.builder().groupId("org.mongodb").artifactId("mongodb-driver-sync").build();
  }
}
