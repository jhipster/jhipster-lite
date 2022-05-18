import { GeneratorVue } from '@/springboot/primary';
import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectHistoryService } from '@/common/domain/ProjectHistoryService';
import { stubProjectHistoryService } from '../../common/domain/ProjectHistoryService.fixture';
import sinon, { SinonStub } from 'sinon';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  projectHistoryService: ProjectHistoryService;
  globalWindow: WindowStub;
}

interface WindowStub {
  clearTimeout: SinonStub;
  setTimeout: SinonStub;
}

const stubGlobalWindow = (): WindowStub =>
  ({
    clearTimeout: sinon.stub(),
    setTimeout: sinon.stub(),
  } as WindowStub);

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { projectHistoryService, globalWindow }: WrapperOptions = {
    projectHistoryService: stubProjectHistoryService(),
    globalWindow: stubGlobalWindow(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(GeneratorVue, {
    global: {
      provide: {
        projectHistoryService,
        globalWindow,
      },
    },
  });
  component = wrapper.vm;
};

describe('Generator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should get project history with current project folder', async () => {
    const projectHistoryService = stubProjectHistoryService();
    wrap({ projectHistoryService });

    const projectPathInput = wrapper.find('#path');
    await projectPathInput.setValue('path');
    component.getCurrentProjectHistory();

    const path = projectHistoryService.get.getCall(0).args[0];
    expect(path).toEqual('path');
  });

  it('should delay get project history', () => {
    const projectHistoryService = stubProjectHistoryService();
    const globalWindow = stubGlobalWindow();
    wrap({ projectHistoryService, globalWindow });

    component.debounceGetProjectHistory();

    const [delayedMethod, timeout] = globalWindow.setTimeout.getCall(0).args;
    expect(delayedMethod()).toEqual(component.getCurrentProjectHistory());
    expect(timeout).toBe(400);
    expect(globalWindow.clearTimeout.called).toBe(false);
  });

  it('should clear timeout', () => {
    const TIMEOUT_ID = 1;
    const projectHistoryService = stubProjectHistoryService();
    const globalWindow = stubGlobalWindow();
    globalWindow.setTimeout.returns(TIMEOUT_ID);
    wrap({ projectHistoryService, globalWindow });

    component.debounceGetProjectHistory();
    component.debounceGetProjectHistory();

    const [timeoutId] = globalWindow.clearTimeout.getCall(0).args;
    expect(timeoutId).toBe(TIMEOUT_ID);
  });
});
