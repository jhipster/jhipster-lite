import sinon, { SinonStub } from 'sinon';
import ToastService from '@/common/secondary/ToastService';

export interface ToastServiceFixture extends ToastService {
  register: SinonStub;
  success: SinonStub;
  error: SinonStub;
}

export const stubToastService = (): ToastServiceFixture =>
  ({
    register: sinon.stub(),
    success: sinon.stub(),
    error: sinon.stub(),
  } as ToastServiceFixture);
