<template>
    <div>
        <h4 class="title is-4">Svar på invitasjonen</h4>

        <div class="columns">
            <div class="column">
                <div class="field">
                    <label class="label">Fullt navn</label>
                    <p class="control has-icons-left">
                        <input v-model="guest.fullName"
                               v-validate="'required|max:54'"
                               name="fullname"
                               class="input"
                               placeholder="Fullt navn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('fullname')"
                              class="help is-danger">
                        {{errors.first('fullname')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Kommer</label>
                    <div class="control">
                        <label class="radio">
                            <input type="radio"
                                   value="yes"
                                   v-model="guest.attending"
                                   v-validate="'required'"
                                   name="attending"/>
                            Ja
                        </label>
                        <label class="radio">
                            <input type="radio"
                                   value="no"
                                   v-model="guest.attending"
                                   name="attending"/>
                            Nei
                        </label>
                    </div>
                    <span v-show="errors.has('attending')"
                          class="help is-danger">
                        {{errors.first('attending')}}
                    </span>
                </div>

                <div class="field">
                    <label class="label">Annet (frivillig)</label>
                    <p class="control has-icons-left">
                        <textarea v-model="guest.note"
                                  v-validate="'max:445'"
                                  name="note"
                                  class="textarea"
                                  placeholder="Eventuelle matallergier eller annet"></textarea>
                    </p>
                </div>
            </div>

            <div class="column">
                <div class="field">
                    <label class="label">Fullt navn</label>
                    <p class="control has-icons-left">
                        <input v-model="guest2.fullName"
                               v-validate="'max:54'"
                               name="fullname2"
                               class="input"
                               placeholder="Fullt navn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('fullname2')"
                              class="help is-danger">
                        {{errors.first('fullname2')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Kommer</label>
                    <div class="control">
                        <label class="radio">
                            <input type="radio"
                                   value="yes"
                                   v-model="guest2.attending"
                                   v-validate="requireAttendingValidation"
                                   name="attending2"/>
                            Ja
                        </label>
                        <label class="radio">
                            <input type="radio"
                                   value="no"
                                   v-model="guest2.attending"
                                   name="attending2"/>
                            Nei
                        </label>
                    </div>
                    <span v-show="errors.has('attending2')"
                          class="help is-danger">
                        {{errors.first('attending2')}}
                    </span>
                </div>

                <div class="field">
                    <label class="label">Annet (frivillig)</label>
                    <p class="control has-icons-left">
                        <textarea v-model="guest2.note"
                                  v-validate="'max:445'"
                                  name="note2"
                                  class="textarea"
                                  placeholder="Eventuelle matallergier eller annet"></textarea>
                    </p>
                </div>
            </div>

            <div class="column"></div>
        </div>

        <div>
            <button @click="save()"
                    class="button is-success">
                Registrer svar
            </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'guest-registration',
    data () {
        return {
            guest: {
                fullName: null,
                attending: null,
                note: null
            },
            guest2: {
                fullName: null,
                attending: null,
                note: null
            }
        }
    },
    methods: {
        save () {
            function guestList (guests) {
                let list = []
                guests.map(it => {
                    if (it.fullName) {
                        list.push({
                            fullName: it.fullName,
                            attending: it.attending === 'yes',
                            note: it.note
                        })
                    }
                })

                return list
            }

            this.$validator.validateAll().then((result) => {
                if (result) {
                    axios.post('api/guests', guestList([this.guest, this.guest2]))
                        .then(() => {
                            this.guest = {
                                fullName: null,
                                attending: null,
                                note: null
                            }
                            this.guest2 = {
                                fullName: null,
                                attending: null,
                                note: null
                            }
                            this.$nextTick(() => this.$validator.reset())
                            this.$snotify.success('Ditt svar er registrert')
                        })
                        .catch(error => {
                            this.$snotify.error('Oops! Det oppstod en feil. Vennligst prøv på nytt')
                            console.log(error)
                        })
                }
            })
        }
    },
    computed: {
        requireAttendingValidation: function () {
            return this.guest2.fullName ? 'required' : ''
        }
    }
}
</script>
