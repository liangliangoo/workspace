<template>
    <el-carousel type="card" height="400px" width="100%">
        <el-carousel-item v-for="(item,i) in videoList" :key="i" >
            <video :src="item.fileUrl" controls autoplay preload="auto" language="zh-CN" class="video" width="100%" height="96%"></video>
        </el-carousel-item>
    </el-carousel>
</template>

<script>
    export default {
        name: 'videoPlayer',
        data(){
            return {
                videoList:[],
            }
        },
        mounted() {
            this.initVideo();
        },
        methods: {
            initVideo() {
                //初始化视频方法 循环列表获取每个视频的id
                this.videoList.map((item,i)=>{
                    let myPlayer = this.$video('myVideo'+item.fileCode, {
                        //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
                        controls: true,
                        //自动播放属性,muted:静音播放
                        autoplay: "false",
                        //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
                        preload: "auto",
                        //设置视频播放器的显示宽度（以像素为单位）
                        width: "650px",
                        //设置视频播放器的显示高度（以像素为单位）
                        height: "300px",
                        //封面图
                        poster:item.fileName,
                        language: "en",// 初始化语言
                        muted:false, // 是否静音,
                        // 倍速播放
                        playbackRates: [0.5, 1, 1.5, 2],

                    });
                })
            },
            queryFileInfo(){
                let req = {"fileType":"video"};
                this.$api.queryFileInfo(req).then(res => {
                    this.videoList = res.data.rows;
                }).catch(err => {
                    this.$commsgbox.alert(err);
                });
            },
        },
        created(){
            // this.queryFileInfo();
        }
    }
</script>

<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 500px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>
