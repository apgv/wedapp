<template>
    <div>
        <div v-if="authenticated">
            <h4 v-if="accommodationId"
                class="title is-4">
                Endre overnatting
            </h4>
            <h4 v-else
                class="title is-4">
                Legg til overnatting
            </h4>

            <div>
                <div class="field">
                    <label class="label">Navn</label>
                    <p class="control has-icons-left">
                        <input v-model="accommodation.name"
                               v-validate="'required|max:40'"
                               name="name"
                               class="input"
                               placeholder="Navn"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-building"></i>
                    </span>
                        <span v-show="errors.has('name')"
                              class="help is-danger">
                        {{errors.first('name')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Adresse</label>
                    <p class="control has-icons-left">
                        <input v-model="accommodation.address"
                               v-validate="'required|max:40'"
                               name="address"
                               class="input"
                               placeholder="Adresse"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-map-marker"></i>
                    </span>
                        <span v-show="errors.has('address')"
                              class="help is-danger">
                        {{errors.first('address')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Telefon</label>
                    <p class="control has-icons-left">
                        <input v-model="accommodation.phone"
                               v-validate="'numeric|max:15'"
                               name="phone"
                               class="input"
                               placeholder="Telefon"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-mobile"></i>
                    </span>
                        <span v-show="errors.has('phone')"
                              class="help is-danger">
                        {{errors.first('phone')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Hjemmeside (http://navn.domene)</label>
                    <p class="control has-icons-left">
                        <input v-model="accommodation.homePage"
                               v-validate="'url:require_protocol|max:255'"
                               name="homepage"
                               class="input"
                               placeholder="Hjemmeside"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-globe"></i>
                    </span>
                        <span v-show="errors.has('homepage')"
                              class="help is-danger">
                        {{errors.first('homepage')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Ekstra info (HTML syntaks)</label>
                    <p class="control">
                        <textarea v-model="accommodation.infoHtml"
                                  v-validate="'max:500'"
                                  name="info"
                                  class="textarea"
                                  placeholder="Ekstra info">
                        </textarea>
                        <span v-show="errors.has('info')"
                              class="help is-danger">
                        {{errors.first('info')}}
                    </span>
                    </p>
                </div>

                <div>
                    <button @click="saveAccommodation"
                            class="button is-link">
                        Lagre
                    </button>
                    <router-link :to="'/accommodations'"
                                 class="button is-text">
                        Til overnattinger
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
    name: 'accommodations-add-edit',
    props: ['auth', 'authenticated', 'accommodationId'],
    data () {
        return {
            accommodation: {
                name: null,
                address: null,
                phone: null,
                homePage: null,
                infoHtml: null
            }
        }
    },
    methods: {
        fetchAccommodation () {
            if (this.authenticated && this.accommodationId) {
                axios.get(`/api/accommodations/${this.accommodationId}`, {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(response => {
                    this.accommodation = response.data
                }).catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
            }
        },
        saveAccommodation () {
            if (this.authenticated) {
                this.$validator.validateAll()
                    .then((result) => {
                        if (result) {
                            const successCallback = () => {
                                this.accommodation = {
                                    name: null,
                                    address: null,
                                    phone: null,
                                    homePage: null,
                                    infoHtml: null
                                }
                                this.$nextTick(() => this.$validator.reset())
                                this.$snotify.success('Overnatting ble lagret')
                            }

                            const errorCallback = (error) => {
                                this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                                console.log(error)
                            }

                            if (this.accommodationId) {
                                axios.put('/api/accommodations', this.accommodation, {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .then(() => {
                                        router.replace('/accommodations')
                                    })
                                    .catch(errorCallback)
                            } else {
                                axios.post('/api/accommodations', this.accommodation, {
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
        this.fetchAccommodation()
    }
}
</script>
