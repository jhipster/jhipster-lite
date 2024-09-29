import i18n from '@/i18n';

describe('i18n configuration', () => {
  it('loads en translation', () => {
    expect(i18n.getResourceBundle('en', '')['home']['translationEnabled']).toEqual('Internationalization enabled');
  });

  it('loads fr translation', () => {
    expect(i18n.getResourceBundle('fr', '')['home']['translationEnabled']).toEqual('Internationalisation activée');
  });
});
