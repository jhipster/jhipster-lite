import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubLogger } from '../../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { VueService } from '@/springboot/domain/client/VueService';
import { stubVueService } from '../../../domain/client/VueService.fixture';
import { VueGeneratorVue } from '@/springboot/primary/generator/vue-generator';
import { NotificationService } from '@/common/domain/NotificationService';
import { stubNotificationService } from '../../../../common/domain/NotificationService.fixture';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  vueService: VueService;
  logger: Logger;
  project: ProjectToUpdate;
  toastService: NotificationService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { vueService, logger, project, toastService }: WrapperOptions = {
    vueService: stubVueService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
    toastService: stubNotificationService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(VueGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        vueService,
        logger,
        toastService,
      },
    },
  });
  component = wrapper.vm;
};

describe('VueGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should not add Vue when project path is not filled', async () => {
    const vueService = stubVueService();
    vueService.add.resolves({});
    await wrap({ vueService, project: createProjectToUpdate({ folder: '' }) });

    await component.addVue();

    expect(vueService.add.called).toBe(false);
  });

  it('should add Vue when project path is filled', async () => {
    const vueService = stubVueService();
    vueService.add.resolves({});
    await wrap({ vueService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addVue();

    const args = vueService.add.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add Vue with Style when checkbox is checked', async () => {
    const vueService = stubVueService();
    vueService.addWithStyle.resolves({});
    await wrap({ vueService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const checkbox = wrapper.find('#vue-with-style');
    await checkbox.setValue(true);
    await component.addVue();

    const args = vueService.addWithStyle.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding Vue failure', async () => {
    const logger = stubLogger();
    const vueService = stubVueService();
    vueService.add.rejects({});
    await wrap({ vueService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addVue();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Vue to project failed');
  });

  it('should handle error on adding Vue with style failure', async () => {
    const logger = stubLogger();
    const vueService = stubVueService();
    vueService.addWithStyle.rejects({});
    await wrap({ vueService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    const checkbox = wrapper.find('#vue-with-style');
    await checkbox.setValue(true);
    await component.addVue();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Vue with style to project failed');
  });
});
