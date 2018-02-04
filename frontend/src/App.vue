<template>
    <div id="app"
         class="container">
        <app-menu :auth="auth"
                  :authenticated="authenticated">
        </app-menu>

        <vue-snotify></vue-snotify>

        <router-view :auth="auth"
                     :authenticated="authenticated">
        </router-view>
    </div>
</template>

<script>
import AppMenu from './components/AppMenu'
import AuthService from './auth/AuthService'
import {eventBus} from './main'

const auth = new AuthService()
const {login, logout, authenticated} = auth

export default {
    components: {AppMenu},
    name: 'App',
    data () {
        eventBus.$on('authChange', authState => {
            this.authenticated = authState.authenticated
        })
        return {
            auth,
            authenticated
        }
    },
    methods: {
        login,
        logout
    }
}
</script>
