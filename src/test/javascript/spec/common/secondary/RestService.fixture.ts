import { RestService } from '@/common/secondary/RestService';

export const createRestService = (restHistory?: Partial<RestService>): RestService => ({
  serviceId: 'init',
  timestamp: '2022-05-06T08:39:02.912804607Z',
  ...restHistory,
});
