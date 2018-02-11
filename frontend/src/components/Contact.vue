<template>
    <div>
        <h4 class="title is-4">Kontakt oss</h4>

        <div v-if="authenticated"
             class="field">
            <router-link :to="'/contactaddedit'"
                         class="button is-link">
                Legg til kontakt
            </router-link>
        </div>

        <div v-if="haveContacts"
             v-for="contact in contacts"
             :key="contact.id"
             class="box">
            <div class="columns">
                <div class="column">
                    <ul class="fa-ul">
                        <li>
                            <span class="fa-li"><i class="fas fa-user"></i></span>&nbsp;{{contact.name}}
                        </li>
                        <li>
                            <div>
                                <span class="fa-li"><i class="fas fa-phone"></i></span>&nbsp;{{contact.phone}}
                            </div>
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-at"></i></span>&nbsp;{{contact.email}}
                        </li>
                    </ul>
                </div>
                <div v-if="authenticated"
                     class="column is-three-quarters">
                    <router-link :to="`/contactaddedit/${contact.id}`"
                                 class="button is-text icon is-medium">
                        <i class="fas fa-edit"></i>
                    </router-link>
                    <a @click.prevent="deleteContact(contact)"
                       class="button is-text icon is-medium">
                        <i class="fas fa-trash"></i>
                    </a>
                </div>
            </div>
        </div>

        <info-message :show="!haveContacts"
                      :message="'Det er ikke lagt til kontaktinformasjon.'">
        </info-message>
    </div>
</template>

<script>
import axios from 'axios'
import InfoMessage from './InfoMessage'

export default {
    components: {InfoMessage},
    name: 'contact',
    props: ['auth', 'authenticated'],
    data () {
        return {
            contacts: []
        }
    },
    methods: {
        fetch () {
            axios.get('/api/contacts')
                .then(response => {
                    this.contacts = response.data
                })
                .catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
        },
        deleteContact (contact) {
            if (this.authenticated) {
                axios.delete(`/api/contacts/${contact.id}`, {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(() => {
                    let index = this.contacts.indexOf(contact)

                    if (index > -1) {
                        this.$snotify.success('Kontakt ble fjernet')
                        this.contacts.splice(index, 1)
                    }
                }).catch(error => {
                    this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                    console.log(error)
                })
            }
        }
    },
    computed: {
        haveContacts: function () {
            return this.contacts.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
