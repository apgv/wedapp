<template>
    <div>
        <h4 class="title is-4">Ønskeliste</h4>

        <gift v-for="gift in gifts"
              :key="gift.id"
              :gift="gift"
              v-on:checkbokClicked="save(gift)">
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
    components: {
        InfoMessage,
        Gift
    },
    name: 'gift-list',
    data () {
        return {
            gifts: []
        }
    },
    methods: {
        fetch () {
            axios.get('/api/gifts')
                .then(response => {
                    this.gifts = response.data
                })
                .catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
        },
        save (gift) {
            console.log('save()')
        }
    },
    computed: {
        haveGifts: function () {
            return this.gifts.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
