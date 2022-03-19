import { Service } from '@/common/domain/Service';

export type RestServiceId = string;

export const toService = (restServiceId: RestServiceId): Service => {
  switch (restServiceId) {
    case 'init':
      return Service.INITIALIZATION;
    case 'maven':
      return Service.MAVEN;
    case 'java-base':
      return Service.JAVA_BASE;
    case 'springboot':
      return Service.SPRINGBOOT;
    case 'springboot-tomcat':
      return Service.SPRINGBOOT_MVC_WITH_TOMCAT;
    case 'angular':
      return Service.ANGULAR;
    case 'react':
      return Service.REACT;
    case 'vue':
      return Service.VUE;
    case 'frontend-maven-plugin':
      return Service.FRONTEND_MAVEN_PLUGIN;
    default:
      return Service.UNKNOWN;
  }
};
