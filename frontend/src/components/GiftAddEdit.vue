<template>
    <div>
        <div v-if="authenticated">
            <h4 v-if="giftId"
                class="title is-4">
                Endre gaveønske
            </h4>
            <h4 v-else
                class="title is-4">
                Legg til gaveønske
            </h4>

            <div class="field">
                <label class="label">Gaven skal kunne krysses av</label>
                <div class="control">
                    <label class="radio">
                        <input type="radio"
                               value="yes"
                               v-model="gift.checkable"
                               v-validate="'required'"
                               name="checkable"/>
                        Ja
                    </label>
                    <label class="radio">
                        <input type="radio"
                               value="no"
                               v-model="gift.checkable"
                               name="checkable"/>
                        Nei
                    </label>
                </div>
                <span v-show="errors.has('checkable')"
                      class="help is-danger">
                    {{errors.first('checkable')}}
                </span>
            </div>

            <div>
                <div class="field">
                    <label class="label">Tittel</label>
                    <p class="control has-icons-left">
                        <input v-model="gift.title"
                               v-validate="'required|max:80'"
                               name="title"
                               class="input"
                               placeholder="Tittel"/>
                        <span class="icon is-small is-left">
                            <i class="fas fa-font"></i>
                        </span>
                        <span v-show="errors.has('title')"
                              class="help is-danger">
                            {{errors.first('title')}}
                        </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Beskrivelse (frivillig)</label>
                    <p class="control has-icons-left">
                        <input v-model="gift.description"
                               v-validate="'max:445'"
                               name="description"
                               class="input"
                               placeholder="Beskrivelse"/>
                        <span class="icon is-small is-left">
                            <i class="fas fa-paragraph"></i>
                        </span>
                        <span v-show="errors.has('description')"
                              class="help is-danger">
                            {{errors.first('description')}}
                        </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Hjemmeside (frivillig)</label>
                    <div class="columns">
                        <div class="column">
                            <p class="control has-icons-left">
                                <input v-model="gift.link.url"
                                       v-validate="'url:require_protocol|max:255'"
                                       name="url"
                                       class="input"
                                       placeholder="http://example.com"/>
                                <span class="icon is-small is-left">
                                    <i class="fas fa-globe"></i>
                                </span>
                                <span v-show="errors.has('url')"
                                      class="help is-danger">
                                    {{errors.first('url')}}
                                </span>
                            </p>
                        </div>
                        <div class="column">
                            <p class="control has-icons-left">
                                <input v-model="gift.link.text"
                                       v-validate="'max:80'"
                                       name="linkText"
                                       class="input"
                                       placeholder="Lenketekst"/>
                                <span class="icon is-small is-left">
                                    <i class="fas fa-font"></i>
                                </span>
                                <span v-show="errors.has('linkText')"
                                      class="help is-danger">
                                    {{errors.first('linkText')}}
                                </span>
                            </p>
                        </div>
                        <div class="column">
                            <a v-if="gift.link.url"
                               :href="gift.link.url"
                               target="_blank">
                                {{linkText}}
                            </a>
                        </div>
                    </div>
                </div>

                <div>
                    <button @click="saveGift"
                            class="button is-link">
                        Lagre
                    </button>
                    <router-link :to="'/giftList'"
                                 class="button is-text">
                        Til gaveønsker
                    </router-link>
                </div>

            </div>
        </div>

        <not-authenticated :auth="auth"
                           :authenticated="authenticated">
        </not-authenticated>
    </div>
</template>

<script>
import NotAuthenticated from './NotAuthenticated'
import axios from 'axios'
import router from '../router'

export default {
    components: {NotAuthenticated},
    name: 'gift-add-edit',
    props: ['auth', 'authenticated', 'giftId'],
    data () {
        return {
            gift: {
                checkable: null,
                title: null,
                description: null,
                link: {
                    url: null,
                    text: null
                }
            }
        }
    },
    methods: {
        fetchGifts () {
            if (this.authenticated && this.giftId) {
                axios.get(`/api/gifts/${this.giftId}`, {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(response => {
                    const giftFrontend = Object.assign({}, response.data)
                    giftFrontend.checkable = giftFrontend.checkable ? 'yes' : 'no'
                    this.gift = giftFrontend
                }).catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
            }
        },
        saveGift () {
            function toGift (gift) {
                const changedGift = Object.assign({}, gift)
                changedGift.checkable = changedGift.checkable === 'yes'
                return changedGift
            }

            if (this.authenticated) {
                this.$validator.validateAll()
                    .then((result) => {
                        if (result) {
                            const successCallback = () => {
                                this.gift = {
                                    checkable: null,
                                    title: null,
                                    description: null,
                                    link: {
                                        url: null,
                                        text: null
                                    }
                                }
                                this.$nextTick(() => this.$validator.reset())
                                this.$snotify.success('Gaveønsket ble lagret')
                            }

                            const errorCallback = (error) => {
                                this.$snotify.error('Oops. Noe gikk galt. Vennligst prøv på nytt')
                                console.log(error)
                            }

                            if (this.giftId) {
                                axios.put('/api/gifts', toGift(this.gift), {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .then(() => {
                                        router.replace('/giftlist')
                                    })
                                    .catch(errorCallback)
                            } else {
                                axios.post('/api/gifts', toGift(this.gift), {
                                    headers: {'X-JWT': this.auth.jwt()}
                                }).then(successCallback)
                                    .catch(errorCallback)
                            }
                        }
                    })
            }
        }
    },
    computed: {
        linkText: function () {
            return this.gift.link.text ? this.gift.link.text : this.gift.link.url
        }
    },
    created () {
        this.fetchGift()
    }
}
</script>
