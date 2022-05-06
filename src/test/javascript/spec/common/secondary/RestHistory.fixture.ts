import { RestHistory } from '@/common/secondary/RestHistory';
import { createRestService } from './RestService.fixture';

export const createRestHistory = (): RestHistory => [
  createRestService({
    serviceId: 'init',
    timestamp: '2022-05-06T08:39:02.912804607Z',
  }),
  createRestService({
    serviceId: 'java-base',
    timestamp: '2022-05-07T08:39:02.912804607Z',
  }),
  createRestService({
    serviceId: 'maven-java',
    timestamp: '2022-05-08T08:39:02.912804607Z',
  }),
];
