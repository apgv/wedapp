<template>
    <div>
        <h4 class="title is-4">
            Rediger forsiden
        </h4>
        <article class="message">
            <div class="message-header">
                HTML editor
            </div>

            <div class="message-body">
                <textarea class="textarea"
                          v-model="page.content">
                </textarea>
            </div>
        </article>
        <div class="field is-grouped">
            <div class="control">
                <button class="button is-danger"
                        @click="revertChanges()">
                    Angre endringer
                </button>
            </div>
            <div class="control">
                <button class="button is-primary"
                        @click="save()">
                    Lagre endring
                </button>
            </div>
        </div>

        <article class="message">
            <div class="message-header">
                Forhåndsvisning
            </div>

            <div class="message-body">
                <div class="content"
                     v-html="page.content">
                </div>
            </div>
        </article>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'index-editor',
    data () {
        return {
            page: {
                content: null
            }
        }
    },
    methods: {
        fetch () {
            axios.get('/api/indexpage')
                .then(response => {
                    this.page = response.data
                })
                .catch(error => {
                    this.$snotify.error('Oops! Det oppstod en feil ved henting av data')
                    console.log(error)
                })
        },
        save () {
            axios.post('/api/indexpage', this.page)
                .then(() => {
                    this.$snotify.success('Endring ble lagret')
                })
                .catch(error => {
                    this.$snotify.error('Oops! Det oppstod en feil ved lagring av data')
                    console.log(error)
                })
        },
        revertChanges () {
            this.fetch()
        }
    },
    created () {
        this.fetch()
    }
}
</script>
