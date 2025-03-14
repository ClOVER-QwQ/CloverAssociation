<template>
  <div class="container">
  <div class="left_column">
  <table class="creator-mean">
    <tbody>
    <tr v-for="creator in creators" :key="creator.id">
      <td>
        <img :src="creator.avatar"  alt="无"/>
        <br>
        <p><RouterLink class="name" :to="{name:'Creator',query:{id:creator.id}}">{{creator.name}}</RouterLink></p>
      </td>
    </tr>
    </tbody>
  </table>
  </div>
  <div class="right_column">
    <RouterView/>
  </div>
  </div>
</template>

<script lang="ts" name="Creators" setup>
import { reactive, onMounted } from "vue";
import axios from "axios";
import type { creators } from "@/types/creator.ts";

// 初始化响应式对象
const creators = reactive<creators>([{
  id: 0,
  name: "",
  avatar: "",
  description: "",
  birthDate: "",
  address: "",
  phone: "",
  email: "",
  experience: "",
  skill: "",
  createdAt: "",
  updatedAt: "",
}]);

// 在组件挂载后获取数据
onMounted(() => {
  axios.get("http://47.94.43.180:8080/creator/getBasicAll")
      .then((res) => {
        creators.splice(0, creators.length, ...res.data); // 更新响应式数组
      })
      .catch((err) => {
        console.error("Failed to fetch creator:", err);
      });
});
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
  height: 100vh; /* 确保容器占满整个屏幕高度 */
}

.left_column {
  width: 10%; /* 左侧栏宽度 */
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.right_column {
  flex: 1;
  padding: 20px;
  position: relative; /* 相对定位，用于伪元素定位 */
  height: 100%; /* 确保高度填满容器 */
  overflow: hidden; /* 防止内容溢出 */
}

.creator-mean {
  width: 100%; /* 确保表格宽度占满左侧栏 */
  vertical-align: middle;
  text-align: center;
  border-collapse: collapse; /* 合并内外边框 */
}

.creator-mean td,
.creator-mean th {
  border: 2px solid #ddd; /* 为单元格和表头单元格添加边框 */
  padding: 10px; /* 添加内边距 */
}

.name {
  color: green;
  font-size: 16px; /* 调整字体大小 */
  text-decoration: none;
}

img {
  width: 100px; /* 调整图片大小 */
  border-radius: 50%; /* 添加圆角效果 */
}
</style>