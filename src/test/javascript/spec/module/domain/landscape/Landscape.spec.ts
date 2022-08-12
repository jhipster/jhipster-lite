import { Landscape } from '@/module/domain/landscape/Landscape';
import { defaultLandscape } from '../Modules.fixture';

describe('Landscape', () => {
  it('Should not get unknown module', () => {
    expect(() => new Landscape([]).getModule('unknown')).toThrowError();
  });

  it('Should get module', () => {
    expect(defaultLandscape().getModule('infinitest').slug).toBe('infinitest');
  });

  it('Should get module in feature', () => {
    expect(defaultLandscape().getModule('vue').slug).toBe('vue');
  });
});
