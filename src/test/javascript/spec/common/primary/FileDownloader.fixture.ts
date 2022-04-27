import sinon, { SinonStub } from 'sinon';
import { FileDownloader } from '@/common/primary/FileDownloader';

export interface FileDownloaderFixture extends FileDownloader {
  download: SinonStub;
  url: SinonStub;
}

export const stubFileDownloader = (): FileDownloaderFixture =>
  ({
    download: sinon.stub(),
    url: sinon.stub(),
  } as FileDownloaderFixture);
