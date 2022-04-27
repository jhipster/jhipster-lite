import { Project } from '@/springboot/domain/Project';
import { ProjectService } from '@/springboot/domain/ProjectService';
import { AxiosHttp } from '@/http/AxiosHttp';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { DocumentFile } from '@/common/domain/DocumentFile';
import { toDocumentFile } from '@/common/secondary/ResponseDocumentFile';
import { AxiosRequestConfig } from 'axios';

export default class ProjectRepository implements ProjectService {
  constructor(private axiosHttp: AxiosHttp) {}

  async init(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/inits/full', restProject);
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

  async download(project: Project): Promise<DocumentFile> {
    const restProject: RestProject = toRestProject(project);
    const requestConfig: AxiosRequestConfig = {
      responseType: 'blob',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
    };
    return this.axiosHttp
      .post<ArrayBuffer, RestProject>('api/projects/download', restProject, requestConfig)
      .then(toDocumentFile('export.xls'));
  }
}
