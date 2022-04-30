import { GeneratorVue } from '@/springboot/primary';
import { shallowMount } from '@vue/test-utils';

describe('Generator', () => {
  it('should exist', () => {
    const wrapper = shallowMount(GeneratorVue);

    expect(wrapper.exists()).toBe(true);
  });
});
