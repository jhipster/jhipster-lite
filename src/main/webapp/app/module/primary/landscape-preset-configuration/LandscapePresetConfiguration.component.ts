import { defineComponent, onMounted, ref } from 'vue';

import { MODULES_REPOSITORY } from '@/module/application/ModuleProvider';
import { Preset } from '@/module/domain/Preset';
import { inject } from '@/injections';

export default defineComponent({
  name: 'LandscapePresetConfigurationVue',
  props: {
    selectedPresetName: {
      type: String,
      default: '',
    },
  },
  emits: ['selected'],
  setup(props, { emit }) {
    const presets = ref<Preset[]>([]);
    const modules = inject(MODULES_REPOSITORY);

    onMounted(() => {
      modules
        .preset()
        .then(response => {
          presets.value = response.presets;
        })
        .catch(error => console.error(error));
    });

    const handlePresetChange = (event: Event) => {
      const selectedValue = (event.target as HTMLSelectElement).value;
      if (selectedValue === '') {
        emit('selected', null);
      } else {
        const selectedPreset = presets.value.find(preset => preset.name === selectedValue);
        emit('selected', selectedPreset);
      }
    };

    return {
      presets,
      handlePresetChange,
    };
  },
});
