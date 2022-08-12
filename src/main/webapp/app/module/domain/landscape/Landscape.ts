import { ModuleSlug } from '../ModuleSlug';
import { LandscapeLevel } from './LandscapeLevel';
import { LandscapeModule } from './LandscapeModule';

export class Landscape {
  constructor(public readonly levels: LandscapeLevel[]) {}

  public getModule(module: ModuleSlug): LandscapeModule {
    const foundModule = this.levels
      .flatMap(level => level.elements)
      .flatMap(element => element.allModules())
      .find(currentModule => currentModule.slug === module);

    if (foundModule === undefined) {
      throw new Error(`Can't find module ${module} in landscape`);
    }

    return foundModule;
  }
}
