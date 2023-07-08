import { LocalWindowThemeRepository } from '@/module/secondary/LocalWindowThemeRepository';
import { describe, expect, it } from 'vitest';

const fakeStorage = (): Storage => {
  let store: { [key: string]: string } = {};
  return {
    getItem: (key: string) => store[key],
    setItem: (key: string, value: string) => (store[key] = value),
    removeItem: (key: string) => delete store[key],
    clear: () => (store = {}),
    get length() {
      return Object.keys(store).length;
    },
    key: (index: number) => Object.keys(store)[index],
  };
};

describe('LocalWindowThemeRepository', () => {
  it.todo('should return light theme if no user theme preference stored in localStorage', () => {
    const storage = fakeStorage();
    storage.clear();

    const repo = new LocalWindowThemeRepository(window, storage);
    expect(repo.get()).toBe('light-theme');
  });

  it.todo('should return dark theme if user theme prefer it', () => {
    const storage = fakeStorage();
    storage.clear();

    const repo = new LocalWindowThemeRepository(window, storage);
    repo.choose('dark-theme');
    expect(repo.get()).toBe('dark-theme');
  });
});
