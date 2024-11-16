<template>
  <div class="post-lost-item">
    <h1 class="form-title">发布帖子</h1>
    <img :src="userAvatar" alt="头像" class="avatar" @click="goToProfile" />
    <el-form
      :model="postData"
      ref="form"
      label-width="150px"
      label-position="left"
      @submit.prevent="submitPost"
      class="post-form"
    >
      <el-form-item
        label="物品图片"
        class="form-item"
        prop="image"
        :rules="[
          { required: true, message: '请上传物品图片', trigger: 'change' },
        ]"
      >
        <el-upload
          class="upload-demo"
          :before-upload="beforeUpload"
          :on-change="handleImageChange"
          :show-file-list="false"
          action=""
        >
          <el-button type="primary" class="upload-button">点击上传</el-button>
        </el-upload>
        <div v-if="imagePreview" class="image-preview">
          <div class="preview-container">
            <img :src="imagePreview" alt="图片预览" class="preview-img" />
            <span class="delete-icon" @click="deleteImage">&#10005;</span>
          </div>
        </div>
      </el-form-item>
      <el-form-item
        label="姓名"
        prop="name"
        class="form-item"
        :rules="inputRules('请输入姓名')"
      >
        <el-input v-model="postData.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item
        label="电话号码"
        prop="phone"
        class="form-item"
        :rules="inputRules('请输入电话号码')"
      >
        <el-input v-model="postData.phone" placeholder="请输入电话号码" />
      </el-form-item>
      <el-form-item
        label="丢失物品描述"
        prop="description"
        class="form-item"
        :rules="inputRules('请输入物品描述')"
      >
        <el-input
          type="textarea"
          v-model="postData.description"
          placeholder="请输入物品描述"
        />
      </el-form-item>
      <el-form-item
        label="丢失或找到的位置"
        prop="location"
        class="form-item"
        :rules="inputRules('请输入丢失或找到的位置')"
      >
        <el-input
          type="textarea"
          v-model="postData.location"
          placeholder="请输入位置"
        />
      </el-form-item>
      <el-form-item class="form-item">
        <el-button type="primary" class="submit-button" @click="submitPost(0)">
          发布到寻找失主
        </el-button>
        <el-button type="success" class="submit-button" @click="submitPost(1)">
          发布到寻找失物
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userAvatar: "",
      imagePreview: "",
      postData: {
        image: null,
        name: "",
        phone: "",
        description: "",
        location: "",
      },
    };
  },
  created() {
    // 组件创建时获取用户头像
    this.fetchUserAvatar();
  },
  methods: {
    goToProfile() {
      // 路由跳转至个人主页
      this.$router.push("/personalhome");
    },
    fetchUserAvatar() {
      const token = localStorage.getItem("token");
      // 获取头像
      this.$axios
        .get("/api/home", { headers: { Authorization: `Bearer ${token}` } })
        .then((result) => {
          this.userAvatar = result.data.avatar;
        });
    },
    beforeUpload(file) {
      // 检查文件类型是否为图片
      const isImage = file.type.startsWith("image/");
      if (!isImage) this.$message.error("只能上传图片文件");
      return isImage;
    },
    handleImageChange(file) {
      // 获取当前文件对象
      const currentFile = file.raw || file.file;
      if (currentFile) {
        const isImage = currentFile.type.startsWith("image/");
        if (isImage) {
          // 存储上传的原始文件
          this.postData.image = currentFile;
          const reader = new FileReader();
          reader.onload = () => {
            // 读取文件并设置预览
            this.imagePreview = reader.result;
          };
          // 将文件读取为DataURL
          reader.readAsDataURL(currentFile);
        }
      }
    },
    deleteImage() {
      // 清除图片预览
      this.imagePreview = "";
      // 清除上传的图片数据
      this.postData.image = null;
    },
    submitPost(pageId) {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 创建FormData对象以进行文件上传
          const formData = new FormData();
          Object.entries(this.postData).forEach(([key, value]) => {
            if (value) formData.append(key, value);
          });
          this.$axios
            .post(`/api/post${pageId}`, formData, {
              headers: {
                Authorization: `Bearer ${this.token}`,
              },
            })
            .then((result) => {
              // 当响应的编码为 0 时，说明发帖成功
              if (result.data.code == 0) {
                this.$message({
                  message: result.data.message,
                  type: "success",
                });
                this.$router.push("/home");
              }
              // 当响应的编码不为 0 时，说明发帖失败
              else {
                this.$message({
                  message: result.data.message,
                  type: "warning",
                });
              }
            });
        }
      });
    },
    inputRules(message) {
      return [{ required: true, message, trigger: "blur" }];
    },
  },
};
</script>

<style scoped>
/* 整体样式 */
.post-lost-item {
  padding: 30px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom right, #6a82fb, #f2c1cb);
}

/* 标题样式 */
.form-title {
  text-align: center;
  font-size: 35px;
  font-weight: bold;
  color: #2c3e50;
  margin-bottom: 20px;
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

/* 表单样式 */
.post-form {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  width: 100%;
}

/* 表单项位置 */
.form-item {
  margin-bottom: 20px;
}

/* 上传按钮样式 */
.upload-button {
  padding: 8px 16px;
}

/* 提交按钮样式 */
.submit-button {
  margin-right: 15px;
  width: 150px;
}

/* 边角圆润 */
.el-input,
.el-button {
  border-radius: 6px;
}

/* 预览位置 */
.preview-container {
  position: relative;
  display: inline-block;
}

/* 预览样式 */
.preview-img {
  max-width: 300px;
  max-height: 300px;
  object-fit: cover;
  border-radius: 6px;
}

/* 红叉样式 */
.delete-icon {
  position: absolute;
  top: 5px;
  right: 5px;
  font-size: 20px;
  color: white;
  cursor: pointer;
  z-index: 10;
  background-color: red;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 红叉特效 */
.delete-icon:hover {
  background-color: darkred;
}
</style>
