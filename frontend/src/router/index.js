import Vue from 'vue'
import Router from 'vue-router'
import VeeValidate, {Validator} from 'vee-validate'
import nbNO from 'vee-validate/dist/locale/nb_NO'
import HelloWorld from '@/components/HelloWorld'
import GuestRegistration from '@/components/GuestRegistration'

Vue.use(Router)
Vue.use(VeeValidate)

Validator.localize('nb_NO', nbNO)

export default new Router({
    routes: [
        {
            path: '/helloworld',
            name: 'HelloWorld',
            component: HelloWorld
        }, {
            path: '/',
            name: 'GuestRegistration',
            component: GuestRegistration
        }
    ]
})
