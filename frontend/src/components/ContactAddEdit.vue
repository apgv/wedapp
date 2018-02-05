<template>
    <div>
        <div v-if="authenticated">
            <h4 v-if="contactId"
                class="title is-4">
                Endre kontakt
            </h4>
            <h4 v-else
                class="title is-4">
                Legg til kontakt
            </h4>

            <div>
                <div class="field">
                    <label class="label">Navn</label>
                    <p class="control has-icons-left">
                        <input v-model="contact.name"
                               v-validate="'required|max:54'"
                               name="name"
                               class="input"
                               placeholder="Navn"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-user"></i>
                    </span>
                        <span v-show="errors.has('name')"
                              class="help is-danger">
                        {{errors.first('name')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Telefon</label>
                    <p class="control has-icons-left">
                        <input v-model="contact.phone"
                               v-validate="'digits:8'"
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
                    <label class="label">E-post</label>
                    <p class="control has-icons-left">
                        <input v-model="contact.email"
                               v-validate="'email|max:40'"
                               name="email"
                               class="input"
                               type="email"
                               placeholder="E-post"/>
                        <span class="icon is-small is-left">
                        <i class="fas fa-at"></i>
                    </span>
                        <span v-show="errors.has('email')"
                              class="help is-danger">
                        {{errors.first('email')}}
                    </span>
                    </p>
                </div>

                <div>
                    <button @click="saveContact"
                            class="button is-link">
                        Lagre
                    </button>
                    <router-link :to="'/contact'"
                                 class="button is-text">
                        Til kontakter
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
    name: 'contact-add-edit',
    props: ['auth', 'authenticated', 'contactId'],
    data () {
        return {
            contact: {
                name: null,
                phone: null,
                email: null
            }
        }
    },
    methods: {
        fetchContact () {
            if (this.authenticated && this.contactId) {
                axios.get(`/api/contacts/${this.contactId}`, {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(response => {
                    this.contact = response.data
                }).catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
            }
        },
        saveContact () {
            if (this.authenticated) {
                this.$validator.validateAll()
                    .then((result) => {
                        if (result) {
                            const successCallback = () => {
                                this.contact = {
                                    name: null,
                                    phone: null,
                                    email: null
                                }
                                this.$nextTick(() => this.$validator.reset())
                                this.$snotify.success('Kontakt ble lagret')
                            }

                            const errorCallback = (error) => {
                                this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                                console.log(error)
                            }

                            if (this.contactId) {
                                axios.put('/api/contacts', this.contact, {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .then(() => {
                                        router.replace('/contact')
                                    })
                                    .catch(errorCallback)
                            } else {
                                axios.post('/api/contacts', this.contact, {
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
        this.fetchContact()
    }
}
</script>
