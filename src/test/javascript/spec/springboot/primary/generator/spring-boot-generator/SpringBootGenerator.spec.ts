import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { LoggerFixture, stubLogger } from '../../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { SpringBootService } from '@/springboot/domain/SpringBootService';
import { stubSpringBootService } from '../../../domain/SpringBootService.fixture';
import { SpringBootGeneratorVue } from '@/springboot/primary/generator/spring-boot-generator';
import { NotificationService } from '@/common/domain/NotificationService';
import { stubNotificationService } from '../../../../common/domain/NotificationService.fixture';
import { stubToastService, ToastServiceFixture } from '../../../../common/secondary/ToastService.fixture';

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

const expectLoggerErrorToBe = (logger: LoggerFixture, message: string) => {
  const [loggerMessage] = logger.error.getCall(0).args;
  expect(loggerMessage).toBe(message);
};

const expectToastErrorToBe = (toastService: ToastServiceFixture, message: string) => {
  const [toastMessage] = toastService.error.getCall(0).args;
  expect(toastMessage).toBe(message);
};

const expectToastSuccessToBe = (toastService: ToastServiceFixture, message: string) => {
  const [toastMessage] = toastService.success.getCall(0).args;
  expect(toastMessage).toBe(message);
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBoot();

    const args = springBootService.addSpringBoot.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot successfully added');
  });

  it('should handle error on adding spring boot failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBoot.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBoot();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot to project failed error');
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootMvcTomcat();

    const args = springBootService.addSpringBootMvcTomcat.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot MVC with Tomcat successfully added');
  });

  it('should handle error on adding SpringBoot MVC with Tomcat failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootMvcTomcat();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot MVC with Tomcat to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot MVC with Tomcat to project failed error');
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootWebfluxNetty();

    const args = springBootService.addSpringBootWebfluxNetty.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot Webflux with Netty successfully added');
  });

  it('should handle error on adding SpringBoot Webflux with Netty failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootWebfluxNetty();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot Webflux with Netty to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot Webflux with Netty to project failed error');
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootActuator();

    const args = springBootService.addSpringBootActuator.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot Webflux with Netty successfully added');
  });

  it('should handle error on adding SpringBoot Actuator failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootActuator();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot Actuator to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot Actuator to project failed error');
  });

  it('should not add SpringDoc when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringDoc.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSpringDoc();

    expect(springBootService.addSpringDoc.called).toBe(false);
  });

  it('should add SpringDoc when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringDoc.resolves({});
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringDoc();

    const args = springBootService.addSpringDoc.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringDoc successfully added');
  });

  it('should handle error on adding SpringDoc failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addSpringDoc.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringDoc();

    expectLoggerErrorToBe(logger, 'Adding SpringDoc to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringDoc to project failed error');
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
        const toastService = stubToastService();
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

        await component.addSpringBootAopLogging();

        const args = springBootService.addSpringBootAopLogging.getCall(0).args[0];
        expect(args).toEqual({
          baseName: 'beer',
          folder: 'project/path',
          projectName: 'Beer Project',
          packageName: 'tech.jhipster.beer',
          serverPort: 8080,
        });
        expectToastSuccessToBe(toastService, 'SpringBoot AOP Logging successfully added');
      });

      it('should handle error on adding SpringBoot AOP Logging failure', async () => {
        const logger = stubLogger();
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.rejects('error');
        const toastService = stubToastService();
        await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

        await component.addSpringBootAopLogging();

        expectLoggerErrorToBe(logger, 'Adding SpringBoot AOP Logging to project failed');
        expectToastErrorToBe(toastService, 'Adding SpringBoot AOP Logging to project failed error');
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
        const toastService = stubToastService();
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

        await component.addSpringBootLogstash();

        const args = springBootService.addSpringBootLogstash.getCall(0).args[0];
        expect(args).toEqual({
          baseName: 'beer',
          folder: 'project/path',
          projectName: 'Beer Project',
          packageName: 'tech.jhipster.beer',
          serverPort: 8080,
        });
        expectToastSuccessToBe(toastService, 'SpringBoot Logstash successfully added');
      });

      it('should handle error on adding SpringBoot Logstash failure', async () => {
        const logger = stubLogger();
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.rejects('error');
        const toastService = stubToastService();
        await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

        await component.addSpringBootLogstash();

        expectLoggerErrorToBe(logger, 'Adding SpringBoot Logstash to project failed');
        expectToastErrorToBe(toastService, 'Adding SpringBoot Logstash to project failed error');
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootSecurityJWT();

    const args = springBootService.addJWT.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot Security JWT successfully added');
  });

  it('should handle error on adding SpringBoot Security JWT failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addJWT.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootSecurityJWT();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot Security JWT to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot Security JWT to project failed error');
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
    const toastService = stubToastService();
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootSecurityJWTBasicAuth();

    const args = springBootService.addBasicAuthJWT.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'SpringBoot Security JWT Basic Auth  successfully added');
  });

  it('should handle error on adding SpringBoot Security JWT Basic Auth failure', async () => {
    const logger = stubLogger();
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.rejects('error');
    const toastService = stubToastService();
    await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addSpringBootSecurityJWTBasicAuth();

    expectLoggerErrorToBe(logger, 'Adding SpringBoot Security JWT Basic Auth to project failed');
    expectToastErrorToBe(toastService, 'Adding SpringBoot Security JWT Basic Auth to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addPostgreSQL();

      const args = springBootService.addPostgres.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database PostgreSQL successfully added');
    });

    it('should handle error on adding SpringBoot Database PostgreSQL failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addPostgreSQL();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database PostgreSQL to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database PostgreSQL to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMySQL();

      const args = springBootService.addMySQL.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database MySQL successfully added');
    });

    it('should handle error on adding SpringBoot Database MySQL failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMySQL();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database MySQL to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database MySQL to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMariaDB();

      const args = springBootService.addMariaDB.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database MariaDB successfully added');
    });

    it('should handle error on adding SpringBoot Database MariaDB failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMariaDB();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database MariaDB to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database MariaDB to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMongoDB();

      const args = springBootService.addMongoDB.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database MongoDB successfully added');
    });

    it('should handle error on adding SpringBoot Database MongoDB failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMongoDB();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database MongoDB to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database MongoDB to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addFlyway();

      const args = springBootService.addSpringBootFlywayInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database Migration Flyway successfully added');
    });

    it('should handle error on adding SpringBoot Database Migration Flyway failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayInit.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addFlyway();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database Migration Flyway to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database Migration Flyway to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addFlywayUser();

      const args = springBootService.addSpringBootFlywayUser.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database Migration Flyway with Users and Authority changelogs successfully added');
    });

    it('should handle error on adding Flyway User and Authority changelogs failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootFlywayUser.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addFlywayUser();

      expectLoggerErrorToBe(logger, 'Adding Flyway with Users and Authority changelogs failed');
      expectToastErrorToBe(toastService, 'Adding Flyway with Users and Authority changelogs failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addLiquibase();

      const args = springBootService.addSpringBootLiquibaseInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database Migration Liquibase successfully added');
    });

    it('should handle error on adding SpringBoot Database Migration Liquibase failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseInit.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addLiquibase();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database Migration Liquibase to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database Migration Liquibase to project failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addLiquibaseUser();

      const args = springBootService.addSpringBootLiquibaseUser.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(
        toastService,
        'SpringBoot Database Migration Liquibase with Users and Authority changelogs successfully added'
      );
    });

    it('should handle error on adding Liquibase with Users and Authority changelogs failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootLiquibaseUser.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addLiquibaseUser();

      expectLoggerErrorToBe(logger, 'Adding Liquibase with Users and Authority changelogs failed');
      expectToastErrorToBe(toastService, 'Adding Liquibase with Users and Authority changelogs failed error');
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
      const toastService = stubToastService();
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMongock();

      const args = springBootService.addSpringBootMongockInit.getCall(0).args[0];
      expect(args).toEqual({
        baseName: 'beer',
        folder: 'project/path',
        projectName: 'Beer Project',
        packageName: 'tech.jhipster.beer',
        serverPort: 8080,
      });
      expectToastSuccessToBe(toastService, 'SpringBoot Database MongoDB successfully added');
    });

    it('should handle error on adding SpringBoot Database Migration Mongock failure', async () => {
      const logger = stubLogger();
      const springBootService = stubSpringBootService();
      springBootService.addSpringBootMongockInit.rejects('error');
      const toastService = stubToastService();
      await wrap({ springBootService, logger, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

      await component.addMongock();

      expectLoggerErrorToBe(logger, 'Adding SpringBoot Database Migration Mongock to project failed');
      expectToastErrorToBe(toastService, 'Adding SpringBoot Database Migration Mongock to project failed error');
    });
  });
});
