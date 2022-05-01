import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { createProjectToUpdate } from '../../ProjectToUpdate.fixture';
import { SvelteGeneratorVue } from '@/springboot/primary/generator/svelte-generator';

let wrapper: VueWrapper;

interface WrapperOptions {
  project: ProjectToUpdate;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { project }: WrapperOptions = {
    project: createProjectToUpdate(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(SvelteGeneratorVue, {
    props: {
      project,
    },
  });
};

describe('SvelteGenerator', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });
});
