import Vue from 'vue'
import Router from 'vue-router'
import VeeValidate, {Validator} from 'vee-validate'
import nbNO from 'vee-validate/dist/locale/nb_NO'
import Snotify, {SnotifyPosition} from 'vue-snotify'
import Index from '@/components/Index'
import IndexEditor from '@/components/IndexEditor'
import GuestRegistration from '@/components/GuestRegistration'
import Guests from '@/components/Guests'
import GiftList from '@/components/GiftList'
import GiftAddEdit from '@/components/GiftAddEdit'
import Directions from '@/components/Directions'
import DirectionsAddEdit from '@/components/DirectionsAddEdit'
import Accommodations from '@/components/Accommodations'
import AccommodationsAddEdit from '@/components/AccommodationsAddEdit'
import Contact from '@/components/Contact'
import ContactAddEdit from '@/components/ContactAddEdit'
import ExternalRouting from '@/components/ExternalRouting'
import Auth0Callback from '@/components/Auth0Callback'
import ReAuthenticate from '@/components/ReAuthenticate'
import moment from 'moment'

Vue.use(Router)
Vue.use(VeeValidate)
Vue.use(Snotify, {
    toast: {
        position: SnotifyPosition.centerTop
    }
})

Validator.localize('nb_NO', nbNO)
moment.locale('nb')

Vue.filter('formatDate', (value) => {
    if (value) {
        return moment(value).format('L')
    }
})

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/index',
            name: 'Index',
            component: Index
        },
        {
            path: '/indexeditor',
            name: 'IndexEditor',
            component: IndexEditor
        },
        {
            path: '/guestregistration',
            name: 'GuestRegistration',
            component: GuestRegistration
        },
        {
            path: '/guests',
            name: 'Guests',
            component: Guests
        },
        {
            path: '/giftlist',
            name: 'GiftList',
            component: GiftList
        },
        {
            path: '/giftaddedit/:giftId?',
            name: 'GiftAddEdit',
            component: GiftAddEdit,
            props: true
        },
        {
            path: '/directions',
            name: 'Directions',
            component: Directions
        },
        {
            path: '/directionsaddedit/:directionId?',
            name: 'DirectionsAddEdit',
            component: DirectionsAddEdit,
            props: true
        },
        {
            path: '/accommodations',
            name: 'Accommodations',
            component: Accommodations
        },
        {
            path: '/accommodationsaddedit/:accommodationId?',
            name: 'AccommodationsAddEdit',
            component: AccommodationsAddEdit,
            props: true
        },
        {
            path: '/contact',
            name: 'Contact',
            component: Contact
        },
        {
            path: '/contactaddedit/:contactId?',
            name: 'ContactAddEdit',
            component: ContactAddEdit,
            props: true
        },
        {
            path: '/externalrouting',
            name: 'ExternalRouting',
            component: ExternalRouting
        },
        {
            path: '/auth0callback',
            name: 'Auth0Callback',
            component: Auth0Callback
        },
        {
            path: '/reauthenticate',
            name: 'ReAuthenticate',
            component: ReAuthenticate
        },
        {
            path: '/',
            redirect: '/externalrouting'
        },
        {
            path: '*',
            redirect: '/externalrouting'
        }
    ]
})
