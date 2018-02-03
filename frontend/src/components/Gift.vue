<template>
    <div class="box">
        <div class="columns">
            <div class="column has-text-centered">
                <span @click="checkboxClicked">
                    <i :class="giftIcon"></i>
                </span>
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
    props: ['gift'],
    methods: {
        isTaken () {
            return this.gift.checkable && this.gift.checked
        },
        checkboxClicked () {
            this.$emit('checkbokClicked')
        }
    },
    computed: {
        giftIcon: function () {
            if (this.isTaken()) {
                return 'fas fa-check fa-lg'
            } else if (this.gift.checkable) {
                return 'far fa-square fa-lg'
            } else {
                return 'fas fa-circle fa-lg'
            }
        },
        cssClass: function () {
            return this.isTaken() ? 'has-text-grey-light' : ''
        },
        textStyle: function () {
            return this.isTaken() ? 'text-decoration: line-through' : ''
        }
    }
}
</script>
