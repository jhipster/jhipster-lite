import { Project } from '@/springboot/domain/Project';

export interface ReactService {
  add(project: Project): Promise<void>;
  addWithStyle(project: Project): Promise<void>;
}
