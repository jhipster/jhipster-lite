import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ToastVue } from '@/common/primary/toast';
import sinon, { SinonStub } from 'sinon';
import { ToastType } from '@/common/primary/toast/ToastType';
import { AlertListener } from '@/common/domain/alert/AlertListener';
import { AlertListenerFixture, stubAlertListener } from '../../domain/AlertListener.fixure';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  alertListener: AlertListener;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { alertListener }: WrapperOptions = {
    alertListener: stubAlertListener(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(ToastVue, {
    global: {
      provide: {
        alertListener,
      },
    },
  });
  component = wrapper.vm;
};

const openToast = (listen: (alertListener: AlertListenerFixture) => SinonStub) => async (): Promise<void> => {
  const alertListener = stubAlertListener();
  wrap({ alertListener });
  const [callback] = listen(alertListener).getCall(0).args;

  callback('message');

  await wrapper.vm.$nextTick();
};

const errorToast = openToast(alertListener => alertListener.onError);

const successToast = openToast(alertListener => alertListener.onSuccess);

describe('Toast', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should display toast on success', async () => {
    await successToast();

    expect(component.message).toBe('message');
    expect(component.type).toBe(ToastType.SUCCESS);
  });

  it('should display toast on error', async () => {
    await errorToast();

    expect(component.message).toBe('message');
    expect(component.type).toBe(ToastType.ERROR);
  });

  it('should hide toast on close', async () => {
    await successToast();

    await wrapper.find('[data-selector="toast.close"]').trigger('click');

    expect(wrapper.find('[data-selector="toast-overlay"]').exists()).toBe(false);
  });

  it('should unsubscribe on before unmount', () => {
    const alertListener = stubAlertListener();
    const unsubscribeSuccess = sinon.stub();
    alertListener.onSuccess.returns(unsubscribeSuccess);
    const unsubscribeError = sinon.stub();
    alertListener.onError.returns(unsubscribeError);
    wrap({ alertListener });

    wrapper.unmount();

    expect(unsubscribeSuccess.calledOnce).toBe(true);
    expect(unsubscribeError.calledOnce).toBe(true);
  });
});
