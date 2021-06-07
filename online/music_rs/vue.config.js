// vue.config.js
// 解决跨域问题
module.exports = {
	// 选项...
	devServer: {
        // Paths
        open: true,
        // host: 'localhost',
        host: '0.0.0.0',
        port: 8080,
		proxy: {
			'/api': {
				target: 'http://localhost:8082/',
				changeOrigin: true,
				ws: true,
				pathRewrite: {
					'^/api': '',
				},
			},
		},
	},
}
