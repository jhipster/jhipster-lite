import { ApplicationListener } from '@/common/primary/applicationlistener/ApplicationListener';
import { Loader } from '@/loader/primary/Loader';
import { Landscape } from '@/module/domain/landscape/Landscape';
import { LandscapeElement } from '@/module/domain/landscape/LandscapeElement';
import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { LandscapeFeature } from '@/module/domain/landscape/LandscapeFeature';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { defineComponent, inject, nextTick, onBeforeUnmount, onMounted, ref } from 'vue';
import { LandscapeModuleVue } from '../landscape-module';
import { buildConnectors, LandscapeConnectorLine } from './LandscapeConnector';
import { emptyLandscapeSize, LandscapeConnectorsSize } from './LandscapeConnectorsSize';
import { DisplayMode } from './DisplayMode';
import { ModuleSlug } from '@/module/domain/ModuleSlug';

export default defineComponent({
  name: 'LandscapeVue',
  components: { LandscapeModuleVue },
  setup() {
    const modules = inject('modules') as ModulesRepository;
    const applicationListener = inject('applicationListener') as ApplicationListener;

    const selectedMode = ref<DisplayMode>('COMPACTED');

    const landscapeContainer = ref<HTMLElement>();
    const landscape = ref(Loader.loading<Landscape>());
    const landscapeConnectorsLines = ref<LandscapeConnectorLine[]>([]);
    const landscapeElements = ref(new Map<string, HTMLElement>());
    const landscapeSize = ref<LandscapeConnectorsSize>(emptyLandscapeSize());

    const emphasizedModule = ref<ModuleSlug>();

    onMounted(() => {
      modules.landscape().then(response => {
        landscape.value.loaded(response);

        nextTick(() => {
          updateConnectors();
        });

        applicationListener.addEventListener('resize', updateConnectors);
      });
    });

    onBeforeUnmount(() => {
      applicationListener.removeEventListener('resize', updateConnectors);
    });

    const updateConnectors = (): void => {
      landscapeConnectorsLines.value = landscape.value
        .value()
        .levels.flatMap(level => level.elements)
        .flatMap(toModules)
        .flatMap(toLines);

      landscapeSize.value = maxLandscape();
    };

    const toModules = (element: LandscapeElement): LandscapeModule[] => {
      if (element instanceof LandscapeFeature) {
        return element.modules;
      }

      return [element];
    };

    const toLines = (module: LandscapeModule): LandscapeConnectorLine[] => {
      const dependentElement = landscapeElements.value.get(module.slug)!.getBoundingClientRect();

      return module.dependencies
        .flatMap(dependency =>
          buildConnectors({
            dependentElement,
            dependentElementSlug: module.slug,
            dependencyElement: landscapeElements.value.get(dependency)!,
            dependencyElementSlug: dependency,
            container: landscapeContainer.value!,
          })
        )
        .flatMap(connector => connector.lines);
    };

    const maxLandscape = (): LandscapeConnectorsSize => {
      return {
        width: Math.max.apply(
          null,
          landscapeConnectorsLines.value.map(line => line.end.x)
        ),
        height: Math.max.apply(
          null,
          landscapeConnectorsLines.value.map(line => line.end.y)
        ),
      };
    };

    const isFeature = (element: LandscapeElement): boolean => {
      return element instanceof LandscapeFeature;
    };

    const modeSwitchClass = (mode: DisplayMode): string => {
      if (selectedMode.value === mode) {
        return '-selected';
      }

      return '-not-selected';
    };

    const selectMode = (mode: DisplayMode): void => {
      selectedMode.value = mode;

      nextTick(() => {
        updateConnectors();
      });
    };

    const elementFlavor = (module: ModuleSlug): string => {
      if (emphasizedModule.value === undefined) {
        return flavoredClass();
      }

      const isDependentModule = landscape.value.value().getModule(emphasizedModule.value).hasDependency(module);
      if (isDependentModule) {
        return flavoredClass('-highlighted');
      }

      return flavoredClass();
    };

    const flavoredClass = (alternative?: string): string => {
      if (alternative === undefined) {
        return modeClass();
      }

      return alternative + ' ' + modeClass();
    };

    const modeClass = (): string => {
      switch (selectedMode.value) {
        case 'COMPACTED':
          return '-compacted';
        case 'EXTENDED':
          return '-extended';
      }
    };
    const emphasizeModule = (module: ModuleSlug): void => {
      emphasizedModule.value = module;
    };

    const deEmphasizeModule = (): void => {
      emphasizedModule.value = undefined;
    };

    const connectorClass = (startingElement: string): string => {
      if (startingElement === emphasizedModule.value) {
        return '-highlighted';
      }

      return '';
    };

    return {
      landscape,
      isFeature,
      landscapeConnectorsLines,
      landscapeElements,
      landscapeSize,
      landscapeContainer,
      modeSwitchClass,
      selectMode,
      modeClass,
      emphasizeModule,
      deEmphasizeModule,
      connectorClass,
      elementFlavor,
    };
  },
});
