<template>
	<div class="admin-background"></div>
	<el-container id="all-screen">
		<el-aside>
			<el-menu
				:default-openeds="['1', '3']"
				text-color="#fff"
				active-text-color="rgba(48, 49, 51, 0.5)"
				background-color="rgba(48, 49, 51, 0.5)"
			>
				<el-submenu index="1">
					<template #title>
						<i class="el-icon-setting"></i>
						个性化音乐推荐系统后台管理
					</template>
					<el-menu-item-group>
						<template #title>歌曲管理</template>
						<el-menu-item index="1-1">歌曲管理</el-menu-item>
					</el-menu-item-group>
				</el-submenu>
			</el-menu>
		</el-aside>

		<el-container>
			<el-header
				style="text-align: right; font-size: 15px;position:relative;"
			>
				<el-input
					style="margin-left:10px;width:400px;height:50px;position:absolute;right:500px;"
					placeholder="请输入歌曲名"
					v-model="inputSearch"
					prefix-icon="el-icon-search"
					clearable
					@keyup.enter="searchSongs"
					@clear="clearSearchInput"
				></el-input>
				<div
					style="display:inline-block;position:absolute;left:10px;width:200px;text-align:center;"
				>
					<!-- <el-checkbox style="margin-left:-30px;width: 80px" v-model="checkedAll">全选</el-checkbox> -->
					<el-button
						style="margin-right:10px;"
						size="small"
						type="primary"
						round
						@click="clearDialog"
					>
						添加数据
					</el-button>

					<el-button
						size="small"
						type="danger"
						round
						@click="handleDeleteMore"
					>
						批量删除
					</el-button>
				</div>

				<el-dropdown
					style="position:absolute;top:5px;right:20px;width:100px;height:50px;text-align:center;color:#fff;"
				>
					<i
						class="el-icon-user-solid"
						style="margin-right:10px;"
					></i>
					<span>{{ adminName }}</span>

					<template #dropdown>
						<el-dropdown-menu>
							<el-dropdown-item @click="logOut">
								退出
							</el-dropdown-item>
						</el-dropdown-menu>
					</template>
				</el-dropdown>
			</el-header>

			<el-main>
				<!-- 可以进行多选的表格 -->
				<el-table
					ref="multipleTable"
					:data="tableData"
					@selection-change="handleSelectionChange"
					:header-cell-style="{
						fontWeight: 'normal',
						border: 'none',
					}"
				>
					<el-table-column
						align="center"
						type="selection"
						width="50"
					></el-table-column>

					<el-table-column
						align="center"
						prop="iid"
						label="歌曲id"
						width="150"
					></el-table-column>
					<el-table-column
						align="center"
						prop="songName"
						label="歌曲名"
						width="200"
					>
						<!-- 在表格中添加跳转链接 -->
						<template #default="scope">
							{{ scope.row.songName }}
						</template>
					</el-table-column>
					<el-table-column
						align="center"
						prop="singerName"
						label="演唱者"
						width="150"
					>
						<template #default="scope">
							<!-- <el-link underline="False" type="primary" to="/"> -->
							<span>{{ scope.row.singerName }}</span>
							<!-- </el-link> -->
						</template>
					</el-table-column>
					<el-table-column
						align="center"
						prop="album"
						label="专辑"
						width="200"
					>
						<template #default="scope">
							<span>{{ scope.row.album }}</span>
						</template>
					</el-table-column>
					<el-table-column
						align="center"
						prop="songTime"
						label="时长"
						width="150"
					>
						<template #default="scope">
							<span>{{ scope.row.songTime }}</span>
						</template>
					</el-table-column>

					<el-table-column align="center" prop="address" label="操作">
						<template #default="scope">
							<el-button
								type="primary"
								@click="handleEdit(scope.$index, scope.row)"
								style="margin-right:20px"
							>
								编辑
							</el-button>

							<el-button
								@click="
									handleDelete(
										scope.$index,
										scope.row,
										tableData,
									)
								"
								type="danger"
								plain
							>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
				<!-- 分页 -->
				<el-pagination
					background
					layout="prev, pager, next"
					:total="totalNum"
					:current-page="currentPage"
					style="padding-top:30px;"
					@current-change="handleCurrentChange"
				></el-pagination>
			</el-main>
		</el-container>
	</el-container>
	<!-- 添加数据的弹出框 -->
	<el-dialog title="添加歌曲" modal="false" v-model="dialogFormVisibleAdd">
		<el-form :model="form">
			<el-form-item label="歌名" :label-width="formLabelWidth">
				<el-input
					v-model="form.songName"
					autocomplete="off"
					placeholder="请输入姓名"
				></el-input>
			</el-form-item>
			<el-form-item label="歌手名称" :label-width="formLabelWidth">
				<el-input
					v-model="form.singerName"
					autocomplete="off"
					placeholder="请输入歌手名称"
				></el-input>
			</el-form-item>
			<el-form-item label="专辑名称" :label-width="formLabelWidth">
				<el-input
					v-model="form.album"
					autocomplete="off"
					placeholder="请输入歌曲专辑名称"
				></el-input>
			</el-form-item>
			<el-form-item label="图片链接" :label-width="formLabelWidth">
				<el-input
					v-model="form.picUrl"
					autocomplete="off"
					placeholder="请输入歌曲图片链接"
				></el-input>
			</el-form-item>
			<el-form-item label="歌曲时长" :label-width="formLabelWidth">
				<el-input
					v-model="form.songTime"
					autocomplete="off"
					placeholder="请输入歌曲时长，单位为s"
				></el-input>
			</el-form-item>
			<el-form-item label="MP3链接" :label-width="formLabelWidth">
				<el-input
					v-model="form.downUrl"
					autocomplete="off"
					placeholder="请输入歌曲MP3链接"
				></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogFormVisibleAdd = false">
					取 消
				</el-button>
				<el-button type="primary" @click="addData">
					确 定
				</el-button>
			</span>
		</template>
	</el-dialog>
	<!-- 更新歌曲信息 -->
	<el-dialog title="更新歌曲" modal="false" v-model="dialogFormVisibleEdit">
		<el-form :model="form">
			<el-form-item label="歌曲id" :label-width="formLabelWidth">
				<el-input
					v-model="form.iid"
					autocomplete="off"
					disabled="true"
				></el-input>
			</el-form-item>
			<el-form-item label="歌名" :label-width="formLabelWidth">
				<el-input
					v-model="form.songName"
					autocomplete="off"
					placeholder="请输入姓名"
				></el-input>
			</el-form-item>
			<!-- <el-form-item label="歌手名称" :label-width="formLabelWidth">
				<el-input
					v-model="form.singerName"
					autocomplete="off"
					placeholder="请输入歌手名称"
				></el-input>
			</el-form-item> -->
			<el-form-item label="专辑名称" :label-width="formLabelWidth">
				<el-input
					v-model="form.album"
					autocomplete="off"
					placeholder="请输入歌曲专辑名称"
				></el-input>
			</el-form-item>
			<el-form-item label="图片链接" :label-width="formLabelWidth">
				<el-input
					v-model="form.picUrl"
					autocomplete="off"
					placeholder="请输入歌曲图片链接"
				></el-input>
			</el-form-item>
			<!-- <el-form-item label="歌曲时长" :label-width="formLabelWidth">
				<el-input
					v-model="form.songTime"
					autocomplete="off"
					placeholder="请输入歌曲时长，单位为s"
				></el-input>
			</el-form-item> -->
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="dialogFormVisibleEdit = false">
					取 消
				</el-button>
				<el-button type="primary" @click="submitEdit">
					确 定
				</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script>
import {
	_getDisplaySongs,
	_deleteSong,
	_updateSong,
	_addSong,
	_getSearchSongs,
} from "../../network/request"
// import { ref } from "vue"
export default {
	name: "adminMenu",
	data() {
		// checked: true,
		const item = {
			iid: "0001",
			songName: "老街",
			singerName: "李荣浩",
			album: "",
			songTime: "",
			picUrl: "",
			downUrl: "",
			publishTime: "",
		}
		return {
			// 管理员姓名
			adminName: "管理员",
			//  修改tableData赋值方式，直接复制json数据
			tableData: Array(10).fill(item), // 这样赋值不能实现多选，会出现全选
			// tableData: {}, // 这样赋值不能实现多选，会出现全选
			// 搜索框输入
			inputSearch: "",
			// 修改弹出框
			dialogFormVisibleEdit: false,
			// 添加数据弹出框
			dialogFormVisibleAdd: false,
			form: {
				iid: "",
				songName: "",
				singerName: "",
				album: "",
				picUrl: "",
				songTime: "",
				downUrl: "",
			},
			// 弹出框中输入框的宽度
			formLabelWidth: "120px",
			// 当前页面
			currentPage: 1,
			pageSize: 10,
			totalNum: 30000,
			// 选中行的数据
			multipleSelection: [],
		}
	},
	created() {
		this.adminName = this.$route.query.name
		// 获得所有档案数据
		_getDisplaySongs({ currentPage: "1" }).then((response) => {
			this.tableData = response.data.data
			console.log(this.tableData)
		})
	},
	methods: {
		// 退出登录
		logOut() {
			this.$confirm("是否退出?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			})
				.then(() => {
					this.$message({
						type: "success",
						message: "退出成功!",
					})
					// 跳转到登录页
					this.$router.push("/admin")
				})
				.catch(() => {
					this.$message({
						type: "info",
						message: "已取消退出",
					})
				})
		},
		// 取消查询，恢复所有数据
		clearSearchInput() {
			_getDisplaySongs({ currentPage: "1" }).then((response) => {
				this.tableData = response.data.data
			})
			this.inputSearch = ""
		},
		// 清空弹出框中的数据
		clearForm() {
			this.form.iid = ""
			this.form.songName = ""
			this.form.singerName = ""
			this.form.album = ""
			this.form.picUrl = ""
			this.form.songTime = ""
			this.form.downUrl = ""
		},
		// 清空添加歌曲对话框的数据
		clearDialog() {
			this.dialogFormVisibleAdd = true
			this.clearForm()
		},
		// 点击弹出框的确定，添加数据
		addData() {
			this.dialogFormVisibleAdd = false
			_addSong({
				songName: this.form.songName,
				singerName: this.form.singerName,
				picUrl: this.form.picUrl,
				album: this.form.album,
				songTime: this.form.songTime + "000",
				downUrl: this.form.downUrl,
				// 这里需要加Content-type，不然传参数会有问题
			}).then((response) => {
				// 在前端进行比较数据的方法并不好，需要在后端进行比较
				if (response.data.code == 200) {
					_getDisplaySongs({ currentPage: "1" }).then((response) => {
						this.tableData = response.data.data
						console.log(this.tableData)
					})
					this.$alert(
						"歌曲id：" + response.data.data.iid,
						"添加提示",
						{
							confirmButtonText: "确定",
							callback: () => {
								this.$message({
									type: "success",
									message: "添加成功",
								})
							},
						},
					)
				} else {
					// 弹出提示
					this.$message({
						type: "info",
						message: "添加失败!",
					})
				}
			})
		},
		// 点击编辑按钮开始编辑
		handleEdit(index, row) {
			// 打印行号和行的内容
			console.log(index, row)
			this.dialogFormVisibleEdit = true
			this.form.iid = row.iid
			this.form.songName = row.songName
			this.form.singerName = row.singerName
			this.form.album = row.album
			this.form.picUrl = row.picUrl
			this.form.songTime = row.songTime
		},
		// 提交编辑
		submitEdit() {
			this.dialogFormVisibleEdit = false
			_updateSong({
				iid: this.form.iid,
				songName: this.form.songName,
				picUrl: this.form.picUrl,
				album: this.form.album,
			}).then((response) => {
				// 在前端进行比较数据的方法并不好，需要在后端进行比较
				if (response.data.code == 200) {
					// 重新渲染数据
					_getDisplaySongs({
						currentPage: this.currentPage + "",
					}).then((response) => {
						this.tableData = response.data.data
						console.log(this.tableData)
					})
					this.$message({
						type: "success",
						message: "更新成功!",
					})
				} else {
					// 弹出提示
					this.$message({
						type: "info",
						message: "更新失败!",
					})
				}
			})
		},
		// 单行删除，index为当前行在数组中的下标，row为当前行的内容，rows为所有行数组
		handleDelete(index, row, rows) {
			this.$confirm("是否删除?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			})
				.then(() => {
					// 发出删除请求
					_deleteSong({ iid: row.iid }).then((response) => {
						if (response.data.code == 200) {
							// 页面上删除
							rows.splice(index, 1)
							// 删除成功提示信息
							this.$message({
								type: "success",
								message: "删除成功!",
							})
						} else {
							// 删除失败提示信息
							this.$message({
								type: "info",
								message: "删除失败!",
							})
						}
					})
				})
				.catch(() => {
					this.$message({
						type: "info",
						message: "取消删除",
					})
				})
		},
		// 批量删除
		handleDeleteMore() {
			// 判断是否选中
			if (this.multipleSelection.length == 0) {
				// 弹出提示
				this.$message({
					type: "info",
					message: "未选择数据，请重试!",
				})
				return false
			}
			this.$confirm("是否删除?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			})
				.then(() => {
					for (var i = 0; i < this.multipleSelection.length; i++) {
						_deleteSong({
							iid: this.multipleSelection[i].iid,
						}).then((response) => {
							// 在前端进行比较数据的方法并不好，需要在后端进行比较
							if (response.data.code == 200) {
								this.$message({
									type: "success",
									message: "正在删除",
								})
							} else {
								// 弹出提示
								this.$message({
									type: "info",
									message: "删除失败!",
								})
							}
						})
					}
					// 删除完成
					this.$message({
						type: "success",
						message: "删除完成",
					})
					// 刷新所有档案数据
					_getDisplaySongs({ currentPage: "1" }).then((response) => {
						this.tableData = response.data.data
						console.log(this.tableData)
					})
				})
				.catch(() => {
					this.$message({
						type: "info",
						message: "已取消删除",
					})
				})
		},
		// 多选处理
		handleSelectionChange(val) {
			console.log(val)
			this.multipleSelection = val
			console.log(this.multipleSelection)
		},
		// 下一页
		handleCurrentChange(val) {
			// 得到歌曲的详情信息
			_getDisplaySongs({ currentPage: val }).then((response) => {
				this.tableData = response.data.data
				console.log(this.tableData)
			})
		},
		// 搜索歌曲
		searchSongs() {
			_getSearchSongs({ keyword: this.inputSearch }).then((response) => {
				this.tableData = response.data.data
				console.log(this.tableData)
			})
		},
	},
}
</script>

<style scoped>
/* 充满全屏 */
#all-screen {
	position: absolute;
	/*设置内部填充为0，几个布局元素之间没有间距*/
	padding: 0px;
	/*外部间距也是如此设置*/
	margin: 0px;
	/*统一设置高度为100%*/
	height: 100%;
	/* 同一设置宽度为100% */
	width: 100%;
	bottom: 0;
}
.el-header {
	/* background-color: #303133; */
	background-color: rgba(48, 49, 51, 0.5);
	color: #fff;
	line-height: 60px;
}

#all-screen .el-aside {
	color: #fff;
	/* background-color: #303133; */
	background-color: rgba(48, 49, 51, 0.5);
	width: 300px;
}

#all-screen .el-aside .el-menu {
	color: #fff;
	margin-left: -30px;
	/* background-color: #303133; */
	background-color: rgba(48, 49, 51, 0.5);
	border: none;
}
#all-screen .el-aside .el-menu:hover {
	background-color: rgba(48, 49, 51, 0.5);
}

#all-screen .el-aside .el-menu .el-submenu__title:hover {
	background-color: rgba(48, 49, 51, 0.5);
	color: #fff !important;
}

#all-screen .el-aside .el-menu .el-menu-item {
	color: #fff;
	background-color: #303133;
}

/* 表格样式 */
#all-screen .fail {
	/* pointer-events: none; */
	color: #9b9b9b;
	cursor: not-allowed;
}
#all-screen .succ {
	/* pointer-events: none; */
	/* color: #9b9b9b;
	cursor: not-allowed; */
}

.v-modal {
	background-color: rgba(0, 0, 0, 0.2);
}
.admin-background {
	background: url("../../assets/imgs/background-admin.jpg") no-repeat center
		center;
	background-size: 100% 100%;
	background-attachment: fixed;
	background-color: #cccccc;
	z-index: -1;
	position: absolute;
	width: 100%;
	height: 100%;
}
</style>
