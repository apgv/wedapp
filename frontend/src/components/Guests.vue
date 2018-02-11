<template>
    <div>
        <div v-if="authenticated">

            <h4 class="title is-4">Svar mottatt på invitasjonen</h4>

            <div class="columns">
                <div class="column">
                    <div class="control has-icons-left">
                        <input v-model="filterKey"
                               class="input"
                               placeholder="Søk"/>
                        <span class="icon is-left">
                            <i class="fas fa-search"></i>
                        </span>
                    </div>
                </div>
                <div class="column">
                    <div class="field is-horizontal">
                        <div class="control">
                            <label class="checkbox">
                                <input type="checkbox"
                                       v-model="attendingFilter">
                                Kommer
                            </label>
                            <label class="checkbox">
                                <input type="checkbox"
                                       v-model="notAttendingFilter">
                                Kommer ikke
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="is-pulled-right">
                Viser {{filteredGuests.length}} av {{guests.length}}
            </div>
            <table class="table is-fullwidth is-striped is-hoverable">
                <thead>
                <tr>
                    <th>Navn</th>
                    <th>Kommer</th>
                    <th>Annet</th>
                    <th>Opprettet</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="guest in filteredGuests"
                    :key="guest.id">
                    <td>{{guest.fullName}}</td>
                    <td>{{guest.attending ? 'Ja' : 'Nei'}}</td>
                    <td>{{guest.note}}</td>
                    <td>{{guest.createdDate | formatDate}}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <not-authenticated :auth="auth"
                           :authenticated="authenticated">
        </not-authenticated>
    </div>
</template>

<script>
import NotAuthenticated from './NotAuthenticated'
import axios from 'axios'

export default {
    components: {NotAuthenticated},
    name: 'guests',
    props: ['auth', 'authenticated'],
    data () {
        return {
            guests: [],
            filterKey: '',
            attendingFilter: false,
            notAttendingFilter: false
        }
    },
    methods: {
        fetchGuests () {
            if (this.authenticated) {
                axios.get('/api/guests', {
                    headers: {'X-JWT': this.auth.jwt()}
                }).then(response => {
                    this.guests = response.data
                }).catch(error => {
                    this.$snotify.error('Feil ved henting av data')
                    console.log(error)
                })
            }
        }
    },
    computed: {
        filteredGuests: function () {
            let data = this.guests
            let filterKey = this.filterKey && this.filterKey.toLowerCase()

            if (filterKey) {
                data = data.filter(guest => {
                    return guest.fullName.toLowerCase().indexOf(filterKey) > -1 ||
                        (guest.note && guest.note.toLowerCase().indexOf(filterKey) > -1)
                })
            }

            if (this.attendingFilter) {
                data = data.filter(guest => {
                    return guest.attending
                })
            }

            if (this.notAttendingFilter) {
                data = data.filter(guest => {
                    return !guest.attending
                })
            }

            return data
        }
    },
    created () {
        this.fetchGuests()
    }
}
</script>
