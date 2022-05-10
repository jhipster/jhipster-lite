import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { SvelteGeneratorVue } from '@/springboot/primary/generator/svelte-generator';
import { stubSvelteService } from '../../../domain/client/SvelteService.fixture';
import { SvelteService } from '../../../../../../../main/webapp/app/springboot/domain/client/SvelteService';
import { stubVueService } from '../../../domain/client/VueService.fixture';
import { stubLogger } from '../../../../common/domain/Logger.fixture';
import { Logger } from '../../../../../../../main/webapp/app/common/domain/Logger';
import { stubToastService } from '../../../../common/secondary/ToastService.fixture';
import ToastService from '../../../../../../../main/webapp/app/common/secondary/ToastService';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  project: ProjectToUpdate;
  svelteService: SvelteService;
  logger: Logger;
  toastService: ToastService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { project, svelteService, logger, toastService }: WrapperOptions = {
    project: createProjectToUpdate(),
    svelteService: stubSvelteService(),
    logger: stubLogger(),
    toastService: stubToastService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(SvelteGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        svelteService,
        logger,
        toastService,
      },
    },
  });
  component = wrapper.vm;
};

const PROJECT_FOLDER = 'folder/path';
describe('SvelteGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should add Svelte', async () => {
    const svelteService = stubSvelteService();
    await wrap({ svelteService });

    await component.addSvelte();

    const [addedProject] = svelteService.add.getCall(0).args;
    expect(addedProject).toEqual({
      baseName: 'beer',
      folder: PROJECT_FOLDER,
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should not add Svelte when project path is not filled', async () => {
    const svelteService = stubSvelteService();
    await wrap({ svelteService, project: createProjectToUpdate({ folder: '' }) });

    await component.addSvelte();

    expect(svelteService.add.called).toBe(false);
  });

  it('should add Svelte with style when checkbox is checked', async () => {
    const svelteService = stubSvelteService();
    await wrap({ svelteService });

    const checkbox = wrapper.find('#svelte-with-style');
    await checkbox.setValue(true);
    await component.addSvelte();

    expect(svelteService.addWithStyle.called).toBeTruthy();
  });

  it('should handle error on adding Svelte failure', async () => {
    const logger = stubLogger();
    const toastService = stubToastService();
    const svelteService = stubSvelteService();
    svelteService.add.rejects({});
    await wrap({ svelteService, toastService, logger });

    await component.addSvelte();

    const [loggerMessage] = logger.error.getCall(0).args;
    const [toastMessage] = toastService.error.getCall(0).args;

    expect(toastMessage).toBe('Adding Svelte to project failed');
    expect(loggerMessage).toBe('Adding Svelte to project failed');
  });

  it('should handle success on adding Svelte', async function () {
    const toastService = stubToastService();
    const svelteService = stubSvelteService();
    await wrap({ svelteService, toastService });

    await component.addSvelte();

    const [toastMessage] = toastService.success.getCall(0).args;

    expect(toastMessage).toBe('Svelte successfully added');
  });
});
