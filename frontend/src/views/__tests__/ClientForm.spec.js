import { mount, flushPromises } from '@vue/test-utils'
import ClientForm from '../ClientForm.vue'
import { describe, it, expect, vi, beforeEach } from 'vitest'
import axios from 'axios'
import { i18n } from '@/tests/i18n-test-setup'


vi.mock('axios')

describe('ClientForm.vue', () => {
    beforeEach(() => {
        vi.resetAllMocks()
    })

    it('laeb automargid ja renderdab valikud', async () => {
        axios.get.mockResolvedValueOnce({
            data: [
                { code: 'BMW', name: 'BMW' },
                { code: 'AUDI', name: 'Audi' }
            ]
        })

        const wrapper = mount(ClientForm, {
            global: {
                plugins: [i18n]
            }
        })

        await flushPromises()
        const options = wrapper.findAll('select.form-select option')
        const texts = options.map(o => o.text())
        expect(texts).toContain('BMW')
        expect(texts).toContain('Audi')
    })
})
