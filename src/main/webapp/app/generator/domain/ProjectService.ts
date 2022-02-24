import { Project } from '@/generator/domain/Project';

export interface ProjectService {
  init(project: Project): void;
}
