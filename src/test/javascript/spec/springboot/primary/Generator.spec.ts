import { ProjectService } from '@/springboot/domain/ProjectService';
import { GeneratorVue } from '@/springboot/primary';
import { shallowMount, VueWrapper } from '@vue/test-utils';
import { stubProjectService } from '../domain/ProjectService.fixture';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from './ProjectToUpdate.fixture';
import { stubLogger } from '../../common/domain/Logger.fixture';
import { Logger } from '@/common/domain/Logger';
import { FileDownloader } from '@/common/primary/FileDownloader';
import { stubFileDownloader } from '../../common/primary/FileDownloader.fixture';

let wrapper: VueWrapper;

interface WrapperOptions {
  logger: Logger;
  projectService: ProjectService;
  fileDownloader: FileDownloader;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { logger, fileDownloader, projectService }: WrapperOptions = {
    logger: stubLogger(),
    fileDownloader: stubFileDownloader(),
    projectService: stubProjectService(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(GeneratorVue, {
    global: {
      provide: {
        logger,
        fileDownloader,
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

  it('should handle error on init failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.init.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#init');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Project initialization failed');
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

  it('should handle error on adding maven failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addMaven.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#maven');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Maven to project failed');
  });

  it('should not add JaCoCo when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addJaCoCo.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#jacoco');
    await button.trigger('click');

    expect(projectService.addJaCoCo.called).toBe(false);
  });

  it('should add JaCoCo when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addJaCoCo.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#jacoco');
    await button.trigger('click');

    const args = projectService.addJaCoCo.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding JaCoCo failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addJaCoCo.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#jacoco');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding JaCoCo to project failed');
  });

  it('should not add Sonar Backend when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSonarBackend.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#sonar-backend');
    await button.trigger('click');

    expect(projectService.addSonarBackend.called).toBe(false);
  });

  it('should add Sonar Backend when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSonarBackend.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#sonar-backend');
    await button.trigger('click');

    const args = projectService.addSonarBackend.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding Sonar Backend failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addSonarBackend.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#sonar-backend');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Sonar Backend to project failed');
  });

  it('should not add Sonar Backend+Frontend when project path is not filled', async () => {
    const projectService = stubProjectService();
    projectService.addSonarBackendFrontend.resolves({});
    await wrap({ projectService });

    const button = wrapper.find('#sonar-backend-frontend');
    await button.trigger('click');

    expect(projectService.addSonarBackendFrontend.called).toBe(false);
  });

  it('should add Sonar Backend+Frontend when project path is filled', async () => {
    const projectService = stubProjectService();
    projectService.addSonarBackendFrontend.resolves({});
    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#sonar-backend-frontend');
    await button.trigger('click');

    const args = projectService.addSonarBackendFrontend.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should handle error on adding Sonar Backend+Frontend failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addSonarBackendFrontend.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#sonar-backend-frontend');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Sonar Backend+Frontend to project failed');
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

  it('should handle error on adding java base failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addJavaBase.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#javabase');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Java Base to project failed');
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

  it('should handle error on adding Frontend Maven Plugin failure', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.addFrontendMavenPlugin.rejects({});
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const initButton = wrapper.find('#frontend-maven-plugin');
    await initButton.trigger('click');

    const [message] = logger.error.getCall(0).args;
    expect(message).toBe('Adding Frontend Maven Plugin to project failed');
  });

  it('should download initialized project with basename', async () => {
    const projectService = stubProjectService();
    projectService.download.resolves({});
    // @ts-ignore
    global.URL.createObjectURL = jest.fn(() => new Blob([{}], { type: 'application/zip' }));

    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#download');
    await button.trigger('click');

    const args = projectService.download.getCall(0).args[0];
    expect(args).toEqual({
      baseName: 'beer',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should download initialized project without basename', async () => {
    const projectService = stubProjectService();
    projectService.download.resolves({});
    // @ts-ignore
    global.URL.createObjectURL = jest.fn(() => new Blob([{}], { type: 'application/zip' }));

    await wrap({ projectService });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate({
      folder: 'project/path',
      baseName: '',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    });
    await fillFullForm(projectToUpdate);

    const button = wrapper.find('#download');
    await button.trigger('click');

    const args = projectService.download.getCall(0).args[0];
    expect(args).toEqual({
      baseName: '',
      folder: 'project/path',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });

  it('should not download an non existing project', async () => {
    const logger = stubLogger();
    const projectService = stubProjectService();
    projectService.download.rejects(new Error('foo'));
    await wrap({ projectService, logger });
    const projectToUpdate: ProjectToUpdate = createProjectToUpdate();
    await fillFullForm(projectToUpdate);

    const downloadButton = wrapper.find('#download');
    await downloadButton.trigger('click');

    const [message, error] = logger.error.getCall(0).args;
    expect(message).toBe('Downloading project failed');
    expect(error).toStrictEqual(new Error('foo'));
  });
});
