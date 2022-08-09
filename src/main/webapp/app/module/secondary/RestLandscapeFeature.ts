import { RestLandscapeModule, toLandscapeModule } from './RestLandscapeModule';
import { RestLandscapeElementType } from './RestLandscapeElementType';
import { LandscapeFeature } from '../domain/landscape/LandscapeFeature';

export interface RestLandscapeFeature {
  type: RestLandscapeElementType;
  slug: string;
  modules: RestLandscapeModule[];
}

export const toLandscapeFeature = (feature: RestLandscapeFeature): LandscapeFeature =>
  new LandscapeFeature(feature.slug, feature.modules.map(toLandscapeModule));
