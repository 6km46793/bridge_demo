import Vue from 'vue'
import App from './App.vue'
import bridge from './utils/bridge'

Vue.config.productionTip = false

Vue.prototype.$bridge = bridge

new Vue({
  render: h => h(App),
}).$mount('#app')
