import { computed, defineComponent, inject } from 'vue';
import { DefaultButtonVue } from '@/common/primary/default-button';
import { StoreGeneric } from 'pinia';
import { fromServiceProjection, ServiceProjection } from '@/springboot/primary/generator/ServiceProjection';

export default defineComponent({
  name: 'GeneratorButtonComponent',

  components: {
    DefaultButtonVue,
  },

  props: {
    label: {
      type: String,
      required: true,
    },
    service: {
      type: String,
      required: true,
    },
    selectorPrefix: {
      type: String,
      required: true,
    },
  },

  setup(props) {
    const historyStore = inject('historyStore') as StoreGeneric;

    const hasCalledService = computed(() => historyStore.hasCalledService(fromServiceProjection(props.service as ServiceProjection)));

    return {
      hasCalledService,
    };
  },
});
