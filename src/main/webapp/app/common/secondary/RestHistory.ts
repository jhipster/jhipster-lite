import { RestServiceId, toService } from '@/common/secondary/RestServiceId';
import { History } from '@/common/domain/History';

export interface RestHistory {
  serviceIds: RestServiceId[];
}

export const toHistory = (restHistory: RestHistory): History => ({
  services: restHistory.serviceIds.map(toService),
});
