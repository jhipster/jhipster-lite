import { shallowMount, VueWrapper } from '@vue/test-utils';
import { AlertBus } from '../../../../../../../main/webapp/app/common/domain/alert/AlertBus';
import { ProjectToUpdate } from '../../../../../../../main/webapp/app/springboot/primary/ProjectToUpdate';
import { stubAlertBus } from '../../../../common/domain/AlertBus.fixture';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { stubSetupService } from '../../../domain/SetupService.fixture';
import { SetupService } from '../../../../../../../main/webapp/app/springboot/domain/SetupService';
import { SetupGeneratorVue } from '../../../../../../../main/webapp/app/springboot/primary/generator/setup-generator';

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
