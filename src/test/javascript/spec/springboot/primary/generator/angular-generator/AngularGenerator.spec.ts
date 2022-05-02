import { shallowMount, VueWrapper } from '@vue/test-utils';
import { stubAngularService } from '../../../domain/client/AngularService.fixture';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubLogger } from '../../../../common/domain/Logger.fixture';
import { AngularService } from '@/springboot/domain/client/AngularService';
import { Logger } from '@/common/domain/Logger';
import { AngularGeneratorVue } from '@/springboot/primary/generator/angular-generator';
import { NotificationService } from '@/common/domain/NotificationService';
import { stubNotificationService } from '../../../common/domain/NotificationService.fixture';

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
    await wrap({ angularService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addAngular();

    const args = angularService.add.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding Angular failure', async () => {
    const logger = stubLogger();
    const angularService = stubAngularService();
    angularService.add.rejects({});
    await wrap({ angularService, logger, project: createProjectToUpdate({ folder: 'path' }) });

    await component.addAngular();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Angular to project failed');
  });
});
