<template>
    <div>
        <div class="userDetail">
            <!--头像-->
            <div class="headPortraitImage" title = "个人头像">
                <span class="span-title">个人头像</span>
                <el-upload action="#" list-type="picture-card" :auto-upload="false"
                           :file-list="headPortraitList"
                           accept=".png,.jpg,.gif,.jpeg">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                        <span v-if="file.userCode"><img class="el-upload-list__item-thumbnail" :src="file.headPortrait" alt="file.fileName"></span>
                        <span v-if="!file.userCode"><img class="el-upload-list__item-thumbnail" :src="file.url" alt=""></span>

                        <span class="el-upload-list__item-actions">

                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!file.userCode" class="el-upload-list__item-delete" @click="handleUpload(file)">
                      <i class="el-icon-upload"></i>
                    </span>
                  </span>
                    </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="headPortrait" alt="">
                </el-dialog>
            </div>
        </div>
        <div class="can-add-friends" title="可添加好友列表">
            <span class="span-title">可添加好友列表</span>
            <div v-for="(item,index) in canAddFriendList" :key="index" style="margin-top :20px;">
                <el-row>
                    <el-col :span="3">
                        <div class="chat-portrait"><img :src="item.friendPortrait" /></div>
                    </el-col>

                    <el-col :span="10">
                        <div>{{item.friendCode}}</div>
                    </el-col>

                    <el-col :span="3">
                        <div class="chat-nickName">{{item.friendName}}</div>
                    </el-col>

                    <el-col :span="3">
                        <el-button size="mini" @click="addFriend(item)">添加</el-button>
                    </el-col>
                </el-row>
            </div>
        </div>
        <div class="friends-add-request" title="好友添加申请列表">
            <span class="span-title">好友添加申请列表</span>
            <div v-for="(item,index) in friendAddRequestList" :key="index" style="margin-top :20px;">
                <el-row>
                    <el-col :span="3">
                        <div class="chat-portrait"><img :src="item.friendPortrait" /></div>
                    </el-col>

                    <el-col :span="10">
                        <div>{{item.friendCode}}</div>
                    </el-col>

                    <el-col :span="3">
                        <div class="chat-nickName">{{item.friendName}}</div>
                    </el-col>

                    <el-col :span="3">
                        <el-button size="mini" @click="agreeAddFriend(item)">同意</el-button>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        components:{

        },
        data() {
            return {
                //能够添加为好友的列表
                canAddFriendList:[],
                //好友添加申请
                friendAddRequestList:[],
                //发送人userCode
                fromCode:sessionStorage.getItem("userCode"),
                //头像地址
                headPortrait:'',
                headPortraitList:[],
                dialogVisible:false,
                labelPosition:'left',
            }
        },
        methods: {
            //查询好友添加申请
            queryAddFriendRequestList(){
                let req = {"userCode": this.fromCode};
                this.$api.queryAddFriendRequest(req).then(res => {
                    this.friendAddRequestList = res.data.data;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //同意好友添加
            agreeAddFriend(item){
                let req = Object.assign(item,{"userCode": this.fromCode});
                this.$api.agreeAddFriend(req).then(res => {
                    this.queryAddFriendRequestList();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //添加好友提交
            addFriend(item) {
                console.log("添加的好友信息" + item);
                let req = {"userCode": this.fromCode, "friendCode": item.friendCode};
                this.$api.addFriend(req).then(res => {
                    this.getCanAddFriendList();
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //查询可添加的好友列表
            getCanAddFriendList() {
                console.log("添加好友");
                let req = {"userCode": this.fromCode};
                this.$api.getCanAddFriendList(req).then(res => {
                    this.canAddFriendList = res.data.data;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //查看头像
            handlePictureCardPreview(file) {
                console.log(file);
                if (!file.userCode) {
                    this.headPortrait = file.url;
                } else {
                    this.headPortrait = file.headPortrait;
                }
                this.dialogVisible = true;
            },
            handleUpload(file){
                let that = this;
                console.log(file);
                let formData = new FormData();
                formData.append('file',file.raw);
                formData.append("userCode", this.fromCode);
                formData.append("nickName","尹家村帅勇");
                that.$api.headPortraitImageUpload(formData).then(res => {
                    let user = res.data.data;
                    let headPortrait = res.data.data.headPortrait;
                    this.headPortrait = headPortrait;
                    sessionStorage.setItem("headPortrait",headPortrait);
                    that.headPortraitList = [];
                    that.headPortraitList.push(user);
                }).catch(err => {
                    that.$commsgbox.alert(err);
                });
            },
            //查询界面信息
            getChatUserInfo(){
                let req = {"userCode": this.fromCode};
                this.$api.getChatUserInfo(req).then(res => {
                    //个人详细信息
                    let user = res.data.data.user;
                    this.headPortraitList.push(user);
                    //可添加好友列表
                    this.canAddFriendList = res.data.data.canAddFriendList;
                    //待同意好友列表
                    this.friendAddRequestList = res.data.data.friendAddRequestList;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            }
        },
        created() {
            this.getChatUserInfo();
        },
        mounted() {

        },
    }
</script>
<style>
    .chat-portrait img{
        display: block;
        width: 40px;
        height: 40px;
    }

    .chat-portrait{
        width:40px;
        height: 40px;
        margin-left: 10px;
    }
    .chat-nickName{
        text-align: center;
        font-size: 16px;
        font-weight: bold;
    }


    .userDetail{
        height:100px;
    }
    .can-add-friends{
        height: 200px;
        /*background-color: #9EEA6A;*/
    }
    .friends-add-request{
        height: 200px;
        /*background-color: #d27468;*/
    }

    /**********************/
    .el-upload{
        width: 80px;
        height: 80px;
        line-height: 80px;
    }
    .el-upload el-upload--picture-card{
        height: 80px;
        height: 80px;
    }
    .el-upload-list--picture-card .el-upload-list__item{
        width: 80px;
        height: 80px;
        line-height: 80px;
    }
    .el-upload-list--picture-card .el-upload-list__item-thumbnail{
        width: 80px;
        height: 80px;
        line-height: 80px;
    }
    .avatar{
        width: 80px;
        height: 80px;
    }
    .headPortraitImage{
        width: 300px;
        padding-left: 200px;
    }
    .span-title{
        font-weight: bold;
        font-size: 16px;
    }
</style>