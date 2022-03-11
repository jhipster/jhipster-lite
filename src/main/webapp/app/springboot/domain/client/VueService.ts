import { Project } from '@/springboot/domain/Project';

export interface VueService {
  add(project: Project): Promise<void>;
  addWithStyle(project: Project): Promise<void>;
}
