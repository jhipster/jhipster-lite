import { defineComponent } from 'vue';

export default defineComponent({
  name: 'DefaultButton',

  props: {
    label: {
      type: String,
      required: true,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
});
