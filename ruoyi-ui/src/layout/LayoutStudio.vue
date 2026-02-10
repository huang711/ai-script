<template>
  <div class="studio-layout">
    <!-- TopBar -->
    <div class="studio-header">
      <div class="header-left">
        <router-link to="/studio/dashboard" class="logo-link">
          <img v-if="logo" :src="logo" class="logo-img" />
          <h1 class="logo-title">工作空间</h1>
        </router-link>
      </div>
      
      <div class="header-right">
        <div class="avatar-container">
          <el-dropdown class="avatar-wrapper" trigger="click">
            <div class="avatar-wrapper">
              <img :src="avatar" class="user-avatar">
              <i class="el-icon-caret-bottom" />
            </div>
            <el-dropdown-menu slot="dropdown" class="user-dropdown">
              <router-link to="/user/profile">
                <el-dropdown-item>个人中心</el-dropdown-item>
              </router-link>
              <el-dropdown-item divided @click.native="logout">
                <span style="display:block;">退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <div class="studio-main">
      <router-view />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import logoImg from '@/assets/logo/logo.png'

export default {
  name: 'LayoutStudio',
  data() {
    return {
      logo: logoImg
    }
  },
  computed: {
    ...mapGetters([
      'avatar'
    ])
  },
  methods: {
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {});
    }
  }
}
</script>

<style lang="scss" scoped>
.studio-layout {
  min-height: 100vh;
  background-color: #f7f9fb;
  display: flex;
  flex-direction: column;
}

.studio-header {
  height: 50px;
  background: #fff;
  border-bottom: 1px solid #dcdfe6;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  position: sticky;
  top: 0;
  z-index: 1000;

  .header-left {
    display: flex;
    align-items: center;
    width: 200px;

    .logo-link {
      display: flex;
      align-items: center;
      text-decoration: none;
    }

    .logo-img {
      width: 32px;
      height: 32px;
      margin-right: 10px;
    }

    .logo-title {
      font-size: 18px;
      font-weight: 600;
      color: #303133;
      margin: 0;
    }
  }

  .header-center {
    flex: 1;
    display: flex;
    justify-content: center;
  }

  .header-right {
    width: 200px;
    display: flex;
    justify-content: flex-end;

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;
        display: flex;
        align-items: center;
        cursor: pointer;

        .user-avatar {
          cursor: pointer;
          width: 32px;
          height: 32px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 20px;
          font-size: 12px;
        }
      }
    }
  }
}

.studio-main {
  flex: 1;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  box-sizing: border-box;
}
</style>
