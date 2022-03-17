import { Project } from '@/springboot/domain/Project';

export interface ProjectService {
  init(project: Project): Promise<void>;
  addMaven(project: Project): Promise<void>;
  addFrontendMavenPlugin(project: Project): Promise<void>;
  addJavaBase(project: Project): Promise<void>;
  addSpringBoot(project: Project): Promise<void>;
  addSpringBootMvcTomcat(project: Project): Promise<void>;
  addSpringBootBannerIppon(project: Project): Promise<void>;
  addSpringBootBannerJHipsterV2(project: Project): Promise<void>;
  addSpringBootBannerJHipsterV3(project: Project): Promise<void>;
  addSpringBootBannerJHipsterV7(project: Project): Promise<void>;
  addSpringBootBannerJHipsterV7React(project1: Project): Promise<void>;
  addSpringBootBannerJHipsterV7Vue(project1: Project): Promise<void>;
}
