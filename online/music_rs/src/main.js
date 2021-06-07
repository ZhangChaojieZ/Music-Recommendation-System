import { createApp } from "vue"
import App from "./App.vue"
import axios from "axios"
import VueAxios from "vue-axios"
import router from "./router"
import store from "./store"

// 完整引入ElementUI和样式
import ElementPlus from "element-plus"
import "element-plus/lib/theme-chalk/index.css"
import installElementPlus from "./plugins/element.js"
// import './assets/index.css'
// 引入字体图标的css文件
import "./assets/iconfont/iconfont.css"

// 引入音乐播放器插件，使用这个页面会消失
// import APlayer from '@moefe/vue-aplayer';

// import VueCompositionApi from '@vue/composition-api'

const app = createApp(App)
installElementPlus(app)
app.use(VueAxios, axios)
app.use(ElementPlus)
// 注入路由
app.use(router)
// app.use(VueCompositionApi)
// vuex状态管理
app.use(store)

// 引入自定义的公共js方法
// app.config.globalProperties.$uitls = utils

app.config.productionTip = false
// 设置全局axios
app.config.globalProperties.$axios = axios
// 设置基本的默认路径
axios.defaults.baseURL = "/api"
// 这里挂载要放在use的下面才能正确渲染

// 挂在放在最后执行
app.mount("#app")
