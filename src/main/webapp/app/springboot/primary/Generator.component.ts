import { ProjectService } from '@/springboot/domain/ProjectService';
import { defineComponent, inject, ref } from 'vue';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { AngularService } from '@/springboot/domain/client/AngularService';
import { ReactService } from '@/springboot/domain/client/ReactService';
import { VueService } from '@/springboot/domain/client/VueService';

export default defineComponent({
  name: 'GeneratorComponent',
  components: {},
  setup() {
    const projectService = inject('projectService') as ProjectService;
    const angularService = inject('angularService') as AngularService;
    const reactService = inject('reactService') as ReactService;
    const vueService = inject('vueService') as VueService;

    const project = ref<ProjectToUpdate>({
      folder: '',
    });
    const isAngularWithStyle = ref<boolean>(false);
    const isReactWithStyle = ref<boolean>(false);
    const isVueWithStyle = ref<boolean>(false);

    const initProject = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.init(toProject(project.value));
      }
    };

    const addMaven = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addMaven(toProject(project.value));
      }
    };

    const addJavaBase = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addJavaBase(toProject(project.value));
      }
    };

    const addSpringBoot = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBoot(toProject(project.value));
      }
    };

    const addSpringBootMvcTomcat = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootMvcTomcat(toProject(project.value));
      }
    };

    const addAngular = async (): Promise<void> => {
      if (project.value.folder !== '') {
        if (isAngularWithStyle.value) {
          await angularService.addWithStyle(toProject(project.value));
        } else {
          await angularService.add(toProject(project.value));
        }
      }
    };

    const addReact = async (): Promise<void> => {
      if (project.value.folder !== '') {
        if (isReactWithStyle.value) {
          await reactService.addWithStyle(toProject(project.value));
        } else {
          await reactService.add(toProject(project.value));
        }
      }
    };

    const addVue = async (): Promise<void> => {
      if (project.value.folder !== '') {
        if (isVueWithStyle.value) {
          await vueService.addWithStyle(toProject(project.value));
        } else {
          await vueService.add(toProject(project.value));
        }
      }
    };

    const addFrontendMavenPlugin = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addFrontendMavenPlugin(toProject(project.value));
      }
    };

    return {
      project,
      isAngularWithStyle,
      isReactWithStyle,
      isVueWithStyle,
      initProject,
      addMaven,
      addJavaBase,
      addSpringBoot,
      addSpringBootMvcTomcat,
      addAngular,
      addReact,
      addVue,
      addFrontendMavenPlugin,
    };
  },
});
