import { defineComponent, inject } from 'vue';
import { DefaultButtonVue } from '@/common/primary/default-button';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { SvelteService } from '@/springboot/domain/client/SvelteService';
import { AlertBus } from '@/common/domain/alert/AlertBus';

export default defineComponent({
  name: 'SvelteGeneratorComponent',

  components: {
    DefaultButtonVue,
  },

  props: {
    project: {
      type: Object,
      required: true,
    },
  },

  setup(props) {
    const alertBus = inject('alertBus') as AlertBus;
    const svelteService = inject('svelteService') as SvelteService;

    const selectorPrefix = 'svelte-generator';

    const addSvelte = async (): Promise<void> => {
      try {
        await tryAddSvelte();
        alertBus.success('Svelte successfully added');
      } catch (error) {
        alertBus.error(`Adding Svelte to project failed ${error}`);
      }
    };

    const tryAddSvelte = async (): Promise<void> => {
      if (props.project.folder === '') {
        return;
      }
      await svelteService.addWithStyle(toProject(props.project as ProjectToUpdate));
    };

    return {
      selectorPrefix,
      addSvelte,
    };
  },
});
