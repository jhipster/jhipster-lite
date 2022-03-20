import { Project } from '@/springboot/domain/Project';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { AxiosHttp } from '@/http/AxiosHttp';
import { VueService } from '@/springboot/domain/client/VueService';

export default class VueRepository implements VueService {
  constructor(private axiosHttp: AxiosHttp) {}

  async add(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/vue', restProject);
  }

  async addWithStyle(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/vue/styled', restProject);
  }
}
