import { ProjectService } from '@/generator/domain/ProjectService';
import sinon, { SinonStub } from 'sinon';

export interface ProjectServiceFixture extends ProjectService {
  init: SinonStub;
}

export const stubProjectService = (): ProjectServiceFixture => ({
  init: sinon.stub(),
});
