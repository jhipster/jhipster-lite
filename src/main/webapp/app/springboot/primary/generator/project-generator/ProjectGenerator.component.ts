import { defineComponent, inject } from 'vue';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { Logger } from '@/common/domain/Logger';
import { ProjectService } from '@/springboot/domain/ProjectService';
import { FileDownloader } from '@/common/primary/FileDownloader';
import { GeneratorButtonVue } from '@/springboot/primary/generator/generator-button';
import ToastService from '@/common/secondary/ToastService';

export default defineComponent({
  name: 'ProjectGeneratorComponent',

  components: {
    GeneratorButtonVue,
  },

  props: {
    project: {
      type: Object,
      required: true,
    },
    buildTool: {
      type: String,
      required: true,
    },
    setupTool: {
      type: String,
      required: true,
    },
  },

  setup(props) {
    const logger = inject('logger') as Logger;
    const projectService = inject('projectService') as ProjectService;
    const fileDownloader = inject('fileDownloader') as FileDownloader;
    const toastService = inject('toastService') as ToastService;

    const selectorPrefix = 'project-generator';

    const initProject = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .init(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Project successfully intialized'))
          .catch(error => {
            logger.error('Project initialization failed', error.message);
            toastService.error('Project initialization failed ' + error.message);
          });
      }
    };

    const addMaven = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addMaven(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Maven successfully added'))
          .catch(error => {
            logger.error('Adding Maven to project failed', error);
            toastService.error('Adding Maven to project failed ' + error.message);
          });
      }
    };

    const addCodespaces = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addCodespacesSetup(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Codespaces setup successfully added'))
          .catch(error => {
            logger.error('Adding Codespaces setup to project failed', error);
            toastService.error('Adding Codespaces setup to project failed ' + error.message);
          });
      }
    };

    const addGitpod = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addGitpodSetup(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Gitpod setup successfully added'))
          .catch(error => {
            logger.error('Adding Gitpod setup to project failed', error);
            toastService.error('Adding Gitpod setup to project failed ' + error.message);
          });
      }
    };

    const addJaCoCo = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addJaCoCo(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('JaCoCo successfully added'))
          .catch(error => {
            logger.error('Adding JaCoCo to project failed', error);
            toastService.error('Adding JaCoCo to project failed ' + error.message);
          });
      }
    };

    const addSonarBackend = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addSonarBackend(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Sonar Backend successfully added'))
          .catch(error => {
            logger.error('Adding Sonar Backend to project failed', error);
            toastService.error('Adding Sonar Backend to project failed ' + error.message);
          });
      }
    };

    const addSonarBackendFrontend = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addSonarBackendFrontend(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Sonar Backend+Frontend successfully added'))
          .catch(error => {
            logger.error('Adding Sonar Backend+Frontend to project failed', error);
            toastService.error('Adding Sonar Backend+Frontend to project failed ' + error.message);
          });
      }
    };

    const addJavaBase = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addJavaBase(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Java Base successfully added'))
          .catch(error => {
            logger.error('Adding Java Base to project failed', error);
            toastService.error('Adding Java Base to project failed ' + error.message);
          });
      }
    };

    const addFrontendMavenPlugin = async (): Promise<void> => {
      if (props.project.folder !== '') {
        await projectService
          .addFrontendMavenPlugin(toProject(props.project as ProjectToUpdate))
          .then(() => toastService.success('Frontend Maven Plugin successfully added'))
          .catch(error => {
            logger.error('Adding Frontend Maven Plugin to project failed', error);
            toastService.error('Adding Frontend Maven Plugin to project failed ' + error.message);
          });
      }
    };

    const download = async (): Promise<void> => {
      await projectService
        .download(toProject(props.project as ProjectToUpdate))
        .then(file => {
          toastService.success('File ready for download');
          fileDownloader.download(file);
        })
        .catch(error => {
          logger.error('Downloading project failed', error);
          toastService.error('Downlaod failed ' + error.message);
        });
    };

    return {
      selectorPrefix,
      props,
      initProject,
      addMaven,
      addJaCoCo,
      addSonarBackend,
      addSonarBackendFrontend,
      addJavaBase,
      addFrontendMavenPlugin,
      addCodespaces,
      addGitpod,
      download,
    };
  },
});
