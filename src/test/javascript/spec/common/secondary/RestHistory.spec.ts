import { RestHistory, toHistory } from '../../../../../main/webapp/app/common/secondary/RestHistory';
import { History } from '../../../../../main/webapp/app/common/domain/History';
import { toService } from '../../../../../main/webapp/app/common/secondary/RestServiceId';
import { createRestHistory } from './RestHistory.fixture';

describe('RestHistory', () => {
  it('should convert to History', () => {
    const restHistory: RestHistory = createRestHistory();

    expect(toHistory(restHistory)).toEqual<History>({
      services: restHistory.serviceIds.map(toService),
    });
  });
});
