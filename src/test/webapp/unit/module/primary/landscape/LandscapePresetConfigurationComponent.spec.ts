import { flushPromises, mount } from '@vue/test-utils';
import { LandscapePresetConfigurationVue } from '@/module/primary/landscape-presetconfiguration';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { defaultPresets, ModulesRepositoryStub, stubModulesRepository } from '../../domain/Modules.fixture';

describe('LandscapePresetConfigurationComponent', () => {
  let modulesRepository: ModulesRepositoryStub;

  beforeEach(() => {
    vi.spyOn(console, 'error').mockImplementation(() => {});

    modulesRepository = stubModulesRepository();
    modulesRepository.preset.resolves(defaultPresets());
  });

  const wrap = () => {
    return mount(LandscapePresetConfigurationVue, {
      global: {
        provide: {
          modules: modulesRepository,
        },
      },
    });
  };

  it('should fetch presets on mount and populate the select box', async () => {
    const wrapper = wrap();
    await flushPromises();

    expect(modulesRepository.preset.called).toBe(true);
    expect(wrapper.vm.presets).toEqual(defaultPresets().presets);

    const options = wrapper.findAll('option');
    expect(options.length).toBe(defaultPresets().presets.length);
    options.forEach((option, index) => {
      expect(option.text()).toBe(defaultPresets().presets[index].name);
    });
  });

  it('should handle API error gracefully', async () => {
    const error = new Error('API Error');
    modulesRepository.preset.rejects(error);
    const wrapper = wrap();
    await flushPromises();

    expect(wrapper.vm.presets).toEqual([]);
    expect(console.error).toHaveBeenCalledWith(error);
  });

  it('should emit preset-selected event with selected preset', async () => {
    const wrapper = wrap();
    await flushPromises();

    const select = wrapper.find('select');
    select.element.value = wrapper.vm.presets[0].name;
    await select.trigger('change');

    expect(wrapper.emitted('preset-selected')).toBeTruthy();
    expect(wrapper.emitted('preset-selected')![0]).toEqual([wrapper.vm.presets[0]]);
  });
});
