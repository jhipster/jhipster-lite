import { Landscape } from '@/module/domain/landscape/Landscape';
import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { ComponentLandscapeElement } from './ComponentLandscapeElement';

export class ComponentLandscapeModule implements ComponentLandscapeElement {
  private constructor(
    public readonly slug: string,
    public readonly operation: string,
    public readonly dependencies: string[],
    public readonly dependantModules: string[]
  ) {}

  public static from(module: LandscapeModule, landscape: Landscape): ComponentLandscapeModule {
    return new ComponentLandscapeModule(
      module.slug.get(),
      module.operation,
      module.dependencies.map(dependency => dependency.get()),
      landscape.dependantModules(module).map(dependantModule => dependantModule.get())
    );
  }

  public allModules(): ComponentLandscapeModule[] {
    return [this];
  }

  public isFeature(): boolean {
    return false;
  }
}
