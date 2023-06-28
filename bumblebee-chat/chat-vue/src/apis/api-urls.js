/**
 *  @author Mr.peng
 *  @description 该文件主要放置请求的所有接口地址，方便统一管理(不是规定，个人习惯)
 */
export default {
    // 用户
    user: {
        login: '/user/login',
        loginOut: '/user/logout',
        queryUserMenus:'/user/queryUserMenus',
    },
    // 权限
    permission: {
        queryPermissionManage:'/permission/queryPermissionManage',
        queryUserRole:'/permission/queryUserRole',
        queryRoleMenus :'/permission/queryRoleMenus',
        queryRolePermission:'/permission/queryRolePermission',
        saveUserRole :'/permission/saveUserRole',
        saveRoleMenus:'/permission/saveRoleMenus',
        saveRolePermission:'/permission/saveRolePermission',
    },

    // 数据
    data: {
        queryUserInfo:'/data/queryUserInfo',
        submitUserInfo:'/data/submitUserInfo',
        deleteUserInfo:'/data/deleteUserInfo',

        querySysLogInfo:'/data/querySysLogInfo',

        queryRoleInfo:'/data/queryRoleInfo',
        submitRoleInfo:'/data/submitRoleInfo',
        deleteRoleInfo:'/data/deleteRoleInfo',

        queryMenuInfo:'/data/queryMenuInfo',
        submitMenuInfo:'/data/submitMenuInfo',
        deleteMenuInfo:'/data/deleteMenuInfo',

        queryPermissionInfo:'/data/queryPermissionInfo',
        submitPermissionInfo:'/data/submitPermissionInfo',
        deletePermissionInfo:'/data/deletePermissionInfo',

    },
    //文件
    file: {
        queryFileInfo:'/file/queryFileInfo',
        deleteFile:'/file/deleteFile',
        downloadFileInfo:'/file/downloadFileInfo',

        imageUpload:'/file/imageUpload',
        pdfUpload:'/file/pdfUpload',
        wordUpload:'/file/wordUpload',
        videoUpload:'/file/videoUpload',

        excelUpload:'/file/excelUpload',
        showH5FromExcelFile:'/file/showH5FromExcelFile'

    },
    //聊天
    netty: {
        getWebSocketUri:'/netty/getWebSocketUri',
        getChatInfo :'/netty/getChatInfo',
        getChatFriendsList:'/netty/getChatFriendsList',
        getCanAddFriendList:'/netty/getCanAddFriendList',
        addFriend :'/netty/addFriend',
        getUserDetail:'/netty/getUserDetail',
        headPortraitImageUpload:'/netty/headPortraitImageUpload',
        getChatUserInfo:'/netty/getChatUserInfo',
        queryAddFriendRequest:'/netty/queryAddFriendRequest',
        agreeAddFriend:'/netty/agreeAddFriend',

        addGroupChat:'/netty/addGroupChat',
        getFriendList:'/netty/getFriendList',
    }

}