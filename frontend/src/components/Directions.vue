<template>
    <div>
        <h4 class="title is-4">Veibeskrivelser</h4>

        <div v-if="directions.length > 0"
             v-for="direction in directions"
             class="content">
            <h5 class="title is-5">{{direction.title}}</h5>
            <div v-html="direction.content"></div>
        </div>

        <article v-else
                 class="message is-link">
            <div class="message-header">
                <p>Info</p>
            </div>
            <div class="message-body">
                Det er ikke lagt til noen veibeskrivelser.
            </div>
        </article>
    </div>
</template>

<script>
import axios from 'axios'

export default {
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
    created () {
        this.fetch()
    }
}
</script>
