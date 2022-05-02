import { shallowMount } from '@vue/test-utils';
import { ToastVue, ToastType } from '@/common/primary/toast';
import { nextTick } from '@vue/runtime-core';

describe('Toast', () => {
  it('should exist', () => {
    const wrapper = shallowMount(ToastVue, {});

    expect(wrapper.exists()).toBe(true);
  });

  it('should show success toast', () => {
    const wrapper = shallowMount(ToastVue, {});

    wrapper.vm.success('Test message');

    expect(wrapper.vm.message).toBe('Test message');
    expect(wrapper.vm.toastType).toBe(ToastType.SUCCESS);
    nextTick(() => {
      expect(wrapper.classes()).toContain('fade');
    });
  });

  it('should show error toast', () => {
    const wrapper = shallowMount(ToastVue, {});

    wrapper.vm.error('Test error message');

    expect(wrapper.vm.message).toBe('Test error message');
    expect(wrapper.vm.toastType).toBe(ToastType.ERROR);
    nextTick(() => {
      expect(wrapper.classes()).toContain('fade');
    });
  });
});
