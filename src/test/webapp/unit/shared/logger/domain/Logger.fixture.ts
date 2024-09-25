import { Logger } from '@/shared/logger/domain/Logger';
import sinon, { SinonStub } from 'sinon';

export interface LoggerFixture extends Logger {
  error: SinonStub;
}

export const stubLogger = (): LoggerFixture => ({
  error: sinon.stub(),
});
