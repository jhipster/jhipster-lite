import sinon, { SinonStub } from 'sinon';
import { ProjectHistoryService } from '../../../../../main/webapp/app/common/domain/ProjectHistoryService';

export interface ProjectHistoryServiceFixture extends ProjectHistoryService {
  get: SinonStub;
}

export const stubProjectService = (): ProjectHistoryServiceFixture => ({
  get: sinon.stub(),
});
