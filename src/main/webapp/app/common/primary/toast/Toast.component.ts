import { defineComponent, inject, onBeforeUnmount, onMounted, ref, Ref } from 'vue';
import { Unsubscribe } from '@/common/domain/alert/Unsubscribe';
import { ToastType } from '@/common/primary/toast/ToastType';
import { ToastMessage } from '@/common/primary/toast/ToastMessage';
import { AlertMessage } from '@/common/domain/alert/AlertMessage';
import { AlertListener } from '@/common/domain/alert/AlertListener';
import { IconVue } from '@/common/primary/icon';

export default defineComponent({
  name: 'Toast',

  components: {
    IconVue,
  },

  setup() {
    const alertListener = inject('alertListener') as AlertListener;

    const toast = ref(null);
    const show = ref(false);
    const type: Ref<ToastType | undefined> = ref(undefined);
    const message: Ref<ToastMessage | undefined> = ref(undefined);

    let unsubscribeSuccessAlertBus!: Unsubscribe;
    let unsubscribeErrorAlertBus!: Unsubscribe;

    const hideToast = () => {
      show.value = false;
    };

    const showToast = () => {
      show.value = true;
    };

    const displaySuccess = (alertMessage: AlertMessage) => {
      type.value = ToastType.SUCCESS;
      message.value = alertMessage;
      showToast();
    };

    const displayError = (alertMessage: AlertMessage) => {
      type.value = ToastType.ERROR;
      message.value = alertMessage;
      showToast();
    };

    onMounted(() => {
      unsubscribeSuccessAlertBus = alertListener.onSuccess(displaySuccess);
      unsubscribeErrorAlertBus = alertListener.onError(displayError);
    });

    onBeforeUnmount(() => {
      unsubscribeSuccessAlertBus();
      unsubscribeErrorAlertBus();
    });

    return {
      toast,
      type,
      message,
      hideToast,
      show,
    };
  },
  methods: {
    close() {
      this.hideToast();
    },
  },
});
