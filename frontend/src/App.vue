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
    name: 'App'
}
</script>
