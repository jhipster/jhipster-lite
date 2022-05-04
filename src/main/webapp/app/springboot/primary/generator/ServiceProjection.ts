import { Service } from '@/common/domain/Service';

export type ServiceProjection =
  | 'aop-logging'
  | 'angular'
  | 'angular-with-jwt'
  | 'download'
  | 'initialization'
  | 'flyway'
  | 'flyway-with-users-and-authority-changelogs'
  | 'frontend-maven-plugin'
  | 'jacoco-check-minimal-coverage'
  | 'java-base'
  | 'liquibase'
  | 'liquibase-with-users-and-authority-changelogs'
  | 'logstash'
  | 'maven-java'
  | 'mariadb'
  | 'mysql'
  | 'mongodb'
  | 'mongock'
  | 'postgresql'
  | 'sonar-java-backend'
  | 'sonar-java-backend-and-frontend'
  | 'spring-boot'
  | 'spring-boot-actuator'
  | 'spring-boot-jwt'
  | 'spring-boot-jwt-with-basic-authentication'
  | 'spring-boot-mvc-with-tomcat'
  | 'spring-boot-webflux-netty'
  | 'react'
  | 'react-styled'
  | 'vue'
  | 'vue-styled'
  | 'unknown';

const SERVICES_PROJECTION: Record<Service, ServiceProjection> = {
  [Service.AOP_LOGGING]: 'aop-logging',
  [Service.ANGULAR]: 'angular',
  [Service.ANGULAR_WITH_JWT]: 'angular-with-jwt',
  [Service.DOWNLOAD]: 'download',
  [Service.FLYWAY]: 'flyway',
  [Service.FLYWAY_WITH_USERS_AND_AUTHORITY_CHANGELOGS]: 'flyway-with-users-and-authority-changelogs',
  [Service.FRONTEND_MAVEN_PLUGIN]: 'frontend-maven-plugin',
  [Service.INITIALIZATION]: 'initialization',
  [Service.JACOCO_CHECK_MINIMAL_COVERAGE]: 'jacoco-check-minimal-coverage',
  [Service.JAVA_BASE]: 'java-base',
  [Service.LIQUIBASE]: 'liquibase',
  [Service.LIQUIBASE_WITH_USERS_AND_AUTHORITY_CHANGELOGS]: 'liquibase-with-users-and-authority-changelogs',
  [Service.LOGSTASH]: 'logstash',
  [Service.MAVEN_JAVA]: 'maven-java',
  [Service.MARIADB]: 'mariadb',
  [Service.MYSQL]: 'mysql',
  [Service.MONGODB]: 'mongodb',
  [Service.MONGOCK]: 'mongock',
  [Service.POSTGRESQL]: 'postgresql',
  [Service.SONAR_JAVA_BACKEND]: 'sonar-java-backend',
  [Service.SONAR_JAVA_BACKEND_AND_FRONTEND]: 'sonar-java-backend-and-frontend',
  [Service.SPRINGBOOT]: 'spring-boot',
  [Service.SPRINGBOOT_ACTUATOR]: 'spring-boot-actuator',
  [Service.SPRINGBOOT_JWT]: 'spring-boot-jwt',
  [Service.SPRINGBOOT_JWT_WITH_BASIC_AUTHENTICATION]: 'spring-boot-jwt-with-basic-authentication',
  [Service.SPRINGBOOT_MVC_WITH_TOMCAT]: 'spring-boot-mvc-with-tomcat',
  [Service.SPRINGBOOT_WEBFLUX_NETTY]: 'spring-boot-webflux-netty',
  [Service.REACT]: 'react',
  [Service.REACT_STYLED]: 'react-styled',
  [Service.VUE]: 'vue',
  [Service.VUE_STYLED]: 'vue-styled',
  [Service.UNKNOWN]: 'unknown',
};

export const toServiceProjection = (service: Service): ServiceProjection => SERVICES_PROJECTION[service];

const SERVICES: Record<ServiceProjection, Service> = {
  'aop-logging': Service.AOP_LOGGING,
  angular: Service.ANGULAR,
  'angular-with-jwt': Service.ANGULAR_WITH_JWT,
  download: Service.DOWNLOAD,
  flyway: Service.FLYWAY,
  'flyway-with-users-and-authority-changelogs': Service.FLYWAY_WITH_USERS_AND_AUTHORITY_CHANGELOGS,
  'frontend-maven-plugin': Service.FRONTEND_MAVEN_PLUGIN,
  initialization: Service.INITIALIZATION,
  'jacoco-check-minimal-coverage': Service.JACOCO_CHECK_MINIMAL_COVERAGE,
  'java-base': Service.JAVA_BASE,
  liquibase: Service.LIQUIBASE,
  'liquibase-with-users-and-authority-changelogs': Service.LIQUIBASE_WITH_USERS_AND_AUTHORITY_CHANGELOGS,
  logstash: Service.LOGSTASH,
  'maven-java': Service.MAVEN_JAVA,
  mariadb: Service.MARIADB,
  mysql: Service.MYSQL,
  mongodb: Service.MONGODB,
  mongock: Service.MONGOCK,
  postgresql: Service.POSTGRESQL,
  'sonar-java-backend': Service.SONAR_JAVA_BACKEND,
  'sonar-java-backend-and-frontend': Service.SONAR_JAVA_BACKEND_AND_FRONTEND,
  'spring-boot': Service.SPRINGBOOT,
  'spring-boot-actuator': Service.SPRINGBOOT_ACTUATOR,
  'spring-boot-jwt': Service.SPRINGBOOT_JWT,
  'spring-boot-jwt-with-basic-authentication': Service.SPRINGBOOT_JWT_WITH_BASIC_AUTHENTICATION,
  'spring-boot-mvc-with-tomcat': Service.SPRINGBOOT_MVC_WITH_TOMCAT,
  'spring-boot-webflux-netty': Service.SPRINGBOOT_WEBFLUX_NETTY,
  react: Service.REACT,
  'react-styled': Service.REACT_STYLED,
  vue: Service.VUE,
  'vue-styled': Service.VUE_STYLED,
  unknown: Service.UNKNOWN,
};

export const fromServiceProjection = (serviceProjection: ServiceProjection): Service => SERVICES[serviceProjection];
