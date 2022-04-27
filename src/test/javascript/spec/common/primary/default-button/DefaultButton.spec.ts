import { shallowMount, VueWrapper } from '@vue/test-utils';
import { DefaultButtonVue } from '@/common/primary/default-button';

let wrapper: VueWrapper;

interface WrapperOptions {
  disabled: boolean;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { disabled }: WrapperOptions = {
    disabled: false,
    ...wrapperOptions,
  };
  wrapper = shallowMount(DefaultButtonVue, {
    props: {
      disabled,
    },
  });
};

describe('DefaultButton', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
