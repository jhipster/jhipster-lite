import { Project } from '@/springboot/domain/Project';

export interface SvelteService {
  add(project: Project): Promise<void>;
  addWithStyle(project: Project): Promise<void>;
}
