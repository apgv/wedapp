<template>
    <div>
        <div v-if="authenticated">
            <h4 v-if="directionId"
                class="title is-4">
                Endre veibeskrivelse
            </h4>
            <h4 v-else
                class="title is-4">
                Legg til veibeskrivelse
            </h4>

            <div>
                <div class="field">
                    <label class="label">Tittel</label>
                    <p class="control has-icons-left">
                        <input v-model="direction.title"
                               v-validate="'required|max:40'"
                               name="title"
                               class="input"
                               placeholder="Tittel"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-map-marker"></i>
                    </span>
                        <span v-show="errors.has('title')"
                              class="help is-danger">
                        {{errors.first('title')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Google Maps Embed API (HTML syntaks)</label>
                    <p class="control">
                        <textarea v-model="direction.content"
                                  v-validate="'required|max:500'"
                                  name="content"
                                  class="textarea"
                                  placeholder="HTML syntaks">
                        </textarea>
                        <span v-show="errors.has('content')"
                              class="help is-danger">
                        {{errors.first('content')}}
                    </span>
                    </p>
                </div>

                <div>
                    <button @click="saveDirection"
                            class="button is-link">
                        Lagre
                    </button>
                    <router-link :to="'/directions'"
                                 class="button is-text">
                        Til veibeskrivelser
                    </router-link>
                </div>

            </div>
        </div>

        <not-authenticated :auth="auth"
                           :authenticated="authenticated">
        </not-authenticated>
    </div>
</template>

<script>
import NotAuthenticated from './NotAuthenticated'
import axios from 'axios'
import router from '../router'

export default {
    components: {NotAuthenticated},
    name: 'directions-add-edit',
    props: ['auth', 'authenticated', 'directionId'],
    data () {
        return {
            direction: {
                title: null,
                content: null
            }
        }
    },
    methods: {
        fetchDirection () {
            if (this.authenticated && this.directionId) {
                axios.get(`/directions/${this.directionId}`, {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(response => {
                    this.direction = response.data
                }).catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
            }
        },
        saveDirection () {
            if (this.authenticated) {
                this.$validator.validateAll()
                    .then((result) => {
                        if (result) {
                            const successCallback = () => {
                                this.direction = {
                                    title: null,
                                    content: null
                                }
                                this.$nextTick(() => this.$validator.reset())
                                this.$snotify.success('Veibeskrivelse ble lagret')
                            }

                            const errorCallback = (error) => {
                                this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                                console.log(error)
                            }

                            if (this.directionId) {
                                axios.put('/api/directions', this.directions, {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .then(() => {
                                        router.replace('/directions')
                                    })
                                    .catch(errorCallback)
                            } else {
                                axios.post('/api/directions', this.direction, {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .catch(errorCallback)
                            }
                        }
                    })
            }
        }
    },
    created () {
        this.fetchDirection()
    }
}
</script>
