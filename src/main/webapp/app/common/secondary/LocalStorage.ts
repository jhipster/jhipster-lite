import { Storage } from '@/common/domain/Storage';

export default class LocalStorageAdapter implements Storage {
  save(key: string, value: string): void {
    localStorage.setItem(key, value);
  }

  load(key: string): string | null {
    return localStorage.getItem(key);
  }

  delete(key: string): void {
    localStorage.removeItem(key);
  }
}
