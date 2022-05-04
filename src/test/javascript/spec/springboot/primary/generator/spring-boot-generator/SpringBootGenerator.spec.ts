import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubLogger } from '../../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { SpringBootService } from '@/springboot/domain/SpringBootService';
import { stubSpringBootService } from '../../../domain/SpringBootService.fixture';
import { SpringBootGeneratorVue } from '@/springboot/primary/generator/spring-boot-generator';
import { NotificationService } from '@/common/domain/NotificationService';
import { stubNotificationService } from '../../../../common/domain/NotificationService.fixture';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  springBootService: SpringBootService;
  logger: Logger;
  project: ProjectToUpdate;
  toastService: NotificationService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { springBootService, logger, project, toastService }: WrapperOptions = {
    springBootService: stubSpringBootService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
    toastService: stubNotificationService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(SpringBootGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        springBootService,
        logger,
        toastService,
      },
    },
  });
  component = wrapper.vm;
};

describe('SpringBootGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should not add SpringBoot when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBoot.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBoot();

    expect(springBootService.addSpringBoot.called).toBe(false);
  });

  it('should add SpringBoot when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBoot.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBoot();

    const args = springBootService.addSpringBoot.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding spring boot failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBoot.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBoot();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot to project failed');
  });

  it('should not add SpringBoot MVC with Tomcat when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBootMvcTomcat();

    expect(springBootService.addSpringBootMvcTomcat.called).toBe(false);
  });

  it('should add SpringBoot MVC with Tomcat when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootMvcTomcat();

    const args = springBootService.addSpringBootMvcTomcat.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding SpringBoot MVC with Tomcat failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootMvcTomcat();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot MVC with Tomcat to project failed');
  });

  it('should not add SpringBoot Webflux with Netty when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBootWebfluxNetty();

    expect(springBootService.addSpringBootWebfluxNetty.called).toBe(false);
  });

  it('should add SpringBoot Webflux with Netty when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootWebfluxNetty();

    const args = springBootService.addSpringBootWebfluxNetty.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding SpringBoot Webflux with Netty failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootWebfluxNetty();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Webflux with Netty to project failed');
  });

  it('should not add SpringBoot Actuator when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBootActuator();

    expect(springBootService.addSpringBootActuator.called).toBe(false);
  });

  it('should add SpringBoot Actuator when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootActuator();

    const args = springBootService.addSpringBootActuator.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding SpringBoot Actuator failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootActuator();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Actuator to project failed');
  });

  describe('Log tools', () => {
    describe('AOP Logging', () => {
      it('should not add SpringBoot AOP Logging when project path is not filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

        await component.addSpringBootAopLogging();

        expect(springBootService.addSpringBootAopLogging.called).toBe(false);
      });

      it('should add SpringBoot AOP Logging when project path is filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

        await component.addSpringBootAopLogging();

        const args = springBootService.addSpringBootAopLogging.getCall(0).args[0];
        expect(args).toEqual({
          baseName: 'beer',
          folder: 'project/path',
          projectName: 'Beer Project',
          packageName: 'tech.jhipster.beer',
          serverPort: 8080,
        });
      });

      it('should handle error on adding SpringBoot AOP Logging failure', async () => {
        const logger = stubLogger();
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.rejects({});
        await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

        await component.addSpringBootAopLogging();

        const [message] = logger.error.getCall(0).args;
        expect(message).toBe('Adding SpringBoot AOP Logging to project failed');
      });
    });

    describe('Logstash', () => {
      it('should not add SpringBoot Logstash when project path is not filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

        await component.addSpringBootLogstash();

        expect(springBootService.addSpringBootLogstash.called).toBe(false);
      });

      it('should add SpringBoot Logstash when project path is filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

        await component.addSpringBootLogstash();

        const args = springBootService.addSpringBootLogstash.getCall(0).args[0];
        expect(args).toEqual({
          baseName: 'beer',
          folder: 'project/path',
          projectName: 'Beer Project',
          packageName: 'tech.jhipster.beer',
          serverPort: 8080,
        });
      });

      it('should handle error on adding SpringBoot Logstash failure', async () => {
        const logger = stubLogger();
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.rejects({});
        await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

        await component.addSpringBootLogstash();

        const [message] = logger.error.getCall(0).args;
        expect(message).toBe('Adding SpringBoot Logstash to project failed');
      });
    });
  });

  it('should not add SpringBoot Security JWT when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBootSecurityJWT();

    expect(springBootService.addJWT.called).toBe(false);
  });

  it('should add SpringBoot Security JWT when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootSecurityJWT();

    const args = springBootService.addJWT.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding SpringBoot Security JWT failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addJWT.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootSecurityJWT();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Security JWT to project failed');
  });

  it('should not add SpringBoot Security JWT Basic Auth  when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringBootSecurityJWTBasicAuth();

    expect(springBootService.addBasicAuthJWT.called).toBe(false);
  });

  it('should add SpringBoot Security JWT Basic Auth when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootSecurityJWTBasicAuth();

    const args = springBootService.addBasicAuthJWT.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding SpringBoot Security JWT Basic Auth failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.rejects({});
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addSpringBootSecurityJWTBasicAuth();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Security JWT Basic Auth to project failed');
  });

  describe('Databases', () => {
    it('should not add SpringBoot Database PostgreSQL  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addPostgreSQL();

      expect(springBootService.addPostgres.called).toBe(false);
    });

    it('should add SpringBoot Database PostgreSQL when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addPostgreSQL();

      const args = springBootService.addPostgres.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database PostgreSQL failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addPostgreSQL();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database PostgreSQL to project failed');
    });

    it('should not add SpringBoot Database MySQL  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addMySQL();

      expect(springBootService.addMySQL.called).toBe(false);
    });

    it('should add SpringBoot Database MySQL when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMySQL();

      const args = springBootService.addMySQL.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database MySQL failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMySQL();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MySQL to project failed');
    });

    it('should not add SpringBoot Database MariaDB  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addMariaDB();

      expect(springBootService.addMariaDB.called).toBe(false);
    });

    it('should add SpringBoot Database MariaDB when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMariaDB();

      const args = springBootService.addMariaDB.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database MariaDB failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMariaDB();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MariaDB to project failed');
    });

    it('should not add SpringBoot Database MongoDB  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addMongoDB();

      expect(springBootService.addMongoDB.called).toBe(false);
    });

    it('should add SpringBoot Database MongoDB when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMongoDB();

      const args = springBootService.addMongoDB.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database MongoDB failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMongoDB();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MongoDB to project failed');
    });
  });

  describe('Databases migration', () => {
    it('should not add SpringBoot Database Migration Flyway when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addFlyway();

      expect(springBootService.addSpringBootFlywayInit.called).toBe(false);
    });

    it('should add SpringBoot Database Migration Flyway when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addFlyway();

      const args = springBootService.addSpringBootFlywayInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database Migration Flyway failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayInit.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addFlyway();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database Migration Flyway to project failed');
    });

    it('should not add Flyway User and Authority changelogs when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayUser.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addFlywayUser();

      expect(springBootService.addSpringBootFlywayUser.called).toBe(false);
    });

    it('should add Flyway User and Authority changelogs when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayUser.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addFlywayUser();

      const args = springBootService.addSpringBootFlywayUser.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding Flyway User and Authority changelogs failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayUser.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addFlywayUser();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding Flyway with Users and Authority changelogs failed');
    });

    it('should not add SpringBoot Database Migration Liquibase when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addLiquibase();

      expect(springBootService.addSpringBootLiquibaseInit.called).toBe(false);
    });

    it('should add SpringBoot Database Migration Liquibase when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addLiquibase();

      const args = springBootService.addSpringBootLiquibaseInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database Migration Liquibase failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseInit.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addLiquibase();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database Migration Liquibase to project failed');
    });

    it('should not add Liquibase User and Authority changelogs when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseUser.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addLiquibaseUser();

      expect(springBootService.addSpringBootLiquibaseUser.called).toBe(false);
    });

    it('should add Liquibase User and Authority changelogs when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseUser.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addLiquibaseUser();

      const args = springBootService.addSpringBootLiquibaseUser.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding Liquibase with Users and Authority changelogs failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseUser.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addLiquibaseUser();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding Liquibase with Users and Authority changelogs failed');
    });

    it('should not add SpringBoot Database Migration Mongock when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootMongockInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      await component.addMongock();

      expect(springBootService.addSpringBootMongockInit.called).toBe(false);
    });

    it('should add SpringBoot Database Migration Mongock when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootMongockInit.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMongock();

      const args = springBootService.addSpringBootMongockInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
    });

    it('should handle error on adding SpringBoot Database Migration Mongock failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootMongockInit.rejects({});
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

      await component.addMongock();

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database Migration Mongock to project failed');
    });
  });
});
