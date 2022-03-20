import sinon, { SinonStub } from 'sinon';
import { AngularService } from '@/springboot/domain/client/AngularService';

export interface AngularServiceFixture extends AngularService {
  add: SinonStub;
  addWithStyle: SinonStub;
}

export const stubAngularService = (): AngularServiceFixture => ({
  add: sinon.stub(),
  addWithStyle: sinon.stub(),
});
