<script setup name="Product" lang="ts">
import type { product } from "@/types/product.ts";
import { onMounted, reactive } from "vue";
import axios from "axios";
import useAppStore from "@/store/app";
import { useRouter } from "vue-router";

const appStore = useAppStore();
const router = useRouter();

const prop = defineProps(['id']);

const product = reactive<product>({
  id: 0, // 假设初始 ID 为 0
  avatar: '', // 初始头像为空字符串
  name: '', // 初始名称为空字符串
  version: 1.0, // 初始版本为 1.0
  authorName: '', // 初始作者为空字符串
  readme: '', // 初始 README 内容为空字符串
  description: '', // 初始描述为空字符串
  updateContent: '', // 初始更新内容为空字符串
  link: '', // 初始链接为空字符串
  createdAt: '', // 初始创建时间为当前时间或空字符串
  updatedAt: '' // 初始更新时间为当前时间或空字符串
});

onMounted(() => {
  axios.get(`http://47.94.43.180:8080/product/get/${prop.id}`)
      .then((response) => {
        Object.assign(product, response.data);
      })
      .catch((error) => {
        console.error("获取产品信息失败", error);
      });
});

// 检查登录状态并处理下载
function handleDownload() {
  if (!appStore.isLoggedIn) {
    alert("请先登录后再进行下载！");
    router.push("/logReg/uLog"); // 跳转到登录页面
  } else {
    window.open(product.link, '_blank');
  }
}
</script>

<template>
  <div class="container">
    <div class="container">
      <div class="left-section">
        <h2 style="color:white;">{{ product.name }}</h2>
        <div style="color:white;">
          <br>
          作者: {{ product.authorName }}
          <br>
          <br>
          <h3>作者的话:</h3>
          {{product.readme}}
        </div>
      </div>

      <div class="right-section">
        <h2>简介</h2>
        {{ product.description }}
        <br>
        <h2>更新内容</h2>
        {{ product.updateContent }}
        <h2><a class="linker" @click="handleDownload">下载</a></h2>
      </div>
    </div>
  </div>
</template>

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

.linker {
  color: green;
  font-size: 40px;
  text-decoration: none;
  cursor: pointer;
}
</style>