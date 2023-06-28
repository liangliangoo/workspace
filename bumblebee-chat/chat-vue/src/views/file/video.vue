<template>
    <div>
        <el-upload action="#"
                   list-type="picture-card"
                   :auto-upload="false"
                   :file-list="videoList"
                   accept=".mp4,.wmv">
            <i slot="default" class="el-icon-upload">MP4上传</i>
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
        <el-dialog :visible.sync="dialogVisible" @close="closeDialog">
            <video :src="dialogVideoUrl" controls autoplay class="video"
                   width="100%"></video>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{
        },
        data() {
            return {
                dialogVideoUrl: '',
                dialogVisible: false,
                videoList: [],
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
                    this.dialogVideoUrl = file.url;
                    this.fileCode = file.uid;
                }else{
                    this.dialogVideoUrl = file.fileUrl;
                    this.fileCode = file.fileCode;
                }
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
                this.$api.downloadFileInfo(file);
            },
            handleUpload(file){
                console.log(file);
                let formData = new FormData();
                formData.append('file',file.raw);
                this.$api.videoUpload(formData).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryFileInfo(){
                let req = {"fileType":"video"};
                this.$api.queryFileInfo(req).then(res => {
                    this.videoList = res.data.rows;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            closeDialog(){
                this.dialogVideoUrl = ""; //清空数据 关闭视频播放
            },
        },
        created() {
            this.queryFileInfo();
        }
    }
</script>