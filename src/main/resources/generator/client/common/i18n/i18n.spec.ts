import i18n from '@/i18n';
import { mergeTranslations } from '@/Translations';

describe('i18n configuration', () => {
  it('loads en translation', () => {
    expect(i18n.getResourceBundle('en', '')['home']['translationEnabled']).toEqual('Internationalization enabled');
  });

  it('loads fr translation', () => {
    expect(i18n.getResourceBundle('fr', '')['home']['translationEnabled']).toEqual('Internationalisation activée');
  });

  describe('mergeTranslations function', () => {
    it('merges translations correctly when keys overlap', () => {
      const translationSet1 = {
        en: {
          home: {
            translationEnabled: 'Internationalization enabled',
            welcome: 'Welcome',
          },
        },
      };

      const translationSet2 = {
        en: {
          home: {
            anotherMessage: 'Another message',
          },
        },
      };

      const mergedResult = mergeTranslations([translationSet1, translationSet2]);

      expect(mergedResult).toEqual({
        en: {
          home: {
            translationEnabled: 'Internationalization enabled',
            welcome: 'Welcome',
            anotherMessage: 'Another message',
          },
        },
      });
    });
  });
});
