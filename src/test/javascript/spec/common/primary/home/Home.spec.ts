import { shallowMount, VueWrapper } from '@vue/test-utils';
import { HomeVue } from '@/main/webapp/app/common/primary/home';

let wrapper: VueWrapper;

const wrap = () => {
  wrapper = shallowMount(HomeVue);
};

describe('Home', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
