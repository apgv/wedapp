<template>
    <div>
        <h4 class="title is-4">Ønskeliste</h4>

        <div v-if="authenticated"
             class="field">
            <router-link :to="'/giftaddedit'"
                         class="button is-link">
                Legg til gaveønske
            </router-link>
        </div>

        <gift v-for="gift in gifts"
              :key="gift.id"
              :authenticated="authenticated"
              :gift.sync="gift"
              v-on:checkbokClicked="saveGift(gift)">
        </gift>

        <info-message :show="!haveGifts"
                      :message="'Det er ikke lagt til noen gaveønsker.'">
        </info-message>
    </div>
</template>

<script>
import Gift from './Gift'
import axios from 'axios'
import InfoMessage from './InfoMessage'

export default {
    components: {InfoMessage, Gift},
    name: 'gift-list',
    props: ['auth', 'authenticated'],
    data () {
        return {
            gifts: []
        }
    },
    methods: {
        fetchGifts () {
            axios.get('/api/gifts')
                .then(response => {
                    this.gifts = response.data
                })
                .catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
        },
        saveGift (gift) {
            axios.put(`/api/gifts/${gift.id}/taken`, gift)
                .then(() => {
                    this.fetchGifts()
                    this.$snotify.success('Ditt valg er registrert')
                })
                .catch(error => {
                    this.fetchGifts()
                    this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                    console.log(error)
                })
        }
    },
    computed: {
        haveGifts: function () {
            return this.gifts.length > 0
        }
    },
    created () {
        this.fetchGifts()
    }
}
</script>
