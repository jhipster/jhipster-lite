import { ModuleProperties } from './ModuleProperties';
import { ModuleSlug } from './ModuleSlug';
import { ProjectFolder } from './ProjectFolder';

export interface ModulesToApply {
  modules: ModuleSlug[];
  projectFolder: ProjectFolder;
  commit: boolean;
  properties: ModuleProperties;
}
