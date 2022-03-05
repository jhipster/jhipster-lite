import { ProjectService } from '@/generator/domain/ProjectService';
import { GeneratorVue } from '@/generator/primary';
import { mount, VueWrapper } from '@vue/test-utils';
import { Project } from '@/generator/domain/Project';
import { stubProjectService } from '../domain/ProjectService.fixture';

let wrapper: VueWrapper;

interface WrapperOptions {
  projectService: ProjectService;
}

const wrap = (wrapperOptions?: WrapperOptions) => {
  const { projectService }: WrapperOptions = {
    projectService: stubProjectService(),
    ...wrapperOptions,
  };
  wrapper = mount(GeneratorVue, {
    global: {
      provide: {
        projectService,
      },
    },
  });
};

describe('Generator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should not init project when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.init.resolves({});
    await wrap({ projectService });

    const initButton = wrapper.find('#init');
    await initButton.trigger('click');

    expect(projectService.init.called).toBe(false);
  });

  it('should init project when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.init.resolves({});
    await wrap({ projectService });
    const project: Project = { folder: 'project/path' };

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue(project.folder);
    const initButton = wrapper.find('#init');
    await initButton.trigger('click');

    expect(projectService.init.called).toBe(true);
  });

  it('should not add Maven when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addMaven.resolves({});
    await wrap({ projectService });

    const mavenButton = wrapper.find('#maven');
    await mavenButton.trigger('click');

    expect(projectService.addMaven.called).toBe(false);
  });

  it('should add Maven when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addMaven.resolves({});
    await wrap({ projectService });
    const project: Project = { folder: 'project/path' };

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue(project.folder);
    const mavenButton = wrapper.find('#maven');
    await mavenButton.trigger('click');

    expect(projectService.addMaven.called).toBe(true);
  });
});
