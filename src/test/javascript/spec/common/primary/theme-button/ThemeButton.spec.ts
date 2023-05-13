import { ThemeButtonVue } from '@/common/primary/theme-button/';
import { VueWrapper, mount } from '@vue/test-utils';
import { describe, expect, it } from 'vitest';
import { stubWindow } from '../../../module/primary/GlobalWindow.fixture';
import { LocalWindowThemeRepositoryStub, stubLocalWindowThemeRepository } from '../../../module/domain/ThemeRepository.fixture';

interface WrapperOptions {
  themeRepository: LocalWindowThemeRepositoryStub;
}

const wrap = (options?: Partial<WrapperOptions>): VueWrapper => {
  const { themeRepository }: WrapperOptions = {
    themeRepository: stubLocalWindowThemeRepository(),
    ...options,
  };

  return mount(ThemeButtonVue, {
    global: {
      provide: {
        themeRepository,
        globalWindow: stubWindow(),
      },
    },
  });
};

describe('ThemeButton', () => {
  it('Should exist', () => {
    const wrapper = wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('Should switch theme', () => {
    const themeRepository = stubLocalWindowThemeRepository();
    const wrapper = wrap({ themeRepository });

    const checkbox = wrapper.find('.container_toggle');
    expect(themeRepository.get.calledOnce).toBe(true);
    expect(themeRepository.choose.calledOnce).toBe(true);

    checkbox.trigger('change');
    expect(themeRepository.choose.called).toBe(true);

    checkbox.trigger('change');
    expect(themeRepository.choose.called).toBe(true);
  });
});
