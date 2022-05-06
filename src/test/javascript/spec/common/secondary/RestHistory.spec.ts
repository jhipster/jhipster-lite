import { RestHistory, toHistory } from '@/common/secondary/RestHistory';
import { History } from '@/common/domain/History';
import { toService } from '@/common/secondary/RestServiceId';
import { createRestHistory } from './RestHistory.fixture';

describe('RestHistory', () => {
  it('should convert to History', () => {
    const restHistory: RestHistory = createRestHistory();

    expect(toHistory(restHistory)).toEqual<History>({
      services: restHistory.map(service => toService(service.serviceId)),
    });
  });
});
