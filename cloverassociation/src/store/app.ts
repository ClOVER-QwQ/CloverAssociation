import { defineStore } from "pinia";
import { ref } from "vue";

const useAppStore = defineStore('app', () => {
    // 从 localStorage 获取初始值：导航状态
    const nav = ref(localStorage.getItem("nav") || true);
    const isUser = ref(localStorage.getItem("isUser") || true);
    const isLoggedIn = ref(!!localStorage.getItem("isLoggedIn") || false); // 登录状态
    const userId = ref(localStorage.getItem("userId") || ""); // 用户 ID
    const avatar = ref(localStorage.getItem("avatar") || "/assets/user_avatar/default.png"); // 头像路径

    // 设置导航状态
    function setNav(value: boolean) {
        nav.value = value;
        localStorage.setItem("nav", String(nav.value));
    }

    // 设置登录者
    function setIsUser(value: boolean) {
        isUser.value = value;
        localStorage.setItem("isUser", String(isUser.value));
    }

    // 设置登录状态
    function setLoginStatus(value: boolean) {
        isLoggedIn.value = value;
        localStorage.setItem("isLoggedIn", String(value));
    }

    // 设置用户 ID
    function setUserId(id: string) {
        userId.value = id;
        localStorage.setItem("userId", id);
    }

    // 设置头像路径
    function setAvatar(value: string) {
        avatar.value = value;
        localStorage.setItem("avatar", value);
    }

    return { nav,isUser,isLoggedIn,userId,avatar,setNav,setIsUser,setLoginStatus,setUserId,setAvatar};
});

export default useAppStore;