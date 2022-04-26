import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../ProjectToUpdate.fixture';
import { stubLogger } from '../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { SpringBootService } from '@/springboot/domain/SpringBootService';
import { stubSpringBootService } from '../../domain/SpringBootService.fixture';
import { SpringBootGeneratorVue } from '@/springboot/primary/spring-boot-generator';

let wrapper: VueWrapper;

interface WrapperOptions {
  springBootService: SpringBootService;
  logger: Logger;
  project: ProjectToUpdate;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { springBootService, logger, project }: WrapperOptions = {
    springBootService: stubSpringBootService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
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
      },
    },
  });
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

    const button = wrapper.find('#springboot');
    await button.trigger('click');

    expect(springBootService.addSpringBoot.called).toBe(false);
  });

  it('should add SpringBoot when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBoot.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springboot');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springboot');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot to project failed');
  });

  it('should not add SpringBoot MVC with Tomcat when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    const button = wrapper.find('#springbootmvctomcat');
    await button.trigger('click');

    expect(springBootService.addSpringBootMvcTomcat.called).toBe(false);
  });

  it('should add SpringBoot MVC with Tomcat when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootMvcTomcat.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springbootmvctomcat');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springbootmvctomcat');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot MVC with Tomcat to project failed');
  });

  it('should not add SpringBoot Webflux with Netty when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    const button = wrapper.find('#springbootwebfluxnetty');
    await button.trigger('click');

    expect(springBootService.addSpringBootWebfluxNetty.called).toBe(false);
  });

  it('should add SpringBoot Webflux with Netty when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootWebfluxNetty.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springbootwebfluxnetty');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springbootwebfluxnetty');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Webflux with Netty to project failed');
  });

  it('should not add SpringBoot Actuator when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    const button = wrapper.find('#springboot-actuator');
    await button.trigger('click');

    expect(springBootService.addSpringBootActuator.called).toBe(false);
  });

  it('should add SpringBoot Actuator when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addSpringBootActuator.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springboot-actuator');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springboot-actuator');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Actuator to project failed');
  });

  describe('Log tools', () => {
    describe('AOP Logging', () => {
      it('should not add SpringBoot AOP Logging when project path is not filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

        const button = wrapper.find('#springboot-aop');
        await button.trigger('click');

        expect(springBootService.addSpringBootAopLogging.called).toBe(false);
      });

      it('should add SpringBoot AOP Logging when project path is filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootAopLogging.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

        const button = wrapper.find('#springboot-aop');
        await button.trigger('click');

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

        const initButton = wrapper.find('#springboot-aop');
        await initButton.trigger('click');

        const [message] = logger.error.getCall(0).args;
        expect(message).toBe('Adding SpringBoot AOP Logging to project failed');
      });
    });

    describe('Logstash', () => {
      it('should not add SpringBoot Logstash when project path is not filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

        const button = wrapper.find('#springboot-logstash');
        await button.trigger('click');

        expect(springBootService.addSpringBootLogstash.called).toBe(false);
      });

      it('should add SpringBoot Logstash when project path is filled', async () => {
        const springBootService = stubSpringBootService();
        springBootService.addSpringBootLogstash.resolves({});
        await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

        const button = wrapper.find('#springboot-logstash');
        await button.trigger('click');

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

        const initButton = wrapper.find('#springboot-logstash');
        await initButton.trigger('click');

        const [message] = logger.error.getCall(0).args;
        expect(message).toBe('Adding SpringBoot Logstash to project failed');
      });
    });
  });

  it('should not add SpringBoot Security JWT when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    const button = wrapper.find('#springboot-jwt');
    await button.trigger('click');

    expect(springBootService.addJWT.called).toBe(false);
  });

  it('should add SpringBoot Security JWT when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springboot-jwt');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springboot-jwt');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Security JWT to project failed');
  });

  it('should not add SpringBoot Security JWT Basic Auth  when project path is not filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

    const button = wrapper.find('#springboot-jwt-basic-auth');
    await button.trigger('click');

    expect(springBootService.addBasicAuthJWT.called).toBe(false);
  });

  it('should add SpringBoot Security JWT Basic Auth when project path is filled', async () => {
    const springBootService = stubSpringBootService();
    springBootService.addBasicAuthJWT.resolves({});
    await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#springboot-jwt-basic-auth');
    await button.trigger('click');

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

    const initButton = wrapper.find('#springboot-jwt-basic-auth');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding SpringBoot Security JWT Basic Auth to project failed');
  });

  describe('Databases', () => {
    it('should not add SpringBoot Database PostgreSQL  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      const button = wrapper.find('#springboot-database-postgresql');
      await button.trigger('click');

      expect(springBootService.addPostgres.called).toBe(false);
    });

    it('should add SpringBoot Database PostgreSQL when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addPostgres.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      const button = wrapper.find('#springboot-database-postgresql');
      await button.trigger('click');

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

      const initButton = wrapper.find('#springboot-database-postgresql');
      await initButton.trigger('click');

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database PostgreSQL to project failed');
    });

    it('should not add SpringBoot Database MySQL  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      const button = wrapper.find('#springboot-database-mysql');
      await button.trigger('click');

      expect(springBootService.addMySQL.called).toBe(false);
    });

    it('should add SpringBoot Database MySQL when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMySQL.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      const button = wrapper.find('#springboot-database-mysql');
      await button.trigger('click');

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

      const initButton = wrapper.find('#springboot-database-mysql');
      await initButton.trigger('click');

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MySQL to project failed');
    });

    it('should not add SpringBoot Database MariaDB  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      const button = wrapper.find('#springboot-database-mariadb');
      await button.trigger('click');

      expect(springBootService.addMariaDB.called).toBe(false);
    });

    it('should add SpringBoot Database MariaDB when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMariaDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      const button = wrapper.find('#springboot-database-mariadb');
      await button.trigger('click');

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

      const initButton = wrapper.find('#springboot-database-mariadb');
      await initButton.trigger('click');

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MariaDB to project failed');
    });

    it('should not add SpringBoot Database MongoDB  when project path is not filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: '' }) });

      const button = wrapper.find('#springboot-database-mongodb');
      await button.trigger('click');

      expect(springBootService.addMongoDB.called).toBe(false);
    });

    it('should add SpringBoot Database MongoDB when project path is filled', async () => {
      const springBootService = stubSpringBootService();
      springBootService.addMongoDB.resolves({});
      await wrap({ springBootService, project: createProjectToUpdate({ folder: 'project/path' }) });

      const button = wrapper.find('#springboot-database-mongodb');
      await button.trigger('click');

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

      const initButton = wrapper.find('#springboot-database-mongodb');
      await initButton.trigger('click');

      const [message] = logger.error.getCall(0).args;
      expect(message).toBe('Adding SpringBoot Database MongoDB to project failed');
    });
  });
});
