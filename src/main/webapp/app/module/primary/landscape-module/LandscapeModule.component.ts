import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { defineComponent, PropType } from 'vue';

export default defineComponent({
  name: 'LandscapeComponentVue',
  props: {
    module: {
      type: Object as PropType<LandscapeModule>,
      required: true,
    },
    landscapeElements: {
      type: Object as PropType<Map<string, HTMLElement>>,
      required: true,
    },
    modeClass: {
      type: String,
      required: true,
    },
  },
});
