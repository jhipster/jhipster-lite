import { stubAxiosHttp } from '../../http/AxiosHttpStub';
import { createRestHistory } from './RestHistory.fixture';
import ProjectHistoryRepository from '@/common/secondary/ProjectHistoryRepository';
import { History } from '@/common/domain/History';
import { Service } from '@/common/domain/Service';

describe('ProjectRepository', () => {
  it('should get project history', async () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.get.resolves({ data: createRestHistory() });
    const projectHistoryRepository = new ProjectHistoryRepository(axiosHttpStub);

    const history: History = await projectHistoryRepository.get('folder/path');

    const [uri, config] = axiosHttpStub.get.getCall(0).args;
    expect(uri).toBe('api/projects/history');
    expect(config).toEqual({ params: { folder: 'folder/path' } });
    expect(history).toEqual<History>({
      services: [Service.INITIALIZATION, Service.JAVA_BASE, Service.MAVEN],
    });
  });
});
