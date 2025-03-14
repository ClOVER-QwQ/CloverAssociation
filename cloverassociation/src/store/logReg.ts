import { defineStore} from "pinia";
import {ref} from "vue";

const useLogRegStore = defineStore('logReg', () => {
    // 从 localStorage 获取初始值
    const isTable = ref(localStorage.getItem("isTable") === "true");

    function setIsTable(value: boolean) {
        isTable.value = value;
        localStorage.setItem("isTable", String(value)); // 同步到 localStorage
    }

    return { isTable,setIsTable};
});

export default useLogRegStore;