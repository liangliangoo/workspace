/**
 * @author Mr.peng
 * @description 该文件为接口请求方法的集成文件,以及请求的相关配置
 */
import axios from 'axios'
import apiUrls from './api-urls.js'
import { Loading } from 'element-ui';
import Cookies from "js-cookie"
import qs from 'qs';

axios.defaults.headers.post['Content-Type'] = 'application/json' //请求头
axios.defaults.headers.Accept = 'application/json' //请求头
axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true
axios.defaults.timeout = 60000

//loading效果,因为loading是单例，所以多次请求时，只有一个loading,只有都请求完成后，关闭loading,通过totalApis来判断多请求
let totalApis = 0
let load
// 请求拦截器
axios.interceptors.request.use(function (config) {
    totalApis++
    //加载框
    if (totalApis > 0) {
        load = Loading.service({ text: '请求中...' });
    }

    let token = Cookies.get('ydy-token')
    if (token) {//后端获取的header头部字段是 Authorization
        config.headers.Authorization = token
    }

    return config
}, function (error) {
    return Promise.reject(error)
})
// 响应拦截器
// 目前http 200会走response, 不是200都是走的error
// 所以异常提示情况，通过promise.reject到最外面的catch err里面弹出
axios.interceptors.response.use(function (response) {
    totalApis--
    if (totalApis <= 0) {
        load.close();
    }

    if (response.status === 200) {//http状态码200正常应答, 不管是get还是post,只要逻辑应答有code和msg字段，code不是200，就报错
        if(Object.prototype.hasOwnProperty.call(response.data, "code")
            && Object.prototype.hasOwnProperty.call(response.data, "msg")
            && response.data.code != 200
        ){
            return Promise.reject(response.data.msg);
        }
    }

    return response
}, function (error) {
    totalApis--
    if (totalApis <= 0) {
        load.close();
    }

    //token失效或者不正确
    if (error.response && error.response.status === 401) {
        Cookies.remove("ydy-token");
        sessionStorage.clear();
        window.location.href = "/login"; //没有使用router带redirect跳转，因为router 3.0.7以上版本会报错
        return Promise.reject("登录过期，请重新登录")
    }
    //权限不足
    if (error.response && error.response.status === 403) {
        return Promise.reject("权限不足，请联系管理员")
    }


    return Promise.reject(error)
})

// 公共方法提取
// axios.get或post 先走请求拦截器，然后是响应拦截器，拦截器不能阻塞。
// 然后根据响应拦截器的return确认走下面方法的then或者catch,最后到外部的then或者catch
const returnValue = (url, params, type) => {
    return new Promise((resolve, reject) => {
        if (type === 'get') {
            let reqUrl = url;
            if (Object.keys(params).length != 0) {
                reqUrl = url + '?' + qs.stringify(params);
            }
            axios.get(reqUrl)
                .then((res) => {
                    resolve(res)
                })
                .catch((err) => {
                    reject(err)
                })
        } else if(type === 'href') {
            if (Object.keys(params).length != 0) {
                url = 'api' + url + '?' + qs.stringify(params);
            }
            window.location.href = url;
        }else{
            axios.post(url, params)
                .then((res) => {
                    resolve(res)
                })
                .catch((err) => {
                    reject(err)
                })
        }
    })
}

export default {
    // 用户相关
    //登录
    Login: (params) => { return returnValue(apiUrls.user.login, params) },
    LoginOut: (params) => { return returnValue(apiUrls.user.loginOut, params, 'get') },
    //查询用户菜单
    QueryUserMenus: (params) => { return returnValue(apiUrls.user.queryUserMenus, params, 'get') },

    //权限相关
    //权限查询
    queryPermissionManage: (params) => { return returnValue(apiUrls.permission.queryPermissionManage, params, 'get') },
    QueryUserRole: (params) => { return returnValue(apiUrls.permission.queryUserRole, params, 'get') },
    QueryRoleMenus: (params) => { return returnValue(apiUrls.permission.queryRoleMenus, params, 'get') },
    QueryRolePermission: (params) => { return returnValue(apiUrls.permission.queryRolePermission, params, 'get') },
    //保存权限
    saveUserRole: (params) => { return returnValue(apiUrls.permission.saveUserRole, params) },
    saveRoleMenus: (params) => { return returnValue(apiUrls.permission.saveRoleMenus, params) },
    saveRolePermission:(params) => { return returnValue(apiUrls.permission.saveRolePermission, params) },

    //数据相关
    // 用户
    queryUserInfo:(params) => { return returnValue(apiUrls.data.queryUserInfo, params, 'get') },
    submitUserInfo:(params) => { return returnValue(apiUrls.data.submitUserInfo, params) },
    deleteUserInfo:(params) => { return returnValue(apiUrls.data.deleteUserInfo, params) },

    //日志
    querySysLogInfo:(params) => { return returnValue(apiUrls.data.querySysLogInfo, params,'get') },

    //角色
    queryRoleInfo:(params) => { return returnValue(apiUrls.data.queryRoleInfo, params, 'get') },
    submitRoleInfo:(params) => { return returnValue(apiUrls.data.submitRoleInfo, params) },
    deleteRoleInfo:(params) => { return returnValue(apiUrls.data.deleteRoleInfo, params) },

    //菜单
    queryMenuInfo:(params) => { return returnValue(apiUrls.data.queryMenuInfo, params, 'get') },
    submitMenuInfo:(params) => { return returnValue(apiUrls.data.submitMenuInfo, params) },
    deleteMenuInfo:(params) => { return returnValue(apiUrls.data.deleteMenuInfo, params) },

    //权限
    queryPermissionInfo:(params) => { return returnValue(apiUrls.data.queryPermissionInfo, params, 'get') },
    submitPermissionInfo:(params) => { return returnValue(apiUrls.data.submitPermissionInfo, params) },
    deletePermissionInfo:(params) => { return returnValue(apiUrls.data.deletePermissionInfo, params) },

    //文件相关
    queryFileInfo:(params) => { return returnValue(apiUrls.file.queryFileInfo, params,'get') },
    deleteFile:(params) => { return returnValue(apiUrls.file.deleteFile, params) },
    downloadFileInfo:(params) => { return returnValue(apiUrls.file.downloadFileInfo, params,'href') },

    //图片
    imageUpload:(params) => { return returnValue(apiUrls.file.imageUpload, params) },
    //pdf
    pdfUpload:(params) => { return returnValue(apiUrls.file.pdfUpload, params) },
    //word
    wordUpload:(params) => { return returnValue(apiUrls.file.wordUpload, params) },
    //video
    videoUpload:(params) => { return returnValue(apiUrls.file.videoUpload, params) },
    //excel
    excelUpload:(params) => { return returnValue(apiUrls.file.excelUpload, params) },
    showH5FromExcelFile:(params) => { return returnValue(apiUrls.file.showH5FromExcelFile, params,'get') },

    //netty
    getWebSocketUri:(params) => { return returnValue(apiUrls.netty.getWebSocketUri, params,'get') },
    getChatInfo:(params) => { return returnValue(apiUrls.netty.getChatInfo, params,'get') },
    getChatFriendsList:(params) => { return returnValue(apiUrls.netty.getChatFriendsList, params,'get') },
    getCanAddFriendList:(params) => { return returnValue(apiUrls.netty.getCanAddFriendList, params,'get') },
    addFriend:(params) => { return returnValue(apiUrls.netty.addFriend, params) },

    getUserDetail:(params) => { return returnValue(apiUrls.netty.getUserDetail, params,'get') },
    headPortraitImageUpload:(params) => { return returnValue(apiUrls.netty.headPortraitImageUpload, params) },

    getChatUserInfo:(params) => { return returnValue(apiUrls.netty.getChatUserInfo, params,'get') },
    agreeAddFriend:(params) => { return returnValue(apiUrls.netty.agreeAddFriend, params) },
    queryAddFriendRequest:(params) => { return returnValue(apiUrls.netty.queryAddFriendRequest, params,'get') },

    addGroupChat:(params) => { return returnValue(apiUrls.netty.addGroupChat, params) },
    getFriendList:(params) => { return returnValue(apiUrls.netty.getFriendList, params,'get') },



}