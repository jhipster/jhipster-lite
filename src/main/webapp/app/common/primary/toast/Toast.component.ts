import { defineComponent } from 'vue';
import { Toast } from 'bootstrap';
import { nextTick } from '@vue/runtime-core';

let toast: Toast;

export default defineComponent({
  name: 'Toast',
  data() {
    return {
      toastType: 'success',
      message: '',
    };
  },
  mounted() {
    toast = new Toast(this.$refs.el as Element);
  },
  methods: {
    success(message: string) {
      this.toastType = 'success';
      this.message = message;
      this.showToast();
    },
    error(message: string) {
      this.toastType = 'error';
      this.message = message;
      this.showToast();
    },
    showToast() {
      nextTick(() => {
        toast.show();
      });
    },
  },
});
