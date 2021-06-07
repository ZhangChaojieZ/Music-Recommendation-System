/* 验证账号 */
export function validateUsername(rule, value, callback) {
	if (value.length < 6 || value.length > 20) {
		return callback(new Error("用户名不得小于6个或大于20个字符!"))
	} else {
		callback()
	}
}

/* 验证密码 */
export function validatePassword(rule, value, callback) {
	if (value.length < 6) {
		return callback(new Error("密码不能小于6位"))
	} else {
		callback()
	}
}

/* 合法邮箱 */
export function validateEmail(rule, value, callback) {
	const emailReg = /^(([a-zA-Z0-9_\-.]+)@([a-zA-Z0-9_\-.]+)\.([a-zA-Z]{2,5}){1,25})$/
	if (!value) {
		return callback(new Error("邮箱不能为空!!"))
	}
	setTimeout(() => {
		if (!emailReg.test(value)) {
			return callback(new Error("邮箱格式错误"))
		} else {
			callback()
		}
	}, 100)
}

/* 合法手机号 */
export function validatePhone(rule, value, callback) {
	const phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/
	if (!value) {
		return callback(new Error("手机号码不能为空!!"))
	}
	setTimeout(() => {
		if (!phoneReg.test(value)) {
			return callback(new Error("手机号码格式错误"))
		} else {
			callback()
		}
	}, 100)
}

/* 合法真实姓名 */
export function validateRealName(rule, value, callback) {
	const realnameReg = /^([a-zA-Z0-9\u4e00-\u9fa5·]{1,10})$/
	if (!value) {
		return callback(new Error("真实姓名不能为空!!"))
	}
	setTimeout(() => {
		if (!realnameReg.test(value)) {
			return callback(new Error("您的真实姓名格式错误,请输入英文或汉字!"))
		} else {
			callback()
		}
	}, 100)
}

/* 合法身份证 */
export function validateIdNumber(rule, value, callback) {
	const idNumberReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
	if (!value) {
		return callback(new Error("身份证号码不能为空!!"))
	}
	setTimeout(() => {
		if (!idNumberReg.test(value)) {
			return callback(new Error("您的身份证号码格式错误!"))
		} else {
			callback()
		}
	}, 100)
}
