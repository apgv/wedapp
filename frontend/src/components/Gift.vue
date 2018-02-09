<template>
    <div class="box">
        <div class="columns">
            <div class="column has-text-centered">
                <input type="checkbox"
                       v-if="!authenticated && gift.checkable"
                       v-model="gift.checked"
                       @click="checkboxClicked"/>

                <router-link v-if="authenticated"
                             :to="`/giftaddedit/${gift.id}`"
                             class="button is-text icon is-medium">
                    <i class="fas fa-edit"></i>
                </router-link>
            </div>
            <div class="column is-2"
                 :class="cssClass"
                 :style="textStyle">
                {{gift.title}}
            </div>
            <div class="column is-4"
                 :class="cssClass"
                 :style="textStyle">
                {{gift.description}}
            </div>
            <div class="column is-5">
                <a v-if="gift.link"
                   :href="gift.link.url"
                   :class="cssClass"
                   :style="textStyle"
                   target="_blank">
                    {{gift.link.text}}
                </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'gift',
    props: ['gift', 'authenticated'],
    methods: {
        isTaken () {
            return this.gift.checkable && this.gift.checked
        },
        checkboxClicked () {
            this.$emit('checkbokClicked')
        }
    },
    computed: {
        cssClass: function () {
            return this.isTaken() ? 'has-text-grey-light' : ''
        },
        textStyle: function () {
            return this.isTaken() ? 'text-decoration: line-through' : ''
        }
    }
}
</script>
