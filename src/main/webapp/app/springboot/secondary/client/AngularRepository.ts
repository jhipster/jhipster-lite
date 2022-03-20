import { Project } from '@/springboot/domain/Project';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { AxiosHttp } from '@/http/AxiosHttp';
import { AngularService } from '@/springboot/domain/client/AngularService';

export default class AngularRepository implements AngularService {
  constructor(private axiosHttp: AxiosHttp) {}

  async add(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/angular', restProject);
  }

  async addWithStyle(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/angular/styled', restProject);
  }
}
