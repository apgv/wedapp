<template>
    <div id="app"
         class="container">
        <app-menu></app-menu>

        <vue-snotify></vue-snotify>

        <router-view/>
    </div>
</template>

<script>
import AppMenu from './components/AppMenu'

export default {
    components: {AppMenu},
    name: 'App',
    beforeCreate: function () {
        if (process.env.NODE_ENV === 'production' && window.location.protocol === 'http:') {
            window.location.reload(true)
        }
    }
}
</script>
