import { ModuleToApply } from './ModuleToApply';
import { Modules } from './Modules';
import { ModuleSlug } from './ModuleSlug';
import { ProjectFolder } from './ProjectFolder';
import { Project } from './Project';

export interface ModulesRepository {
  list(): Promise<Modules>;

  apply(module: ModuleSlug, moduleToApply: ModuleToApply): Promise<void>;

  appliedModules(folder: ProjectFolder): Promise<ModuleSlug[]>;

  download(folder: ProjectFolder): Promise<Project>;
}
