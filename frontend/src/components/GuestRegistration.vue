<template>
    <div>
        <h4 class="title is-4">Svar på invitasjonen</h4>

        <article class="message is-info">
            <div class="message-body">
                Vennligst fyll ut påkrevde felter merket med (*)
            </div>
        </article>

        <div class="columns">
            <div class="column">
                <div class="field">
                    <label class="label">Fornavn *</label>
                    <p class="control has-icons-left">
                        <input v-model="guest.firstName"
                               v-validate="'required|max:20'"
                               name="firstname"
                               class="input"
                               placeholder="Fornavn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('firstname')"
                              class="help is-danger">
                        {{errors.first('firstname')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Etternavn *</label>
                    <p class="control has-icons-left">
                        <input v-model="guest.lastName"
                               v-validate="'required|max:20'"
                               name="lastname"
                               class="input"
                               placeholder="Etternavn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('lastname')"
                              class="help is-danger">
                        {{errors.first('lastname')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">E-post</label>
                    <p class="control has-icons-left">
                        <input v-model="guest.email"
                               v-validate="'email|max:40'"
                               name="email"
                               class="input"
                               type="email"
                               placeholder="E-post"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-envelope"></i>
                    </span>
                        <span v-show="errors.has('email')"
                              class="help is-danger">
                        {{errors.first('email')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Kommer *</label>
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
                    <label class="label">Annet</label>
                    <p class="control has-icons-left">
                        <textarea v-model="guest.extraInfo"
                                  name="otherInfo"
                                  class="textarea"
                                  placeholder="Eventuelle matallergier eller annet"></textarea>
                    </p>
                </div>
            </div>

            <div class="column">
                <div class="field">
                    <label class="label">Fornavn (*)</label>
                    <p class="control has-icons-left">
                        <input v-model="guest2.firstName"
                               v-validate="'required|max:20'"
                               name="firstname"
                               class="input"
                               placeholder="Fornavn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('firstname')"
                              class="help is-danger">
                        {{errors.first('firstname')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Etternavn (*)</label>
                    <p class="control has-icons-left">
                        <input v-model="guest2.lastName"
                               v-validate="'required|max:20'"
                               name="lastname"
                               class="input"
                               placeholder="Etternavn"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-user"></i>
                    </span>
                        <span v-show="errors.has('lastname')"
                              class="help is-danger">
                        {{errors.first('lastname')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">E-post</label>
                    <p class="control has-icons-left">
                        <input v-model="guest2.email"
                               v-validate="'email|max:40'"
                               name="email"
                               class="input"
                               type="email"
                               placeholder="E-post"/>
                        <span class="icon is-small is-left">
                        <i class="fa fa-envelope"></i>
                    </span>
                        <span v-show="errors.has('email')"
                              class="help is-danger">
                        {{errors.first('email')}}
                    </span>
                    </p>
                </div>

                <div class="field">
                    <label class="label">Kommer (*)</label>
                    <div class="control">
                        <label class="radio">
                            <input type="radio"
                                   value="yes"
                                   v-model="guest2.attending"
                                   v-validate="'required'"
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
                    <span v-show="errors.has('attending')"
                          class="help is-danger">
                        {{errors.first('attending')}}
                    </span>
                </div>

                <div class="field">
                    <label class="label">Annet</label>
                    <p class="control has-icons-left">
                        <textarea v-model="guest2.extraInfo"
                                  name="otherInfo"
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
            <button @click="clearForm()"
                    class="button is-text">
                Avbryt
            </button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'guest-registration',
    data () {
        return {
            guest: {
                firstName: null,
                lastName: null,
                email: null,
                attending: null,
                extraInfo: null
            },
            guest2: {
                firstName: null,
                lastName: null,
                email: null,
                attending: null,
                extraInfo: null
            }
        }
    },
    methods: {
        save () {
            console.log('save()')
            this.$validator.validateAll()
        },
        clearForm () {
            console.log('clearForm()')
        }
    }
}
</script>
