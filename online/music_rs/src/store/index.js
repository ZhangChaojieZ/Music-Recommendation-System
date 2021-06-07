// 注册上面引入的各大模块
import { createStore } from "vuex"
export default createStore({
	// 共同维护的一个状态，state里面可以是很多个全局状态
	state: {
		// 登录用户id
		uid: "",
		// 登录用户，sessionStorage中存的是字符串，所以这里需要转化
		user: JSON.parse(window.sessionStorage.getItem("user")),
        // user: window.sessionStorage.getItem("user"),
		// 登录状态，false表示未登录
		isLogin: false,
		token: JSON.parse(window.sessionStorage.getItem("token")),
	},

	// 获取属性的状态，获取数据并渲染
	getters: {
		//获取登录状态
		isLogin: (state) => state.isLogin,
		// 返回用户信息
		loginUser: (state) => {
			if (!state.user) {
				console.log("state中user为空", state.user)
				return window.sessionStorage.getItem("user")
			}
		},
	},

	// 设置属性状态，处理数据的唯一途径，state的改变或赋值只能在这里
	mutations: {
		//保存登录状态
		userStatus(state, flag) {
			state.isLogin = flag
		},
		// 存用户
		SET_USER: (state, data) => {
			state.user = data
            // sessionStorage中存的是字符串，所以这里需要转化
			window.sessionStorage.setItem("user", JSON.stringify(data))
		},
		// 保存登录状态字
		SET_TOKEN: (state, data) => {
			state.token = data
			window.sessionStorage.setItem("token", JSON.stringify(data))
		},
	},

	// 应用mutations，数据的异步操作
	actions: {
		//获取登录状态
		// userLogin({ commit }, flag) {
		// 	commit("userStatus", flag)
		// },
	},
})

