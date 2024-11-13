<template>
  <div class="register-background">
    <div class="title">广东工业大学失物招领系统</div>
    <el-card class="box-card">
      <h2>注册</h2>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-position="left"
        label-width="80px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password1">
          <el-input
            type="password"
            v-model="ruleForm.password1"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password2">
          <el-input
            type="password"
            v-model="ruleForm.password2"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="btnGroup">
        <el-button
          type="primary"
          @click="submitForm('ruleForm')"
          v-loading="loading"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="goBack">返回</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass1 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password1) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: "",
        password1: "",
        password2: "",
      },
      rules: {
        username: [
          { required: true, message: "用户名不能为空！", trigger: "blur" },
        ],
        password1: [
          { required: true, validator: validatePass1, trigger: "blur" },
        ],
        password2: [
          { required: true, validator: validatePass2, trigger: "blur" },
        ],
      },
      // 不显示加载动画
      loading: false,
    };
  },
  methods: {
    submitForm(formName) {
      // 验证表单中的账号密码是否有效，因为在上面rules中定义为了必填 required: true
      this.$refs[formName].validate((valid) => {
        // 点击登录后，让登录按钮开始转圈圈（展示加载动画）
        this.loading = true;
        // 如果经过校验，账号密码都不为空，则发送请求到后端登录接口
        if (valid) {
          // 使用 axios 将登录信息发送到后端
          // 当收到后端的响应时执行该括号内的代码，result 为响应信息，也就是后端返回的信息
          this.$axios
            .post("/api/register", {
              username: this.ruleForm.username,
              password: this.ruleForm.password1,
            })
            .then((result) => {
              // 当响应的编码为 0 时，说明注册成功
              if (result.data.code == 0) {
                // 显示后端响应的成功信息
                this.$message({
                  message: result.data.message,
                  type: "success",
                });
                // 重置表单
                this.resetForm(formName);
              }
              // 当响应的编码不为 0 时，说明注册失败
              else {
                // 显示后端响应的失败信息
                this.$message({
                  message: result.data.message,
                  type: "warning",
                });
              }
              // 不管响应成功还是失败，收到后端响应的消息后就不再让登录按钮显示加载动画了
              this.loading = false;
              console.log(result);
            });
        }
        // 如果账号或密码有一个没填，就直接提示必填，不向后端请求
        else {
          console.log("error submit!!");
          this.loading = false;
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
/* 背景样式 */
.register-background {
  background: linear-gradient(to right, #54aefe, #9ef8fd);
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

/* 字体样式 */
.title {
  font-family: "Arial", sans-serif;
  font-size: 36px;
  color: #333;
  margin-bottom: 40px;
}

/* 注册面板样式 */
.box-card {
  width: 400px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  padding: 20px;
  background-color: white;
}

/* 按钮样式 */
.btnGroup {
  display: flex;
  justify-content: space-between;
}
</style>