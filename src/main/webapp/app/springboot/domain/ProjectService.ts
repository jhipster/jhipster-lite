import { Project } from '@/springboot/domain/Project';

export interface ProjectService {
  init(project: Project): Promise<void>;
  addMaven(project: Project): Promise<void>;
  addFrontendMavenPlugin(project: Project): Promise<void>;
  addJavaBase(project: Project): Promise<void>;
  addSpringBoot(project: Project): Promise<void>;
  addSpringBootMvcTomcat(project: Project): Promise<void>;
  addEhcacheWithJavaConf(project: Project): Promise<void>;
  addEhcacheWithXML(project: Project): Promise<void>;
  addSimpleCache(project: Project): Promise<void>;
}
