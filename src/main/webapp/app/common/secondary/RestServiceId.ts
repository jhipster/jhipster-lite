import { Service } from '@/common/domain/Service';

export type RestServiceId = string;

const SERVICES: Record<string, Service> = {
  'aop-logging': Service.AOP_LOGGING,
  angular: Service.ANGULAR,
  'angular-jwt': Service.ANGULAR_WITH_JWT,
  download: Service.DOWNLOAD,
  flyway: Service.FLYWAY,
  'flyway-user-and-authority-changelogs': Service.FLYWAY_WITH_USERS_AND_AUTHORITY_CHANGELOGS,
  'frontend-maven-plugin': Service.FRONTEND_MAVEN_PLUGIN,
  init: Service.INITIALIZATION,
  'jacoco-check-min-coverage': Service.JACOCO_CHECK_MINIMAL_COVERAGE,
  'java-base': Service.JAVA_BASE,
  liquibase: Service.LIQUIBASE,
  'liquibase-user-and-authority-changelogs': Service.LIQUIBASE_WITH_USERS_AND_AUTHORITY_CHANGELOGS,
  logstash: Service.LOGSTASH,
  'maven-java': Service.MAVEN_JAVA,
  'codespaces-setup': Service.CODESPACES_SETUP,
  'gitpod-setup': Service.GITPOD_SETUP,
  mariadb: Service.MARIADB,
  mysql: Service.MYSQL,
  mongodb: Service.MONGODB,
  mongock: Service.MONGOCK,
  postgresql: Service.POSTGRESQL,
  'sonar-java-backend': Service.SONAR_JAVA_BACKEND,
  'sonar-java-backend-and-frontend': Service.SONAR_JAVA_BACKEND_AND_FRONTEND,
  springboot: Service.SPRINGBOOT,
  'springboot-actuator': Service.SPRINGBOOT_ACTUATOR,
  'springdoc-openapi': Service.SPRING_DOC,
  'springboot-jwt': Service.SPRINGBOOT_JWT,
  'springboot-jwt-basic-auth': Service.SPRINGBOOT_JWT_WITH_BASIC_AUTHENTICATION,
  'springboot-tomcat': Service.SPRINGBOOT_MVC_WITH_TOMCAT,
  'springboot-webflux-netty': Service.SPRINGBOOT_WEBFLUX_NETTY,
  react: Service.REACT,
  'react-styled': Service.REACT_STYLED,
  'react-jwt': Service.REACT_JWT,
  vue: Service.VUE,
  'vue-styled': Service.VUE_STYLED,
  unknown: Service.UNKNOWN,
};

export const toService = (restServiceId: RestServiceId): Service =>
  Object.keys(SERVICES).includes(restServiceId) ? SERVICES[restServiceId] : Service.UNKNOWN;
