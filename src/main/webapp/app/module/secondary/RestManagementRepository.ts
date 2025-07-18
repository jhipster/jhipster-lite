import { ManagementInfo } from '@/module/domain/ManagementInfo';
import { ManagementRepository } from '@/module/domain/ManagementRepository';
import { AxiosHttp } from '@/shared/http/infrastructure/secondary/AxiosHttp';

export class RestManagementRepository implements ManagementRepository {
  constructor(private readonly axiosInstance: AxiosHttp) {}

  getInfo(): Promise<ManagementInfo> {
    return this.axiosInstance.get<ManagementInfo>('/management/info').then(res => res.data);
  }
}
