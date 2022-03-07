import { Project } from '@/generator/domain/Project';
import { ProjectService } from '@/generator/domain/ProjectService';
import { defineComponent, inject, ref } from 'vue';

export default defineComponent({
  name: 'GeneratorComponent',
  components: {},
  setup() {
    const projectService = inject('projectService') as ProjectService;

    const project = ref<Project>({ folder: '' });

    const initProject = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.init(project.value);
      }
    };

    const addMaven = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addMaven(project.value);
      }
    };

    const addJavaBase = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addJavaBase(project.value);
      }
    };

    const addSpringBoot = async (): Promise<void> => {
      if (project.value.folder !== '') {
        await projectService.addSpringBoot(project.value);
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
