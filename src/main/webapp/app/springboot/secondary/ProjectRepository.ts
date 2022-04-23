import { Project } from '@/springboot/domain/Project';
import { ProjectService } from '@/springboot/domain/ProjectService';
import { AxiosHttp } from '@/http/AxiosHttp';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';

export default class ProjectRepository implements ProjectService {
  constructor(private axiosHttp: AxiosHttp) {}

  async init(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/projects', restProject);
  }

  async addMaven(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/build-tools/maven', restProject);
  }

  async addJaCoCo(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/java/jacoco-minimum-coverage', restProject);
  }

  async addSonarBackend(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/developer-tools/sonar/java-backend', restProject);
  }

  async addSonarBackendFrontend(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/developer-tools/sonar/java-backend-and-frontend', restProject);
  }

  async addFrontendMavenPlugin(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/developer-tools/frontend-maven-plugin', restProject);
  }

  async addJavaBase(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/java/base', restProject);
  }

  async download(project: Project): Promise<BlobPart> {
    const restProject: RestProject = toRestProject(project);
    return this.axiosHttp
      .post<BlobPart, RestProject>('api/project-downloads', restProject, {
        responseType: 'blob',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
        },
      })
      .then(response => response.data);
  }
}
