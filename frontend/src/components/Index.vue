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
                    if (error === 'Network Error') {
                        console.log('Network Error')
                    } else {
                        console.log(error)
                        this.$snotify.error('Oops! Det oppstod en feil ved henting av data')
                    }
                })
        }
    },
    beforeCreate () {
        if (process.env.NODE_ENV === 'production' && location.protocol === 'http:') {
            window.location.reload(true)
        }
    },
    created () {
        this.fetch()
    }
}
</script>
