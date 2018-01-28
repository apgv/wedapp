import Vue from 'vue'
import Router from 'vue-router'
import VeeValidate, {Validator} from 'vee-validate'
import nbNO from 'vee-validate/dist/locale/nb_NO'
import Snotify, {SnotifyPosition} from 'vue-snotify'
import Index from '@/components/Index'
import GuestRegistration from '@/components/GuestRegistration'
import GiftList from '@/components/GiftList'
import Directions from '@/components/Directions'
import Accommodations from '@/components/Accommodations'
import Contact from '@/components/Contact'

Vue.use(Router)
Vue.use(VeeValidate)
Vue.use(Snotify, {
    toast: {
        position: SnotifyPosition.centerTop
    }
})

Validator.localize('nb_NO', nbNO)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index
        }, {
            path: '/guestregistration',
            name: 'GuestRegistration',
            component: GuestRegistration
        },
        {
            path: '/giftlist',
            name: 'GiftList',
            component: GiftList
        },
        {
            path: '/directions',
            name: 'Directions',
            component: Directions
        },
        {
            path: '/accommodations',
            name: 'Accommodations',
            component: Accommodations
        },
        {
            path: '/contact',
            name: 'Contact',
            component: Contact
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
})
