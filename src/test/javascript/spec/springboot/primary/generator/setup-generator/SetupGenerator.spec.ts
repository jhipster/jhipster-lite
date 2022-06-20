import { shallowMount, VueWrapper } from '@vue/test-utils';
import { AlertBus } from '@/common/domain/alert/AlertBus';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { stubAlertBus } from '../../../../common/domain/AlertBus.fixture';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubSetupService } from '../../../domain/SetupService.fixture';
import { SetupService } from '@/springboot/domain/SetupService';
import { SetupGeneratorVue } from '@/springboot/primary/generator/setup-generator';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  alertBus: AlertBus;
  setupService: SetupService;
  project: ProjectToUpdate;
}
const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { alertBus, setupService, project }: WrapperOptions = {
    alertBus: stubAlertBus(),
    setupService: stubSetupService(),
    project: createProjectToUpdate(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(SetupGeneratorVue, {
    props: {
      project,
    },
    global: {
      provide: {
        alertBus,
        setupService,
      },
    },
  });
  component = wrapper.vm;
};
describe('SetupGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
