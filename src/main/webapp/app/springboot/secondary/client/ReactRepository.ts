import { Project } from '@/springboot/domain/Project';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { AxiosHttp } from '@/http/AxiosHttp';
import { ReactService } from '@/springboot/domain/client/ReactService';

export default class ReactRepository implements ReactService {
  constructor(private axiosHttp: AxiosHttp) {}

  async add(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/clients/react', restProject);
  }

  async addWithStyle(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/clients/react/styled', restProject);
  }
}
