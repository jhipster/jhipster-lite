import { shallowMount } from '@vue/test-utils';
import { VueWrapper } from '@vue/test-utils';
import { AppVue } from '../../../../../../main/webapp/app/common/primary/app';

let wrapper: VueWrapper;

const wrap = () => {
  wrapper = shallowMount(AppVue);
};

describe('App', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
