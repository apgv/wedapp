<template>
    <div>
        <div v-if="hasPageContent"
             v-html="page.content"
             class="content">
        </div>

        <info-message :show="!hasPageContent"
                      :message="'Det er ikke lagt til innhold.'">
        </info-message>
    </div>
</template>

<script>
import axios from 'axios'
import InfoMessage from './InfoMessage'

export default {
    components: {InfoMessage},
    name: 'index',
    data () {
        return {
            page: {
                content: null
            }
        }
    },
    methods: {
        fetch () {
            axios.get('/api/indexpage')
                .then(response => {
                    this.page = response.data
                })
                .catch(error => {
                    this.$snotify.error('Oops! Det oppstod en feil ved henting av data')
                    console.log(error)
                })
        }
    },
    computed: {
        hasPageContent: function () {
            return this.page.content
        }
    },
    created () {
        this.fetch()
    }
}
</script>
