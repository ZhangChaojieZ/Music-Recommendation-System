<template>
	<div id="user-detail">
		<!-- 用户信息 -->
		<div id="user">
			<div class="user-img">
				<img src="../assets/imgs/user-image.jpg" alt="加载失败" />
			</div>
			<div class="user-info">
				<div class="user-name-gender">
					<span class="name">{{ name }}</span>
					<!-- <span class="gender">测试性别{{ gender }}</span> -->
					<i
						:class="[
							'gender',
							'iconfont',
							gender == '男'
								? 'icon-xingbie-nan'
								: 'icon-xingbie-nv',
						]"
					></i>
				</div>
				<div class="user-area">
					<span class="area">所在区域：</span>
					<span>{{ area }}</span>
				</div>
				<div class="user-des">
					<span class="des">个人简介：</span>
					<span>{{ des }}</span>
				</div>
			</div>
		</div>
		<!-- 间隔 -->
		<div class="record-interval">
			<span>
				播放记录
			</span>
		</div>
		<!-- 用户歌曲播放记录 -->
		<div id="user-record">
			<el-table
				:data="songsData"
				stripe
				style="width: 100%;"
				:header-cell-style="{ fontWeight: 'normal', border: 'none' }"
			>
				<!-- :header-cell-style="{ fontWeight: 'normal' }"设置表头样式，中间的连接符去掉，改成驼峰形式 -->
				<el-table-column
					align="center"
					prop="index"
					label=""
					width="120"
				>
					<template #default="scope">
						<div
							:class="
								scope.row.downUrl == 'None' ? 'fail' : 'succ'
							"
						>
							<sapn
								style="font-size:16px;width:20px;display:inline-block;"
							>
								{{ scope.$index + 1 }}
							</sapn>
							<i
								class="iconfont icon-bofang"
								style="margin-left:10px;font-size:18px;width:20px;display:inline-block;"
								@click="startPlay(scope.$index)"
								:class="
									scope.row.downUrl == 'None'
										? 'fail'
										: 'succ'
								"
							></i>
							<i
								class="iconfont icon-shoucang_huaban1"
								style="margin-left:10px;font-size:18px;width:20px;display:inline-block;"
								@click="deleteLove(scope.$index)"
								:class="[
									scope.row.downUrl == 'None'
										? 'fail'
										: 'succ',
									scope.row.isLove === true ? 'active' : '',
								]"
							></i>
						</div>
					</template>
				</el-table-column>
				<el-table-column
					align="center"
					prop="songName"
					label="歌曲名"
					width="220"
				>
					<!-- 在表格中添加跳转链接 -->
					<template #default="scope">
						<!-- <el-link underline="False" type="primary" to="/"> -->
						<span
							:class="
								scope.row.downUrl == 'None' ? 'fail' : 'succ'
							"
						>
							{{ scope.row.songName }}
						</span>
						<!-- </el-link> -->
					</template>
				</el-table-column>
				<el-table-column
					align="center"
					prop="singerName"
					label="歌手"
					width="220"
				>
					<template #default="scope">
						<!-- <el-link underline="False" type="primary" to="/"> -->
						<span
							:class="
								scope.row.downUrl == 'None' ? 'fail' : 'succ'
							"
						>
							{{ scope.row.singerName }}
						</span>
						<!-- </el-link> -->
					</template>
				</el-table-column>
				<el-table-column
					align="center"
					prop="album"
					label="专辑"
					width="300"
				>
					<template #default="scope">
						<span
							:class="
								scope.row.downUrl == 'None' ? 'fail' : 'succ'
							"
						>
							{{ scope.row.album }}
						</span>
					</template>
				</el-table-column>
				<el-table-column
					align="center"
					prop="songTime"
					label="时长"
					width="200"
				>
					<template #default="scope">
						<span
							:class="
								scope.row.downUrl == 'None' ? 'fail' : 'succ'
							"
						>
							{{ scope.row.songTime }}
						</span>
					</template>
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
import {
	_getRecordSong,
	_addLoveSong,
	_cancelLoveSong,
} from "../network/request.js"
// import { useStore } from "vuex"
export default {
	name: "userRecord",
	data() {
		const item = {
			iid: "",
			songName: "",
			singerName: "",
			album: "",
			songTime: "",
			downUrl: "",
			picUrl: "",
			// 收藏列表默认为收藏
			isLove: false,
		}
		return {
			uid: "",
			// 相似用户的姓名
			name: "",
			// 相似用户的性别
			gender: "男",
			// 相似用户所在区域
			area: "",
			// 用户个人简介
			des: "",
			downUrl: "",
			songsData: Array(10).fill(item),
		}
	},
	created() {
		// 获得相似用户信息
		console.log("传入的参数：" + this.$route.params)
		this.name = this.$route.params.name
		this.gender = this.$route.params.gender
		// this.gender = "男"
		this.area = this.$route.params.area
        this.des = this.$route.params.des
		// 获得用户id
		// this.uid = useStore().state.user.uid
		console.log("相似用户id：" + this.$route.params.uid)
		console.log("相似用户姓名：" + this.name)
		// console.log(this.currentPage)
		// 得到歌曲的详情信息
		_getRecordSong({ uid: this.$route.params.uid }).then((response) => {
			this.songsData = response.data.data
			console.log(this.songsData)
		})
		// 页面加载时读取数据
		if (window.sessionStorage.getItem("user")) {
			console.log("页面加载")
			const session_user = JSON.parse(
				window.sessionStorage.getItem("user"),
			)
			console.log(session_user)
			this.$store.commit("SET_USER", session_user)
		}
		// 页面刷新时保存状态
		window.addEventListener("beforeunload", () => {
			console.log("页面刷新")
			console.log(this.$store.state.user)
			window.sessionStorage.setItem(
				"user",
				JSON.stringify(this.$store.state.user),
			)
		})
	},
	methods: {
		startPlay(index) {
			console.log(index)
			var song = this.songsData[parseInt(index)]
			console.log(song)
			this.downUrl = song.downUrl
			console.log(this.downUrl)
			console.log(song.picUrl)
			var iid = song.iid
			var picUrl = song.picUrl
			var songName = song.songName
			var singerName = song.singerName
			var mp3Url = this.downUrl
			// 将事件传递给子组件，第一个参数为自定义事件名，后续参数为传递过去的数据
			console.log("开始emit")
			this.$emit("playmusic", iid, mp3Url, picUrl, songName, singerName)
			console.log("结束emit")
		},
		// 取消收藏歌曲
		deleteLove(index) {
			var isLove = this.songsData[parseInt(index)].isLove
			var songName = this.songsData[parseInt(index)].songName
			if (isLove === true) {
				this.songsData[parseInt(index)].isLove = false
				_cancelLoveSong({
					uid: this.uid,
					iid: this.songsData[parseInt(index)].iid,
				}).then((response) => {
					if (response.data.code === 200) {
						console.log("取消收藏")
						this.$message({
							type: "info",
							message: songName + " " + "取消收藏!",
						})
					}
				})
			} else {
				this.songsData[parseInt(index)].isLove = true
				_addLoveSong({
					uid: this.uid,
					iid: this.songsData[parseInt(index)].iid,
				}).then((response) => {
					if (response.data.code === 200) {
						console.log("收藏歌曲")
						this.$message({
							type: "success",
							message: songName + " " + "收藏成功!",
						})
					}
				})
			}
		},
	},
}
</script>

<style scoped>
#user-detail {
	position: absolute;
	width: 1060px !important;
	right: 0;
	left: 0;
	margin-left: auto;
	margin-right: auto;
}

/* 用户信息 */
#user-detail #user {
	position: relative;
	height: 100px;
}

/* 用户头像格式 */
#user-detail #user .user-img {
	display: inline-block;
	position: absolute;
	left: 10px;
}
#user-detail #user .user-img > img {
	width: 100px;
	height: 100px;
	display: inline-block;
}

/* 用户信息 */
#user-detail #user .user-info {
	display: inline-block;
	position: absolute;
	left: 150px;
}

/* 用户姓名、性别 */
#user-detail #user .user-info .user-name-gender {
	padding-bottom: 20px;
    text-align: left;
}
#user-detail #user .user-info .user-name-gender .name {
	padding-right: 50px;
}
#user-detail #user .user-info .user-name-gender .gender {
	color: #f56c6c;
	font-size: 16px;
}

/* 用户所在区域 */
#user-detail #user .user-info .user-area {
    text-align: left;
	padding-bottom: 20px;
}
#user-detail #user .user-info .user-area .area {
	color: #9b9b9b;
}

/* 用户个人简介 */
#user-detail #user .user-info .user-des {
    text-align: left;
}
#user-detail #user .user-info .user-des .des {
	color: #9b9b9b;
}

/* 播放记录间隔 */
#user-detail .record-interval {
	background-color: initial;
	text-align: left;
	padding-top: 20px;
	border-bottom: #a55050 3px solid;
}

/* 表格中不可播放的的禁止点击样式 */
#user-detail .fail {
	/* pointer-events: none; */
	color: #9b9b9b;
	cursor: not-allowed;
}
/* 可以播放则不做任何修改 */
#user-detail .succ {
	/* pointer-events: none; */
	/* color: #9b9b9b;
	cursor: not-allowed; */
}
/* 收藏歌曲激活状态 */
#user-detail .active {
	color: #f56c6c;
}

/* 字体图标鼠标覆盖变色 */
#user-detail i.iconfont.icon-bofang:hover {
	cursor: pointer;
	/* color: rgb(231, 56, 40); */
	color: #f56c6c;
}

/* 字体图标鼠标覆盖变色 */
#user-detail i.iconfont.icon-shoucang_huaban1:hover {
	cursor: pointer;
}
</style>
