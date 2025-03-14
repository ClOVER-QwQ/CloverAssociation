<template>

  <div class="background-container">
    <div class="header-container">
      <h1 class="main-title">四叶草结社</h1>
      <div class="login-register-btn">
        <!-- 如果用户已登录，显示头像 -->
        <div v-if="isLoggedIn" class="user-avatar">
          <img :src="avatar" alt="用户头像" @click="goToProfile"/>
        </div>
        <!-- 如果用户未登录，显示登录/注册按钮 -->
        <button v-else id="loginRegisterBtn" @click="isUser">登录/注册</button>
      </div>
      <div v-if="nav">
        <table class="main-mean">
          <tbody style="border: none">
          <tr>
            <td>
              <RouterLink :to="{name:'Index'}" class="linker">首页</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'Creators'}" class="linker">开发者</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'Products'}" class="linker">产品</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'support'}" class="linker">支持</RouterLink>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div v-else>
        <table class="main-mean">
          <tbody style="border: none">
          <tr>
            <td>
              <RouterLink :to="{name:'Index'}" class="linker">首页</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'CCreators'}" class="linker">开发者</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'Products'}" class="linker">产品</RouterLink>
            </td>
            <td>
              <RouterLink :to="{name:'support'}" class="linker">用户</RouterLink>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div>
      <RouterView/>
    </div>
    <footer>
      <a class="ju"
         href="https://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=Iv1GJjGqx8GemwNkt6H0iU33UKn0AaXK&authKey=gy3SUXfs2lts3MBaY2C%2F6hCs4POH%2FdPD8aqQGSVd25tLHPu0ojsbOwV53Ofqt8Bw&noverify=0&group_code=677477238"
         target="_blank"><b>加入我们</b></a>
      <div class="last">
        <p class="cp">2024 四叶草结社. 保留所有权利</p>
        <a class="icp" href="https://beian.miit.gov.cn/" target="_blank">皖ICP备2024039386号-1</a>
      </div>
    </footer>
  </div>
</template>

<script lang="ts" name="App" setup>
import {RouterLink, RouterView, useRouter} from "vue-router";
import useAppStore from "@/store/app.ts";
import {storeToRefs} from "pinia";

const appStore = useAppStore();
const {nav, isLoggedIn, avatar} = storeToRefs(appStore);

const router = useRouter();

function goToProfile() {
  router.push("/profile");
}

function isUser() {
  router.replace('/logReg');
}
</script>

<style scoped>
.header-container {
  margin-bottom: 20px;
}

.login-register-btn {
  position: fixed;
  top: 20px; /* 根据需要调整，使其位于右上角 */
  right: 20px; /* 根据需要调整，使其位于右上角 */
  z-index: 1000; /* 确保按钮在背景图之上 */
}

.login-register-btn button {
  padding: 10px 20px;
  font-size: 16px;

  color: #4CAF50; /* 设置字体颜色为绿色 */
  background-color: rgba(50, 50, 50, 0.7);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影，提升按钮立体感 */
  transition: background-color 0.3s, box-shadow 0.3s; /* 平滑过渡效果 */
}

.login-register-btn button:hover {
  background-color: rgba(0, 120, 255, 1); /* 鼠标悬停时改变背景色 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 鼠标悬停时改变阴影 */
}

.user-avatar img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  cursor: pointer; /* 添加鼠标悬停效果 */
  transition: transform 0.3s; /* 添加平滑过渡效果 */
}

.user-avatar img:hover {
  transform: scale(1.1); /* 鼠标悬停时放大头像 */
}

.background-container {
  background-image: url('@/assets/mainbk.jpg');
  background-size: cover;
  min-height: 100vh; /* 确保内容区域有足够的高度 */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
}

.main-title {
  margin: 0;
  padding: 0;
  color: green;
  font-size: 50px;
  text-align: center;
}

.main-mean {
  font-size: 30px;
  color: green;
  border: 2px solid;
  padding: 4px;
  border-spacing: 2px;
  border-collapse: collapse;
}

.main-mean td, .main-mean th {
  border: 2px solid;
}

.linker {
  color: green;
  text-decoration: none;
}

.ju {
  color: green;
  text-decoration: none;
  font-size: 20px;
}

.cp {
  color: white;
  font-size: 10px;
}

.icp {
  color: white;
  text-decoration: none;
  font-size: 10px;
}

.last {
  line-height: 0.5;
}

footer {
  background-color: black;
  text-align: center;
  line-height: 1.5;
  padding: 10px 0;
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>