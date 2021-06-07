// import Vue from "vue";
import { createWebHistory, createRouter } from "vue-router"
import admin from "../components/admin/Admin.vue"
import adminMenu from "../components/admin/AdminMenu.vue"
import login from "../components/user/Login.vue"
// import test from "../Test.vue"
import main from "../components/Main.vue"
import recommendsongs from "../views/RecommendSongs.vue"
import recommendusers from "../views/RecommendUsers.vue"
import discover from "../views/Discover.vue"
import hotmusic from "../views/HotMusic.vue"
import searchMusic from "../views/SearchMusic.vue"
import loveSong from "../views/love-song.vue"
import userRecord from "../views/user-record.vue"
import store from "../store/index.js"

const routes = [
	// {
	// 	path: "/",
	// 	// 重定向
	// 	redirect: "/login",
	// },
	{
		path: "/login",
		name: "login",
		component: login,
	},

	{
		path: "/admin",
		name: "admin",
		component: admin,
	},
	{
		path: "/adminMenu",
		name: "adminMenu",
		component: adminMenu,
	},
	{
		path: "/",
		name: "main",
		component: main,
		redirect: "/discover",
		meta: {
			requireLogin: true,
		},
		// 子路由
		children: [
			{
				path: "recommendsongs",
				name: "recommendsongs",
				// component: (resolve) => require([recommendsongs], resolve),
				component: recommendsongs,
				meta: {
					requireLogin: true,
				},
			},
			{
				path: "recommendusers",
				name: "recommendusers",
				component: recommendusers,
				meta: {
					requireLogin: true,
				},
			},
			{
				path: "discover",
				name: "discover",
				component: discover,
				meta: {
					requireLogin: true,
				},
			},
			{
				path: "hotmusic",
				name: "hotmusic",
				component: hotmusic,
				meta: {
					requireLogin: true,
				},
			},
			{
				path: "searchMusic",
				name: "searchMusic",
				component: searchMusic,
			},
            {
                path: "loveSong",
                name: "loveSong",
                component: loveSong,
            },
            {
                path: "userRecord",
                name: "userRecord",
                component: userRecord,
            },
		],
	},
    // {
    //     path: "/userRecord",
    //     name: "userRecord",
    //     component: userRecord,
    // },
]

const router = createRouter({
	// 4. Provide the history implementation to use. We are using the hash history for simplicity here.
	history: createWebHistory(),
	routes, // short for `routes: routes`
})

// 登录验证
router.beforeEach((to, from, next) => {
	// 判断是否需要登录
	if (to.meta.requireLogin) {
		// 如果由token说明已经登录，则跳转到首页
        console.log("路由跳转验证")
		// console.log(JSON.parse(window.sessionStorage.getItem("user")))
        console.log(store.state)
        // console.log(window.sessionStorage.getItem("user"))
		if (store.state.user || to.path === "/login") {
			next()
		} else {
			// 如果没有登录，则所有页面都需要跳转到登录界面
			next(`/login?redirect=${to.path}`)
		}
	} else {
		next()
	}
})

export default router
