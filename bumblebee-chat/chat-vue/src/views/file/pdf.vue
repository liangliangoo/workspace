<template>
    <div>
        <el-upload action="#"
                   list-type="picture-card"
                   :auto-upload="false"
                   :file-list="pdfList"
                   accept=".pdf">
            <i slot="default" class="el-icon-upload">pdf上传</i>
            <div slot="file" slot-scope="{file}">
                <span v-if="file.fileCode">{{file.fileName}}</span>
                <span v-if="!file.fileCode">{{file.name}}</span>
                <span class="el-upload-list__item-actions">

                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
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
        <el-dialog :visible.sync="dialogVisible">
            <embed :src="dialogPdfUrl" style="width: 100%;height: 400px;"/>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{
        },
        data() {
            return {
                dialogPdfUrl: '',
                dialogVisible: false,
                pdfList: [],
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
                if(!file.fileCode){
                    this.dialogPdfUrl = file.url;
                }else{
                    this.dialogPdfUrl = file.fileUrl;
                }
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            handleUpload(file){
                console.log(file);
                let formData = new FormData();
                formData.append('file',file.raw);
                this.$api.pdfUpload(formData).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryFileInfo(){
                let req = {"fileType":"pdf"};
                this.$api.queryFileInfo(req).then(res => {
                    this.pdfList = res.data.rows;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            }
        },
        created() {
            this.queryFileInfo();
        }
    }
</script>
<style scoped >

</style>