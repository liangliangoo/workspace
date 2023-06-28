<template>
    <div class="chat_body">
        <el-container >
            <!--左边-->
            <div class="left-aside">
                <!--头像-->
                <el-row slot="reference">
                    <div class="head-portrait"><img :src="headPortrait" /></div>
                </el-row>
            </div>
            <!--中间-->
            <el-aside width="250px">
                <!--聊天列表头部-->
                <div class="chat-record">
                    <div class="chat-record-input">
                        <el-input v-model="inputQuery" size="mini" prefix-icon="el-icon-search" placeholder="搜索" clearable></el-input>
                    </div>
                    <div class="chat-record-add">
                        <a href="#" style="line-height: 25px" title="发起群聊" @click="addGroupChatDialog">✚</a>
                    </div>
                </div>
                <!--聊天记录列表-->
                <div v-for="(item,index) in friendChatList" :key="index" style="margin-top :20px;">
                    <el-row @click.native="clickChatRecord(item)">
                        <el-col :span="6">
<!--                                                            <el-badge :value="2" class="item">-->
                            <div class="chat-portrait"><img :src="item.friendPortrait" /></div>
<!--                                                            </el-badge>-->
                        </el-col>

                        <el-col :span="18">
                            <el-row>
                                <el-col :span="15" class="chat-nickName">{{item.friendName}}</el-col>
                                <el-col :span="9">{{item.showTime}}</el-col>
                            </el-row>
                            <div class="chat-newMsg">{{item.message}}</div>
                        </el-col>
                    </el-row>
                </div>
            </el-aside>
            <!--右边-->
            <el-main>
                <el-row class="main-title">
                    <el-col :span="21">{{mainTitle}}</el-col>
                    <el-col :span="3" @click.native="lookDetail">
                        <a href="#" class="el-icon-more" aria-hidden="true" title="聊天信息"></a>
                    </el-col>
                </el-row>
                <div class="main-msg">
                    <div v-for="(item,index) in chatMsgRecord" :key="index">
                        <el-row v-if="item.fromCode != fromCode">
                            <el-row><span class="message-time">{{item.showTime}}</span></el-row>
                            <el-col :span="3">
                                <div class="msg-portrait-left"><img :src="item.fromHeadImage" /></div>
                            </el-col>
                            <el-col :span="21">
                                <div class="chat-message-left-nickName">{{item.fromName}}</div>
                                <div class="chat-msg-left">
                                    <span class="msg-detail">{{item.message}}</span>
                                </div>
                            </el-col>
                        </el-row>

                        <el-row v-if="item.fromCode == fromCode">
                            <el-row>
                                <span class="message-time">{{item.showTime}}</span>
                            </el-row>
                            <el-col :span="21">
                                <div class="chat-message-right-nickName">{{item.fromName}}</div>
                                <div class="chat-msg-right">
                                    <span class="msg-detail">{{item.message}}</span>
                                </div>
                            </el-col>
                            <el-col :span="3">
                                <div class="msg-portrait-right"><img :src="headPortrait" /></div>
                            </el-col>
                        </el-row>
                    </div>
                </div>

                <div class ="main-chat-input">
                    <!--工具栏-->
                    <el-popover placement="top-start" width="400" trigger="click" class="emoBox">
                        <div class="emotionList">
                            <a href="javascript:void(0);" @click="getEmo(index)" v-for="(item,index) in faceList" :key="index" class="emotionItem">{{item}}</a>
                        </div>
                        <el-button class="emotionSelect" slot="reference">
                            <i class="el-icon-picture-outline-round" aria-hidden="true" title="表情"></i>
                        </el-button>
                    </el-popover>

                    <el-button class="emotionSelect">
                        <i class="el-icon-folder-opened" aria-hidden="true" title="发送文件"></i>
                    </el-button>

                    <el-button class="emotionSelect">
                        <i class="el-icon-chat-dot-round" aria-hidden="true" title="聊天记录"></i>
                    </el-button>

                    <!--输入框-->
                    <el-input type="textarea" :rows="7" v-model="textarea"  resize="none" border="none" @keyup.enter.native="sendMsg" id="textarea" >

                    </el-input>
                </div>
                <el-button size="mini" style="float:right" @click="sendMsg">发送(S)</el-button>
            </el-main>
        </el-container>

        <!--创建群聊弹框-->
        <el-dialog :visible.sync="groupChatDialog" :close-on-click-modal="false" :append-to-body="true">
            <div style="height: 300px;">
                <div class="add-chatGroup-left" >
                    <el-input v-model="inputQuery" size="mini" prefix-icon="el-icon-search" placeholder="搜索" clearable></el-input>
                    <div v-for="(item,index) in friendList" :key="index" style="margin-top :20px;">
                        <el-row>
                            <el-col :span="6">
                                <div class="chat-portrait"><img :src="item.friendPortrait" /></div>
                            </el-col>

                            <el-col :span="15">
                                <div style="height: 35px;line-height: 35px;font-size: 18px;font-weight: bold">{{item.friendName}}</div>
                            </el-col>
                            <el-col :span="2">
                                <div>
                                    <el-checkbox @change="groupChatCheckChange($event,item)"></el-checkbox>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </div>
                <div class="add-chatGroup-right">
                    <div>
                        <span style="margin-right: 100px;">{{checkGroupChatTitle}}</span>
                        <el-button size="mini" type="success" @click="addGroupChat">确认</el-button>
                        <el-button size="mini" type="info">取消</el-button>
                    </div>
                    <div v-for="tag in checkChatUsers" :key="tag.friendCode" style="margin-top :20px;">
                        <el-row closable
                                :disable-transitions="false"
                                @close="handleCloseTag(tag.friendCode)">
                            <el-col :span="6">
                                <div class="chat-portrait"><img :src="tag.friendPortrait" /></div>
                            </el-col>

                            <el-col :span="10">
                                <div style="height: 35px;line-height: 35px;font-size: 18px;font-weight: bold">{{tag.friendName}}</div>
                            </el-col>
                        </el-row>
                    </div>
                </div>
            </div>
        </el-dialog>

        <!--群聊和个人详细信息-->
        <el-drawer
                title="我是标题"
                :visible.sync="drawer"
                :with-header="false">
            <span>我来啦!</span>
        </el-drawer>
    </div>
</template>
<script>
    const appData=require("../../assets/json/emoji.json")//引入存放emoji表情的json文件
    export default {
        components:{

        },
        data() {
            return {
                dialogVisible :true,
                //搜索框输入
                inputQuery:'',
                //登录客户头像
                headPortrait:sessionStorage.getItem("headPortrait"),
                //聊天记录表
                friendChatList:[],
                //聊天
                mainTitle:'',
                //信息
                textarea:'',
                //接收人UserCode
                toCode :'',
                //发送人userCode
                fromCode:sessionStorage.getItem("userCode"),
                fromName:sessionStorage.getItem("nickName"),
                //通信url
                webSocketUrl:sessionStorage.getItem("webSocketUrl"),
                //当前聊天对象的聊天记录
                chatMsgRecord :[],
                //所有的聊天记录
                chatRecord:'',
                //当前聊天的code组装key
                currentChatKey:'',
                /********/
                //群聊弹框
                groupChatDialog:false,
                //选择的好友
                checkChatUsers:[],
                //勾选好友抬头
                checkGroupChatTitle:'请勾选需要添加的联系人',
                //可添加为群聊的好友
                friendList:[],
                /*表情包*/
                faceList:[],//表情包数据
                content:'',
                /*群聊详细信息*/
                drawer:false,
            }
        },
        methods:{
            //查看聊天详细信息
            lookDetail(){
                this.drawer = true;
            },

            //获取表情包，放入输入框
            getEmo(index){
                let textArea = document.getElementById('textarea');
                //将选中的表情插入到输入文本的光标之后
                function changeSelectedText(obj, str) {
                    if (window.getSelection) {
                        // 非IE浏览器
                        textArea.setRangeText(str);
                        // 在未选中文本的情况下，重新设置光标位置
                        textArea.selectionStart += str.length;
                        textArea.focus()
                    } else if (document.selection) {
                        // IE浏览器
                        obj.focus();
                        var sel = document.selection.createRange();
                        sel.text = str;
                    }
                }
                changeSelectedText(textArea,this.faceList[index]);
                this.content=textArea.value;// 要同步data中的数据
                return;
            },

            //添加群聊好友
            addGroupChat(){
                if(this.checkChatUsers.length < 2){
                    this.$msg.success("请选择2个及以上好友！");
                    return;
                }
                console.log(this.checkChatUsers);
                this.groupChatDialog = false;
                //提交
                this.$api.addGroupChat(this.checkChatUsers).then(res => {

                }).catch(err => {
                    this.$commsgbox.alert(err);
                });

            },
            //勾选好友
            groupChatCheckChange(checked,item){
                if(checked  == true){
                    console.log("勾选："+item.friendCode);
                    this.checkChatUsers.push(item);
                }else{
                    console.log("取消："+item.friendCode);
                    for(var index in this.checkChatUsers){
                        if(this.checkChatUsers[index].friendCode === item.friendCode){
                            this.checkChatUsers.splice(index, 1);
                        }
                    }
                }
                if(this.checkChatUsers.length > 0 ){
                    this.checkGroupChatTitle  = "已选择了"+this.checkChatUsers.length+"个联系人";
                }else{
                    this.checkGroupChatTitle = '请勾选需要添加的联系人';
                }

            },
            //群聊弹框
            addGroupChatDialog(){
                let req = {"userCode":this.fromCode};
                this.$api.getFriendList(req).then(res => {
                    this.friendList = res.data.data;
                    this.groupChatDialog = true;
                    this.checkChatUsers = [];
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },

            /***********************/
            //查询聊天列表
            getChatFriendsList(){
                let req = {"userCode":this.fromCode};
                this.$api.getChatFriendsList(req).then(res => {
                    this.friendChatList = res.data.data;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
            //点击好友列表，开始聊天
            clickChatRecord(item){
                let that = this;
                //聊天信息展示的抬头显示好友昵称
                that.mainTitle = item.friendName;
                //好友的code为收信人code
                that.toCode = item.friendCode;

                //找到mappingCode
                let mappingCode = item.mappingCode;

                //给当前聊天纤细对象赋值
                if(that.chatRecord != undefined && mappingCode in that.chatRecord){
                    that.chatMsgRecord = that.chatRecord[mappingCode];
                }else{
                    that.chatMsgRecord = [];
                }
                //更新当前聊天对象祝贺key
                that.currentChatKey = mappingCode;
                //信息下拉滚条置到底部
                that.setScrollToEnd();
                //好友列表重新排序 TODO


            },
            //发送信息
            sendMsg () {
                let that = this;
                //信息输入框内容
                let msg = that.textarea;
                //发送人userCode
                let fromCode = that.fromCode;
                //接收人userCode
                let toCode = that.toCode;
                //当前聊天组合的mappingCode
                let mappingCode = that.currentChatKey;
                //判断信息不为空
                if(msg.trim()===''){
                    that.$commsgbox.alert("不能发送空白信息！");
                    return;
                }
                //判断收信人是否选择
                if(toCode===''){
                    that.$commsgbox.alert("请选择要发送信息的好友！");
                    return;
                }
                //组装发送的信息体
                let req = {
                    "fromCode":fromCode,
                    "fromName":this.fromName,
                    "mappingCode":mappingCode,
                    "toCode":toCode,
                    "message":msg,
                    "fromHeadImage":this.headPortrait,
                    "showTime":this.$comfunc.getHHmm()
                };
                //把组装的发送的信息添加到当前聊天对象的聊天信息集合中
                that.chatMsgRecord.push(req);
                //把对象转为字符串传输
                let agentData  = JSON.stringify(req);
                //websocket发送信息
                that.webSocketSendMessage(agentData);
                //更新好友列表的最新信息
                that.friendChatList.forEach(function(val,index){
                    let friendCode = val.friendCode;
                    if(toCode === friendCode){
                        //更新信息和时间
                        val.message = msg;
                        val.showTime = that.$comfunc.getHHmm();
                        return;
                    }
                });
                //信息输入框置空
                that.textarea = "";
                //聊天详细信息一直位于底部
                that.setScrollToEnd();
            },

            //websocket发送信息
            webSocketSendMessage(agentData){
                let that = this;
                //若是ws开启状态
                if (that.websock.readyState === that.websock.OPEN) {
                    that.websocketSend(agentData);
                }
                // 若是 正在开启状态，则等待300毫秒
                else if (that.websock.readyState === that.websock.CONNECTING) {
                    setTimeout(function () {
                        that.websocketSend(agentData);
                    }, 300);
                }
                // 若未开启 ，则等待500毫秒
                else {
                    that.initWebSocket();
                    setTimeout(function () {
                        that.websocketSend(agentData)
                    }, 500);
                }
            },
            //数据发送
            websocketSend(agentData){
                let that = this;
                console.log("发送的信息："+agentData);
                that.websock.send(agentData);
            },
            //关闭
            websocketClose(e){
                console.log("connection closed (" + e.code + ")");
            },
            //设置div的下拉条始终在底部
            setScrollToEnd(){
                let that = this;
                that.$nextTick(()=> {
                    let box = that.$el.querySelector(".main-msg")
                    box.scrollTop = box.scrollHeight
                });
            },
            //监听服务端返回信息数据接收
            websocketOnmessage(e){
                let that = this;
                let reData = e.data;
                console.log("接收到的信息为："+ reData);
                //好友上线下线信息
                if(that.showFriendNoticeMessage(reData)){return;}
                //json转换
                reData = JSON.parse(reData);
                /**
                 * 对数据做处理，处理逻辑为：
                 * 1、如果收到的信息为当前聊天对象的信息，直接把值付给当前聊天信息对象
                 * 2、如果收到的信息不是当前聊天对象的，找到该对象的聊天信息，然后把信息加进去
                 * 3、更新聊天列表的时间和信息
                 * @type {string}
                 */
                that.handleReceiveMessage(reData);
                //聊天详细信息一直位于底部
                that.setScrollToEnd();
            },
            //好友上下线消息提醒
            showFriendNoticeMessage(reData){
                let that = this;
                if(reData.indexOf("{") == -1){
                    console.log("message提示:"+reData);
                    that.$msg.success(reData);
                    return true;
                }
            },
            //处理接收到的信息
            handleReceiveMessage(reData){
                let that = this;
                //聊天组code
                let mappingCode = reData.mappingCode;
                //1、判断如果发送的信息为当前聊天对象，直接拼接信息
                if(that.currentChatKey === mappingCode){
                    console.log("聊天对象为当前对象");
                    that.chatMsgRecord.push(reData);
                }else{
                    //2、如果不是当前聊天的对象,拼接到对应list，然后重新放入map中
                    console.log("聊天对象为好友列表对象");
                    if(mappingCode in that.chatRecord){
                        let tmpChatMsgRecord = that.chatRecord[mappingCode];
                        tmpChatMsgRecord.push(reData);
                        that.chatRecord[mappingCode] = tmpChatMsgRecord;
                    }else{
                        let tmpChatMsgRecord =[];
                        tmpChatMsgRecord.push(reData);
                        that.chatRecord[mappingCode] = tmpChatMsgRecord;
                    }
                }
                //3、更新聊天列表的时间和信息
                console.log("更新好友列表信息");
                that.friendChatList.forEach(function(val,index){
                    let code = val.mappingCode;
                    //找到聊天列表中与当前接收到的信息为同一人的对象
                    if(code == mappingCode){
                        //更新信息和时间
                        val.message = reData.message;
                        val.showTime = that.$comfunc.getHHmm();
                    }
                });
            },
            //查询聊天界面信息
            getChatInfo(){
                let that = this;
                //连接websocket的userCode
                let req = {"userCode":that.fromCode};
                that.$api.getChatInfo(req).then(res => {
                    //好友聊天列表
                    that.friendChatList = res.data.data.friendChatList;
                    //所有的聊天记录
                    that.chatRecord = res.data.data.chatRecord;
                }).catch(err => {
                    that.$commsgbox.alert(err);
                });
            },
            //初始化websocket
            initWebSocket(){
                //ws地址
                const wsUri = this.webSocketUrl +"?"+this.fromCode;
                this.websock = new WebSocket(wsUri);
                //绑定message响应
                this.websock.onmessage = this.websocketOnmessage;
                //绑定关闭响应
                this.websock.onclose = this.websocketClose;
            },
            //初始化加载表情包列表
            initEmoji(){
                for (let i in appData){//读取json文件保存数据给数组
                    this.faceList.push(appData[i].char);
                }
            },
        },
        created() {
            //初始化websocket组件和方法
            this.initWebSocket();
            //初始化查询个人信息 好友列表 和所有的聊天信息
            this.getChatInfo();
        },
        mounted() {
            this.initEmoji();
        },
        filters: {
            time:function(time){
                return this.$comfunc.timeFormat(time);
            },
        }
    }
</script>
<style scoped>
    .message-time{
        background-color: #DADADA;
        padding:1px 0px;
    }
    .add-chatGroup-left{
        width:350px;
        float: left;
        height: 300px;
        overflow-y: auto;
        /*右边框*/
        border-width: 0 1px 0 0;
        border-style: solid;
        border-color: black;
    }
    .add-chatGroup-right{
        width: 400px;
        float: left;
        height:300px;
        overflow-y: auto;
    }
    .chat-message-left-nickName{
        text-align: left;
        margin: 0px 10px;
    }
    .chat-message-right-nickName{
        text-align: right;
        margin: 0px 10px;
    }
    .msg-detail{
        padding: 0px 15px;
    }
    .chat-msg-right{
        text-align: center;
        min-height:35px;
        height:max-content;
        line-height: 35px;
        margin: 5px 10px;
        background-color: #9EEA6A;
        border-radius:5px;
        width:max-content;
        float:right;
        max-width:250px;
        word-wrap: break-word;
    }
    .msg-portrait-right{
        width:35px;
        height: 35px;
        margin-right:20px;
        margin-top:10px;
    }
    .msg-portrait-right img{
        display: block;
        width: 35px;
        height: 35px;
    }
    .chat-msg-left{
        text-align: center;
        min-height:35px;
        height:max-content;
        line-height: 35px;
        margin: 5px 10px;
        background-color: #FFFFFF;
        border-radius:5px;
        width:max-content;
        max-width:250px;
        word-wrap: break-word;
    }
    .msg-portrait-left{
        width:35px;
        height: 35px;
        margin: 10px 15px;
    }
    .msg-portrait-left img{
        display: block;
        width: 35px;
        height: 35px;
    }
    .main-chat-input{
        height: 155px;
        background-color: #ffffff;
    }
    .main-msg{
        height:250px;
        overflow-y: auto;
    }

    .chat-newMsg{
        text-align: left;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .chat-nickName{
        text-align: left;
        font-size: 16px;
        font-weight: bold;
        width: 100px;
        float: left;
    }
    .chat-msgTime{
        text-align: right;
        font-size: 12px;
        width: 20px;
    }
    .chat-record-add{
        float: right;
        width: 25px;
        height: 25px;
        margin-right: 10px;
        margin-top:20px;
        background-color: #DCD9D8;
    }
    .head-portrait{
        margin:20px 12px;
    }
    .head-portrait img {
        display: block;
        width: 35px;
        height: 35px;
    }
    a{
        text-decoration:none;
    }
    a:hover{color: black}
    input {
        background-color:transparent;
    }
    .chat-record-input{
        width: 175px;
        margin-left: 10px;
        line-height: 65px;
        float: left;
    }

    .left-aside{
        width: 60px;
        background-color: #28292C;
    }
    .chat_body{
        height: 500px;
        border: #99a9bf solid 1px;
    }
    .el-container{
        height: 500px;
        margin-bottom: 40px;
    }
    .el-aside {
        background-color: #EEEAE8;
    }
    .el-main {
        background-color: #F5F5F5;
        padding: 0px;
    }

    .chat-record{
        height: 65px;
        width:230px;
    }
    .main-title{
        height: 65px;
        border-bottom: #99a9bf solid 1px;
        font-size: 16px;
        font-weight: bold;
        text-align: left;
        line-height: 65px;
        padding-left: 25px;
    }

    /*表情*/
    .emotionSelect{
        border: none;
        padding:5px 10px;
        float:left;
    }
    .emotionList{
        display: flex;
        flex-wrap: wrap;
        padding:5px;
    }
    .emotionItem{
        width:10%;
        font-size:20px;
        text-align:center;
    }
    /*包含以下四种的链接*/
    .emotionItem {
        text-decoration: none;
    }
    /*正常的未被访问过的链接*/
    .emotionItem:link {
        text-decoration: none;
    }
    /*已经访问过的链接*/
    .emotionItem:visited {
        text-decoration: none;
    }
    /*鼠标划过(停留)的链接*/
    .emotionItem:hover {
        text-decoration: none;
    }
    /* 正在点击的链接*/
    .emotionItem:active {
        text-decoration: none;
    }
</style>

<style lang="scss">
    /* el-popover是和app同级的，所以scoped的局部属性设置无效 */
    /* 需要设置全局style */
    .el-popover{
        height:200px;
        width:300px;
        overflow-y:auto;
    }
</style>