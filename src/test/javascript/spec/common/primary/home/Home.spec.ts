import { shallowMount, VueWrapper } from '@vue/test-utils';
import { HomeVue } from '@/common/primary/home';

let wrapper: VueWrapper;

const wrap = () => {
  wrapper = shallowMount(HomeVue, {
    global: {
      stubs: ['router-link'],
    },
  });
};

describe('Home', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
