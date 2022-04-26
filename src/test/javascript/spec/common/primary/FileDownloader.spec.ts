import sinon from 'sinon';

import { DocumentFile } from '@/common/domain/DocumentFile';
import { FileDownloader } from '@/common/primary/FileDownloader';

const imageArrayBuffer = Uint8Array.from([]).buffer;

const stubWindow = () => ({
  URL: { createObjectURL: sinon.stub(), revokeObjectURL: sinon.stub() },
  document: { createElement: sinon.stub(), body: { appendChild: sinon.stub(), removeChild: sinon.stub() } },
});

const stubLink = () => ({
  href: '',
  click: sinon.stub(),
  download: '',
});

const minimalFile = (): DocumentFile => ({
  file: imageArrayBuffer,
  contentType: 'image/png',
});

const fullFile = (): DocumentFile => ({
  name: 'chips.png',
  file: imageArrayBuffer,
  contentType: 'image/png',
});

describe('FileDownloader', () => {
  it('should download', () => {
    const windowStub = stubWindow();
    const link = stubLink();
    windowStub.document.createElement.returns(link);

    const fileDownloader = new FileDownloader(windowStub as any);
    fileDownloader.download(fullFile());

    expect(windowStub.document.body.appendChild.calledOnce).toBeTruthy();
    expect(windowStub.document.body.removeChild.calledOnce).toBeTruthy();
    expect(windowStub.document.createElement.calledOnce).toBeTruthy();
    expect(windowStub.URL.createObjectURL.calledOnce).toBeTruthy();
    expect(windowStub.URL.revokeObjectURL.calledOnce).toBeTruthy();
    expect(link.click.calledOnce).toBeTruthy();
    expect(link.download).toBe('chips.png');
  });
  it('should download without name', () => {
    const windowStub = stubWindow();
    const link = stubLink();
    windowStub.document.createElement.returns(link);

    const fileDownloader = new FileDownloader(windowStub as any);

    fileDownloader.download(minimalFile());
    expect(link.download).toBe('');
  });
  it('Should get URL from file downloader', () => {
    const windowStub = stubWindow();
    windowStub.URL.createObjectURL.returns('URL');

    const fileDownloader = new FileDownloader(windowStub as any);
    const url = fileDownloader.url(fullFile());

    expect(windowStub.URL.createObjectURL.calledOnce).toBe(true);
    expect(url).toBe('URL');
  });
});
