import { Module } from '../domain/Module';
import { ModuleSlug } from '../domain/ModuleSlug';
import { RestModuleProperties, toProperties } from './RestModuleProperties';

export interface RestModule {
  slug: string;
  description: string;
  properties?: RestModuleProperties;
  tags?: string[];
}

export const toModule = (restModule: RestModule): Module => ({
  slug: new ModuleSlug(restModule.slug),
  description: restModule.description,
  properties: toProperties(restModule.properties),
  tags: toTags(restModule.tags),
});

const toTags = (tags: string[] | undefined): string[] => {
  if (tags === undefined) {
    return [];
  }

  return tags;
};
