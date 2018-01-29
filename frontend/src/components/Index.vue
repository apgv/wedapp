<template>
    <div>
        <div class="content" v-html="page.content"></div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
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
    created () {
        this.fetch()
    }
}
</script>
