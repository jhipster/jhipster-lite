import { ProjectService } from '@/springboot/domain/ProjectService';
import { GeneratorVue } from '@/springboot/primary';
import { mount, VueWrapper } from '@vue/test-utils';
import { stubProjectService } from '../domain/ProjectService.fixture';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from './ProjectToUpdate.fixture';
import { AngularService } from '@/springboot/domain/client/AngularService';
import { stubAngularService } from '../domain/client/AngularService.fixture';
import { stubReactService } from '../domain/client/ReactService.fixture';
import { ReactService } from '@/springboot/domain/client/ReactService';
import { stubVueService } from '../domain/client/VueService.fixture';
import { VueService } from '@/springboot/domain/client/VueService';

let wrapper: VueWrapper;

interface WrapperOptions {
  projectService: ProjectService;
  angularService: AngularService;
  reactService: ReactService;
  vueService: VueService;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { projectService, angularService, reactService, vueService }: WrapperOptions = {
    projectService: stubProjectService(),
    angularService: stubAngularService(),
    reactService: stubReactService(),
    vueService: stubVueService(),
    ...wrapperOptions,
  };
  wrapper = mount(GeneratorVue, {
    global: {
      provide: {
        projectService,
        angularService,
        reactService,
        vueService,
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

  it('should add SpringBoot MVC with Tomcat when project path is filled', async () => {
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

  it('should not add Angular when project path is not filled', async () => {
    const angularService = stubAngularService();
    angularService.add.resolves({});
    await wrap({ angularService });

    const button = wrapper.find('#angular');
    await button.trigger('click');

    expect(angularService.add.called).toBe(false);
  });

  it('should add Angular when project path is filled', async () => {
    const angularService = stubAngularService();
    angularService.add.resolves({});
    await wrap({ angularService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#angular');
    await button.trigger('click');

    const args = angularService.add.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add Angular with Style when checkbox is checked', async () => {
    const angularService = stubAngularService();
    angularService.addWithStyle.resolves({});
    await wrap({ angularService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const checkbox = wrapper.find('#angular-with-style');
    await checkbox.setValue(true);
    const button = wrapper.find('#angular');
    await button.trigger('click');

    const args = angularService.addWithStyle.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should not add React when project path is not filled', async () => {
    const reactService = stubReactService();
    reactService.add.resolves({});
    await wrap({ reactService });

    const button = wrapper.find('#react');
    await button.trigger('click');

    expect(reactService.add.called).toBe(false);
  });

  it('should add React when project path is filled', async () => {
    const reactService = stubReactService();
    reactService.add.resolves({});
    await wrap({ reactService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#react');
    await button.trigger('click');

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
    await wrap({ reactService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const checkbox = wrapper.find('#react-with-style');
    await checkbox.setValue(true);
    const button = wrapper.find('#react');
    await button.trigger('click');

    const args = reactService.addWithStyle.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should not add Vue when project path is not filled', async () => {
    const vueService = stubVueService();
    vueService.add.resolves({});
    await wrap({ vueService });

    const button = wrapper.find('#vue');
    await button.trigger('click');

    expect(vueService.add.called).toBe(false);
  });

  it('should add Vue when project path is filled', async () => {
    const vueService = stubVueService();
    vueService.add.resolves({});
    await wrap({ vueService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

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
    await wrap({ vueService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

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

  it('should not add Frontend Maven Plugin when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addFrontendMavenPlugin.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#frontend-maven-plugin');
    await button.trigger('click');

    expect(projectService.addFrontendMavenPlugin.called).toBe(false);
  });

  it('should add Frontend Maven Plugin when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addFrontendMavenPlugin.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#frontend-maven-plugin');
    await button.trigger('click');

    const args = projectService.addFrontendMavenPlugin.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add Ippon Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerIppon.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerippon');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerIppon.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });
  it('should add Ippon Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerIppon.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerippon');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerIppon.called).toBe(false);
  });

  it('should add JHV2 Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV2.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerjhipster-v2');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerJhipsterV2.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add JHV2 Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV2.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerjhipster-v2');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerJhipsterV2.called).toBe(false);
  });

  it('should add JHV3 Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV3.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerjhipster-v3');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerJhipsterV3.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add JHV3 Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV2.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerjhipster-v3');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerJhipsterV3.called).toBe(false);
  });

  it('should add JHV7 Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerjhipster-v7');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerJhipsterV7.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add JHV7 Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerjhipster-v7');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerJhipsterV7.called).toBe(false);
  });

  it('should add JHV7React Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7React.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerjhipster-v7-react');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerJhipsterV7React.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add JHV7React Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7React.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerjhipster-v7-react');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerJhipsterV7React.called).toBe(false);
  });

  it('should add JHV7Vue Banner when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7Vue.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#springbootbannerjhipster-v7-vue');
    await button.trigger('click');

    const args = projectService.addSpringBootBannerJhipsterV7Vue.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should add JHV7Vue Banner when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSpringBootBannerJhipsterV7Vue.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#springbootbannerjhipster-v7-vue');
    await button.trigger('click');

    expect(projectService.addSpringBootBannerJhipsterV7Vue.called).toBe(false);
  });
});
