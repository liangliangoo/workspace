module.exports = {
    publicPath: '/',
    outputDir: 'dist',
    assetsDir: '',
    indexPath: 'index.html',
    configureWebpack: {
        resolve: {
            alias: {
                'assets': '@/assets',
                'components': '@/components',
                'views': '@/views',
                'api': '@/api',
                'common': '@/common',
                'layout': '@/layout',
                'router': '@/router',
            }
        }
    },
    devServer: {
        host: '0.0.0.0',
        port: 80,
        open: false,
        proxy:{
            '/api':{
                target: 'http://127.0.0.1:8080',//后端springboot的url地址
                changeOrigin: true,// 允许跨域
                secure: false,
                pathRewrite: {
                    '^/api': '/'
               }
            }
        },
        overlay: {
            warning: true,
            errors: true,
        }
    }
}