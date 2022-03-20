import { Folder } from '@/springboot/domain/Folder';
import { History } from '@/common/domain/History';

export interface ProjectHistoryService {
  get(folder: Folder): Promise<History>;
}
