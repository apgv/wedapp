<template>
    <div>
        <h4 class="title is-4">Veibeskrivelser</h4>

        <div v-if="hasDirections"
             v-for="direction in directions"
             :key="direction.id"
             class="content">
            <h5 class="title is-5">{{direction.title}}</h5>
            <div v-html="direction.content"></div>
        </div>

        <info-message :show="!hasDirections"
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
        hasDirections: function () {
            return this.directions.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
