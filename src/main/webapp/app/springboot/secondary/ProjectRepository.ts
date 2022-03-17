import { Project } from '@/springboot/domain/Project';
import { ProjectService } from '@/springboot/domain/ProjectService';
import { AxiosHttp } from '@/http/AxiosHttp';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';

export default class ProjectRepository implements ProjectService {
  constructor(private axiosHttp: AxiosHttp) {}

  async init(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/projects/init', restProject);
  }

  async addMaven(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/build-tools/maven', restProject);
  }

  async addFrontendMavenPlugin(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/frontend-maven-plugin', restProject);
  }

  async addJavaBase(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/java/base', restProject);
  }

  async addSpringBoot(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot', restProject);
  }

  async addSpringBootMvcTomcat(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/mvc/web/tomcat', restProject);
  }

  async addSpringBootBannerIppon(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/ippon', restProject);
  }

  async addSpringBootBannerJHipsterV2(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/jhipster-v2', restProject);
  }

  async addSpringBootBannerJHipsterV3(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/jhipster-v3', restProject);
  }

  async addSpringBootBannerJHipsterV7(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/jhipster-v7', restProject);
  }

  async addSpringBootBannerJHipsterV7React(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/jhipster-v7-react', restProject);
  }

  async addSpringBootBannerJHipsterV7Vue(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/banner/jhipster-v7-vue', restProject);
  }
}
