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

        <footer class="footer">
            <div class="container">
                <div class="content has-text-centered">
                    <p>
                        <strong>wedapp</strong> by <a href="https://github.com/apgv">Andreas Pedro Garcia-Vedal</a>.
                        The source code is licensed <a href="http://opensource.org/licenses/mit-license.php">MIT</a>.
                    </p>
                </div>
            </div>
        </footer>

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
