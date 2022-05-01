import { defineComponent, inject, ref } from 'vue';
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
import { ProjectHistoryService } from '@/common/domain/ProjectHistoryService';
import { History } from '@/common/domain/History';
import { ToastVue } from '@/common/primary/toast';
import ToastService from '@/common/secondary/ToastService';

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
    ToastVue,
  },
  setup() {
    const projectHistoryService = inject('projectHistoryService') as ProjectHistoryService;
    const globalWindow = inject('globalWindow') as Window;

    const selectorPrefix = 'generator';

    const project = ref<ProjectToUpdate>({
      folder: '',
    });
    const language = ref<string>();
    const buildTool = ref<string>('maven');
    const server = ref<string>();
    const client = ref<string>();

    let timeoutId: number | undefined = undefined;
    const getCurrentProjectHistory = (): Promise<History> => projectHistoryService.get(project.value.folder);
    const debounceGetProjectHistory = (): void => {
      if (timeoutId) globalWindow.clearTimeout(timeoutId);
      timeoutId = globalWindow.setTimeout(() => getCurrentProjectHistory(), 400);
    };

    return {
      project,
      language,
      buildTool,
      server,
      client,
      selectorPrefix,
      getCurrentProjectHistory,
      debounceGetProjectHistory,
    };
  },
  mounted() {
    const toastService = inject('toastService') as ToastService;
    toastService.register(this.$refs.toast);
  },
});
