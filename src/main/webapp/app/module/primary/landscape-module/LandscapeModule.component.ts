import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { defineComponent, PropType, ref } from 'vue';

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
    moduleFlavor: {
      type: String,
      required: true,
    },
  },
  emits: ['over', 'out'],
  setup(props, { emit }) {
    const isModuleEmphasized = ref(false);

    const moduleClass = (): string => {
      if (isModuleEmphasized.value) {
        return flavoredClass('-highlighted');
      }

      return flavoredClass();
    };

    const flavoredClass = (alternative?: string): string => {
      if (alternative === undefined) {
        return props.moduleFlavor;
      }

      return alternative + ' ' + props.moduleFlavor;
    };

    const emphasisizeModule = (): void => {
      isModuleEmphasized.value = true;

      emit('over');
    };

    const deEmphasisizeModule = (): void => {
      isModuleEmphasized.value = false;

      emit('out');
    };

    return {
      moduleClass,
      emphasisizeModule,
      deEmphasisizeModule,
    };
  },
});
