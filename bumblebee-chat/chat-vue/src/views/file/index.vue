<template>
    <div>
    <el-row>
        <el-col :span="24"><image-Info></image-Info></el-col>
    </el-row>
    <el-row>
        <el-col :span="24"><excel-Info></excel-Info></el-col>
    </el-row>
    <el-row>
        <el-col :span="24"><pdf-Info></pdf-Info></el-col>
    </el-row>
    <el-row>
        <el-col :span="24"><docx-info></docx-info></el-col>
    </el-row>
    <el-row>
        <el-col :span="24"><video-info></video-info></el-col>
    </el-row>
    </div>
</template>
<script>
    import imageInfo from '../file/image.vue';
    import pdfInfo from '../file/pdf.vue';
    import docxInfo from '../file/docx.vue';
    import videoInfo from '../file/video.vue';
    import excelInfo from '../file/excel.vue';
    export default {
        components:{
            imageInfo,
            pdfInfo,
            docxInfo,
            videoInfo,
            excelInfo,
        },
        data() {
            return {
            };
        },
        methods: {

        },
        created() {
        }
    }
</script>

<style>

</style>