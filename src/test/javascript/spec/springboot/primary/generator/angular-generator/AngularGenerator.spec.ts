import { shallowMount, VueWrapper } from '@vue/test-utils';
import { stubAngularService } from '../../../domain/client/AngularService.fixture';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { LoggerFixture, stubLogger } from '../../../../common/domain/Logger.fixture';
import { AngularService } from '@/springboot/domain/client/AngularService';
import { Logger } from '@/common/domain/Logger';
import { AngularGeneratorVue } from '@/springboot/primary/generator/angular-generator';
import { NotificationService } from '@/common/domain/NotificationService';
import { stubNotificationService } from '../../../../common/domain/NotificationService.fixture';
import { stubToastService, ToastServiceFixture } from '../../../../common/secondary/ToastService.fixture';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  angularService: AngularService;
  logger: Logger;
  project: ProjectToUpdate;
  toastService: NotificationService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { angularService, logger, project, toastService }: WrapperOptions = {
    angularService: stubAngularService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
    toastService: stubNotificationService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(AngularGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        angularService,
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

describe('AngularGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should not add Angular when project path is not filled', async () => {
    const angularService = stubAngularService();
    angularService.add.resolves({});
    await wrap({ angularService, project: createProjectToUpdate({ folder: '' }) });

    await component.addAngular();

    expect(angularService.add.called).toBe(false);
  });

  it('should add Angular when project path is filled', async () => {
    const angularService = stubAngularService();
    angularService.add.resolves({});
    const toastService = stubToastService();
    await wrap({ angularService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addAngular();

    const args = angularService.add.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'Angular successfully added');
  });

  it('should handle error on adding Angular failure', async () => {
    const logger = stubLogger();
    const angularService = stubAngularService();
    const toastService = stubToastService();
    angularService.add.rejects('error');
    await wrap({ angularService, logger, project: createProjectToUpdate({ folder: 'path' }), toastService });

    await component.addAngular();

    expectLoggerErrorToBe(logger, 'Adding Angular to project failed');
    expectToastErrorToBe(toastService, 'Adding Angular to project failed error');
  });

  it('should not add Angular with JWT when project path is not filled', async () => {
    const angularService = stubAngularService();
    angularService.addWithJWT.resolves({});
    await wrap({ angularService, project: createProjectToUpdate({ folder: '' }) });

    await component.addAngularWithJWT();

    expect(angularService.addWithJWT.called).toBe(false);
  });

  it('should add Angular with JWT when project path is filled', async () => {
    const angularService = stubAngularService();
    angularService.addWithJWT.resolves({});
    const toastService = stubToastService();
    await wrap({ angularService, project: createProjectToUpdate({ folder: 'project/path' }), toastService });

    await component.addAngularWithJWT();

    const args = angularService.addWithJWT.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
    expectToastSuccessToBe(toastService, 'Angular with authentication JWT successfully added');
  });

  it('should handle error on adding Angular with JWT failure', async () => {
    const logger = stubLogger();
    const angularService = stubAngularService();
    const toastService = stubToastService();
    angularService.addWithJWT.rejects('error');
    await wrap({ angularService, logger, project: createProjectToUpdate({ folder: 'path' }), toastService });

    await component.addAngularWithJWT();

    expectLoggerErrorToBe(logger, 'Adding Angular with authentication JWT to project failed');
    expectToastErrorToBe(toastService, 'Adding Angular with authentication JWT to project failed error');
  });
});
