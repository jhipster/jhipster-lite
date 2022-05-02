import sinon, { SinonStub } from 'sinon';
import { NotificationService } from '@/common/domain/NotificationService';

export interface NotificationServiceFixture extends NotificationService {
  register: SinonStub;
  success: SinonStub;
  error: SinonStub;
}

export const stubNotificationService = (): NotificationServiceFixture => ({
  register: sinon.stub(),
  success: sinon.stub(),
  error: sinon.stub(),
});
