import { createI18n } from 'vue-i18n'
import et from '../i18n/translations.json'
import en from '../i18n/translations_en.json'

export const i18n = createI18n({
    legacy: false,
    locale: 'et',
    fallbackLocale: 'en',
    messages: { et, en }
})
