<template>
    <div>
        <el-row :gutter="12">
            <el-col :span="12">
                <el-card shadow="hover" class="box-card">
                    <chat ref="chat"></chat>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover" class="box-card">
                    <chat-info ref="chatInfo"></chat-info>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import chat from './chat.vue';
    import chatInfo from './chatInfo.vue';
    export default {
        components:{
            chat,
            chatInfo,
        },
        data() {
            return {
            }
        },
        methods:{

        },
        created() {

        },
        mounted() {

        },
    }
</script>
<style scoped>
    .box-card{
        height: 550px;
    }
</style>