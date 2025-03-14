<template>
  <div class="register-container">
    <h2>用户注册</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="confirmPassword">确认密码</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <button type="submit">注册</button>
    </form>
    <p class="login-link">
      已有账号？<a href="#" @click="goToLogin">返回登录</a>
    </p>
  </div>
</template>

<script setup name="UReg" lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const username = ref(''); // 用户名
const password = ref(''); // 密码
let response = ref();
const confirmPassword = ref(''); // 确认密码
const router = useRouter();

// 提交表单的逻辑
async function submitForm() {
  // 确认密码是否一致
  if (password.value !== confirmPassword.value) {
    alert('密码和确认密码不匹配！');
    return;
  }

  // 调用后端注册接口
  try {
    await axios.post(`http://47.94.43.180:8080/user/reg/${username.value}/${password.value}`).then(
        (res) => {
          response.value = res.data;
        }
    );

    console.log(response.value)

    if (response.value === 1) {
      alert('注册成功！');
      await router.push('/logReg/uLog'); // 注册成功后跳转到登录页面
    } else {
      alert('用户名已存在或者用户名，密码不合要求');
    }
  } catch (error) {
    console.error('注册失败:', error);
    alert('注册失败，请稍后再试');
  }
}

// 返回登录页面
function goToLogin(event: Event) {
  event.preventDefault(); // 阻止默认的链接行为
  router.push('/logReg/uLog'); // 跳转到登录页面
}
</script>

<style scoped>
.register-container {
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
  background-color: #28a745; /* 绿色背景 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px; /* 添加顶部间距 */
}

button:hover {
  background-color: #218838; /* 深绿色背景 */
}

.login-link {
  margin-top: 10px;
  font-size: 0.9em;
  text-align: center; /* 文字居中 */
}

.login-link a {
  color: #007bff;
  text-decoration: none;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>