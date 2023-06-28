<template>
    <div>
        <div class="options">
            <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="handleUserInfoAdd()">用户新增</el-button>
            <el-button type="primary" size="medium" icon="el-icon-search" @click="handelQuery()">用户查询</el-button>
        </div>
        <el-table
                :data="userInfoTableData"
                height="230"
                border
                style="width: 100%"
                size="mini">
            <el-table-column
                    prop="userCode"
                    label="用户Code"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="userName"
                    label="用户名"
                    align="center"
                    width="200">
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" size="medium" icon="el-icon-edit" @click="handleUserInfoEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="primary" size="medium" icon="el-icon-delete" @click="handleUserInfoDelete(scope.$index, scope.row)">删除</el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="nowPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"
                :total="userInfoTotal">
        </el-pagination>

        <!--新增-->
        <el-dialog title="编辑用户" :visible.sync="dialogFormUser">
            <el-form :model="userForm">
                <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="userForm.userName" autocomplete="off" :disabled="isDisabled"></el-input>
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="userForm.passWord" type="password" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormUser= false">取 消</el-button>
                <el-button type="primary" @click="userFormSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: "userInfo",
        data() {
            return {
                userInfoTableData: [],
                nowPage:1,
                userInfoTotal:0,
                pageSize:10,

                formLabelWidth: '100px',
                dialogFormUser:false,
                userForm:{
                    userCode :'',
                    userName:'',
                    passWord:'',
                },
                isDisabled:false,
            }
        },
        methods:{
            queryUserInfo(){
                let req = {"nowPage":this.nowPage,"pageSize":this.pageSize};
                this.$api.queryUserInfo(req).then(res => {
                    this.userInfoTableData = res.data.rows;
                    this.userInfoTotal = res.data.total;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryUserInfo();
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.queryUserInfo();
            },
            handelQuery(){
                this.queryUserInfo();
            },
            //新增
            handleUserInfoAdd(){
                this.dialogFormUser = true;
                this.isDisabled = false;
                this.userForm.userCode = '';
                this.userForm.userName = '';
                this.userForm.passWord = '';
            },
            //编辑
            handleUserInfoEdit(index,row){
                this.dialogFormUser = true;
                this.isDisabled = true;
                this.userForm = row;
            },
            //删除
            handleUserInfoDelete(index,row){
                console.log("删除"+row.userCode);
                let req = {"userCode":row.userCode};
                this.$api.deleteUserInfo(req).then(res => {
                    this.queryUserInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //提交
            userFormSubmit(){
                console.log("提交"+this.userForm);
                this.dialogFormUser = false;
                let req = this.userForm;
                this.$api.submitUserInfo(req).then(res => {
                    this.queryUserInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },

        },
        created() {
            //查询用户信息
            this.queryUserInfo();
        },
    }
</script>

<style scoped>
    .options {
        text-align:left;
        margin-left:15px;
    }
</style>