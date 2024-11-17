<template>
  <div class="lost-and-found">
    <el-header class="header">
      <div class="header-left">
        <el-button
          icon="el-icon-arrow-left"
          @click="goBack"
          class="back-button"
        ></el-button>
      </div>
      <div class="header-right">
        <img :src="userAvatar" alt="头像" class="avatar" @click="goToProfile" />
      </div>
    </el-header>
    <el-main>
      <div class="container">
        <div class="sidebar">
          <el-button
            class="category-button"
            type="primary"
            @click="toggleCategory('lost')"
            :class="{ active: category === 'lost' }"
          >
            寻找失物
          </el-button>
          <br />
          <el-button
            class="category-button"
            type="primary"
            @click="toggleCategory('found')"
            :class="{ active: category === 'found' }"
          >
            寻找失主
          </el-button>
        </div>
        <div class="content-area">
          <el-row :gutter="20">
            <el-col v-for="item in displayedItems" :key="item.id" :span="6">
              <el-card class="item-card" @click="showDetail(item)">
                <img
                  :src="item.image"
                  class="item-image"
                  @click="showDetail(item)"
                />
                <p @click="showDetail(item)">{{ item.description }}</p>
              </el-card>
            </el-col>
          </el-row>
          <el-pagination
            :current-page="currentPage"
            :page-size="pageSize"
            :total="totalItems"
            @current-change="handlePageChange"
            layout="total, prev, pager, next"
          ></el-pagination>
          <el-dialog :visible.sync="dialogVisible" title="物品详细信息">
            <img :src="selectedItem.image" class="dialog-image" />
            <div>
              <p><strong>姓名：</strong>{{ selectedItem.name }}</p>
              <p><strong>电话号码：</strong>{{ selectedItem.phone }}</p>
              <p><strong>物品描述：</strong>{{ selectedItem.description }}</p>
              <p><strong>物品位置：</strong>{{ selectedItem.location }}</p>
            </div>
          </el-dialog>
        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userAvatar: "",
      category: "lost",
      items: [],
      displayedItems: [],
      currentPage: 1,
      pageSize: 8,
      totalItems: 0,
      dialogVisible: false,
      selectedItem: {},
    };
  },
  mounted() {
    const token = localStorage.getItem("token");
    if (!token) {
      this.$router.push({ path: "/login" });
    } else {
      this.fetchUserAvatar();
      // 加载物品
      this.loadItems();
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    goToProfile() {
      this.$router.push({ path: "/personalhome" });
    },
    fetchUserAvatar() {
      const token = localStorage.getItem("token");
      this.$axios
        .get("/api/home", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((result) => {
          // 设置用户头像
          this.userAvatar = result.data.avatar;
        });
    },
    loadItems() {
      // 根据当前类别选择API端点
      const endpoint =
        this.category === "lost" ? "/api/lostitems" : "/api/founditems";
      // 请求物品数据
      this.$axios.get(endpoint).then((response) => {
        this.items = response.data.data;
        // 更新总物品数量
        this.totalItems = this.items.length;
        // 更新当前展示的物品
        this.updateDisplayedItems();
      });
    },
    updateDisplayedItems() {
      // 计算当前页物品的起始和结束索引
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      // 更新当前页面显示的物品
      this.displayedItems = this.items.slice(start, end);
    },
    handlePageChange(page) {
      // 更新当前页码
      this.currentPage = page;
      // 更新当前展示的物品
      this.updateDisplayedItems();
    },
    toggleCategory(category) {
      // 切换功能
      this.category = category;
      // 切换类别时重置为第一页
      this.currentPage = 1;
      // 重新加载物品
      this.loadItems();
    },
    showDetail(item) {
      // 设置选中的物品
      this.selectedItem = item;
      // 显示物品详情弹窗
      this.dialogVisible = true;
    },
  },
};
</script>

<style scoped>
/* 整体样式 */
.lost-and-found {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background: linear-gradient(135deg, #ff2942, #ffffff);
}

/* 头部样式 */
.header {
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
  background: linear-gradient(135deg, #ff0000, #ffffff);
}

/* 头部左边样式 */
.header-left {
  display: flex;
  align-items: center;
}

/* 头部右边样式 */
.header-right {
  display: flex;
  align-items: center;
  gap: 15px;
}

/* 返回按钮样式 */
.back-button {
  position: absolute;
  top: 20px;
  left: 20px;
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

/* 容器样式 */
.container {
  display: flex;
  padding: 20px;
  height: calc(100vh - 80px);
}

/* 侧栏样式 */
.sidebar {
  width: 220px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  padding: 20px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  background-color: #f4f4f4;
  border-radius: 8px;
}

/* 功能按钮样式 */
.category-button {
  margin-bottom: 50px;
  width: 100%;
  font-size: 16px;
  height: 45px;
  border-radius: 10px;
}

/* 功能按钮特效 */
.category-button.active {
  background-color: #409eff;
  color: white;
  box-shadow: 0 0 10px rgba(64, 158, 255, 0.5);
}

/* 内容样式 */
.content-area {
  flex: 1;
  padding-left: 30px;
  overflow-y: auto;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* 卡片样式 */
.item-card {
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  border-radius: 8px;
}

/* 卡片特效 */
.item-card:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

/* 页面图片样式 */
.item-image {
  width: 100%;
  height: auto;
  max-height: 300px;
  border-radius: 8px;
  object-fit: cover;
}

/* 弹窗图片样式 */
.dialog-image {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 300px;
  border-radius: 8px;
}

/* 页码样式 */
.el-pagination {
  margin-top: 20px;
}
</style>
