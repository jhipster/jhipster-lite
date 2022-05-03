import sinon from 'sinon';

import ToastService from '@/common/secondary/ToastService';
import { shallowMount } from '@vue/test-utils';
import { ToastVue } from '@/common/primary/toast';

describe('ToastService', () => {
  it('should register a toast instance', () => {
    const toast = shallowMount(ToastVue);
    const toastService = new ToastService();
    toastService.register(toast.vm);

    expect(toastService.toastRef).toBeTruthy();
  });

  it('should trigger a success toast', () => {
    const toast = shallowMount(ToastVue);
    toast.vm.success = sinon.stub();
    const toastService = new ToastService();
    toastService.register(toast.vm);
    toastService.success('success message');

    expect(toast.vm.success.called).toBe(true);
  });

  it('should trigger a failure toast', () => {
    const toast = shallowMount(ToastVue);
    toast.vm.error = sinon.stub();
    const toastService = new ToastService();
    toastService.register(toast.vm);
    toastService.error('error message');

    expect(toast.vm.error.called).toBe(true);
  });
});
