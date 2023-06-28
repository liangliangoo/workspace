<template>
    <el-collapse accordion @change="handleChange">
        <el-collapse-item title="用户管理" name="userInfo">
            <div >
                <user-Info v-if="activeName == 'userInfo'"></user-Info>
            </div>

        </el-collapse-item>
        <el-collapse-item title="角色管理" name="roleInfo">
            <div><role-Info v-if="activeName == 'roleInfo'"></role-Info></div>
        </el-collapse-item>
        <el-collapse-item title="菜单管理" name="menuInfo">
            <div><menu-Info v-if="activeName == 'menuInfo'"></menu-Info></div>
        </el-collapse-item>
        <el-collapse-item title="权限管理" name="permissionInfo">
            <div><permission-Info v-if="activeName == 'permissionInfo'"></permission-Info></div>
        </el-collapse-item>
        <el-collapse-item title="日志查询" name="logInfo">
            <div><log-Info v-if="activeName == 'logInfo'"></log-Info></div>
        </el-collapse-item>
    </el-collapse>
</template>
<script>
import userInfo from '../dataInfo/userInfo.vue'
import roleInfo from '../dataInfo/roleInfo.vue'
import menuInfo from '../dataInfo/menuInfo.vue'
import permissionInfo from '../dataInfo/permissionInfo.vue'
import logInfo from '../dataInfo/logInfo.vue'
export default {
    components:{
        userInfo,
        roleInfo,
        menuInfo,
        permissionInfo,
        logInfo
    },
    data() {
        return {
            activeName :'',
        }
    },
    methods:{
        handleChange(val){
            this.activeName = val;
        }

    },
    created() {

    },
}
</script>
<style>
    .el-collapse-item__header{
        padding-left: 50px;
    }
    .el-collapse{
        height:100%;
        overflow-y:scroll;
    }
</style>