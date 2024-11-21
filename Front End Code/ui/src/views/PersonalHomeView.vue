<template>
  <el-container class="profile-container">
    <el-header>
      <h1 class="profile-title">个人主页</h1>
    </el-header>
    <el-main>
      <div class="profile-item">
        <el-upload
          ref="upload"
          :show-file-list="false"
          :before-upload="beforeAvatarUpload"
          :on-change="avatarChange"
          action=""
        >
          <div class="avatar-wrapper">
            <img :src="user.avatar" alt="User Avatar" class="avatar" />
          </div>
        </el-upload>
      </div>
      <el-form class="form-container">
        <div class="back-button">
          <el-button @click="goHome">返回</el-button>
        </div>
        <el-form-item label="用户名">
          <el-input
            v-model="newUsername"
            @input="isUsernameChanged = true"
            placeholder="请输入新用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input
            type="tel"
            v-model="newPhone"
            @input="isPhoneChanged = true"
            placeholder="请输入新手机号"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input
            type="text"
            v-model="newPassword"
            @input="isPasswordChanged = true"
            placeholder="请输入新密码"
          ></el-input>
        </el-form-item>
        <div class="button-group">
          <el-button type="primary" @click="confirmChanges">确认更改</el-button>
          <el-button type="danger" @click="logout">退出登录</el-button>
        </div>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      user: {
        avatar: "",
        username: "",
        phone: "",
        password: "",
      },
      avatarFile: null,
      newUsername: "",
      newPhone: "",
      newPassword: "",
      isUsernameChanged: false,
      isPhoneChanged: false,
      isPasswordChanged: false,
    };
  },
  mounted() {
    const token = localStorage.getItem("token");
    if (!token) {
      this.$router.push("/login");
    }
  },
  created() {
    this.fetchUserData();
  },
  methods: {
    // 获取用户数据并初始化表单字段
    fetchUserData() {
      const token = localStorage.getItem("token");
      this.$axios
        .get("/api/personalhome", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((result) => {
          this.user = result.data;
          this.newUsername = this.user.username;
          this.newPhone = this.user.phone;
          this.newPassword = this.user.password;
        });
    },
    // 处理头像文件选择事件
    avatarChange(file) {
      // 获取用户选择的文件
      this.avatarFile = file.raw;
      // 创建文件读取器
      const reader = new FileReader();
      // 设置头像为上传的新图片
      reader.onload = (e) => {
        this.user.avatar = e.target.result;
      };
      // 以数据URL格式读取文件
      reader.readAsDataURL(this.avatarFile);
    },
    // 检查文件类型
    beforeAvatarUpload(file) {
      const isImage = file.type.startsWith("image/");
      if (!isImage) {
        this.$message.error("上传头像只能是图片!");
      }
      return isImage;
    },
    // 确认更改用户信息
    confirmChanges() {
      const token = localStorage.getItem("token");
      // 验证输入不能为空
      if (!this.newUsername || !this.newPhone || !this.newPassword) {
        this.$message.error("用户名、手机号和密码不能为空！");
        return;
      }
      // 存储需要更新的字段
      const updates = {};
      updates.username = this.newUsername;
      updates.phone = this.newPhone;
      updates.password = this.newPassword;
      // 创建FormData对象来处理文件上传
      const formData = new FormData();
      // 添加头像文件
      formData.append("avatar", this.avatarFile);
      // 将需要更新的字段添加到FormData对象中
      Object.keys(updates).forEach((key) => {
        formData.append(key, updates[key]);
      });
      this.$axios
        .put("/api/update", formData, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((result) => {
          // 当响应的编码为 0 时，说明更新成功
          if (result.data.code == 0) {
            // 更新本地状态，重置编辑状态
            this.isUsernameChanged = false;
            this.isPhoneChanged = false;
            this.isPasswordChanged = false;
            this.avatarFile = null;
            // 刷新用户数据
            this.fetchUserData();
            this.$message({
              message: result.data.message,
              type: "success",
            });
          }
          // 当响应的编码不为 0 时，说明更新失败
          else {
            this.$message({
              message: result.data.message,
              type: "warning",
            });
          }
        });
    },
    // 返回
    goHome() {
      this.$router.go(-1);
    },
    // 退出登录
    logout() {
      // 移除token
      localStorage.removeItem("token");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
/* 主页容器样式 */
.profile-container {
  width: 100%;
  height: 100vh;
  padding: 20px;
  background: linear-gradient(135deg, #530071, #ffffff);
}

/* 标题样式 */
.profile-title {
  text-align: center;
  font-size: 28px;
  font-weight: bold;
  color: #2c3e50;
  margin-bottom: 20px;
}

/* 信息样式 */
.profile-item {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

/* 头像容器样式 */
.avatar-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 头像样式 */
.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 2px solid #008cba;
  cursor: pointer;
}

/* 按钮样式 */
.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

/* 表单容器样式 */
.form-container {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin-left: 38%;
  margin-right: 38%;
}

/* 返回样式 */
.back-button {
  margin-bottom: 10px;
}
</style>
