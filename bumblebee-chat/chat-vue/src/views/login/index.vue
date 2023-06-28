<template>
    <div class="login-wrap">
        <el-header height="100px">有缘</el-header>

        <div class="ms-login">
            <div class="ms-title">欢迎登录</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="userName">
                    <el-input v-model="param.userName" placeholder="输入账号">
                        <el-button slot="prepend" icon="el-icon-user"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="passWord" style="margin-top:25px;">
                    <el-input type="password" placeholder="登录密码" v-model="param.passWord" @keyup.enter.native="submitForm()">
                        <el-button slot="prepend" icon="el-icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                    <span>注：账号密码随便填写（ admin 是管理员，其他为普通用户）</span>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
import Cookies from "js-cookie";
export default {
    components :{
    },
    data: function() {
        return {
            param: {
                userName: 'admin',
                passWord: '123456'
            },
            rules: {
                userName: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
                passWord: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
            }
        };
    },
    methods: {
        // 根据路径绑定到对应的一级菜单，防止页面刷新重新跳回第一个
        toIndex() {
            console.log(this.$route.path.split('/')[1]);
            return this.$route.path.split('/')[1];
        },
        // 切换菜单栏
        handleSelect(index) {
            this.$router.push('/' + index);
        },
        submitForm() {
            this.$refs.login.validate(valid => {
                if (valid) {
                    let req = {"passWord":this.param.passWord,"userName":this.param.userName}
                    this.$api.Login(req).then(res => {
                        let nickName = req.userName;
                        Cookies.set("ydy-token",res.data.data.token,{ expires: 1 }) //1day
                        sessionStorage.setItem("userCode",res.data.data.user.userCode);
                        if(res.data.data.user.nickName != null ){
                            nickName = res.data.data.user.nickName;
                        }
                        sessionStorage.setItem("nickName",nickName);
                        sessionStorage.setItem("headPortrait",res.data.data.user.headPortrait);
                        sessionStorage.setItem("webSocketUrl",res.data.data.webSocketUrl);
                        let redirectUrl = decodeURIComponent(this.$route.query.redirect || '/')
                        // 跳转到指定的路由
                        this.$router.push({
                            path: redirectUrl
                        })
                    }).catch(err => {
                        this.$commsgbox.alert(err);
                    });
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        }
    }
};
</script>

<style scoped>
.login-wrap {
    position: absolute;
    width: 100%;
    height: 100%;
    background: url('../../assets/img/newlogin-bg.jpg');
}
.login-wrap .el-header {
    z-index:1;
    margin-top: 2%;
    color: rgba(255, 255, 255, 1);
    text-align: center;
    font-size: 50px;
    font-weight: 800;
    line-height: 100px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, .12);
}
.ms-title {
    width: 100%;
    text-align: center;
    font-size: 22px;
    margin: 25px 0px 15px;
}
.ms-login {
    position: relative;
    width: 450px;
    height: 335px;
    max-width: 90%;
    margin: 100px auto;
    border-radius: 5px;
    background: white;
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    margin-top: 10%;
}
.login-btn button {
    width: 100%;
    height: 36px;
    text-align: center;
    margin-bottom: 10px;
}
</style>
