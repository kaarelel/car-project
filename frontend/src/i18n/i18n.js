import { createI18n } from 'vue-i18n'
import et from './translations.json'
import en from './translations_en.json'

const messages = {
    et,
    en
}

const i18n = createI18n({
    legacy: false,
    locale: 'et',
    fallbackLocale: 'en',
    messages
})

export default i18n
