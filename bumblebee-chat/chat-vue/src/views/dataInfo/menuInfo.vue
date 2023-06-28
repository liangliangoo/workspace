<template>
    <div>
        <div class="options">
            <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="handleMenuInfoAdd()">菜单新增</el-button>
            <el-button type="primary" size="medium" icon="el-icon-search" @click="handelQuery()">菜单查询</el-button>
        </div>
        <el-table
                :data="menuInfoTableData"
                height="230"
                border
                style="width: 100%"
                size="mini">
            <el-table-column
                    prop="menuCode"
                    label="菜单Code"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="menuParentCode"
                    label="上级菜单Code"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="menuTitle"
                    label="菜单名称"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="menuUrl"
                    label="菜单请求"
                    align="center"
                    width="200">
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" size="medium" icon="el-icon-edit" @click="handleMenuInfoEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="primary" size="medium" icon="el-icon-delete" @click="handleMenuInfoDelete(scope.$index, scope.row)">删除</el-button>
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
                :total="menuInfoTotal">
        </el-pagination>

        <!--新增-->
        <el-dialog title="编辑菜单" :visible.sync="dialogFormMenu">
            <el-form :model="menuForm">
                <el-form-item label="上级菜单" :label-width="formLabelWidth">
                    <el-select v-model="menuForm.menuParentCode" autocomplete="off">
                        <option>111</option>
                        <option>222</option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜单名称" :label-width="formLabelWidth">
                    <el-input v-model="menuForm.menuTitle" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="菜单路径" :label-width="formLabelWidth">
                    <el-input v-model="menuForm.menuUrl" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormMenu= false">取 消</el-button>
                <el-button type="primary" @click="menuFormSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: "menuInfo",
        data() {
            return {
                menuInfoTableData: [],
                nowPage:1,
                menuInfoTotal:0,
                pageSize:10,

                formLabelWidth: '100px',
                dialogFormMenu:false,
                menuForm:{
                    menuCode :'',
                    menuParentCode:'',
                    menuTitle:'',
                    menuUrl:'',
                },
            }
        },
        methods:{
            queryMenuInfo(){
                let req = {"nowPage":this.nowPage,"pageSize":this.pageSize};
                this.$api.queryMenuInfo(req).then(res => {
                    this.menuInfoTableData = res.data.rows;
                    this.menuInfoTotal = res.data.total;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryMenuInfo();
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.queryMenuInfo();
            },
            handelQuery(){
                this.queryMenuInfo();
            },
            //新增
            handleMenuInfoAdd(){
                this.dialogFormMenu = true;
                this.menuForm.menuCode = '';
                this.menuForm.menuParentCode = '';
                this.menuForm.menuTitle = '';
                this.menuForm.menuUrl = '';
            },
            //编辑
            handleMenuInfoEdit(index,row){
                this.dialogFormMenu = true;
                this.menuForm = row;
            },
            //删除
            handleMenuInfoDelete(index,row){
                console.log("删除"+row.menuCode);
                let req = {"menuCode":row.menuCode};
                this.$api.deleteMenuInfo(req).then(res => {
                    this.queryMenuInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //提交
            menuFormSubmit(){
                console.log("提交"+this.menuForm);
                this.dialogFormMenu = false;
                let req = this.menuForm;
                this.$api.submitMenuInfo(req).then(res => {
                    this.queryMenuInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },

        },
        created() {
            //查询用户信息
            this.queryMenuInfo();
        },
    }
</script>

<style scoped>
    .options {
        text-align:left;
        margin-left:15px;
    }
</style>