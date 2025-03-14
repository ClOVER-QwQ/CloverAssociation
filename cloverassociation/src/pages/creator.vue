<template>
  <div class="container">
    <div class="container">
      <div class="left-section">
        <h1 style="color:white;">四叶</h1>
        <div style="color:white;">
          姓名:{{creator.name}}
          <br/>
          年龄:{{age}}
          <br/>
          邮箱:
          <br/>{{creator.email}}
          <br/>
          地址:{{creator.address}}
        </div>
      </div>

      <div class="right-section">
        <h2>工作经验</h2>
        {{creator.experience}}
        <h2>技能清单</h2>
        {{creator.skill}}
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="Creator">
import {computed, onMounted, reactive} from "vue";
import { defineProps } from "vue";
import axios from "axios";
import type { creator } from "@/types/creator.ts"; // 注意类型名称的大小写

// 定义 props，确保 id 是数字类型
const props = defineProps({
  id: {
    type: String,
    required: true
  }
});

// 初始化响应式对象
const creator = reactive<creator>({
  id: 0,
  name: '',
  avatar: '',
  description:'',
  birthDate: '', // 日期字符串
  address: '',
  phone: '',
  email: '',
  experience: '',
  skill: '',
  createdAt: '',
  updatedAt: ''
});

onMounted(()=>{
  axios.get(`http://47.94.43.180:8080/creator/get/${props.id}`)
      .then((res) => {
        Object.assign(creator, res.data); // 更新响应式对象
        console.log(res.data);
      })
      .catch((err) => {
        console.error("Failed to fetch creator data:", err);
      });
})

// 计算年龄
const calculateAge = (birthDate: string): number => {
  const birthDateObj = new Date(birthDate); // 将字符串转换为 Date 对象
  const today = new Date();
  let age = today.getFullYear() - birthDateObj.getFullYear();
  const monthDiff = today.getMonth() - birthDateObj.getMonth();
  if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDateObj.getDate())) {
    age--;
  }
  return age;
};

// 计算年龄的 computed 属性
const age = computed(() => {
  return calculateAge(creator.birthDate);
});
</script>

<style scoped>
html, body {
  margin: 0;
  padding: 0;
  width: 100%; /* 确保宽度填满视口 */
  height: 100%; /* 确保高度填满视口 */
}

.container {
  display: flex; /* 使用 Flex 布局 */
  width: 100%; /* 宽度填满父容器 */
  height: 100%; /* 高度填满父容器 */
  background-color: #f0f0f0; /* 可选：设置背景颜色以便观察效果 */
}
.left-section {
  background-color: green; /* 背景色 */
  flex: 0 0 20%; /* 固定宽度 20% */
  position: sticky; /* 粘性定位 */
  top: 0;
  height: 100vh; /* 高度为视口高度 */
  overflow-y: auto; /* 内容超出时可滚动 */
  align-items: center;
  display: flex;
  flex-direction: column;
}

.right-section {
  flex: 1 1 80%; /* 占据剩余 80% */
  padding-left: 20px;
  white-space: pre-wrap;
  background: white;
  height: 100%; /* 高度占满容器 */
}
</style>