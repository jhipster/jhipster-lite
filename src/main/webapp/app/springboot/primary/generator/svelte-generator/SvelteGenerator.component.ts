import { defineComponent, inject, ref } from 'vue';
import { DefaultButtonVue } from '@/common/primary/default-button';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { VueService } from '@/springboot/domain/client/VueService';
import { Logger } from '@/common/domain/Logger';
import ToastService from '@/common/secondary/ToastService';

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
    const logger = inject('logger') as Logger;
    const toastService = inject('toastService') as ToastService;
    const svelteService = inject('svelteService') as VueService;

    const selectorPrefix = 'svelte-generator';
    const isSvelteWithStyle = ref<boolean>(false);

    const addSvelte = async (): Promise<void> => {
      try {
        await tryAddSvelte();
        toastService.success('Svelte successfully added');
      } catch (e) {
        logger.error('Adding Svelte to project failed', e as Error);
        toastService.error('Adding Svelte to project failed');
      }
    };

    const tryAddSvelte = async (): Promise<void> => {
      if (props.project.folder === '') {
        return;
      }
      if (isSvelteWithStyle.value) {
        await svelteService.addWithStyle(toProject(props.project as ProjectToUpdate));
        return;
      }
      await svelteService.add(toProject(props.project as ProjectToUpdate));
    };

    return {
      selectorPrefix,
      isSvelteWithStyle,
      addSvelte,
    };
  },
});
