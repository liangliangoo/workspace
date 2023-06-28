<template>
    <el-row :gutter="12">
        <el-col :span="8">
            <el-card shadow="hover" class="box-card">
                用户--角色下拉
                <el-tree
                        ref="userRoleTree"
                        :data="userRole"
                        show-checkbox
                        node-key="id"
                        :default-checked-keys="userRoleDefaultCheck">
                </el-tree>
                <el-button size="mini" type="primary" icon ="el-icon-save" @click="saveUserRole()">保存</el-button>
            </el-card>
        </el-col>
        <el-col :span="8">
            <el-card shadow="hover" class="box-card">
                角色--菜单多选
                <el-tree
                        ref="roleMenusTree"
                        :data="roleMenus"
                        show-checkbox
                        node-key="id"
                        :default-checked-keys="roleMenusDefaultCheck">
                </el-tree>
                <el-button size="mini" type="primary" icon ="el-icon-save" @click="saveRoleMenus()">保存</el-button>
            </el-card>
        </el-col>
        <el-col :span="8">
            <el-card shadow="hover" class="box-card">
                角色--权限多选
                <el-tree
                        ref="rolePermissionsTree"
                        :data="rolePermission"
                        show-checkbox
                        node-key="id"
                        :default-checked-keys="rolePermissionDefaultCheck">
                </el-tree>
                <el-button size="mini" type="primary" icon ="el-icon-save" @click="saveRolePermission()">保存</el-button>
            </el-card>
        </el-col>

    </el-row>
</template>

<script>
    export default {
        data() {
            return {
                userRole: [],
                roleMenus :[],
                rolePermission:[],
                //用户角色默认勾选
                userRoleDefaultCheck :[],
                //角色菜单默认勾选
                roleMenusDefaultCheck:[],
                //角色权限默认勾选
                rolePermissionDefaultCheck:[],

            };
        },
        methods:{
            queryPermissionManage(){
                this.$api.queryPermissionManage({}).then(res => {
                    this.userRole = res.data.data.userRole,
                    this.roleMenus = res.data.data.roleMenus;
                    this.rolePermission = res.data.data.rolePermission;

                    this.userRoleDefaultCheck = this.getDefaultCheck(this.userRole);
                    this.roleMenusDefaultCheck = this.getDefaultCheck(this.roleMenus);
                    this.rolePermissionDefaultCheck = this.getDefaultCheck(this.rolePermission);
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryUserRole(){
                this.$api.QueryUserRole({}).then(res => {
                    this.userRole = res.data.data;
                    let check = this.getDefaultCheck(this.userRole);
                    this.userRoleDefaultCheck = check;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryRoleMenus(){
                this.$api.QueryRoleMenus({}).then(res => {
                    this.roleMenus = res.data.data;
                    let check= this.getDefaultCheck(this.roleMenus);
                    this.roleMenusDefaultCheck = check;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryRolePermission(){
                this.$api.QueryRolePermission({}).then(res => {
                    this.rolePermission = res.data.data;
                    let check = this.getDefaultCheck(this.rolePermission);
                    this.rolePermissionDefaultCheck = check;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            getDefaultCheck(data){
                let check = [];
                if(data === null){return check;}
                data.forEach(function(value,index){
                    let children  =  value.children;
                    children.forEach(function(item,i){
                        if(item.isCheck === '1'){
                            check.push(item.id);
                        }
                    })
                });
                return check;
            },


            /*保存-----------------------*/
            saveUserRole(){
                let current = this.$refs.userRoleTree.getCheckedKeys(true);
                let last = this.userRoleDefaultCheck;
                console.log("用户-角色--->"+current);
                console.log("初始化选择-->"+this.userRoleDefaultCheck);
                let req = this.getDeleteAndAdd(current,last);
                if(req.add.length==0 && req.del.length == 0){
                    this.$commsgbox.alert("请修改后再点击保存！");
                    return;
                }
                this.$api.saveUserRole(req).then(res => {
                    this.queryUserRole();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            saveRoleMenus(){
                let current = this.$refs.roleMenusTree.getCheckedKeys(true);
                let last = this.roleMenusDefaultCheck;
                console.log("角色-菜单--->"+current);
                console.log("初始化选择-->"+this.roleMenusDefaultCheck);
                let req = this.getDeleteAndAdd(current,last);
                if(req.add.length==0 && req.del.length == 0){
                    this.$commsgbox.alert("请修改后再点击保存！");
                    return;
                }
                this.$api.saveRoleMenus(req).then(res => {
                    this.queryRoleMenus();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            saveRolePermission(){
                let current = this.$refs.rolePermissionsTree.getCheckedKeys(true);
                let last = this.rolePermissionDefaultCheck;
                console.log("角色-权限--->"+current);
                console.log("初始化选择-->"+this.rolePermissionDefaultCheck);
                let req = this.getDeleteAndAdd(current,last);
                if(req.add.length==0 && req.del.length == 0){
                    this.$commsgbox.alert("请修改后再点击保存！");
                    return;
                }
                this.$api.saveRolePermission(req).then(res => {
                    this.queryRolePermission();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            getDeleteAndAdd(currentData,lastData){
                let add = [];
                let del = [];
                currentData.forEach(function(val,index){
                    //新的选择在老的选择里面不存在，表示新增
                    if(lastData.indexOf(val) == -1){
                        add.push(val);
                    }
                });
                lastData.forEach(function(val,index){
                    //老的选择在新的选择里面不存在，表示已删除
                    if(currentData.indexOf(val) == -1){
                        del.push(val);
                    }
                });
                console.log("删除-->"+del);
                console.log("新增-->"+add);
                return {"add":add,"del":del};
            },
        },
        created() {
            //查询用户角色 角色菜单 角色权限
            this.queryPermissionManage();
        }
    };
</script>

<style>
    .text {
        font-size: 14px;
    }
    .box-card {
        height: 400px;
        overflow-y:scroll;
    }
</style>