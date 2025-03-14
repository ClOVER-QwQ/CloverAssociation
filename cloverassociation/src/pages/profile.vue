<template>
  <div class="profile-container">
    <h1>个人主页</h1>

    <!-- 用户信息展示 -->
    <div v-if="isUser && user">
      <img :src="user.avatar" alt="用户头像" class="avatar" />
      <p><strong>用户名：</strong>{{ user.username }}</p>
      <p><strong>描述：</strong>{{ user.description }}</p>
    </div>
    <!-- 创作者信息展示 -->
    <div v-else-if="creator">
      <img :src="creator.avatar" alt="创作者头像" class="avatar" />
      <p><strong>名字：</strong>{{ creator.name }}</p>
      <p><strong>描述：</strong>{{ creator.description }}</p>
    </div>

    <!-- 修改信息按钮 -->
    <button @click="showEditForm = true" class="edit-profile-button">修改信息</button>
    <!-- 修改头像按钮 -->
    <button @click="showAvatarUpload = true" class="edit-profile-button">修改头像</button>
    <!-- 退出登录按钮 -->
    <button @click="logout" class="logout-button">退出登录</button>

    <!-- 修改信息表单 -->
    <form
        v-if="showEditForm"
        @submit.prevent="saveEditForm"
        class="edit-form"
    >
      <div>
        <label :for="isUser ? 'username' : 'name'">{{ isUser ? '用户名' : '名字' }}：</label>
        <input
            :type="isUser ? 'text' : 'text'"
            :id="isUser ? 'username' : 'name'"
            v-model="currentUsername"
            required
        />
      </div>
      <div>
        <label for="description">描述：</label>
        <textarea
            id="description"
            v-model="currentDescription"
            required
        ></textarea>
      </div>
      <button type="submit">保存</button>
      <button type="button" @click="showEditForm = false">取消</button>
    </form>

    <!-- 修改头像表单 -->
    <form
        v-if="showAvatarUpload"
        @submit.prevent="saveAvatar"
        class="edit-form"
    >
      <div>
        <label for="avatar">上传头像：</label>
        <input
            type="file"
            id="avatar"
            @change="handleAvatarUpload"
            accept="image/*"
            required
        />
      </div>
      <button type="submit">上传</button>
      <button type="button" @click="showAvatarUpload = false">取消</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import useAppStore from '@/store/app';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';

// 定义用户和创作者的接口
interface User {
  id: number;
  username: string;
  avatar: string;
  description: string;
  password: string;
}

interface Creator {
  id: number;
  name: string;
  avatar: string;
  description: string;
  birthDate: string;
  address: string;
  phone: string;
  email: string;
  experience: string;
  skill: string;
  createdAt: string;
  updatedAt: string;
}

const appStore = useAppStore();
const { isUser, userId } = storeToRefs(appStore);
const router = useRouter();

// 初始化用户和创作者数据
const user = ref<User | null>(null);
const creator = ref<Creator | null>(null);

// 初始化编辑表单数据
const editUser = ref<User>({
  id: 0,
  username: '',
  avatar: '',
  description: '',
  password: '',
});

const editCreator = ref<Creator>({
  id: 0,
  name: '',
  avatar: '',
  description: '',
  birthDate: '',
  address: '',
  phone: '',
  email: '',
  experience: '',
  skill: '',
  createdAt: '',
  updatedAt: '',
});

// 控制表单显示的变量
const showEditForm = ref(false);
const showAvatarUpload = ref(false);
const avatarFile = ref<File | null>(null);

// 计算属性：动态绑定用户名或名字
const currentUsername = computed({
  get: () => isUser.value ? editUser.value.username : editCreator.value.name,
  set: (value) => {
    if (isUser.value) {
      editUser.value.username = value;
    } else {
      editCreator.value.name = value;
    }
  },
});

// 计算属性：动态绑定描述
const currentDescription = computed({
  get: () => isUser.value ? editUser.value.description : editCreator.value.description,
  set: (value) => {
    if (isUser.value) {
      editUser.value.description = value;
    } else {
      editCreator.value.description = value;
    }
  },
});

// 加载用户或创作者信息
const fetchData = async () => {
  try {
    if (isUser.value && userId.value) {
      const response = await axios.get<User>(`http://47.94.43.180:8080/user/get/${userId.value}`);
      user.value = response.data;
      editUser.value = { ...user.value }; // 初始化编辑表单
    } else if (userId.value) {
      const response = await axios.get<Creator>(`http://47.94.43.180:8080/creator/get/${userId.value}`);
      creator.value = response.data;
      editCreator.value = { ...creator.value }; // 初始化编辑表单
    }
  } catch (error) {
    console.error('获取信息失败', error);
    alert('获取信息失败，请稍后再试');
  }
};

onMounted(async () => {
  await fetchData();
});

// 保存修改按钮
const saveEditForm = async () => {
  try {
    const username = encodeURIComponent(isUser.value ? editUser.value.username : editCreator.value.name);
    const description = encodeURIComponent(isUser.value ? editUser.value.description : editCreator.value.description);

    const path = isUser.value
        ? `/user/update/${userId.value}/${username}/${description}`
        : `/creator/update/${userId.value}/${username}/${description}`;

    const response = await axios.put(
        `http://47.94.43.180:8080${path}`
    );

    if (response.status === 200) {
      alert('信息更新成功！');
      showEditForm.value = false;
      await fetchData(); // 刷新数据
    } else {
      alert('更新失败，请稍后再试');
    }
  } catch (error) {
    console.error('更新信息失败', error);
    alert('更新失败，请稍后再试');
  }
};

// 处理头像上传
const handleAvatarUpload = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files) {
    avatarFile.value = target.files[0];
  }
};

// 保存上传头像
const saveAvatar = async () => {
  try {
    if (!avatarFile.value) {
      alert('请选择头像文件！');
      return;
    }

    const formData = new FormData();
    formData.append('avatar', avatarFile.value);

    const path = isUser.value
        ? `/user/update/avatar/${userId.value}/${encodeURIComponent(avatarFile.value.name)}`
        : `/creator/update/avatar/${userId.value}/${encodeURIComponent(avatarFile.value.name)}`;

    await axios.put(
        `http://47.94.43.180:8080${path}`,
        formData,
        {
          headers: { 'Content-Type': 'multipart/form-data' },
        }
    );

    if (isUser.value) {
      user.value!.avatar = avatarFile.value!.name;
    } else {
      creator.value!.avatar = avatarFile.value!.name;
    }

    alert('头像更新成功！');
    showAvatarUpload.value = false;
    await fetchData(); // 刷新数据
  } catch (error) {
    console.error('更新头像失败', error);
    alert('更新头像失败，请稍后再试');
  }
};

// 退出登录
const logout = () => {
  appStore.setLoginStatus(false);
  appStore.setNav(true);
  appStore.setIsUser(true);
  appStore.setUserId('');
  appStore.setAvatar('/assets/default_avatar.png');
  localStorage.removeItem('isLoggedIn');
  localStorage.removeItem('userId');
  alert('已退出登录！');
  router.push('/login');
};
</script>

<style scoped>
.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-bottom: 20px;
}

.edit-profile-button,
.logout-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  margin-top: 20px;
  margin-right: 10px;
}

.edit-profile-button:hover,
.logout-button:hover {
  background-color: #0056b3;
}

.edit-form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.edit-form div {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>