<template>
    <div>
        <h4 class="title is-4">Overnatting</h4>

        <div v-if="authenticated"
             class="field">
            <router-link :to="'/accommodationsaddedit'"
                         class="button is-link">
                Legg til overnatting
            </router-link>
        </div>

        <div v-if="haveAccommodations"
             v-for="accommodation in accommodations"
             :key="accommodation.id"
             class="box">
            <div class="columns">
                <div class="column is-two-fifths">
                    <div class="columns">
                        <div class="column">
                            <ul class="fa-ul">
                                <li>
                                    <span class="fa-li"><i class="fas fa-building"></i></span>&nbsp;{{accommodation.name}}
                                </li>
                                <li>
                                    <span class="fa-li"><i class="fas fa-map-marker"></i></span>&nbsp;{{accommodation.address}}
                                </li>
                                <li>
                                    <span class="fa-li"><i class="fas fa-phone"></i></span>&nbsp;{{accommodation.phone}}
                                </li>
                                <li>
                                    <span class="fa-li"><i class="fas fa-globe"></i></span>&nbsp;
                                    <a v-if="accommodation.homePage"
                                       :href="accommodation.homePage"
                                       target="_blank">{{accommodation.name}}</a>
                                </li>
                            </ul>
                        </div>
                        <div v-if="authenticated"
                             class="column">
                            <router-link :to="`/accommodationsaddedit/${accommodation.id}`"
                                         class="button is-text icon is-medium">
                                <i class="fas fa-edit"></i>
                            </router-link>
                        </div>
                    </div>
                </div>
                <div v-html="accommodation.infoHtml"
                     class="column content">
                </div>
            </div>
        </div>

        <info-message :show="!haveAccommodations"
                      :message="'Det er ikke lagt til noen overnattinger.'">
        </info-message>
    </div>
</template>

<script>
import axios from 'axios'
import InfoMessage from './InfoMessage'

export default {
    components: {InfoMessage},
    name: 'accommodations',
    props: ['authenticated'],
    data () {
        return {
            accommodations: []
        }
    },
    methods: {
        fetch () {
            axios.get('/api/accommodations')
                .then(response => {
                    this.accommodations = response.data
                })
                .catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
        }
    },
    computed: {
        haveAccommodations: function () {
            return this.accommodations.length > 0
        }
    },
    created () {
        this.fetch()
    }
}
</script>
