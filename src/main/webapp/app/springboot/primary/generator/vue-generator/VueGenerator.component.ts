import { defineComponent, inject, ref } from 'vue';
import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { VueService } from '@/springboot/domain/client/VueService';
import { GeneratorButtonVue } from '@/springboot/primary/generator/generator-button';
import { AlertBus } from '@/common/domain/alert/AlertBus';

export default defineComponent({
  name: 'VueGeneratorComponent',

  components: {
    GeneratorButtonVue,
  },

  props: {
    project: {
      type: Object,
      required: true,
    },
  },

  setup(props) {
    const alertBus = inject('alertBus') as AlertBus;
    const vueService = inject('vueService') as VueService;

    const selectorPrefix = 'vue-generator';
    const isVueWithStyle = ref<boolean>(false);

    const addVue = async (): Promise<void> => {
      if (props.project.folder !== '') {
        if (isVueWithStyle.value) {
          await vueService
            .addWithStyle(toProject(props.project as ProjectToUpdate))
            .then(() => alertBus.success('Vue with style successfully added'))
            .catch(error => alertBus.error(`Adding Vue with style to project failed ${error}`));
        } else {
          await vueService
            .add(toProject(props.project as ProjectToUpdate))
            .then(() => alertBus.success('Vue successfully added'))
            .catch(error => alertBus.error(`Adding Vue to project failed ${error}`));
        }
      }
    };

    return {
      selectorPrefix,
      isVueWithStyle,
      addVue,
      props,
    };
  },
});
