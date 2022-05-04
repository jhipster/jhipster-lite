import { defineComponent } from 'vue';
import { Toast } from 'bootstrap';
import { nextTick } from '@vue/runtime-core';
import { ToastType } from './ToastType.enum';

let toast: Toast;

export default defineComponent({
  name: 'Toast',
  data() {
    return {
      toastType: ToastType.SUCCESS,
      message: '',
    };
  },
  mounted() {
    toast = new Toast(this.$refs.el as Element);
  },
  methods: {
    success(message: string) {
      this.toastType = ToastType.SUCCESS;
      this.message = message;
      this.showToast();
    },
    error(message: string) {
      this.toastType = ToastType.ERROR;
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
