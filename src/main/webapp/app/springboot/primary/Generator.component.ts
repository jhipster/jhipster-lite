import { defineComponent, ref } from 'vue';
import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';
import { AngularGeneratorVue } from '@/springboot/primary/generator/angular-generator';
import { ReactGeneratorVue } from '@/springboot/primary/generator/react-generator';
import { VueGeneratorVue } from '@/springboot/primary/generator/vue-generator';
import { SvelteGeneratorVue } from '@/springboot/primary/generator/svelte-generator';
import { SpringBootGeneratorVue } from '@/springboot/primary/generator/spring-boot-generator';
import { DefaultButtonVue } from '@/common/primary/default-button';
import { HeaderVue } from '@/springboot/primary/header';
import { IconVue } from '@/common/primary/icon';
import { ProjectGeneratorVue } from '@/springboot/primary/generator/project-generator';

export default defineComponent({
  name: 'GeneratorComponent',
  components: {
    HeaderVue,
    IconVue,
    DefaultButtonVue,
    ProjectGeneratorVue,
    SpringBootGeneratorVue,
    AngularGeneratorVue,
    ReactGeneratorVue,
    SvelteGeneratorVue,
    VueGeneratorVue,
  },
  setup() {
    const selectorPrefix = 'generator';

    const project = ref<ProjectToUpdate>({
      folder: '',
    });
    const language = ref<string>();
    const buildTool = ref<string>('maven');
    const server = ref<string>();
    const client = ref<string>();

    return {
      project,
      language,
      buildTool,
      server,
      client,
      selectorPrefix,
    };
  },
});
