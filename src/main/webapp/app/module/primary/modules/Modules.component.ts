import { ModulePropertiesVue } from '../module-properties';
import { AlertBus } from '@/common/domain/alert/AlertBus';
import { Loader } from '@/loader/primary/Loader';
import { ModuleProperty } from '@/module/domain/ModuleProperty';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { defineComponent, inject, onMounted, reactive, ref } from 'vue';
import { ProjectFoldersRepository } from '@/module/domain/ProjectFoldersRepository';
import { Project } from '@/module/domain/Project';
import { IconVue } from '@/common/primary/icon';
import { TagFilterVue } from '../tag-filter';
import { ProjectHistory } from '@/module/domain/ProjectHistory';
import { ModuleSlug } from '@/module/domain/ModuleSlug';
import { ComponentModules } from './ComponentModules';
import { ComponentModule } from './ComponentModule';

export default defineComponent({
  name: 'ModulesVue',
  components: { ModulePropertiesVue, IconVue, TagFilterVue },
  setup() {
    const globalWindow = inject('globalWindow') as Window & typeof globalThis;
    const alertBus = inject('alertBus') as AlertBus;
    const modules = inject('modules') as ModulesRepository;
    const projectFolders = inject('projectFolders') as ProjectFoldersRepository;

    const applicationModules = reactive({
      all: Loader.loading<ComponentModules>(),
      displayed: Loader.loading<ComponentModules>(),
    });

    const selectedTag = ref(undefined as string | undefined);
    const operationInProgress = ref(false);
    const folderPath = ref('');
    const selectedModule = ref<ComponentModule>();
    const moduleProperties = ref(new Map<string, string | boolean | number>());
    const commitModule = ref(true);
    const appliedModules = ref([] as string[]);
    let searchedText = '';

    onMounted(() => {
      modules.list().then(response => {
        applicationModules.all.loaded(ComponentModules.fromModules(response));
        applicationModules.displayed.loaded(ComponentModules.fromModules(response));
      });
      projectFolders.get().then(projectFolder => (folderPath.value = projectFolder));
    });

    const toggleModule = (slug: string): void => {
      const clickedModule = getModule(slug);

      if (selectedModule.value === clickedModule) {
        selectedModule.value = undefined;
      } else {
        selectedModule.value = clickedModule;
      }
    };

    const moduleClass = (slug: string): string => {
      return selectionClass(slug) + ' ' + applicationClass(slug);
    };

    const selectionClass = (slug: string): string => {
      if (isModuleSelected(slug)) {
        return 'selected';
      }

      return 'not-selected';
    };

    const isModuleSelected = (slug: string): boolean => {
      return slug === selectedModule.value?.slug;
    };

    const applicationClass = (slug: string): string => {
      if (appliedModules.value.includes(slug)) {
        return 'applied';
      }

      return 'not-applied';
    };

    const disabledApplication = (slug: string): boolean => {
      return (
        operationInProgress.value ||
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

    const getModule = (slug: string): ComponentModule => {
      return applicationModules.all
        .value()
        .categories.flatMap(category => category.modules)
        .find(module => module.slug === slug)!;
    };

    const updateSearch = (filter: string): void => {
      searchedText = filter;

      updateFilter();
    };

    const updateFilter = (): void => {
      const search = textToSearch();

      if (search === undefined) {
        applicationModules.displayed.loaded(applicationModules.all.value());
      } else {
        applicationModules.displayed.loaded(applicationModules.all.value().filtered(search));
      }
    };

    const textToSearch = (): string | undefined => {
      if (empty(searchedText) && empty(selectedTag.value)) {
        return undefined;
      }

      if (empty(searchedText)) {
        return selectedTag.value!.toLowerCase();
      }

      if (empty(selectedTag.value)) {
        return searchedText.toLowerCase();
      }

      return selectedTag.value!.toLowerCase() + ' ' + searchedText.toLowerCase();
    };

    const displayedModulesCount = (): number => {
      return applicationModules.displayed.value().modulesCount;
    };

    const totalModulesCount = (): number => {
      return applicationModules.all.value().modulesCount;
    };

    const isTagSelected = (tag: string): boolean => {
      return selectedTag.value === tag;
    };

    const toogleTag = (tag: string): void => {
      if (selectedTag.value === tag) {
        selectedTag.value = undefined;
      } else {
        selectedTag.value = tag;
      }

      updateFilter();
    };

    const applyModule = (module: string): void => {
      operationInProgress.value = true;

      modules
        .apply(new ModuleSlug(module), {
          projectFolder: folderPath.value,
          commit: commitModule.value,
          properties: moduleProperties.value,
        })
        .then(() => {
          operationInProgress.value = false;

          alertBus.success('Module "' + module + '" applied');
          appliedModules.value.push(module);
        })
        .catch(() => {
          operationInProgress.value = false;

          alertBus.error('Module "' + module + '" not applied');
        });
    };

    const projectFolderUpdated = (): void => {
      modules
        .history(folderPath.value)
        .then(projectHistory => loadProjectHistory(projectHistory))
        .catch(() => (appliedModules.value = []));
    };

    const loadProjectHistory = (projectHistory: ProjectHistory): void => {
      appliedModules.value = projectHistory.modules.map(module => module.get());

      projectHistory.properties.forEach(property => {
        if (unknownProperty(property.key)) {
          moduleProperties.value.set(property.key, property.value);
        }
      });
    };

    const unknownProperty = (key: string) => {
      return !moduleProperties.value.has(key);
    };

    const appliedModule = (slug: string): boolean => {
      return appliedModules.value.includes(slug);
    };

    const disabledFormatting = (): boolean => {
      return operationInProgress.value || empty(folderPath.value);
    };

    const disabledDownload = (): boolean => {
      return operationInProgress.value || empty(folderPath.value);
    };

    const formatProject = (): void => {
      operationInProgress.value = true;

      modules
        .format(folderPath.value)
        .then(() => {
          operationInProgress.value = false;

          alertBus.success('Project formatted');
        })
        .catch(() => {
          operationInProgress.value = false;

          alertBus.error("Project can't be formatted");
        });
    };

    const downloadProject = (): void => {
      operationInProgress.value = true;

      modules
        .download(folderPath.value)
        .then(project => {
          download(project);

          operationInProgress.value = false;
        })
        .catch(() => {
          alertBus.error("Project can't be downloaded");

          operationInProgress.value = false;
        });
    };

    const download = (project: Project): void => {
      const url = globalWindow.URL.createObjectURL(new Blob([project.content]));
      const link = globalWindow.document.createElement('a');
      link.href = url;
      link.download = project.filename;
      globalWindow.document.body.appendChild(link);
      link.click();

      globalWindow.URL.revokeObjectURL(link.href);
      globalWindow.document.body.removeChild(link);
    };

    return {
      content: applicationModules.displayed,
      folderPath,
      toggleModule,
      isModuleSelected,
      moduleClass,
      selectedModule,
      setStringProperty,
      setNumberProperty,
      setBooleanProperty,
      disabledApplication,
      mandatoryProperties,
      optionalProperties,
      moduleProperties,
      commitModule,
      displayedModulesCount,
      totalModulesCount,
      isTagSelected,
      toogleTag,
      updateSearch,
      applyModule,
      projectFolderUpdated,
      appliedModule,
      disabledFormatting,
      disabledDownload,
      formatProject,
      downloadProject,
    };
  },
});

const empty = (value: string | undefined): boolean => {
  if (value === undefined) {
    return true;
  }

  return value.trim().length === 0;
};
