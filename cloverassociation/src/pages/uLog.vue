<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">登录</button>
    </form>
    <p class="register-link">
      没有账号？<a href="#" @click="goToRegister">请注册</a>
    </p>
    <button class="back-button" @click="goBack">返回选择</button>
  </div>
</template>

<script setup name="ULog" lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";
import useAppStore from "@/store/app.ts";
import type { User } from "@/types/user.ts";

const appStore = useAppStore();
const username = ref("");
const password = ref("");
const router = useRouter();

// 提交表单的逻辑
async function submitForm() {
  try {
    const response = await axios.get<User>(`http://47.94.43.180:8080/user/log/${String(username.value)}/${String(password.value)}`);
    const user: User = response.data;

    if (user) {
      alert('登录成功！');
      appStore.setLoginStatus(true); // 设置登录状态为 true
      appStore.setUserId(String(user.id)); // 存储用户 ID
      appStore.setAvatar(user.avatar); // 存储头像路径
      await router.replace('/'); // 跳转到首页
    } else {
      alert("用户名或密码不正确");
    }
  } catch (error) {
    console.error(error);
    alert("登录失败，请重试");
  }
}

// 跳转到注册页面
function goToRegister(event: Event) {
  event.preventDefault(); // 阻止默认的链接行为
  router.push('/logReg/uReg'); // 跳转到注册页面
}

// 返回选择页面
function goBack() {
  router.push('/logReg');
}
</script>

<style scoped>
.login-container {
  width: 100%; /* 宽度适应父组件 */
  max-width: 300px; /* 最大宽度 */
  margin: 0 auto; /* 水平居中 */
  padding: 20px;
}

h2 {
  margin-bottom: 20px;
  text-align: center; /* 文字居中 */
}

.form-group {
  width: 100%;
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  text-align: left; /* 标签左对齐 */
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc; /* 设置边框样式 */
  border-radius: 4px; /* 设置圆角 */
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px; /* 添加顶部间距 */
}

button:hover {
  background-color: #0056b3;
}

.register-link {
  margin-top: 10px;
  font-size: 0.9em;
  text-align: center; /* 文字居中 */
}

.register-link a {
  color: #007bff;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}

.back-button {
  width: 100%;
  padding: 10px;
  background-color: green;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

.back-button:hover {
  background-color: darkgreen;
}
</style>