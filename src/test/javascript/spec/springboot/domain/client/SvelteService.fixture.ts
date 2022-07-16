import sinon, { SinonStub } from 'sinon';
import { SvelteService } from '@/springboot/domain/client/SvelteService';

export interface SvelteServiceFixture extends SvelteService {
  addWithStyle: SinonStub;
}

export const stubSvelteService = (): SvelteServiceFixture => ({
  addWithStyle: sinon.stub(),
});
