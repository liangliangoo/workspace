<template>
<div class="wrapper">
<!-- 页面头部部分 -->
    <div class="header">
        <div class="logo">SpringCloud-Study</div>
        <!-- 水平一级菜单 -->
        <div style="float:left;">
            <el-menu  mode="horizontal" class="el-menu-demo" @select="handleSelect" active-text-color="#ffd04b">
                <template  v-for="item in menus">
                   <el-menu-item v-if="item.children.length ===0" :key="item.menuUrl" :index="item.menuUrl">{{item.menuTitle}}</el-menu-item>
                   <el-submenu v-else-if="item.children.length >0" :key="item.menuUrl" :index="item.menuUrl">
                    <template slot="title">
                       <span>{{item.menuTitle}}</span>
                     </template>
                     <el-menu-item :index = "child.menuUrl" v-for="child in item.children" :key="child.menuUrl">
                       <span>{{child.menuTitle}}</span>
                     </el-menu-item>
                   </el-submenu>
                </template>
            </el-menu>
        </div>

        <div class="header-right">
            <div class="header-user-con">
                <!-- 用户头像 -->
                <div class="user-avator"><img :src="headImage" /></div>
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{ userName }}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command = "dictConfig">字典配置</el-dropdown-item>
                        <el-dropdown-item command = "updatePassword">修改密码</el-dropdown-item>
                        <el-dropdown-item command="loginOut">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
    

    <el-main>
        <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
          <el-tab-pane
              v-for="(item) in editableTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
            >
              <first-Tab v-if="editableTabsValue===item.name && editableTabsValue === 'firstTab'"></first-Tab>
              <data-Info v-if="editableTabsValue===item.name && editableTabsValue === '/data/dataInfo'"></data-Info>
              <role-Permission v-if="editableTabsValue===item.name && editableTabsValue === '/permission/rolePermission'"></role-Permission>
              <file-manage  v-if="editableTabsValue===item.name && editableTabsValue === '/file/fileManage'"></file-manage>
              <netty-chat v-if="editableTabsValue===item.name && editableTabsValue === '/netty/chat'"></netty-chat>
            </el-tab-pane>
        </el-tabs>
    </el-main>

    <!--字典配置弹框-->
    <el-dialog title="字典管理" :visible.sync="dictConfigVisible">
        <div class="options">
            <el-button icon="el-icon-circle-plus-outline" circle type="primary" @click="dictConfigAdd()">新增</el-button>
            <el-button icon="el-icon-search" circle type="primary" @click="handelQuery()">查询</el-button>
        </div>
        <el-table :data="dictData" size="mini" border stripe height="250">
            <el-table-column property="type" label="字典类型" width="150" align="center"></el-table-column>
            <el-table-column property="value" label="字典值" width="200" align="center"></el-table-column>
            <el-table-column property="desc" label="描述" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" size="medium" icon="el-icon-edit" @click="dictConfigEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="primary" size="medium" icon="el-icon-delete" @click="dictConfigDelete(scope.$index, scope.row)">删除</el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
    </el-dialog>
</div>
</template>

<script>
import Cookies from "js-cookie";
import firstTab from "../first/firstTab.vue";
import dataInfo from "../dataInfo/index.vue";
import rolePermission from "../permission/index.vue";
import fileManage from "../file/index.vue";
import nettyChat from "../chat/index.vue";
export default {
    components:{
        firstTab,
        dataInfo,
        rolePermission,
        fileManage,
        nettyChat
    },
    data() {
        return {
            menus :[],
            editableTabs: [{
              title: '首页',
              name: 'firstTab',
              content: ''
            }],
            editableTabsValue:'firstTab',
            menusMap :'',
            //字典
            dictConfigVisible:false,
            dictData:[],
            //头像
            headImage:require('../../assets/img/img.jpg'),

        }
    },
    computed: {
        userName() {
            let userName = sessionStorage.getItem("nickName");
            return userName ;
        }
    },
    methods:{
        // 切换菜单栏
        handleSelect(key, keyPath) {
           console.log(key);
           this.addTab(key);
        },
        // 用户名下拉菜单选择事件
        handleCommand(command) {
            //登出
            if (command == 'loginOut') {
                Cookies.remove('ydy-token');
                sessionStorage.clear();
                this.$router.push('/login');
            }
            //字典配置
            if(command == 'dictConfig'){
                this.dictConfigVisible = true;
                //查询字典  TODO
            }
        },
        //查询菜单
        queryUserMenus(){
            let userCode = sessionStorage.getItem("userCode");
            let headPortrait = sessionStorage.getItem("headPortrait");
            let req = {"userCode":userCode};
            if(headPortrait != 'null'){this.headImage = headPortrait;}
            this.$api.QueryUserMenus(req).then(res => {
                this.menus = res.data.data;
                //转换每个请求的url和名称
                this.handleUrlAndNameToMap(res.data.data);
            }).catch(err => {
                this.$commsgbox.alert(err);
            });
        },
        //转换每个请求的url和名称
        handleUrlAndNameToMap(data){
            let map = {};
            data.forEach(function(val,index){
                let children = val.children;
                //循环二级菜单
                if(children.length > 0 ){
                    children.forEach(function(childrenVal,childrenIndex){
                        let childrenKey = childrenVal.menuUrl;
                        let childrenValue = childrenVal.menuTitle;
                        map[childrenKey] = childrenValue;
                    })
                }else{
                    let key = val.menuUrl;
                    let value = val.menuTitle;
                    map[key] = value;
                }
            });
            this.menusMap = map;
        },

        addTab(targetName) {
            let isAdd = true;
            this.editableTabs.forEach(function(item){
                if(item.name == targetName){
                    isAdd = false;
                }
            });
            if(isAdd){
                let title = this.menusMap[targetName];
                this.editableTabs.push({
                  title: title,
                  name: targetName,
                  content: targetName,
                  isShow :true,
                });
            }
            this.editableTabsValue = targetName;
        },
        removeTab(targetName) {
            let tabs = this.editableTabs;
            let activeName = this.editableTabsValue;
            if (activeName === targetName) {
              tabs.forEach((tab, index) => {
                if (tab.name === targetName) {
                  let nextTab = tabs[index + 1] || tabs[index - 1];
                  if (nextTab) {
                    activeName = nextTab.name;
                  }
                }
              });
            }
            this.editableTabsValue = activeName;
            this.editableTabs = tabs.filter(tab => tab.name !== targetName);
        },
    },
    created() {
        this.queryUserMenus();
    },

}
</script>

<style scoped>
.wrapper {
    width: 100%;
    height: 100%;
    background: #f0f0f0;
}
.header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    font-size: 22px;
    background-color: #FFFFFF;
}
.header .logo {
    float: left;
    margin-left: 20px;
    margin-top: 20px;
    height: 29px;
    width: 200px;
    vertical-align: center;
}
/* --------------- 用户头像区域的样式 ---------------- */
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.user-name {
    margin-left: 10px;
}
.el-dropdown-link {
    cursor: pointer;
}
.el-dropdown-menu__item {
    text-align: center;
}
/* --------------- 水平一级菜单栏的样式--------------------- */
.el-menu.el-menu--horizontal {
    border-bottom: none !important;
    float: left;
    margin-left: 50px;
}
.el-menu--horizontal > .el-menu-item.is-active {
    border-bottom: 2px solid #409eff;
    color: #3989fa;
    font-weight: 700;
}
.el-menu--horizontal > .el-menu-item {
    font-size: 16px;
    margin: 0 15px;
    color: black;
}

.el-main {
    padding : 0 0 0 0 ;
    width: auto;
}

.el-tab-pane {
    height: 400px;
}
.el-footer{
    height: 10px;
}

    /********字典*/
.options {
    text-align:left;
    margin-left:15px;
}
</style>