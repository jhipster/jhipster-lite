import { ProjectService } from '@/springboot/domain/ProjectService';
import { GeneratorVue } from '@/springboot/primary';
import { mount, VueWrapper } from '@vue/test-utils';
import { stubProjectService } from '../domain/ProjectService.fixture';
import { ProjectToUpdate } from '../../../../../main/webapp/app/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from './ProjectToUpdate.fixture';

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

const fillFullForm = async (projectToUpdate: ProjectToUpdate): Promise<void> => {
  const projectPathInput = wrapper.find('#path');
  await projectPathInput.setValue(projectToUpdate.folder);
  const baseNameInput = wrapper.find('#basename');
  await baseNameInput.setValue(projectToUpdate.baseName);
  const projectNameInput = wrapper.find('#projectname');
  await projectNameInput.setValue(projectToUpdate.projectName);
  const packageNameInput = wrapper.find('#packagename');
  await packageNameInput.setValue(projectToUpdate.packageName);
  const serverPortInput = wrapper.find('#serverport');
  await serverPortInput.setValue(projectToUpdate.serverPort);
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
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#init');
    await initButton.trigger('click');

    const args = projectService.init.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
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
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#maven');
    await button.trigger('click');

    const args = projectService.addMaven.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
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
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#javabase');
    await button.trigger('click');

    const args = projectService.addJavaBase.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
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
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springboot');
    await button.trigger('click');

    const args = projectService.addSpringBoot.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should not add SpringBoot MVC with Tomcat when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootMvcTomcat.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootmvctomcat');
    await button.trigger('click');

    expect(projectService.addSpringBootMvcTomcat.called).toBe(false);
  });

  it('should add SpringBoot when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootMvcTomcat.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootmvctomcat');
    await button.trigger('click');

    const args = projectService.addSpringBootMvcTomcat.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });
});
