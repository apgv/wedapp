<template>
    <div>
        <h4 class="title is-4">Kontakt oss</h4>

        <div v-if="hasContacts"
             v-for="contact in contacts"
             :key="contact.id"
             class="box">
            <div class="columns">
                <div class="column">
                    <ul class="fa-ul">
                        <li>
                            <span class="fa-li"><i class="fas fa-user"></i></span>{{contact.name}}
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-phone"></i></span>{{contact.phone}}
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-at"></i></span>{{contact.email}}
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <info-message :show="!hasContacts"
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
        }
    },
    computed: {
        hasContacts: function () {
            return this.contacts.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
