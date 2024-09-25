import { LandscapeElement } from './LandscapeElement';
import { LandscapeFeatureSlug } from './LandscapeFeatureSlug';
import { LandscapeModule } from './LandscapeModule';

export class LandscapeFeature implements LandscapeElement {
  constructor(
    private readonly featureSlug: LandscapeFeatureSlug,
    public readonly modules: LandscapeModule[],
  ) {}

  public slugString(): string {
    return this.slug().get();
  }

  public slug(): LandscapeFeatureSlug {
    return this.featureSlug;
  }

  public allModules(): LandscapeModule[] {
    return this.modules;
  }
}
