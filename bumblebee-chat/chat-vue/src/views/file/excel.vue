<template>
    <div>
    <el-upload action="#"
               list-type="picture-card"
               :auto-upload="false"
               :file-list="excelList"
               accept=".xsl,.xlsx">
        <i slot="default" class="el-icon-upload">excel上传</i>
        <div slot="file" slot-scope="{file}">
            <span v-if="file.fileCode">{{file.fileName}}</span>
            <span v-if="!file.fileCode">{{file.name}}</span>

            <span class="el-upload-list__item-actions">

                    <span v-if = "file.fileCode" class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="file.fileCode" class="el-upload-list__item-delete" @click="handleDownload(file)">
                      <i class="el-icon-download"></i>
                    </span>
                    <span v-if="file.fileCode" class="el-upload-list__item-delete" @click="handleRemove(file)">
                      <i class="el-icon-delete"></i>
                    </span>
                    <span v-if="!file.fileCode" class="el-upload-list__item-delete" @click="handleUpload(file)">
                      <i class="el-icon-upload"></i>
                    </span>
                  </span>
        </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle">
        <el-scrollbar style="height:100%;">
            <div style="height:500px;" v-html="htmlPreview"></div>
        </el-scrollbar>
    </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{
        },
        data() {
            return {
                dialogVisible: false,
                excelList: [],
                htmlPreview:'',
                dialogTitle:'',
            };
        },
        methods: {
            handleRemove(file) {
                console.log(file);
                let req = {"fileCode":file.fileCode};
                this.$api.deleteFile(req).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            handlePictureCardPreview(file) {
                console.log(file);
                this.dialogVisible = true;
                this.$api.showH5FromExcelFile(file).then(res => {
                    this.htmlPreview = res.data.data;
                    this.dialogTitle = file.fileName;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            handleDownload(file) {
                console.log(file);
                this.$api.downloadFileInfo(file);
            },
            handleUpload(file){
                console.log(file);
                let formData = new FormData();
                formData.append('file',file.raw);
                this.$api.excelUpload(formData).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryFileInfo(){
                let req = {"fileType":"excel"};
                this.$api.queryFileInfo(req).then(res => {
                    this.excelList = res.data.rows;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
        },
        created() {
            this.queryFileInfo();
        }
    }
</script>
<style>
    .el-scrollbar .el-scrollbar__wrap {overflow-x: hidden;}
</style>