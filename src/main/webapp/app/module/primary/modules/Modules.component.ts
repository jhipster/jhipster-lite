import { AlertBus } from '@/common/domain/alert/AlertBus';
import { Loader } from '@/loader/primary/Loader';
import { Module } from '@/module/domain/Module';
import { ModuleProperty } from '@/module/domain/ModuleProperty';
import { Modules } from '@/module/domain/Modules';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { defineComponent, inject, onMounted, reactive, ref } from 'vue';

export default defineComponent({
  name: 'ModulesVue',
  setup() {
    const alertBus = inject('alertBus') as AlertBus;
    const modules = inject('modules') as ModulesRepository;

    const modulesContent = reactive({
      content: Loader.loading<Modules>(),
    });

    const applicationInProgress = ref(false);
    const folderPath = ref('');
    const selectedModule = ref();
    const moduleProperties = ref(new Map<string, string | number | boolean>());

    onMounted(() => {
      modules.list().then(response => modulesContent.content.loaded(response));
    });

    const selectModule = (slug: string): void => {
      selectedModule.value = getModule(slug);
    };

    const disabledApplication = (slug: string): boolean => {
      return (
        applicationInProgress.value ||
        empty(folderPath.value) ||
        getModule(slug).properties.some(property => property.mandatory && isNotSet(property.key))
      );
    };

    const isNotSet = (propertyKey: string): boolean => {
      const value = moduleProperties.value.get(propertyKey);

      if (typeof value === 'string') {
        return empty(value);
      }

      return value === undefined;
    };

    const setStringProperty = (property: string, value: string): void => {
      moduleProperties.value.set(property, value);
    };

    const setNumberProperty = (property: string, value: string): void => {
      if (empty(value)) {
        moduleProperties.value.delete(property);
      } else {
        moduleProperties.value.set(property, +value);
      }
    };

    const setBooleanProperty = (property: string, value: string): void => {
      if (value === 'false') {
        moduleProperties.value.set(property, false);
      } else if (value === 'true') {
        moduleProperties.value.set(property, true);
      } else {
        moduleProperties.value.delete(property);
      }
    };

    const mandatoryProperties = (module: string): ModuleProperty[] => {
      return getModule(module).properties.filter(property => property.mandatory);
    };

    const optionalProperties = (module: string): ModuleProperty[] => {
      return getModule(module).properties.filter(property => !property.mandatory);
    };

    const getModule = (slug: string): Module => {
      return modulesContent.content
        .value()
        .categories.flatMap(category => category.modules)
        .find(module => module.slug === slug)!;
    };

    const applyModule = (module: string): void => {
      applicationInProgress.value = true;

      modules
        .apply(module, {
          projectFolder: folderPath.value,
          properties: moduleProperties.value,
        })
        .then(() => {
          applicationInProgress.value = false;

          alertBus.success('Module "' + module + '" applied');
        })
        .catch(() => {
          applicationInProgress.value = false;

          alertBus.error('Module "' + module + '" not applied');
        });
    };

    return {
      content: modulesContent.content,
      folderPath,
      selectModule,
      selectedModule,
      setStringProperty,
      setNumberProperty,
      setBooleanProperty,
      disabledApplication,
      mandatoryProperties,
      optionalProperties,
      moduleProperties,
      applyModule,
      applicationInProgress,
    };
  },
});

const empty = (value: string): boolean => {
  return value.trim().length === 0;
};
