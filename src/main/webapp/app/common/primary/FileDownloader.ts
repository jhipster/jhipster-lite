import { DocumentFile } from '@/common/domain/DocumentFile';

export class FileDownloader {
  constructor(private globalWindow: Window & typeof globalThis) {}

  download(documentFile: DocumentFile) {
    const blob = new Blob([documentFile.file], { type: documentFile.contentType });
    const link = this.globalWindow.document.createElement('a');
    this.globalWindow.document.body.appendChild(link);
    link.href = this.globalWindow.URL.createObjectURL(blob);
    if (documentFile.name !== undefined) {
      link.download = documentFile.name;
    } else {
      link.download = '';
    }
    link.click();
    this.globalWindow.URL.revokeObjectURL(link.href);
    this.globalWindow.document.body.removeChild(link);
  }

  url(documentFile: DocumentFile): string {
    const blob = new Blob([documentFile.file], { type: documentFile.contentType });
    return this.globalWindow.URL.createObjectURL(blob);
  }
}
