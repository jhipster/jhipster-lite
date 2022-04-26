import { AxiosResponse } from 'axios';

import { DocumentFile } from '@/common/domain/DocumentFile';

export const toDocumentFile =
  (defaultName?: string) =>
  (response: AxiosResponse<ArrayBuffer>): DocumentFile => ({
    name: response.headers['x-suggested-filename'] || defaultName,
    file: response.data,
    contentType: response.headers['content-type'],
  });
