<template>
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div v-if="showNavBurger">
            <div class="navbar-brand">
                <div class="navbar-burger"
                     v-bind:class="{'is-active': showMobileMenu}"
                     @click="toggleMobileMenu()">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div class="navbar-menu"
                 v-bind:class="{'is-active': showMobileMenu}">
                <div class="navbar-start">
                    <div class="navbar-item">
                        <div class="navbar-dropdown">
                            <router-link :to="'/index'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Forside
                            </router-link>
                            <router-link :to="'/guestregistration'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Svar på invitasjonen
                            </router-link>
                            <router-link :to="'/giftlist'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Ønskeliste
                            </router-link>
                            <router-link :to="'/directions'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Veibeskrivelser
                            </router-link>
                            <router-link :to="'/accommodations'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Overnatting
                            </router-link>
                            <router-link :to="'/contact'"
                                         @click.native="toggleMobileMenu()"
                                         class="navbar-item">
                                Kom i kontakt med oss
                            </router-link>
                            <a v-if="!authenticated"
                               @click="login()"
                               class="navbar-item">
                                Logg inn
                            </a>
                            <a v-if="authenticated"
                               @click="logout()"
                               class="navbar-item">
                                Logg ut
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="navbar-menu">
            <div class="navbar-start">
                <router-link :to="'/index'"
                             class="navbar-item">
                    Forside
                </router-link>
                <router-link :to="'/guestregistration'"
                             class="navbar-item">
                    Svar på invitasjonen
                </router-link>
                <router-link :to="'/giftlist'"
                             class="navbar-item">
                    Ønskeliste
                </router-link>
                <router-link :to="'/directions'"
                             class="navbar-item">
                    Veibeskrivelser
                </router-link>
                <router-link :to="'/accommodations'"
                             class="navbar-item">
                    Overnatting
                </router-link>
                <router-link :to="'/contact'"
                             class="navbar-item">
                    Kom i kontakt med oss
                </router-link>
            </div>
            <div class="navbar-end">
                <div v-if="authenticated" class="navbar-item">
                    {{auth.subject.firstName}} ({{auth.subject.email}})
                </div>
                <div class="navbar-item">
                    <a v-if="!authenticated"
                       @click="login()"
                       class="button is-link">
                        Logg inn
                    </a>
                    <a v-if="authenticated"
                       @click="logout()"
                       class="button is-link">
                        Logg ut
                    </a>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    name: 'app-menu',
    data () {
        return {
            showMobileMenu: false
        }
    },
    methods: {
        toggleMobileMenu () {
            this.showMobileMenu = !this.showMobileMenu
        },
        login () {
            this.auth.login()
        },
        logout () {
            this.auth.logout()
            this.toggleMobileMenu()
        }
    },
    computed: {
        showNavBurger: function () {
            // navbar-menu is hidden on touch devices < 1024px
            return document.documentElement.clientWidth < 1024
        }
    }
}
</script>
