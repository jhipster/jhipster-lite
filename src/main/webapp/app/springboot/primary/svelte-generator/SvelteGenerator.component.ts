import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'SvelteGeneratorComponent',

  props: {
    project: {
      type: Object,
      required: true,
    },
  },

  setup() {
    const selectorPrefix = 'svelte-generator';
    const isSvelteWithStyle = ref<boolean>(false);

    return {
      selectorPrefix,
      isSvelteWithStyle,
    };
  },
});
