import { APPLICATION_LISTENER, CURSOR_UPDATER, inject } from '@/injections';
import {
  LANDSCAPE_SCROLLER,
  MODULES_REPOSITORY,
  MODULE_PARAMETERS_REPOSITORY,
  PROJECT_FOLDERS_REPOSITORY,
} from '@/module/application/ModuleProvider';
import { AnchorPointState } from '@/module/domain/AnchorPointState';
import { ModuleParameter } from '@/module/domain/ModuleParameter';
import { ModulePropertyDefinition } from '@/module/domain/ModulePropertyDefinition';
import { type ModuleRankStatistics, toModuleRankStatistics } from '@/module/domain/ModuleRankStatistics';
import { ModuleSlug } from '@/module/domain/ModuleSlug';
import { Preset } from '@/module/domain/Preset';
import { ProjectHistory } from '@/module/domain/ProjectHistory';
import { Landscape } from '@/module/domain/landscape/Landscape';
import { LandscapeElement } from '@/module/domain/landscape/LandscapeElement';
import { LandscapeElementId } from '@/module/domain/landscape/LandscapeElementId';
import { LandscapeFeature } from '@/module/domain/landscape/LandscapeFeature';
import { LandscapeFeatureSlug } from '@/module/domain/landscape/LandscapeFeatureSlug';
import { LandscapeLevel } from '@/module/domain/landscape/LandscapeLevel';
import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { LandscapeSelectionElement } from '@/module/domain/landscape/LandscapeSelectionElement';
import { ModuleRank, extractRankLetter } from '@/module/domain/landscape/ModuleRank';
import { castValue, empty } from '@/module/primary/PropertyValue';
import { LandscapeLoaderVue } from '@/module/primary/landscape-loader';
import { LandscapeMiniMapVue } from '@/module/primary/landscape-minimap';
import { LandscapeModuleVue } from '@/module/primary/landscape-module';
import { LandscapePresetConfigurationVue } from '@/module/primary/landscape-preset-configuration';
import { LandscapeRankModuleFilterVue } from '@/module/primary/landscape-rank-module-filter';
import { DisplayMode } from '@/module/primary/landscape/DisplayMode';
import { LandscapeConnector, buildConnector } from '@/module/primary/landscape/LandscapeConnector';
import { LandscapeConnectorsSize, emptyLandscapeSize } from '@/module/primary/landscape/LandscapeConnectorsSize';
import { LandscapeNavigation } from '@/module/primary/landscape/LandscapeNavigation';
import { ModulePropertiesFormVue } from '@/module/primary/module-properties-form';
import { ProjectActionsVue } from '@/module/primary/project-actions';
import { ALERT_BUS } from '@/shared/alert/application/AlertProvider';
import { IconVue } from '@/shared/icon/infrastructure/primary';
import { Loader } from '@/shared/loader/infrastructure/primary/Loader';
import { Optional } from '@/shared/optional/domain/Optional';
import { Ref, computed, defineComponent, nextTick, onBeforeUnmount, onMounted, ref } from 'vue';

export default defineComponent({
  name: 'LandscapeVue',
  components: {
    LandscapeModuleVue,
    ModulePropertiesFormVue,
    ProjectActionsVue,
    IconVue,
    LandscapeLoaderVue,
    LandscapeMiniMapVue,
    LandscapePresetConfigurationVue,
    LandscapeRankModuleFilterVue,
  },
  setup() {
    const applicationListener = inject(APPLICATION_LISTENER);
    const alertBus = inject(ALERT_BUS);
    const cursorUpdater = inject(CURSOR_UPDATER);
    const landscapeScroller = inject(LANDSCAPE_SCROLLER);
    const modules = inject(MODULES_REPOSITORY);
    const projectFolders = inject(PROJECT_FOLDERS_REPOSITORY);

    const selectedMode = ref<DisplayMode>('COMPACTED');

    const landscape = ref(Loader.loading<Landscape>());
    const levels = ref(Loader.loading<LandscapeLevel[]>());

    const canLoadMiniMap = ref(false);

    const landscapeContainer = ref<HTMLElement>(document.createElement('div'));
    const landscapeConnectors = ref<LandscapeConnector[]>([]);
    const landscapeSize = ref<LandscapeConnectorsSize>(emptyLandscapeSize());
    const landscapeElements = ref(new Map<string, HTMLElement>());
    const landscapeNavigation = ref(Loader.loading<LandscapeNavigation>());

    const emphasizedModule = ref<ModuleSlug>();

    const moduleParameters = inject(MODULE_PARAMETERS_REPOSITORY);
    const folderPath = ref(moduleParameters.getCurrentFolderPath());
    const moduleParametersValues = ref(moduleParameters.get(folderPath.value));
    const anchorPointModulesMap = ref(new Map<string, AnchorPointState>());

    let commitModule = true;

    const isMoving: Ref<boolean> = ref(false);
    const startX: Ref<number> = ref(0);
    const startY: Ref<number> = ref(0);
    const currentScrollX: Ref<number> = ref(0);
    const currentScrollY: Ref<number> = ref(0);

    const operationInProgress = ref(false);

    const selectedPreset = ref<Preset | null>(null);
    const selectedPresetName = computed(() => selectedPreset.value?.name ?? '');

    const highlightedModule = ref<Optional<ModuleSlug>>(Optional.empty());

    const selectedRank = ref<Optional<ModuleRank>>(Optional.empty());
    const moduleRankStatistics = ref<ModuleRankStatistics>([]);

    onMounted(() => {
      modules
        .landscape()
        .then(response => loadLandscape(response))
        .catch(error => console.error(error));
      loadProjectFolders();
    });

    const loadProjectFolders = (): void => {
      if (folderPath.value.length === 0) {
        projectFolders
          .get()
          .then(projectFolder => {
            folderPath.value = projectFolder;
            moduleParametersValues.value = moduleParameters.get(folderPath.value);
          })
          .catch(error => console.error(error));
      }
    };

    const startGrabbing = (mouseEvent: MouseEvent): void => {
      if (mouseEvent.preventDefault) {
        mouseEvent.preventDefault();
      }
      isMoving.value = true;
      startX.value = mouseEvent.clientX;
      startY.value = mouseEvent.clientY;
      const rect = landscapeContainer.value;
      currentScrollX.value = rect.scrollLeft;
      currentScrollY.value = rect.scrollTop;
      cursorUpdater.set('grabbing');
    };

    const stopGrabbing = (): void => {
      isMoving.value = false;
      cursorUpdater.reset();
    };

    const grabbing = (mouseEvent: MouseEvent): void => {
      if (!isMoving.value) {
        return;
      }
      const scrollX = currentScrollX.value + (startX.value - mouseEvent.clientX);
      const scrollY = currentScrollY.value + (startY.value - mouseEvent.clientY);
      landscapeScroller.scroll(landscapeContainer.value, scrollX, scrollY);
    };

    const landscapeNavigationValue = (): LandscapeNavigation => {
      return landscapeNavigation.value.value();
    };

    const loadLandscape = async (response: Landscape): Promise<void> => {
      landscape.value.loaded(response);
      levels.value.loaded(response.standaloneLevels());

      await nextTick().then(updateConnectors);
      landscapeNavigation.value.loaded(new LandscapeNavigation(landscapeElements.value, levels.value.value()));
      document.addEventListener('keydown', handleKeyboard);
      applicationListener.addEventListener('resize', updateConnectors);

      canLoadMiniMap.value = true;
      loadAnchorPointModulesMap();
      loadLandscapeRankModuleFilterProperty();
    };

    const loadAnchorPointModulesMap = (): void => {
      landscapeConnectors.value.forEach(e => {
        const startingElementSlug = e.startingElement.get();
        const endingElementSlug = e.endingElement.get();

        const startingElementSlugExists = anchorPointModulesMap.value.get(startingElementSlug);
        if (!startingElementSlugExists) {
          anchorPointModulesMap.value.set(startingElementSlug, { atStart: true, atEnd: false });
        } else {
          anchorPointModulesMap.value.set(startingElementSlug, {
            atStart: true,
            atEnd: startingElementSlugExists.atEnd,
          });
        }

        const endingElementSlugExists = anchorPointModulesMap.value.get(endingElementSlug);
        if (!endingElementSlugExists) {
          anchorPointModulesMap.value.set(endingElementSlug, { atStart: false, atEnd: true });
        } else {
          anchorPointModulesMap.value.set(endingElementSlug, { atStart: endingElementSlugExists.atStart, atEnd: true });
        }
      });
    };

    const loadLandscapeRankModuleFilterProperty = (): void => {
      moduleRankStatistics.value = toModuleRankStatistics(landscapeValue());
    };

    type Navigation = 'ArrowLeft' | 'ArrowRight' | 'ArrowUp' | 'ArrowDown' | 'Space';
    type NavigationAction = {
      [key in Navigation]: keyof LandscapeNavigation;
    };

    const isNavigation = (code: string): code is Navigation => {
      return ['ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown', 'Space'].includes(code);
    };

    const prepareNavigationActions = (ctrl: boolean): NavigationAction => {
      return {
        ArrowUp: 'goUp',
        ArrowDown: 'goDown',
        ArrowLeft: ctrl ? 'goToDependency' : 'goLeft',
        ArrowRight: ctrl ? 'goToDependent' : 'goRight',
        Space: 'getSlug',
      };
    };

    const handleNavigation = (code: Navigation, ctrl: boolean): void => {
      const navigationActions = prepareNavigationActions(ctrl);

      const module = landscapeNavigationValue()[navigationActions[code]]();
      if (module) {
        toggleModule(module);
      }
    };

    const handleKeyboard = (event: Event): void => {
      // Disable arrows action in input field
      if (isInputActiveElement()) {
        return;
      }

      const keyboardEvent = event as KeyboardEvent;

      if (isNavigation(keyboardEvent.code)) {
        handleNavigation(keyboardEvent.code, keyboardEvent.ctrlKey);
        emphasizeModule(landscapeNavigationValue().getSlug());
      }
    };

    onBeforeUnmount(() => {
      applicationListener.removeEventListener('resize', updateConnectors);
      document.removeEventListener('keydown', handleKeyboard);
    });

    const updateConnectors = (): void => {
      landscapeConnectors.value = levels.value
        .value()
        .flatMap(level => level.elements)
        .flatMap(element => element.allModules())
        .flatMap(toConnectors);

      landscapeSize.value = buildConnectorsSize();
    };

    const toConnectors = (module: LandscapeModule): LandscapeConnector[] => {
      const dependantElement = landscapeElements.value.get(module.slug().get())!;

      return module.dependencies().flatMap(dependency =>
        buildConnector({
          dependantElement,
          dependantElementSlug: module.slug(),
          dependencyElement: landscapeElements.value.get(dependency.get())!,
          dependencyElementSlug: dependency,
        }),
      );
    };

    const buildConnectorsSize = (): LandscapeConnectorsSize => ({
      width: Math.max.apply(
        null,
        landscapeConnectors.value.flatMap(connector => connector.positions).map(position => position.x),
      ),
      height: Math.max.apply(
        null,
        landscapeConnectors.value.flatMap(connector => connector.positions).map(position => position.y),
      ),
    });

    const isFeature = (element: LandscapeElement): boolean => element instanceof LandscapeFeature;

    const landscapeClass = (): string => {
      const hasEmphasizedModule = emphasizedModule.value !== undefined;
      return `jhipster-landscape-map jhipster-landscape-content--modules ${hasEmphasizedModule ? ' has-emphasized-module' : ''}`;
    };

    const modeSwitchClass = (mode: DisplayMode): string => {
      if (selectedMode.value === mode) {
        return '-selected';
      }

      return '-not-selected';
    };

    const selectMode = async (mode: DisplayMode): Promise<void> => {
      selectedMode.value = mode;

      await nextTick().then(updateConnectors);
    };

    const anchorPointClass = (module: LandscapeElementId): string => {
      if (module instanceof LandscapeFeatureSlug) {
        return '';
      }

      let className = '';

      const anchorPointState = anchorPointModulesMap.value.get(module.get());
      if (anchorPointState) {
        if (anchorPointState.atStart) {
          className += ' -left-anchor-point';
        }

        if (anchorPointState.atEnd) {
          className += ' -right-anchor-point';
        }
      }

      return className;
    };

    const elementFlavor = (module: LandscapeElementId): string => {
      return (
        operationInProgressClass()
        + selectionHighlightClass(module)
        + unselectionHighlightClass(module)
        + selectionClass(module)
        + applicationClass(module)
        + flavorClass()
        + anchorPointClass(module)
        + searchHighlightClass(module)
        + diffRankMinimalEmphasisClass(module)
        + rankHighlightClass(module)
      );
    };

    const operationInProgressClass = (): string => {
      if (operationInProgress.value) {
        return ' -not-selectable';
      }

      return '';
    };

    const toHighlightSelectionClass = (selection: LandscapeSelectionElement): string => {
      if (selection.selectable) {
        return ' -selectable-highlighted';
      }

      return ' -not-selectable-highlighted';
    };

    const selectionHighlightClass = (module: LandscapeElementId): string => {
      if (emphasizedModule.value === undefined) {
        return '';
      }

      return landscapeValue().selectionTreeFor(emphasizedModule.value).find(module).map(toHighlightSelectionClass).orElse('');
    };

    const unselectionHighlightClass = (element: LandscapeElementId): string => {
      if (isUnselectedByEmphasizedModule(element)) {
        return ' -highlighted-unselection';
      }

      return '';
    };

    const isUnselectedByEmphasizedModule = (element: LandscapeElementId): boolean => {
      if (emphasizedModule.value === undefined) {
        return false;
      }

      return landscapeValue().unselectionTreeFor(emphasizedModule.value).includes(element);
    };

    const selectionClass = (element: LandscapeElementId): string => {
      if (element instanceof LandscapeFeatureSlug) {
        return '';
      }

      if (isSelected(element)) {
        return ' -selected';
      }

      if (isSelectable(element)) {
        return ' -selectable';
      }

      return ' -not-selectable';
    };

    const applicationClass = (element: LandscapeElementId): string => {
      if (element instanceof LandscapeFeatureSlug) {
        return '';
      }

      if (landscapeValue().isApplied(element)) {
        return ' -applied';
      }

      return '';
    };

    const flavorClass = (): string => ' ' + modeClass();

    const searchHighlightClass = (module: LandscapeElementId): string =>
      highlightedModule.value
        .filter(highlighted => highlighted.get() === module.get())
        .map(() => ' -search-highlighted')
        .orElse('');

    const diffRankMinimalEmphasisClass = (module: LandscapeElementId): string => {
      if (module instanceof LandscapeFeatureSlug) {
        return '';
      }

      return selectedRank.value
        .map(rank => landscapeValue().hasModuleDifferentRank(module, rank))
        .map(hasDifferentRank => (hasDifferentRank ? ' -diff-rank-minimal-emphasis' : ''))
        .orElse('');
    };

    const rankHighlightClass = (module: LandscapeElementId): string => {
      if (module instanceof LandscapeFeatureSlug) {
        return '';
      }

      if (selectedRank.value.isPresent()) {
        return landscapeValue()
          .getModuleRank(module)
          .map(rank => ` -highlight-rank -${extractRankLetter(rank).toLowerCase()}`)
          .orElse('');
      }

      return '';
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

    const isSelected = (element: LandscapeElementId): boolean => landscapeValue().isSelected(element);

    const selectModulesFromPreset = (preset: Preset): void => {
      selectedPreset.value = preset;
      if (!preset) {
        return;
      }

      landscapeValue()
        .selectedModules()
        .forEach(module => {
          landscape.value.loaded(landscapeValue().toggle(module));
        });

      preset.modules.forEach(module => {
        landscape.value.loaded(landscapeValue().toggle(module));
      });
    };

    const clearPresetSelection = () => (selectedPreset.value = null);

    const toggleModule = (module: ModuleSlug): void => {
      // Remove the focus on input field
      if (isInputActiveElement()) {
        (document?.activeElement as HTMLElement).blur();
      }
      landscape.value.loaded(landscapeValue().toggle(module));

      if (isSelectable(module)) {
        clearPresetSelection();
      }
    };

    const isSelectable = (module: ModuleSlug): boolean => landscapeValue().isSelectable(module);

    const disabledNewApplication = (): boolean => disabledApplication() || landscapeValue().noNotAppliedSelectedModule();

    const disabledAllApplication = (): boolean => disabledApplication();

    const disabledApplication = (): boolean => {
      if (operationInProgress.value) {
        return true;
      }

      if (folderPath.value.length === 0) {
        return true;
      }

      if (landscapeValue().noSelectedModule()) {
        return true;
      }

      return missingMandatoryProperty();
    };

    const selectedNewModulesCount = (): number => landscapeValue().notAppliedSelectedModulesCount();

    const selectedModulesCount = (): number => landscapeValue().selectedModulesCount();

    const missingMandatoryProperty = () =>
      selectedModulesProperties().some(
        property => property.mandatory && empty(moduleParametersValues.value.get(property.key)) && empty(property.defaultValue),
      );

    const selectedModulesProperties = (): ModulePropertyDefinition[] => landscapeValue().selectedModulesProperties();

    const updateModuleCommit = (commit: boolean) => {
      commitModule = commit;
    };

    const updateFolderPath = (path: string): void => {
      folderPath.value = path;
      moduleParametersValues.value = moduleParameters.get(folderPath.value);
    };

    const projectFolderUpdated = (): void => {
      modules
        .history(folderPath.value)
        .then(projectHistory => loadProjectHistory(projectHistory))
        .catch(() => {
          // Nothing to do
        });
    };

    const loadProjectHistory = (projectHistory: ProjectHistory): void => {
      landscape.value.loaded(landscapeValue().resetAppliedModules(projectHistory.modules));

      projectHistory.properties.forEach(property => {
        if (unknownProperty(property.key)) {
          moduleParametersValues.value.set(property.key, property.value);
        }
      });
      moduleParameters.store(folderPath.value, moduleParametersValues.value);
    };

    const unknownProperty = (key: string) => !moduleParametersValues.value.has(key);

    const updateProperty = (property: ModuleParameter): void => {
      moduleParametersValues.value.set(property.key, property.value);
      moduleParameters.store(folderPath.value, moduleParametersValues.value);
    };

    const deleteProperty = (key: string): void => {
      moduleParametersValues.value.delete(key);
      moduleParameters.store(folderPath.value, moduleParametersValues.value);
    };

    const applyNewModules = (): void => {
      applyModules(landscapeValue().notAppliedSelectedModules());
    };

    const applyAllModules = (): void => {
      applyModules(landscapeValue().selectedModules());
    };

    const applyModule = (module: ModuleSlug): void => {
      applyModules([module]);
    };

    const applyModules = (modulesToApply: ModuleSlug[]): void => {
      operationStarted();

      selectedModulesProperties().forEach(property => {
        if (unknownProperty(property.key) && property.defaultValue) {
          updateProperty({ key: property.key, value: castValue(property.type, property.defaultValue) });
        }
      });

      modules
        .applyAll({
          modules: modulesToApply,
          projectFolder: folderPath.value,
          commit: commitModule,
          parameters: moduleParametersValues.value,
        })
        .then(() => {
          operationEnded();
          landscape.value.loaded(landscapeValue().appliedModules(modulesToApply));

          alertBus.success('Modules applied');
        })
        .catch(() => {
          operationEnded();

          alertBus.error('Modules not applied');
        });
    };

    const landscapeValue = (): Landscape => landscape.value.value();

    const operationStarted = (): void => {
      operationInProgress.value = true;
    };

    const operationEnded = (): void => {
      operationInProgress.value = false;
    };

    const isApplied = (moduleId: string): boolean => landscapeValue().isApplied(new ModuleSlug(moduleId));

    const isInputActiveElement = (): boolean => document?.activeElement?.tagName === 'INPUT';

    const performSearch = (query: string) => highlightModule(query);

    const highlightModule = (query: string) => {
      if (!query) {
        highlightedModule.value = Optional.empty();
        nextTick().then(resetLandscapeContainerPosition);
        return;
      }

      highlightedModule.value = findModule(query)
        .map(module => new ModuleSlug(module))
        .map(moduleSlug => {
          scrollToHighlightedModule(landscapeElements.value.get(moduleSlug.get())!);
          return moduleSlug;
        });
    };

    const findModule = (query: string): Optional<string> =>
      Optional.ofNullable([...landscapeElements.value.keys()].find(key => key.toLowerCase().includes(query.toLowerCase())));

    const resetLandscapeContainerPosition = (): void | PromiseLike<void> => landscapeScroller.scrollSmooth(landscapeContainer.value, 0, 0);

    const scrollToHighlightedModule = (moduleElement: HTMLElement): void => {
      const rect = moduleElement.getBoundingClientRect();
      const containerRect = landscapeContainer.value.getBoundingClientRect();

      const verticallyOutOfView = rect.top < containerRect.top || rect.bottom > containerRect.bottom;
      const horizontallyOutOfView = rect.left < containerRect.left || rect.right > containerRect.right;

      if (verticallyOutOfView || horizontallyOutOfView) {
        landscapeScroller.scrollIntoView(moduleElement);
      }
    };

    const handleRankFilter = (rank: Optional<ModuleRank>): void => {
      clearPresetSelection();

      selectedRank.value = rank;
      void reloadLandscape(landscapeValue().filterByRank(selectedRank.value));
    };

    const reloadLandscape = async (response: Landscape): Promise<void> => {
      landscape.value.loaded(response);
      levels.value.loaded(response.standaloneLevels());

      await rebuildLandscapeElements();

      await nextTick().then(updateConnectors);
      landscapeNavigation.value.loaded(new LandscapeNavigation(landscapeElements.value, levels.value.value()));
      loadAnchorPointModulesMap();
    };

    const rebuildLandscapeElements = async (): Promise<void> => {
      // Wait for DOM update before clearing and rebuilding refs
      await nextTick();
      landscapeElements.value = new Map<string, HTMLElement>();
    };

    return {
      levels,
      isFeature,
      landscapeConnectors,
      landscapeSize,
      landscapeElements,
      landscapeContainer,
      landscapeClass,
      modeSwitchClass,
      selectMode,
      modeClass,
      emphasizeModule,
      deEmphasizeModule,
      elementFlavor,
      selectModulesFromPreset,
      toggleModule,
      disabledNewApplication,
      disabledAllApplication,
      selectedNewModulesCount,
      selectedModulesCount,
      folderPath,
      selectedModulesProperties,
      moduleParametersValues,
      updateModuleCommit,
      updateFolderPath,
      projectFolderUpdated,
      updateProperty,
      deleteProperty,
      applyModule,
      applyAllModules,
      applyNewModules,
      operationStarted,
      operationEnded,
      isApplied,
      currentScrollX,
      currentScrollY,
      startGrabbing,
      stopGrabbing,
      grabbing,
      canLoadMiniMap,
      selectedPresetName,
      performSearch,
      handleRankFilter,
      moduleRankStatistics,
    };
  },
});
