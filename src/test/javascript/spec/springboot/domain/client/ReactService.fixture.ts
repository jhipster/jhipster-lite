import sinon, { SinonStub } from 'sinon';
import { ReactService } from '@/springboot/domain/client/ReactService';

export interface ReactServiceFixture extends ReactService {
  addWithStyle: SinonStub;
  addWithJWT: SinonStub;
}

export const stubReactService = (): ReactServiceFixture => ({
  addWithStyle: sinon.stub(),
  addWithJWT: sinon.stub(),
});
