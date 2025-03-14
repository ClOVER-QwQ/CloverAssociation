<template>
  <div class="image-container">
    <img alt="无" src="../assets/Alpay.jpg">
    <img alt="无" src="../assets/WeChat.jpg">
  </div>
</template>

<script lang="ts" name="Support" setup>

</script>

<style scoped>
.image-container {
  display: flex;
  flex-direction: row;
  justify-content: center; /* 将内容水平居中 */
}

.image-container img {
  max-width: 500px;
  height: auto;
  margin-right: 30px; /* 可以根据需要调整图片之间的间距 */
}
</style>