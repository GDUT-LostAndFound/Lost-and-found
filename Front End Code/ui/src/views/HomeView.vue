<template>
  <div class="home-view">
    <h1>广东工业大学失物招领系统</h1>
    <div class="button-container">
      <el-button type="primary" @click="goToRecover" class="large-button"
        >找回物品</el-button
      >
      <el-button type="success" @click="goToPost" class="large-button"
        >发布帖子</el-button
      >
    </div>
    <img :src="userAvatar" alt="头像" class="avatar" @click="goToProfile" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      userAvatar: "",
    };
  },
  created() {
    this.fetchUserAvatar();
  },
  methods: {
    // 跳转找回物品
    goToRecover() {
      this.$router.push("/recover");
    },
    // 跳转发布帖子
    goToPost() {
      this.$router.push("/post");
    },
    // 跳转个人主页
    goToProfile() {
      this.$router.push("/profile");
    },
    // 获取用户头像
    fetchUserAvatar() {
      // 从 localStorage 获取 Token
      const token = localStorage.getItem("token");
      this.$axios
        .get("/api/home", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((result) => {
          this.userAvatar = result.data.avatar;
        });
    },
  },
};
</script>

<style scoped>
/* 背景样式 */
.home-view {
  background: linear-gradient(to bottom right, #5f67f1, #dcddf5);
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  position: relative;
}

/* 字体样式 */
h1 {
  font-family: "Arial", sans-serif;
  font-size: 36px;
  color: #333;
  margin-bottom: 40px;
}

/* 按钮布局 */
.button-container {
  display: flex;
  gap: 20px;
}

/* 按钮样式 */
.large-button {
  width: 250px;
  height: 70px;
  font-size: 22px;
  border-radius: 35px;
  transition: transform 0.3s, box-shadow 0.3s;
}

/* 特效样式 */
.large-button:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 30px rgba(0, 0, 0, 0.3);
}

/* 头像样式 */
.avatar {
  position: absolute;
  top: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  cursor: pointer;
  transition: transform 0.3s;
}

/* 头像特效 */
.avatar:hover {
  transform: scale(1.1);
}
</style>
