<template>
    <div>
        <el-upload action="#"
                   list-type="picture-card"
                   :auto-upload="false"
                   :file-list="wordList"
                   accept=".docx,.doc">
            <i slot="default" class="el-icon-upload">doc上传</i>
            <div slot="file" slot-scope="{file}">
                <span v-if="file.fileCode" style="margin-top: 10px;">{{file.fileName}}</span>
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
            <embed :src="dialogWordUrl" style="width: 100%;height: 400px;"/>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{
        },
        data() {
            return {
                dialogWordUrl: '',
                dialogVisible: false,
                wordList: [],
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
                    this.dialogWordUrl = file.url;
                }else{
                    this.dialogWordUrl = file.fileUrl;
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
                this.$api.wordUpload(formData).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryFileInfo(){
                let req = {"fileType":"word"};
                this.$api.queryFileInfo(req).then(res => {
                    this.wordList = res.data.rows;
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