import { Project } from '@/springboot/domain/Project';

export interface ProjectService {
  init(project: Project): Promise<void>;
  addMaven(project: Project): Promise<void>;
  addFrontendMavenPlugin(project: Project): Promise<void>;
  addJavaBase(project: Project): Promise<void>;
  addSpringBoot(project: Project): Promise<void>;
  addSpringBootMvcTomcat(project: Project): Promise<void>;
  addSpringBootAsync(project: Project): Promise<void>;
  addSpringBootDevtoolsDependencies(project: Project): Promise<void>;
  addSpringBootDockerfile(project: Project): Promise<void>;
  addSpringBootDockerJib(project: Project): Promise<void>;
}
