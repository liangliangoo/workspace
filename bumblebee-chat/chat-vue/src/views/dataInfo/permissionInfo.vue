<template>
    <div>
        <div class="options">
            <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="handlePermissionInfoAdd()">权限新增</el-button>
            <el-button type="primary" size="medium" icon="el-icon-search" @click="handelQuery()">权限查询</el-button>
        </div>
        <el-table
                :data="permissionInfoTableData"
                height="230"
                border
                style="width: 100%"
                size="mini">
            <el-table-column
                    prop="permissionCode"
                    label="权限Code"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="permissionUrl"
                    label="权限路径"
                    align="center"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="permissionName"
                    label="权限名称"
                    align="center"
                    width="200">
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" size="medium" icon="el-icon-edit" @click="handlePermissionInfoEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="primary" size="medium" icon="el-icon-delete" @click="handlePermissionInfoDelete(scope.$index, scope.row)">删除</el-button>
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
                :total="permissionInfoTotal">
        </el-pagination>

        <!--新增-->
        <el-dialog title="编辑权限" :visible.sync="dialogFormPermission">
            <el-form :model="permissionForm">
                <el-form-item label="权限路径" :label-width="formLabelWidth">
                    <el-input v-model="permissionForm.permissionUrl" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="权限名称" :label-width="formLabelWidth">
                    <el-input v-model="permissionForm.permissionName" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormPermission= false">取 消</el-button>
                <el-button type="primary" @click="permissionFormSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: "permissionInfo",
        data() {
            return {
                permissionInfoTableData: [],
                nowPage:1,
                permissionInfoTotal:0,
                pageSize:10,

                formLabelWidth: '100px',
                dialogFormPermission:false,
                permissionForm:{
                    permissionCode :'',
                    permissionName:'',
                    permissionUrl:'',
                },
            }
        },
        methods:{
            queryPermissionInfo(){
                let req = {"nowPage":this.nowPage,"pageSize":this.pageSize};
                this.$api.queryPermissionInfo(req).then(res => {
                    this.permissionInfoTableData = res.data.rows;
                    this.permissionInfoTotal = res.data.total;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryPermissionInfo();
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.queryPermissionInfo();
            },
            handelQuery(){
                this.queryPermissionInfo();
            },
            //新增
            handlePermissionInfoAdd(){
                this.dialogFormPermission = true;
                this.permissionForm.permissionCode = '';
                this.permissionForm.permissionName = '';
                this.permissionForm.permissionUrl = '';
            },
            //编辑
            handlePermissionInfoEdit(index,row){
                this.dialogFormPermission = true;
                this.permissionForm = row;
            },
            //删除
            handlePermissionInfoDelete(index,row){
                console.log("删除"+row.permissionCode);
                let req = {"permissionCode":row.permissionCode};
                this.$api.deletePermissionInfo(req).then(res => {
                    this.queryPermissionInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //提交
            permissionFormSubmit(){
                console.log("提交"+this.userForm);
                this.dialogFormPermission = false;
                let req = this.permissionForm;
                this.$api.submitPermissionInfo(req).then(res => {
                    this.queryPermissionInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },

        },
        created() {
            //查询用户信息
            this.queryPermissionInfo();
        },
    }
</script>

<style scoped>
    .options {
        text-align:left;
        margin-left:15px;
    }
</style>