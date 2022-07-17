import { Project } from '@/springboot/domain/Project';

export interface ReactService {
  addWithJWT(project: Project): Promise<void>;
  addWithStyle(project: Project): Promise<void>;
}
