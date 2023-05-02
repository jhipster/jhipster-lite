import { ThemeButtonVue } from '@/common/primary/theme-button/';
import { VueWrapper, mount } from '@vue/test-utils';
import { describe, expect, it } from 'vitest';
import { stubWindow } from '../../../module/primary/GlobalWindow.fixture';
import { LocalWindowThemeRepositoryStub, stubLocalWindowThemeRepository } from '../../../module/domain/ThemeRepository.fixture';

interface WrapperOptions {
  localWindowTheme: LocalWindowThemeRepositoryStub;
}

const wrap = (options?: Partial<WrapperOptions>): VueWrapper => {
  const { localWindowTheme }: WrapperOptions = {
    localWindowTheme: stubLocalWindowThemeRepository(),
    ...options,
  };

  return mount(ThemeButtonVue, {
    global: {
      provide: {
        localWindowTheme,
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
    const localWindowThemeRepository = stubLocalWindowThemeRepository();
    const wrapper = wrap({ localWindowTheme: localWindowThemeRepository });

    const checkbox = wrapper.find('.container_toggle');
    expect(localWindowThemeRepository.get.calledOnce).toBe(true);
    expect(localWindowThemeRepository.choose.calledOnce).toBe(true);

    checkbox.trigger('change');
    expect(localWindowThemeRepository.choose.called).toBe(true);

    checkbox.trigger('change');
    expect(localWindowThemeRepository.choose.called).toBe(true);
  });
});
