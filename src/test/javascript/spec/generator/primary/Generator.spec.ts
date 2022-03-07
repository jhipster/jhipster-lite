import { ProjectService } from '@/generator/domain/ProjectService';
import { GeneratorVue } from '@/generator/primary';
import { mount, VueWrapper } from '@vue/test-utils';
import { Project } from '@/generator/domain/Project';
import { stubProjectService } from '../domain/ProjectService.fixture';
import { createProject } from '../domain/Project.fixture';

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
    const project: Project = createProject({ folder: 'project/path' });

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

    const button = wrapper.find('#maven');
    await button.trigger('click');

    expect(projectService.addMaven.called).toBe(false);
  });

  it('should add Maven when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addMaven.resolves({});
    await wrap({ projectService });
    const project: Project = createProject({ folder: 'project/path' });

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue(project.folder);
    const button = wrapper.find('#maven');
    await button.trigger('click');

    expect(projectService.addMaven.called).toBe(true);
  });

  it('should not add JavaBase when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addJavaBase.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#javabase');
    await button.trigger('click');

    expect(projectService.addJavaBase.called).toBe(false);
  });

  it('should add JavaBase when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addJavaBase.resolves({});
    await wrap({ projectService });
    const project: Project = createProject({ folder: 'project/path' });

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue(project.folder);
    const button = wrapper.find('#javabase');
    await button.trigger('click');

    expect(projectService.addJavaBase.called).toBe(true);
  });

  it('should not add SpringBoot when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBoot.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springboot');
    await button.trigger('click');

    expect(projectService.addSpringBoot.called).toBe(false);
  });

  it('should add SpringBoot when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBoot.resolves({});
    await wrap({ projectService });
    const project: Project = createProject({ folder: 'project/path' });

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue(project.folder);
    const button = wrapper.find('#springboot');
    await button.trigger('click');

    expect(projectService.addSpringBoot.called).toBe(true);
  });
});
