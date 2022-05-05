import { defineComponent, inject } from 'vue';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { Logger } from '@/common/domain/Logger';
import { SpringBootService } from '@/springboot/domain/SpringBootService';
import { GeneratorButtonVue } from '@/springboot/primary/generator/generator-button';
import ToastService from '@/common/secondary/ToastService';

export default defineComponent({
  name: 'SpringBootGeneratorComponent',

  components: {
    GeneratorButtonVue,
  },

  props: {
    project: {
      type: Object,
      required: true,
    },
  },

  setup(props) {
    const logger = inject('logger') as Logger;
    const springBootService = inject('springBootService') as SpringBootService;
    const toastService = inject('toastService') as ToastService;

    const selectorPrefix = 'spring-boot-generator';

    const addSpringBoot = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBoot(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot to project failed', error);
            toastService.error('Adding SpringBoot to project failed ' + error);
          });
      }
    };

    const addSpringBootMvcTomcat = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootMvcTomcat(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot MVC with Tomcat successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot MVC with Tomcat to project failed', error);
            toastService.error('Adding SpringBoot MVC with Tomcat to project failed ' + error);
          });
      }
    };

    const addSpringBootWebfluxNetty = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootWebfluxNetty(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Webflux with Netty successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Webflux with Netty to project failed', error);
            toastService.error('Adding SpringBoot Webflux with Netty to project failed ' + error);
          });
      }
    };

    const addSpringBootActuator = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootActuator(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Webflux with Netty successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Actuator to project failed', error);
            toastService.error('Adding SpringBoot Actuator to project failed ' + error);
          });
      }
    };

    const addSpringBootAopLogging = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootAopLogging(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot AOP Logging successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot AOP Logging to project failed', error);
            toastService.error('Adding SpringBoot AOP Logging to project failed ' + error);
          });
      }
    };

    const addSpringBootLogstash = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootLogstash(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Logstash successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Logstash to project failed', error);
            toastService.error('Adding SpringBoot Logstash to project failed ' + error);
          });
      }
    };

    const addSpringBootSecurityJWT = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addJWT(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Security JWT successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Security JWT to project failed', error);
            toastService.error('Adding SpringBoot Security JWT to project failed ' + error);
          });
      }
    };

    const addSpringBootSecurityJWTBasicAuth = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addBasicAuthJWT(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Security JWT Basic Auth  successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Security JWT Basic Auth to project failed', error);
            toastService.error('Adding SpringBoot Security JWT Basic Auth to project failed ' + error);
          });
      }
    };

    const addPostgreSQL = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addPostgres(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database PostgreSQL successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database PostgreSQL to project failed', error);
            toastService.error('Adding SpringBoot Database PostgreSQL to project failed ' + error);
          });
      }
    };

    const addMySQL = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addMySQL(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database MySQL successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database MySQL to project failed', error);
            toastService.error('Adding SpringBoot Database MySQL to project failed ' + error);
          });
      }
    };

    const addMariaDB = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addMariaDB(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database MariaDB successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database MariaDB to project failed', error);
            toastService.error('Adding SpringBoot Database MariaDB to project failed ' + error);
          });
      }
    };

    const addMongoDB = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addMongoDB(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database MongoDB successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database MongoDB to project failed', error);
            toastService.error('Adding SpringBoot Database MongoDB to project failed ' + error);
          });
      }
    };

    const addFlyway = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootFlywayInit(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database Migration Flyway successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database Migration Flyway to project failed', error);
            toastService.error('Adding SpringBoot Database Migration Flyway to project failed ' + error);
          });
      }
    };

    const addFlywayUser = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootFlywayUser(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database Migration Flyway with Users and Authority changelogs successfully added'))
          .catch(error => {
            logger.error('Adding Flyway with Users and Authority changelogs failed', error);
            toastService.error('Adding Flyway with Users and Authority changelogs failed ' + error);
          });
      }
    };

    const addLiquibase = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootLiquibaseInit(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database Migration Liquibase successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database Migration Liquibase to project failed', error);
            toastService.error('Adding SpringBoot Database Migration Liquibase to project failed ' + error);
          });
      }
    };

    const addLiquibaseUser = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootLiquibaseUser(toProject(props.project as ProjectToUpdate))
          .then(() =>
            toastService.success('SpringBoot Database Migration Liquibase with Users and Authority changelogs successfully added')
          )
          .catch(error => {
            logger.error('Adding Liquibase with Users and Authority changelogs failed', error);
            toastService.error('Adding Liquibase with Users and Authority changelogs failed ' + error);
          });
      }
    };

    const addMongock = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await springBootService
          .addSpringBootMongockInit(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('SpringBoot Database MongoDB successfully added'))
          .catch(error => {
            logger.error('Adding SpringBoot Database Migration Mongock to project failed', error);
            toastService.error('Adding SpringBoot Database Migration Mongock to project failed ' + error);
          });
      }
    };

    return {
      selectorPrefix,
      addSpringBoot,
      addSpringBootMvcTomcat,
      addSpringBootWebfluxNetty,
      addSpringBootActuator,
      addSpringBootAopLogging,
      addSpringBootLogstash,
      addSpringBootSecurityJWT,
      addSpringBootSecurityJWTBasicAuth,
      addPostgreSQL,
      addMySQL,
      addMariaDB,
      addMongoDB,
      addFlyway,
      addFlywayUser,
      addLiquibase,
      addLiquibaseUser,
      addMongock,
    };
  },
});
