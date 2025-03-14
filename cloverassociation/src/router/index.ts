//创建一个路由器并暴露出去
import {createRouter, createWebHashHistory} from "vue-router";

import Index from "@/pages/index.vue"
import Creators from "@/pages/creators.vue";
import Products from "@/pages/products.vue";
import Support from "@/pages/support.vue";
import Creator from "@/pages/creator.vue";
import Product from "@/pages/product.vue";
import ULog from "@/pages/uLog.vue";
import UReg from "@/pages/uReg.vue";
import Profile from "@/pages/profile.vue";
import LogReg from "@/pages/logReg.vue";
import CLog from "@/pages/cLog.vue";
import CCreators from "@/pages/cCreators.vue";

const router = createRouter({
    history: createWebHashHistory(),//路由器的工作模式
    routes:
        [
            {
                name: "Index",
                path: "/index",
                component: Index
            },
            {
                name: "logReg",
                path: "/logReg",
                component: LogReg,
                children: [{
                    name: "ULog",
                    path: "uLog",
                    component: ULog
                }, {
                    name: "UReg",
                    path: "uReg",
                    component: UReg,
                }, {
                    name: "CLog",
                    path: "cLog",
                    component: CLog,
                }]
            },
            {
                name: "Profile",
                path: "/profile",
                component: Profile
            },
            {
                name: "Creators",
                path: '/creator',
                component: Creators,
                children: [{
                    name: "Creator",
                    path: 'creator',
                    component: Creator,
                    props(route) {
                        return route.query
                    }
                }]
            },
            {
                name: "CCreators",
                path: "/cCreators",
                component: CCreators
            },
            {
                name: "Products",
                path: '/product',
                component: Products,
                children: [{
                    name: "Product",
                    path: 'product',
                    component: Product,
                    props(route) {
                        return route.query
                    }
                }]
            },
            {
                name: 'support',
                path: '/support',
                component: Support
            }
        ]
})

export default router