<template>
    <div>
        <div v-if="authenticated">
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
                    <button class="button is-primary"
                            @click="save()">
                        Lagre endring
                    </button>
                </div>
                <div class="control">
                    <router-link :to="'/index'"
                                 class="button is-text">
                        Til forsiden
                    </router-link>
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

        <not-authenticated :auth="auth"
                           :authenticated="authenticated">
        </not-authenticated>
    </div>
</template>

<script>
import axios from 'axios'
import NotAuthenticated from './NotAuthenticated'

export default {
    components: {NotAuthenticated},
    name: 'index-editor',
    props: ['auth', 'authenticated'],
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
            axios.post('/api/indexpage', this.page, {
                headers: {'X-JWT': this.auth.jwt()}
            })
                .then(() => {
                    this.$snotify.success('Endring ble lagret')
                })
                .catch(error => {
                    this.$snotify.error('Oops! Det oppstod en feil ved lagring av data')
                    console.log(error)
                })
        }
    },
    created () {
        this.fetch()
    }
}
</script>
