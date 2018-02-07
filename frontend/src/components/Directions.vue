<template>
    <div>
        <h4 class="title is-4">Veibeskrivelser</h4>

        <div v-if="authenticated"
             class="field">
            <router-link :to="'/directionsaddedit'"
                         class="button is-link">
                Legg til veibeskrivelse
            </router-link>
        </div>

        <div v-if="haveDirections"
             v-for="direction in directions"
             :key="direction.id"
             class="content">
            <h5 class="title is-5">{{direction.title}}</h5>
            <div v-html="direction.content"></div>
        </div>

        <info-message :show="!haveDirections"
                      :message="'Det er ikke lagt til noen veibeskrivelser.'">
        </info-message>
    </div>
</template>

<script>
import axios from 'axios'
import InfoMessage from './InfoMessage'

export default {
    components: {InfoMessage},
    name: 'directions',
    props: ['authenticated'],
    data () {
        return {
            directions: []
        }
    },
    methods: {
        fetch () {
            axios.get('/api/directions')
                .then(response => {
                    this.directions = response.data
                })
                .catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
        }
    },
    computed: {
        haveDirections: function () {
            return this.directions.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
