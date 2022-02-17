import { Project } from '@/main/webapp/app/generator/domain/Project';

export interface ProjectService {
  init(project: Project): void;
}
