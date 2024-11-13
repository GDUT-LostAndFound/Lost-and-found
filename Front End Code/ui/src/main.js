import Vue from 'vue'
import App from './App.vue'

//引入router
import router from './router'

//引入mock
import './mock/login.js'
import './mock/register.js'
import './mock/home.js'

//引入Element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);

//引入axios
import axios from 'axios'
Vue.prototype.$axios = axios

//关闭vue的生产提示
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
