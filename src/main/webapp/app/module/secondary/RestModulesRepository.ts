import { AxiosHttp } from '@/http/AxiosHttp';
import { AxiosRequestConfig, AxiosResponse } from 'axios';
import { Modules } from '../domain/Modules';
import { ModulesRepository } from '../domain/ModulesRepository';
import { ModuleToApply } from '../domain/ModuleToApply';
import { ModuleSlug } from '../domain/ModuleSlug';
import { ProjectFolder } from '../domain/ProjectFolder';
import { Project } from '../domain/Project';
import { Landscape } from '../domain/landscape/Landscape';
import { mapToModules, RestModules } from './RestModules';
import { RestModuleToApply, toRestModuleToApply } from './RestModuleToApply';
import { mapToModuleHistory, RestProjectHistory } from './RestProjectHistory';
import { mapToLandscape, RestLandscape } from './RestLandscape';
import { ProjectHistory } from '../domain/ProjectHistory';

export class RestModulesRepository implements ModulesRepository {
  constructor(private axiosInstance: AxiosHttp) {}

  list(): Promise<Modules> {
    return this.axiosInstance.get<RestModules>('/api/modules').then(mapToModules);
  }

  landscape(): Promise<Landscape> {
    return this.axiosInstance.get<RestLandscape>('/api/modules-landscape').then(mapToLandscape);
  }

  async apply(module: ModuleSlug, moduleToApply: ModuleToApply): Promise<void> {
    await this.axiosInstance.post<void, RestModuleToApply>(`/api/modules/${module}/apply-patch`, toRestModuleToApply(moduleToApply));
  }

  history(folder: ProjectFolder): Promise<ProjectHistory> {
    return this.axiosInstance.get<RestProjectHistory>(`/api/projects?path=${folder}`).then(mapToModuleHistory);
  }

  async format(folder: string): Promise<void> {
    await this.axiosInstance.post<void, RestModuleToApply>(`/api/format-project?path=${folder}`);
  }

  download(folder: string): Promise<Project> {
    const config: AxiosRequestConfig = {
      responseType: 'blob',
      headers: {
        Accept: 'application/octet-stream',
      },
    };

    return this.axiosInstance.get<ArrayBuffer>(`/api/projects?path=${folder}`, config).then(mapToProject);
  }
}

const mapToProject = (response: AxiosResponse<ArrayBuffer>): Project => {
  return {
    filename: response.headers['x-suggested-filename'],
    content: response.data,
  };
};
