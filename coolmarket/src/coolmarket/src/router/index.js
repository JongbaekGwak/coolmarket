import Vue from 'vue'
import VueRouter from 'vue-router'
import NotFoundComponent from '../views/NotFoundComponent.vue'
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import MyPage from '../views/MyPage.vue'
import ManagerPage from '../views/ManagerPage.vue'
import AdDetail from '../views/AdDetail.vue'
import MarketDetail from '../views/MarketDetail.vue'
import CommuDetail from '../views/CommuDetail.vue'
import MarketWrite from '../views/MarketWrite.vue'
import MarketList from '../views/MarketList.vue'
import CommuList from '../views/CommuList.vue'
import MsgList from '../views/MsgList.vue'
import MsgDetail from '../views/MsgDetail.vue'
import CommuWrite from '../views/CommuWrite.vue'
import AdWrite from '../views/AdWrite.vue'
import MarketUpdate from '../views/MarketUpdate.vue'
import CommuUpdate from '../views/CommuUpdate'
import AdUpdate from '../views/AdUpdate'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/MyPage',
    name: 'MyPage',
    component: MyPage,
  },
  {
    path: '/ManagerPage',
    name: 'ManagerPage',
    component: ManagerPage,
  },
  {
    path: '/AdDetail',
    name: 'AdDetail',
    component: AdDetail,
  },
  {
    path: '/MarketDetail',
    name: 'MarketDetail',
    component: MarketDetail,
  },
  {
    path: '/CommuDetail',
    name: 'CommuDetail',
    component: CommuDetail,
  },
  {
    path: '/MarketWrite',
    name: 'MarketWrite',
    component: MarketWrite,
  },
  {
    path: '/CommuWrite',
    name: 'CommuWrite',
    component: CommuWrite,
  },
  {
    path: '/AdWrite',
    name: 'AdWrite',
    component: AdWrite,
  },
  {
    path: '/MarketUpdate',
    name: 'MarketUpdate',
    component: MarketUpdate,
  },
  {
    path: '/CommuUpdate',
    name: 'CommuUpdate',
    component: CommuUpdate,
  },
  {
    path: '/AdUpdate',
    name: 'AdUpdate',
    component: AdUpdate,
  },
  {
    path: '/MarketList',
    name: 'MarketList',
    component: MarketList,
  },
  {
    path: '/CommuList',
    name: 'CommuList',
    component: CommuList,
  },
  {
    path: '/MsgList',
    name: 'MsgList',
    component: MsgList
  },
  {
    path: '/MsgDetail',
    name: 'MsgDetail',
    component: MsgDetail
  },
  { path: '/*', component: NotFoundComponent },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
