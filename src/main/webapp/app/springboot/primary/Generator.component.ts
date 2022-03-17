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

    const addBannerIppon = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerIppon(toProject(project.value));
      }
    };

    const addBannerJHipsterV2 = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerJHipsterV2(toProject(project.value));
      }
    };
    const addBannerJHipsterV3 = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerJHipsterV3(toProject(project.value));
      }
    };
    const addBannerJHipsterV7 = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerJHipsterV7(toProject(project.value));
      }
    };
    const addBannerJHipsterV7React = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerJHipsterV7React(toProject(project.value));
      }
    };
    const addBannerJHipsterV7Vue = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBootBannerJHipsterV7Vue(toProject(project.value));
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
      addBannerIppon,
      addBannerJHipsterV2,
      addBannerJHipsterV3,
      addBannerJHipsterV7,
      addBannerJHipsterV7React,
      addBannerJHipsterV7Vue,
    };
  },
});
