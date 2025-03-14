<template>
  <div class="main-content">
    <div class="image-container"></div>
    <div class="form-container">
      <div class="radio-group">
        <RouterView />
        <div class="radio" v-if="isTable">
          <label>
            <input type="radio" name="loginType" :value="true" v-model="userFlag" />
            用户登录
          </label>
          <label>
            <input type="radio" name="loginType" :value="false" v-model="userFlag" />
            开发者登录
          </label>
          <button @click="submitForm">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="LogReg" lang="ts">
import {onMounted, ref, watch} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import useLogRegStore from "@/store/logReg.ts";
import {storeToRefs} from "pinia";
import useAppStore from "@/store/app.ts";

let userFlag = ref(true);

const logRegStore = useLogRegStore();
const appStore = useAppStore();
const {isTable} = storeToRefs(logRegStore); // 控制单选框是否显示
let {isUser} = storeToRefs(appStore);
const router = useRouter();
const route = useRoute();

function submitForm() {
  appStore.setIsUser(userFlag.value);
  if (isUser.value == true) {
    router.push("/logReg/uLog"); // 如果选中“用户登录”，跳转到登录页面
  } else {
    router.push("/logReg/cLog"); // 如果选中“开发者登录”，跳转到注册页面
  }
}
function setTableBasedOnPath() {
  if (route.path === "/logReg") {
    logRegStore.setIsTable(true); // 如果路径是 /logReg，显示单选框
  } else {
    logRegStore.setIsTable(false); // 其他路径，隐藏单选框
  }
}

// 监听路由变化，动态更新 isTable 的值
onMounted(() => {
  setTableBasedOnPath(); // 页面加载时设置
});

watch(() => route.path, () => {
  setTableBasedOnPath(); // 路径变化时更新
});

</script>

<style scoped>
.main-content {
  display: flex; /* 使用 Flexbox 布局 */
  width: 45%;
  height: 40%;
  border: 1px solid black;
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.image-container {
  flex: 1; /* 占据一半空间 */
  background-image: url('@/assets/log.png'); /* 背景图片 */
  background-size: cover; /* 背景图片覆盖整个容器 */
  background-position: center; /* 背景图片居中 */
}

.form-container {
  flex: 1; /* 占据剩余空间 */
  display: flex;
  flex-direction: column; /* 竖着排列 */
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
}

.radio-group {
  display: flex;
  flex-direction: column; /* 单选框竖直排列 */
  align-items: flex-start; /* 对齐到左侧 */
  margin-bottom: 20px; /* 添加一些间距 */
}

label {
  display: block; /* 每个单选框占一行 */
  margin: 5px 0; /* 添加一些间距 */
}

button {
  margin: 10px 0; /* 添加一些间距 */
}
</style>