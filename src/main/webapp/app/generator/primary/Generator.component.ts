import { Project } from '@/generator/domain/Project';
import { ProjectService } from '@/generator/domain/ProjectService';
import { defineComponent, inject, ref } from 'vue';

export default defineComponent({
  name: 'GeneratorComponent',
  components: {},
  setup() {
    const projectService = inject('projectService') as ProjectService;

    const project = ref<Project>({ folder: '' });

    const initProject = async () => {
      if (project.value.folder !== '') {
        await projectService.init(project.value);
      }
    };

    const addMaven = async () => {
      if (project.value.folder !== '') {
        await projectService.addMaven(project.value);
      }
    };

    return {
      project,
      initProject,
      addMaven,
    };
  },
});
