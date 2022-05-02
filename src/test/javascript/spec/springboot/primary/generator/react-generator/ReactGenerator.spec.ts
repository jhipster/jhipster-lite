import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubLogger } from '../../../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { ReactService } from '@/springboot/domain/client/ReactService';
import { stubReactService } from '../../../domain/client/ReactService.fixture';
import { ReactGeneratorVue } from '@/springboot/primary/generator/react-generator';
import { stubNotificationService } from '../../../common/domain/NotificationService.fixture';
import { NotificationService } from '@/common/domain/NotificationService';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  reactService: ReactService;
  logger: Logger;
  project: ProjectToUpdate;
  toastService: NotificationService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { reactService, logger, project, toastService }: WrapperOptions = {
    reactService: stubReactService(),
    logger: stubLogger(),
    project: createProjectToUpdate(),
    toastService: stubNotificationService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(ReactGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        reactService,
        logger,
        toastService,
      },
    },
  });
  component = wrapper.vm;
};

describe('ReactGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should not add React when project path is not filled', async () => {
    const reactService = stubReactService();
    reactService.add.resolves({});
    await wrap({ reactService, project: createProjectToUpdate({ folder: '' }) });

    await component.addReact();

    expect(reactService.add.called).toBe(false);
  });

  it('should add React when project path is filled', async () => {
    const reactService = stubReactService();
    reactService.add.resolves({});
    await wrap({ reactService, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addReact();

    const args = reactService.add.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add React with Style when checkbox is checked', async () => {
    const reactService = stubReactService();
    reactService.addWithStyle.resolves({});
    await wrap({ reactService, project: createProjectToUpdate({ folder: 'project/path' }) });

    const checkbox = wrapper.find('#react-with-style');
    await checkbox.setValue(true);
    await component.addReact();

    const args = reactService.addWithStyle.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding React failure', async () => {
    const logger = stubLogger();
    const reactService = stubReactService();
    reactService.add.rejects({});
    await wrap({ reactService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    await component.addReact();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding React to project failed');
  });

  it('should handle error on adding React with style failure', async () => {
    const logger = stubLogger();
    const reactService = stubReactService();
    reactService.addWithStyle.rejects({});
    await wrap({ reactService, logger, project: createProjectToUpdate({ folder: 'project/path' }) });

    const checkbox = wrapper.find('#react-with-style');
    await checkbox.setValue(true);
    await component.addReact();

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding React with style to project failed');
  });
});
