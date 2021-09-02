import Vue from 'vue'
import './plugins/fontawesome'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueSession from 'vue-session'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
var sessionOptions = {
  persist: true,
}
Vue.use(VueSession, sessionOptions)
Vue.config.productionTip = false
new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app')
