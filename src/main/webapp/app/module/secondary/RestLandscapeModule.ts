import { LandscapeModule } from '../domain/landscape/LandscapeModule';
import { RestLandscapeElementType } from './RestLandscapeElementType';

export interface RestLandscapeModule {
  type: RestLandscapeElementType;
  slug: string;
  operation: string;
  dependencies?: string[];
}

export const toLandscapeModule = (module: RestLandscapeModule): LandscapeModule =>
  new LandscapeModule(module.slug, module.operation, module.dependencies || []);
