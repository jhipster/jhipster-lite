import { Project } from '@/springboot/domain/Project';

export interface ProjectService {
  init(project: Project): Promise<void>;
  addMaven(project: Project): Promise<void>;
  addFrontendMavenPlugin(project: Project): Promise<void>;
  addJavaBase(project: Project): Promise<void>;
  addSpringBoot(project: Project): Promise<void>;
  addSpringBootMvcTomcat(project: Project): Promise<void>;
  addSpringBootBannerIppon(project: Project): Promise<void>;
  addSpringBootBannerJhipsterV2(project: Project): Promise<void>;
  addSpringBootBannerJhipsterV3(project: Project): Promise<void>;
  addSpringBootBannerJhipsterV7(project: Project): Promise<void>;
  addSpringBootBannerJhipsterV7React(project1: Project): Promise<void>;
  addSpringBootBannerJhipsterV7Vue(project1: Project): Promise<void>;
}
