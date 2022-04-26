import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../ProjectToUpdate.fixture';
import { stubLogger } from '../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { VueService } from '@/springboot/domain/client/VueService';
import { stubVueService } from '../../domain/client/VueService.fixture';
import { VueGeneratorVue } from '@/springboot/primary/vue-generator';

let wrapper: VueWrapper;

interface WrapperOptions {
  vueService: VueService;
  logger: Logger;
  project: ProjectToUpdate;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { vueService, logger, project }: WrapperOptions = {
    vueService: stubVueService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
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
      },
    },
  });
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

    const button = wrapper.find('#vue');
    await button.trigger('click');

    expect(vueService.add.called).toBe(false);
  });

  it('should add Vue when project path is filled', async () => {
    const vueService = stubVueService();
    vueService.add.resolves({});
    await wrap({ vueService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const button = wrapper.find('#vue');
    await button.trigger('click');

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
    const button = wrapper.find('#vue');
    await button.trigger('click');

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

    const initButton = wrapper.find('#vue');
    await initButton.trigger('click');

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
    const initButton = wrapper.find('#vue');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Vue with style to project failed');
  });
});
