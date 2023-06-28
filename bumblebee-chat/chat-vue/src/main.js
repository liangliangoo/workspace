import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import api from './apis/index';
import common from './common/index';
import Video from 'video.js'
import 'video.js/dist/video-js.css'


// 引入全局重置样式
import 'assets/css/reset.css'

Vue.use(ElementUI);
Vue.use(api);
Vue.use(common);
Vue.config.productionTip = false;
Vue.prototype.$video = Video;

new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
}).$mount('#app')