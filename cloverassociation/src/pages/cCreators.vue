<template>
  <div class="creator-table">
    <h2>创作者管理</h2>
    <div class="controls">
      <input type="text" v-model="searchQuery" placeholder="搜索创作者" />
      <button @click="fetchCreators">搜索</button>
      <button @click="addCreator">新增</button>
    </div>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>头像</th>
        <th>姓名</th>
        <th>描述</th>
        <th>操作</th>
        <th>作品ID</th>
        <th>作品名称</th>
        <th>创作时间</th>
        <th>更新时间</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="creator in creators" :key="creator.id">
        <td>{{ creator.id }}</td>
        <td>
          <img :src="creator.avatar" alt="头像" class="avatar" />
        </td>
        <td>{{ creator.name }}</td>
        <td>{{ creator.description }}</td>
        <td>
          <button @click="editCreator(creator.id)">修改</button>
          <button @click="deleteCreator(creator.id)">删除</button>
        </td>
        <td>
          <ul>
            <li v-for="product in creator.products" :key="product.id">
              {{ product.id }}
            </li>
          </ul>
        </td>
        <td>
          <ul>
            <li v-for="product in creator.products" :key="product.id">
              {{ product.name }}
            </li>
          </ul>
        </td>
        <td>
          <ul>
            <li v-for="product in creator.products" :key="product.id">
              {{ product.createdAt }}
            </li>
          </ul>
        </td>
        <td>
          <ul>
            <li v-for="product in creator.products" :key="product.id">
              {{ product.updatedAt }}
            </li>
          </ul>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button  @click="previousPage">上一页</button>
      <span>当前页: {{ currentPage }}</span>
      <button  @click="nextPage">下一页</button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import axios from "axios";

// 定义接口
interface CCreator {
  id: number;
  name: string;
  description: string;
  avatar: string;
  birthDate: string;
  address: string;
  phone: string;
  email: string;
  experience: string;
  skill: string;
  createdAt: string;
  updatedAt: string;
  products: CProduct[];
}

interface CProduct {
  id: number;
  avatar: string;
  name: string;
  version: number;
  authorId: number;
  authorName: string;
  readme: string;
  description: string;
  updateContent: string;
  link: string;
  createdAt: string;
  updatedAt: string;
}

// 响应式状态
const creators = ref<CCreator[]>([]);
const searchQuery = ref("");
const currentPage = ref(1);
const pageSize = ref(3);
const lastPage = ref(1);

// 方法
const fetchCreators = async () => {
  try {
    const response = await axios.get(`http://47.94.43.180:8080/creator/getAll/${currentPage.value}/${pageSize.value}`);
    if (response.data) {
      creators.value = response.data.list; // 假设后端返回的数据中 products 列表在 list 字段
      const pagination = response.data.pagination || {}; // 如果 pagination 不存在，则使用空对象
      lastPage.value = pagination.totalPages || 1; // 如果 totalPages 不存在，则默认为 1
    }
  } catch (error) {
    console.error(error);
  }
};

const addCreator = () => {
  // 实现新增逻辑
};

const editCreator = (id: number) => {
  // 实现修改逻辑
};

const deleteCreator = (id: number) => {
  // 实现删除逻辑
};

// 下一页
const nextPage = () => {
  if (currentPage.value < lastPage.value) {
    currentPage.value++;
    fetchCreators();
  } else {
    alert("下一页为空");
  }
};

// 上一页
const previousPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
    fetchCreators();
  } else {
    alert("上一页为空");
  }
};

// 初始化加载数据
fetchCreators();
</script>

<style scoped>
.creator-table {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.controls {
  margin-bottom: 20px;
}

input {
  padding: 8px;
  margin-right: 10px;
}

button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
  margin: 8px;
}

button:hover {
  background-color: #45a049;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

th {
  background-color: #f0f0f0;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}

.pagination button {
  margin: 0 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 5px;
}
</style>