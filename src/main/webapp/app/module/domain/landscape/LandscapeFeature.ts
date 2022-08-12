import { LandscapeModule } from './LandscapeModule';
import { LandscapeFeatureSlug } from './LandscapeFeatureSlug';

export class LandscapeFeature {
  constructor(public readonly slug: LandscapeFeatureSlug, public readonly modules: LandscapeModule[]) {}

  public allModules(): LandscapeModule[] {
    return this.modules;
  }
}
