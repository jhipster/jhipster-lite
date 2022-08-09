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

const componentWithLandscape = async (applicationListener: ApplicationListener): Promise<VueWrapper> => {
  const modules = repositoryWithLandscape();

  const wrapper = wrap({ modules, applicationListener });

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
});
