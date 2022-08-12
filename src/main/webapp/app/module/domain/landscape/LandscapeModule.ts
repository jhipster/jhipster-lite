import { ModuleSlug } from '../ModuleSlug';
import { LandscapeElementId } from './LandscapeElementId';
import { ModuleOperation } from './ModuleOperation';

export class LandscapeModule {
  constructor(
    public readonly slug: ModuleSlug,
    public readonly operation: ModuleOperation,
    public readonly dependencies: LandscapeElementId[]
  ) {}

  public allModules(): LandscapeModule[] {
    return [this];
  }

  public hasDependency(dependency: string): boolean {
    return this.dependencies.some(currentDependency => currentDependency === dependency);
  }
}
