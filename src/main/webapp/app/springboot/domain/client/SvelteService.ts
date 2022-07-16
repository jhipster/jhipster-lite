import { Project } from '@/springboot/domain/Project';

export interface SvelteService {
  addWithStyle(project: Project): Promise<void>;
}
