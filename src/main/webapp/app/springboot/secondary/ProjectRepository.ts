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

  async addEhcacheWithJavaConf(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/ehcache/java-configuration', restProject);
  }

  async addEhcacheWithXML(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/ehcache/xml-configuration', restProject);
  }

  async addSimpleCache(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/simple', restProject);
  }
}
