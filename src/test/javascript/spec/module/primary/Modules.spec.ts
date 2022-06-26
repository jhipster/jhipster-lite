import { flushPromises, shallowMount, VueWrapper } from '@vue/test-utils';
import { defaultModules, stubModulesRepository } from '../domain/Modules.fixture';
import { ModulesVue } from '@/module/primary/modules';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { wrappedElement } from '../../WrappedElement';

interface WrapperOptions {
  modules: ModulesRepository;
}

const wrap = (options: WrapperOptions): VueWrapper => {
  return shallowMount(ModulesVue, {
    global: {
      provide: {
        modules: options.modules,
      },
    },
  });
};

describe('Modules', () => {
  it('Should display loader when waiting for modules', () => {
    const modules = stubModulesRepository();
    modules.list.resolves(defaultModules());

    const wrapper = wrap({ modules });

    expect(wrapper.find(wrappedElement('modules-loader')).exists()).toBe(true);
    expect(wrapper.find(wrappedElement('modules-list')).exists()).toBe(false);
  });

  it('Should load modules at startup', async () => {
    const wrapper = await componentWithModules();

    expect(wrapper.find(wrappedElement('modules-loader')).exists()).toBe(false);
    expect(wrapper.find(wrappedElement('modules-list')).exists()).toBe(true);
    expect(wrapper.find(wrappedElement('spring-cucumber-module-content')).exists()).toBe(true);
  });

  it('Should select module', async () => {
    const wrapper = await componentWithModules();

    await selectModule(wrapper);

    expect(wrapper.find(wrappedElement('property-baseName-field')).attributes('type')).toBe('text');
    expect(wrapper.find(wrappedElement('property-optionalBoolean-field')).exists()).toBe(true);
    expect(wrapper.find(wrappedElement('property-optionalInteger-field')).attributes('type')).toBe('number');
  });

  it('Should disable validation button without project folder', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('property-baseName-field')).setValue('test');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-application-button')).attributes('disabled')).toBeDefined();
  });

  it('Should disable validation button without mandatory fields', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('folder-path-field')).setValue('test');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-application-button')).attributes('disabled')).toBeDefined();
  });

  it('Should disable module application with empty string', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('folder-path-field')).setValue('test');
    wrapper.find(wrappedElement('property-baseName-field')).setValue('');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-application-button')).attributes('disabled')).toBeDefined();
  });

  it('Should enable module application with mandatory fields set', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('folder-path-field')).setValue('test');
    wrapper.find(wrappedElement('property-baseName-field')).setValue('test');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-application-button')).attributes('disabled')).toBeUndefined();
  });

  it('Should display setted properties', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('folder-path-field')).setValue('test');
    wrapper.find(wrappedElement('property-baseName-field')).setValue('test');
    wrapper.find(wrappedElement('property-optionalBoolean-field')).setValue('true');
    wrapper.find(wrappedElement('property-optionalInteger-field')).setValue('42');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-baseName-property-value')).text()).toBe('test');
    expect(wrapper.find(wrappedElement('module-spring-cucumber-optionalBoolean-property-value')).text()).toBe('true');
    expect(wrapper.find(wrappedElement('module-spring-cucumber-optionalInteger-property-value')).text()).toBe('42');
  });

  it('Should set boolean property to false', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('property-optionalBoolean-field')).setValue('false');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-optionalBoolean-property-value')).text()).toBe('false');
  });

  it('Should set boolean property to undefined', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('property-optionalBoolean-field')).setValue('true');
    await flushForm(wrapper);
    wrapper.find(wrappedElement('property-optionalBoolean-field')).setValue('');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-optionalBoolean-property-value')).text()).toBe('');
  });

  it('Should set integer property to undefine', async () => {
    const wrapper = await componentWithModules();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('property-optionalInteger-field')).setValue('42');
    await flushForm(wrapper);
    wrapper.find(wrappedElement('property-optionalInteger-field')).setValue('');
    await flushForm(wrapper);

    expect(wrapper.find(wrappedElement('module-spring-cucumber-optionalInteger-property-value')).text()).toBe('');
  });

  it('Should apply module using repository', async () => {
    const modules = stubModulesRepository();
    modules.list.resolves(defaultModules());
    const wrapper = wrap({ modules });

    await flushPromises();
    await selectModule(wrapper);

    wrapper.find(wrappedElement('folder-path-field')).setValue('test');
    wrapper.find(wrappedElement('property-baseName-field')).setValue('test');
    await flushForm(wrapper);

    wrapper.find(wrappedElement('module-spring-cucumber-application-button')).trigger('click');

    await flushPromises();

    expect(modules.list.calledOnce).toBe(true);
  });
});

const componentWithModules = async (): Promise<VueWrapper> => {
  const modules = stubModulesRepository();
  modules.list.resolves(defaultModules());
  const wrapper = wrap({ modules });

  await flushPromises();

  return wrapper;
};

const selectModule = async (wrapper: VueWrapper) => {
  wrapper.find(wrappedElement('spring-cucumber-module-content')).trigger('click');
  await wrapper.vm.$nextTick();
};

const flushForm = async (wrapper: VueWrapper) => {
  await wrapper.vm.$nextTick();
};
