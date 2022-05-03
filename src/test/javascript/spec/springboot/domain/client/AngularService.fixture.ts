import sinon, { SinonStub } from 'sinon';
import { AngularService } from '@/springboot/domain/client/AngularService';

export interface AngularServiceFixture extends AngularService {
  add: SinonStub;
  addWithJWT: SinonStub;
}

export const stubAngularService = (): AngularServiceFixture => ({
  add: sinon.stub(),
  addWithJWT: sinon.stub(),
});
