<template>
    <div>
        <div class="options">
            <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="handleRoleInfoAdd()">角色新增</el-button>
            <el-button type="primary" size="medium" icon="el-icon-search" @click="handelQuery()">角色查询</el-button>
        </div>
        <el-table
                :data="roleInfoTableData"
                height="230"
                border
                style="width: 100%"
                size="mini">
            <el-table-column prop="roleCode" label="角色code" align="center" ></el-table-column>
            <el-table-column prop="roleName" label="角色名称" align="center" ></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" size="medium" icon="el-icon-edit" @click="handleRoleInfoEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="primary" size="medium" icon="el-icon-delete" @click="handleRoleInfoDelete(scope.$index, scope.row)">删除</el-button>
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
                :total="roleInfoTotal">
        </el-pagination>

        <!--新增-->
        <el-dialog title="编辑用户" :visible.sync="dialogFormRole">
            <el-form :model="roleForm">
                <el-form-item label="角色名称" :label-width="formLabelWidth">
                    <el-input v-model="roleForm.roleName" type="text" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormRole= false">取 消</el-button>
                <el-button type="primary" @click="roleFormSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: "roleInfo",
        data() {
            return {
                roleInfoTableData: [],
                nowPage:1,
                roleInfoTotal:0,
                pageSize:10,

                formLabelWidth: '100px',
                dialogFormRole:false,
                roleForm:{
                    roleCode :'',
                    roleName:'',
                },
            }
        },
        methods:{
            queryRoleInfo(){
                let req = {"nowPage":this.nowPage,"pageSize":this.pageSize};
                this.$api.queryRoleInfo(req).then(res => {
                    this.roleInfoTableData = res.data.rows;
                    this.roleInfoTotal = res.data.total;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryRoleInfo();
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.queryRoleInfo();
            },
            handelQuery(){
                this.queryRoleInfo();
            },
            //新增
            handleRoleInfoAdd(){
                this.dialogFormRole = true;
                this.roleForm.roleCode = '';
                this.roleForm.roleName = '';
            },
            //编辑
            handleRoleInfoEdit(index,row){
                this.dialogFormRole = true;
                this.roleForm = row;
            },
            //删除
            handleRoleInfoDelete(index,row){
                console.log("删除"+row.roleCode);
                let req = {"roleCode":row.roleCode};
                this.$api.deleteRoleInfo(req).then(res => {
                    this.queryRoleInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //提交
            roleFormSubmit(){
                console.log("提交"+this.roleForm);
                this.dialogFormRole = false;
                let req = this.roleForm;
                this.$api.submitRoleInfo(req).then(res => {
                    this.queryRoleInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },

        },
        created() {
            //查询用户信息
            this.queryRoleInfo();
        },
    }
</script>

<style scoped>
    .options {
        text-align:left;
        margin-left:15px;
    }
</style>