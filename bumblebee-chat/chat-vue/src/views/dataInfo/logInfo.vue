<template>
    <div>
        <div class="options">
            <el-button type="primary" size="medium" icon="el-icon-search" @click="handelQuery()">日志查询</el-button>
        </div>
        <el-table
                :data="logInfoTableData"
                height="230"
                border
                style="width: 100%"
                size="mini">
            <el-table-column prop="userName" label="用户名称" align="center" width="100px"></el-table-column>
            <el-table-column prop="os" label="系统" align="center" width="100px"></el-table-column>
            <el-table-column prop="browser" label="浏览器" align="center" width="100px"></el-table-column>
            <el-table-column prop="createTime" label="插入时间" align="center"></el-table-column>
            <el-table-column prop="url" label="请求路径" align="center" width="350px"></el-table-column>
            <el-table-column prop="params" label="请求参数" align="center"></el-table-column>

        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="nowPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"
                :total="logInfoTotal">
        </el-pagination>
    </div>
</template>
<script>
    export default {
        name: "userInfo",
        data() {
            return {
                logInfoTableData: [],
                nowPage:1,
                logInfoTotal:0,
                pageSize:10,
            }
        },
        methods:{
            queryLogInfo(){
                let req = {"nowPage":this.nowPage,"pageSize":this.pageSize};
                this.$api.querySysLogInfo(req).then(res => {
                    this.logInfoTableData = res.data.rows;
                    this.logInfoTotal = res.data.total;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.queryLogInfo();
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.queryLogInfo();
            },
            handelQuery(){
                this.queryLogInfo();
            },
        },
        created() {
            //查询用户信息
            this.queryLogInfo();
        },
    }
</script>

<style scoped>
    .options {
        text-align:left;
        margin-left:15px;
    }
</style>