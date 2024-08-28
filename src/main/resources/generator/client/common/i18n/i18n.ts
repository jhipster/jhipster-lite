import i18n from 'i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import { commonTranslations } from './common/CommonTranslations';
import { toTranslationResources } from './Translations';

i18n.use(LanguageDetector).init({
  fallbackLng: 'en',
  debug: false,
  interpolation: {
    escapeValue: false,
  },
  resources: toTranslationResources(commonTranslations),
});

export default i18n;
