import { ApplicationListener } from '@/common/primary/applicationlistener/ApplicationListener';
import { Loader } from '@/loader/primary/Loader';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import { defineComponent, inject, nextTick, onBeforeUnmount, onMounted, ref } from 'vue';
import { LandscapeModuleVue } from '../landscape-module';
import { buildConnector, LandscapeConnector } from './LandscapeConnector';
import { DisplayMode } from './DisplayMode';
import { ComponentLandscape } from './ComponentLandscape';
import { ComponentLandscapeModule } from './ComponentLandscapeModule';
import { ComponentLandscapeElement } from './ComponentLandscapeElement';
import { Optional } from '@/common/domain/Optional';
import { emptyLandscapeSize, LandscapeConnectorsSize } from './LandscapeConnectorsSize';

export default defineComponent({
  name: 'LandscapeVue',
  components: { LandscapeModuleVue },
  setup() {
    const modules = inject('modules') as ModulesRepository;
    const applicationListener = inject('applicationListener') as ApplicationListener;

    const selectedMode = ref<DisplayMode>('COMPACTED');

    const landscape = ref(Loader.loading<ComponentLandscape>());

    const landscapeContainer = ref<HTMLElement>();
    const landscapeConnectors = ref<LandscapeConnector[]>([]);
    const landscapeSize = ref<LandscapeConnectorsSize>(emptyLandscapeSize());
    const landscapeElements = ref(new Map<string, HTMLElement>());

    const emphasizedModule = ref<string>();

    onMounted(() => {
      modules.landscape().then(response => {
        landscape.value.loaded(ComponentLandscape.from(response));

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
      landscapeConnectors.value = landscape.value
        .value()
        .levels.flatMap(level => level.elements)
        .flatMap(element => element.allModules())
        .flatMap(toConnectors);

      landscapeSize.value = buildConnectorsSize();
    };

    const toConnectors = (module: ComponentLandscapeModule): LandscapeConnector[] => {
      const dependantElement = landscapeElements.value.get(module.slug)!.getBoundingClientRect();

      return module.dependencies.flatMap(dependency =>
        buildConnector({
          dependantElement: dependantElement,
          dependantElementSlug: module.slug,
          dependencyElement: landscapeElements.value.get(dependency)!,
          dependencyElementSlug: dependency,
          container: landscapeContainer.value!,
        })
      );
    };

    const buildConnectorsSize = (): LandscapeConnectorsSize => ({
      width: Math.max.apply(
        null,
        landscapeConnectors.value.flatMap(connector => connector.positions).map(position => position.x)
      ),
      height: Math.max.apply(
        null,
        landscapeConnectors.value.flatMap(connector => connector.positions).map(position => position.y)
      ),
    });

    const isFeature = (element: ComponentLandscapeElement): boolean => {
      return element.isFeature();
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

    const elementFlavor = (module: string): string => {
      return highlightClass(module) + unselectionHighlightClass(module) + selectionClass(module) + flavorClass();
    };

    const highlightClass = (module: string): string => {
      if (isHighlighted(module)) {
        if (isSelectable(module)) {
          return ' -selectable-highlighted';
        }

        return ' -not-selectable-highlighted';
      }

      return '';
    };

    const isHighlighted = (module: string): boolean => {
      if (emphasizedModule.value === undefined) {
        return false;
      }

      return emphasizedModule.value === module || landscapeValue().moduleSelectionTree(emphasizedModule.value).includes(module);
    };

    const unselectionHighlightClass = (module: string): string => {
      if (isUnselectedByEmphasizedModule(module)) {
        return ' -highlighted-unselection';
      }

      return '';
    };

    const selectionClass = (module: string): string => {
      if (isSelected(module)) {
        return ' -selected';
      }

      if (isSelectable(module)) {
        return ' -selectable';
      }

      return ' -not-selectable';
    };

    const flavorClass = (): string => {
      return ' ' + modeClass();
    };

    const modeClass = (): string => {
      switch (selectedMode.value) {
        case 'COMPACTED':
          return '-compacted';
        case 'EXTENDED':
          return '-extended';
      }
    };

    const emphasizeModule = (module: string): void => {
      emphasizedModule.value = module;
    };

    const deEmphasizeModule = (): void => {
      emphasizedModule.value = undefined;
    };

    const connectorClass = (startingElement: string): string => {
      if (emphasizedDependency(startingElement)) {
        if (isSelectable(startingElement)) {
          return '-selectable-highlighted';
        }

        return '-not-selectable-highlighted';
      }

      if (isUnselectedByEmphasizedModule(startingElement)) {
        return '-highlighted-unselection';
      }

      if (isSelected(startingElement)) {
        return '-selected';
      }

      return '';
    };

    const emphasizedDependency = (startingElement: string): boolean => {
      if (emphasizedModule.value === undefined) {
        return false;
      }

      const currentElementEmphasized = emphasizedModule.value === startingElement;
      const dependencyEmphasized = landscapeValue().moduleSelectionTree(emphasizedModule.value).includes(startingElement);

      return currentElementEmphasized || dependencyEmphasized;
    };

    const isUnselectedByEmphasizedModule = (module: string): boolean => {
      if (emphasizedModule.value === undefined) {
        return false;
      }

      if (!isSelected(module)) {
        return false;
      }

      return isDependantOfEmphasizedModule(module) || isDependantOfAFeatureSelectedModule(module);
    };

    const isDependantOfEmphasizedModule = (module: string): boolean => {
      return landscapeValue().dependantSelectedModules(emphasizedModule.value!).includes(module);
    };

    const isDependantOfAFeatureSelectedModule = (module: string): boolean => {
      return landscapeValue()
        .moduleFeature(emphasizedModule.value!)
        .flatMap(feature => Optional.ofUndefinable(feature.moduleSlugs.find(featureModule => landscapeValue().isSelected(featureModule))))
        .map(selectedModule => {
          const moduleDependsOnSelectedModule = landscapeValue().dependantSelectedModules(selectedModule).includes(module);
          const moduleDontDependOnEmphasizedModule = !landscapeValue().modulesToUnselect(emphasizedModule.value!).includes(module);

          return moduleDependsOnSelectedModule && moduleDontDependOnEmphasizedModule;
        })
        .orElse(false);
    };

    const isSelected = (module: string): boolean => {
      return landscapeValue().isSelected(module);
    };

    const toggleModule = (module: string): void => {
      landscapeValue().toggleModule(module);
    };

    const isSelectable = (element: string): boolean => {
      return landscapeValue().isSelectable(element);
    };

    const landscapeValue = (): ComponentLandscape => {
      return landscape.value.value();
    };

    return {
      landscape,
      isFeature,
      landscapeConnectors,
      landscapeSize,
      landscapeElements,
      landscapeContainer,
      modeSwitchClass,
      selectMode,
      modeClass,
      emphasizeModule,
      deEmphasizeModule,
      connectorClass,
      elementFlavor,
      toggleModule,
    };
  },
});
