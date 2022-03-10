import { ProjectService } from '@/springboot/domain/ProjectService';
import { defineComponent, inject, ref } from 'vue';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';

export default defineComponent({
  name: 'GeneratorComponent',
  components: {},
  setup() {
    const projectService = inject('projectService') as ProjectService;

    const project = ref<ProjectToUpdate>({
      folder: '',
    });

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

    return {
      project,
      initProject,
      addMaven,
      addJavaBase,
      addSpringBoot,
    };
  },
});
