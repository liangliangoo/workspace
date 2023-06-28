<template>
    <div>
    <el-upload action="#"
               list-type="picture-card"
               :auto-upload="false"
               :file-list="imageList"
               accept=".png,.jpg,.gif,.jpeg">
        <i slot="default" class="el-icon-upload">图片上传</i>
        <div slot="file" slot-scope="{file}">
            <span v-if="file.fileCode"><img class="el-upload-list__item-thumbnail" :src="file.fileUrl" alt="file.fileName"></span>
            <span v-if="!file.fileCode"><img class="el-upload-list__item-thumbnail" :src="file.url" alt=""></span>

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
        <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
    </div>
</template>
<script>
    export default {
        components:{
        },
        data() {
            return {
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false,
                imageList: [],
            };
        },
        methods: {
            handleRemove(file) {
                console.log(file);
                this.$api.deleteFile(file).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            handlePictureCardPreview(file) {
                console.log(file);
                if(!file.fileCode){
                    this.dialogImageUrl = file.url;
                }else{
                    this.dialogImageUrl = file.fileUrl;
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
                this.$api.imageUpload(formData).then(res => {
                    this.queryFileInfo();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            queryFileInfo(){
                let req = {"fileType":"image"};
                this.$api.queryFileInfo(req).then(res => {
                    this.imageList = res.data.rows;
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