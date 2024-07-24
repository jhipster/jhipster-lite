import { defineComponent, inject, onMounted, ref } from 'vue';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { Preset } from '@/module/domain/Preset';

export default defineComponent({
  name: 'LandscapePresetConfigurationComponent',
  setup() {
    const presets = ref<Preset[]>([]);
    const selectedPreset = ref<Preset | null>(null);
    const modules = inject('modules') as ModulesRepository;

    onMounted(() => {
      modules
        .preset()
        .then(response => {
          presets.value = response.presets;
        })
        .catch(error => console.error(error));
    });

    return {
      presets,
      selectedPreset,
    };
  },
});
