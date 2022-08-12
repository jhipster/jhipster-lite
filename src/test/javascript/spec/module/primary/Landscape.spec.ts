import { ApplicationListener } from '@/common/primary/applicationlistener/ApplicationListener';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { LandscapeVue } from '@/module/primary/landscape';
import { flushPromises, mount, VueWrapper } from '@vue/test-utils';
import sinon, { SinonStub } from 'sinon';
import { wrappedElement } from '../../WrappedElement';
import { defaultLandscape, ModulesRepositoryStub, stubModulesRepository } from '../domain/Modules.fixture';

interface ApplicationListenerStub extends ApplicationListener {
  addEventListener: SinonStub;
  removeEventListener: SinonStub;
}

const stubApplicationListener = (): ApplicationListenerStub => ({
  addEventListener: sinon.stub(),
  removeEventListener: sinon.stub(),
});

interface WrapperOptions {
  modules: ModulesRepository;
  applicationListener: ApplicationListener;
}

const wrap = (options?: Partial<WrapperOptions>): VueWrapper => {
  const { modules, applicationListener }: WrapperOptions = {
    modules: repositoryWithLandscape(),
    applicationListener: stubApplicationListener(),
    ...options,
  };

  return mount(LandscapeVue, {
    global: {
      provide: {
        modules,
        applicationListener,
      },
    },
  });
};

const componentWithLandscape = async (applicationListener?: ApplicationListener): Promise<VueWrapper> => {
  const listener = applicationListener || stubApplicationListener();

  const modules = repositoryWithLandscape();

  const wrapper = wrap({ modules, applicationListener: listener });

  await flushPromises();

  return wrapper;
};

const repositoryWithLandscape = (): ModulesRepositoryStub => {
  const modules = stubModulesRepository();
  modules.landscape.resolves(defaultLandscape());

  return modules;
};

describe('Landscape', () => {
  describe('Loading', () => {
    it('Should display loader when loading landscape', () => {
      const wrapper = wrap();

      expect(wrapper.find(wrappedElement('landscape-loader')).exists()).toBe(true);
      expect(wrapper.find(wrappedElement('landscape')).exists()).toBe(false);
    });

    it('Should load landscape at startup', async () => {
      const applicationListener = stubApplicationListener();
      const wrapper = await componentWithLandscape(applicationListener);

      expect(wrapper.find(wrappedElement('landscape-loader')).exists()).toBe(false);
      expect(wrapper.find(wrappedElement('landscape')).exists()).toBe(true);
      expect(wrapper.find(wrappedElement('landscape-connectors')).findAll('line').length).toBe(14 * 3);
      expect(applicationListener.addEventListener.calledOnce).toBe(true);
    });

    it('Should unload landscape at destroy', async () => {
      const applicationListener = stubApplicationListener();
      const wrapper = await componentWithLandscape(applicationListener);

      wrapper.unmount();

      expect(applicationListener.removeEventListener.calledOnce).toBe(true);
    });
  });

  describe('Display modes', () => {
    it('Should switch to compacted mode', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('compacted-mode-button')).trigger('click');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('compacted-mode-button')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('extended-mode-button')).classes()).toContain('-not-selected');
      expect(wrapper.find(wrappedElement('infinitest-module')).classes()).toContain('-compacted');
    });

    it('Should switch to extended mode', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('extended-mode-button')).trigger('click');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('compacted-mode-button')).classes()).toContain('-not-selected');
      expect(wrapper.find(wrappedElement('extended-mode-button')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('infinitest-module')).classes()).toContain('-extended');
    });
  });

  describe('Modules emphasize', () => {
    it('Should highlight compacted module and dependencies', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      const vueClasses = wrapper.find(wrappedElement('vue-module')).classes();
      expect(vueClasses).toContain('-highlighted');
      expect(vueClasses).toContain('-compacted');

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-highlighted');
      expect(initClasses).toContain('-compacted');

      assertHighlightedConnectorsCount(wrapper, 3);
    });
  });

  it('Should highlight extended module and dependencies', async () => {
    const wrapper = await componentWithLandscape();
    wrapper.find(wrappedElement('extended-mode-button')).trigger('click');
    await wrapper.vm.$nextTick();

    wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
    await wrapper.vm.$nextTick();

    const vueClasses = wrapper.find(wrappedElement('vue-module')).classes();
    expect(vueClasses).toContain('-highlighted');
    expect(vueClasses).toContain('-extended');

    const initClasses = wrapper.find(wrappedElement('init-module')).classes();
    expect(initClasses).toContain('-highlighted');
    expect(initClasses).toContain('-extended');

    assertHighlightedConnectorsCount(wrapper, 3);
  });

  it('Should highlight dependent feature', async () => {
    const wrapper = await componentWithLandscape();

    wrapper.find(wrappedElement('dummy-feature-module')).trigger('mouseover');
    await wrapper.vm.$nextTick();

    const springMvcClasses = wrapper.find(wrappedElement('spring-mvc-feature')).classes();
    expect(springMvcClasses).toContain('-highlighted');
    expect(springMvcClasses).toContain('-compacted');
  });

  it('Should un-highlight module and dependencies', async () => {
    const wrapper = await componentWithLandscape();

    wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
    await wrapper.vm.$nextTick();
    wrapper.find(wrappedElement('vue-module')).trigger('mouseleave');
    await wrapper.vm.$nextTick();

    expect(wrapper.find(wrappedElement('vue-module')).classes()).not.toContain('-highlighted');
    expect(wrapper.find(wrappedElement('init-module')).classes()).not.toContain('-highlighted');
    assertHighlightedConnectorsCount(wrapper, 0);
  });
});

const assertHighlightedConnectorsCount = (wrapper: VueWrapper, count: number): void => {
  expect(
    wrapper
      .find(wrappedElement('landscape-connectors'))
      .findAll('line')
      .filter(line => line.classes().includes('-highlighted')).length
  ).toBe(count);
};
