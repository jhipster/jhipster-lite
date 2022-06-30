import { ModuleToApply } from './ModuleToApply';
import { Modules } from './Modules';
import { ModuleSlug } from './ModuleSlug';

export interface ModulesRepository {
  list(): Promise<Modules>;

  apply(module: ModuleSlug, moduleToApply: ModuleToApply): Promise<void>;
}
