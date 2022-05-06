import { toService } from '@/common/secondary/RestServiceId';
import { History } from '@/common/domain/History';
import { RestService } from '@/common/secondary/RestService';

export type RestHistory = RestService[];

export const toHistory = (restHistory: RestHistory): History => ({
  services: restHistory.map(service => toService(service.serviceId)),
});
