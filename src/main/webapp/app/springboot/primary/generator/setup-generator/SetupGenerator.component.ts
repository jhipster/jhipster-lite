import { defineComponent } from 'vue';
import { GeneratorButtonVue } from '@/springboot/primary/generator/generator-button';

export default defineComponent({
  name: 'SetupGeneratorComponent',

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
    return {};
  },
});
