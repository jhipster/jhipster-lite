import { ApplicationListener } from '@/common/primary/applicationlistener/ApplicationListener';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { LandscapeVue } from '@/module/primary/landscape';
import { flushPromises, mount, VueWrapper } from '@vue/test-utils';
import sinon, { SinonStub } from 'sinon';
import { wrappedElement } from '../../../WrappedElement';
import { defaultLandscape, ModulesRepositoryStub, stubModulesRepository } from '../../domain/Modules.fixture';

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
      expect(wrapper.find(wrappedElement('landscape-connectors')).findAll('polyline').length).toBe(17);
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
    it('Should highlight compacted selectable module and dependencies', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      const vueClasses = wrapper.find(wrappedElement('vue-module')).classes();
      expect(vueClasses).toContain('-selectable-highlighted');
      expect(vueClasses).toContain('-compacted');

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-selectable-highlighted');
      expect(initClasses).toContain('-compacted');

      assertSelectableHighlightedConnectorsCount(wrapper, 1);
    });

    it('Should highlight extended selectable module and dependencies', async () => {
      const wrapper = await componentWithLandscape();
      wrapper.find(wrappedElement('extended-mode-button')).trigger('click');
      await wrapper.vm.$nextTick();

      wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      const vueClasses = wrapper.find(wrappedElement('vue-module')).classes();
      expect(vueClasses).toContain('-selectable-highlighted');
      expect(vueClasses).toContain('-extended');

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-selectable-highlighted');
      expect(initClasses).toContain('-extended');

      assertSelectableHighlightedConnectorsCount(wrapper, 1);
    });

    it('Should highlight dependant feature', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('dummy-feature-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      const dummyFeatureClasses = wrapper.find(wrappedElement('dummy-feature-module')).classes();
      expect(dummyFeatureClasses).toContain('-not-selectable-highlighted');
      expect(dummyFeatureClasses).toContain('-compacted');

      const springMvcClasses = wrapper.find(wrappedElement('spring-mvc-feature')).classes();
      expect(springMvcClasses).toContain('-not-selectable-highlighted');
      expect(springMvcClasses).toContain('-compacted');
      assertNotSelectableHighlightedConnectorsCount(wrapper, 4);
    });

    it('Should highlight not selectable one element feature as not selectable', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('liquibase-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('postgresql-module')).classes()).toContain('-not-selectable-highlighted');
    });

    it('Should highlight selection tree', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);

      wrapper.find(wrappedElement('liquibase-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('java-build-tools-feature')).classes()).toContain('-selectable-highlighted');
      expect(wrapper.find(wrappedElement('spring-boot-module')).classes()).toContain('-selectable-highlighted');
      expect(wrapper.find(wrappedElement('postgresql-module')).classes()).toContain('-selectable-highlighted');
      expect(wrapper.find(wrappedElement('liquibase-module')).classes()).toContain('-selectable-highlighted');
      assertSelectableHighlightedConnectorsCount(wrapper, 3);
    });

    it('Should un-highlight module and dependencies', async () => {
      const wrapper = await componentWithLandscape();

      wrapper.find(wrappedElement('vue-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();
      wrapper.find(wrappedElement('vue-module')).trigger('mouseleave');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('vue-module')).classes()).not.toContain('-selectable-highlighted');
      expect(wrapper.find(wrappedElement('init-module')).classes()).not.toContain('-selectable-highlighted');
      assertSelectableHighlightedConnectorsCount(wrapper, 0);
    });

    it('Should highlight unselection tree', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('spring-boot', wrapper);

      wrapper.find(wrappedElement('init-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('maven-module')).classes()).toContain('-highlighted-unselection');
      expect(wrapper.find(wrappedElement('spring-boot-module')).classes()).toContain('-highlighted-unselection');
      assertUnSelectionHighlightedConnectorsCount(wrapper, 2);
    });

    it('Should not highlight multiple modules in feature', async () => {
      const wrapper = await componentWithLandscape();
      await clickModule('maven', wrapper);

      wrapper.find(wrappedElement('build-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('maven-module')).classes()).toContain('-selectable-highlighted');
      expect(wrapper.find(wrappedElement('gradle-module')).classes()).not.toContain('-selectable-highlighted');
    });

    it('Should highlight module with direct dependency to module in feature for unselection', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('gitlab-maven', wrapper);
      wrapper.find(wrappedElement('gradle-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('gitlab-maven-module')).classes()).toContain('-highlighted-unselection');
    });

    it('Should not highlight feature depending module for unselection when switching module', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('java-base', wrapper);
      wrapper.find(wrappedElement('gradle-module')).trigger('mouseover');
      await wrapper.vm.$nextTick();

      expect(wrapper.find(wrappedElement('java-base-module')).classes()).not.toContain('-highlighted-unselection');
    });
  });

  describe('Selectable module', () => {
    it('Should mark compacted root modules as selectable', async () => {
      const wrapper = await componentWithLandscape();

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-selectable');
      expect(initClasses).toContain('-compacted');

      const infinitestClasses = wrapper.find(wrappedElement('infinitest-module')).classes();
      expect(infinitestClasses).toContain('-selectable');
      expect(infinitestClasses).toContain('-compacted');
    });

    it('Should mark extended root modules as selectable', async () => {
      const wrapper = await componentWithLandscape();
      wrapper.find(wrappedElement('extended-mode-button')).trigger('click');
      await wrapper.vm.$nextTick();

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-selectable');
      expect(initClasses).toContain('-extended');

      const infinitestClasses = wrapper.find(wrappedElement('infinitest-module')).classes();
      expect(infinitestClasses).toContain('-selectable');
      expect(infinitestClasses).toContain('-extended');
    });

    it('Should mark feature depending modules as not selectable', async () => {
      const wrapper = await componentWithLandscape();

      expect(wrapper.find(wrappedElement('java-base-module')).classes()).toContain('-not-selectable');
    });

    it('Should mark feature depending modules as selectable with one selection in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);

      expect(wrapper.find(wrappedElement('java-base-module')).classes()).toContain('-selectable');
    });

    it('Should mark feature depending modules as selectable with one module in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);
      await clickModule('maven', wrapper);
      await clickModule('spring-boot', wrapper);

      expect(wrapper.find(wrappedElement('liquibase-module')).classes()).toContain('-selectable');
    });

    it('Should mark feature depending modules as not selectable with one not selectable module in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);

      expect(wrapper.find(wrappedElement('liquibase-module')).classes()).toContain('-not-selectable');
    });

    it('Should not allow selection creating a bad switch in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('gitlab-maven', wrapper);

      expect(wrapper.find(wrappedElement('gitlab-gradle-module')).classes()).toContain('-not-selectable');
    });
  });
  //github.com/jhipster/jhipster-lite/issues/3129

  describe('Module selection', () => {
    it('Should select selectable module', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);

      const initClasses = wrapper.find(wrappedElement('init-module')).classes();
      expect(initClasses).toContain('-selected');
      expect(initClasses).not.toContain('-selectable');
      expect(initClasses).not.toContain('-not-selectable');

      assertSelectedConnectorsCount(wrapper, 0);
    });

    it('Should select module in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);
      await clickModule('maven', wrapper);

      assertSelectedConnectorsCount(wrapper, 1);
    });

    it('Should not select not selectable module', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('java-base', wrapper);

      expect(wrapper.find(wrappedElement('java-base-module')).classes()).not.toContain('-selected');
    });

    it('Should switch selected module in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('vue', wrapper);
      await clickModule('react', wrapper);

      expect(wrapper.find(wrappedElement('react-module')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('vue-module')).classes()).not.toContain('-selected');
    });

    it('Should select module and dependencies', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('liquibase', wrapper);

      expect(wrapper.find(wrappedElement('init-module')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('maven-module')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('spring-boot-module')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('postgresql-module')).classes()).toContain('-selected');
      expect(wrapper.find(wrappedElement('liquibase-module')).classes()).toContain('-selected');
    });
  });

  describe('Module unselection', () => {
    it('Should unselect selected module', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);
      await clickModule('init', wrapper);

      expect(wrapper.find(wrappedElement('init-module')).classes()).not.toContain('-selected');
    });

    it('Should unselect dependant modules', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('init', wrapper);
      await clickModule('maven', wrapper);
      await clickModule('java-base', wrapper);
      await clickModule('init', wrapper);

      expect(wrapper.find(wrappedElement('init-module')).classes()).not.toContain('-selected');
      expect(wrapper.find(wrappedElement('maven-module')).classes()).not.toContain('-selected');
      expect(wrapper.find(wrappedElement('java-base-module')).classes()).not.toContain('-selected');
    });

    it('Should not unselect feature depending module when switching module feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('java-base', wrapper);
      await clickModule('gradle', wrapper);

      expect(wrapper.find(wrappedElement('java-base-module')).classes()).toContain('-selected');
    });

    it('Should unselect module with direct dependency to module in feature', async () => {
      const wrapper = await componentWithLandscape();

      await clickModule('maven', wrapper);
      await clickModule('gitlab-maven', wrapper);
      await clickModule('gradle', wrapper);

      expect(wrapper.find(wrappedElement('gitlab-maven-module')).classes()).not.toContain('-selected');
    });
  });
});

const assertSelectableHighlightedConnectorsCount = (wrapper: VueWrapper, count: number): void => {
  assertConnectorsCount(wrapper, '-selectable-highlighted', count);
};

const assertNotSelectableHighlightedConnectorsCount = (wrapper: VueWrapper, count: number): void => {
  assertConnectorsCount(wrapper, '-not-selectable-highlighted', count);
};

const assertUnSelectionHighlightedConnectorsCount = (wrapper: VueWrapper, count: number): void => {
  assertConnectorsCount(wrapper, '-highlighted-unselection', count);
};

const assertSelectedConnectorsCount = (wrapper: VueWrapper, count: number): void => {
  assertConnectorsCount(wrapper, '-selected', count);
};

const assertConnectorsCount = (wrapper: VueWrapper, cssClass: string, count: number): void => {
  expect(
    wrapper
      .find(wrappedElement('landscape-connectors'))
      .findAll('polyline')
      .filter(line => line.classes().includes(cssClass)).length
  ).toBe(count);
};

const clickModule = async (slug: string, wrapper: VueWrapper): Promise<void> => {
  wrapper.find(wrappedElement(`${slug}-module`)).trigger('click');
  await wrapper.vm.$nextTick();
};
