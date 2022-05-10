import sinon, { SinonStub } from 'sinon';
import { SvelteService } from '@/springboot/domain/client/SvelteService';

export interface SvelteServiceFixture extends SvelteService {
  add: SinonStub;
  addWithStyle: SinonStub;
}

export const stubSvelteService = (): SvelteServiceFixture => ({
  add: sinon.stub(),
  addWithStyle: sinon.stub(),
});
