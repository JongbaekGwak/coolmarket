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
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') != null) {
        next()
      } else {
        alert('로그인 해주세요')
        router.go(-1)
      }
    },
  },
  {
    path: '/ManagerPage',
    name: 'ManagerPage',
    component: ManagerPage,
    beforeEnter: (to, from, next) => {
      if (
        sessionStorage.getItem('coolUserNo') != null &&
        sessionStorage.getItem('coolRank') == 0
      ) {
        next()
      } else {
        alert('잘못된 접근입니다.')
        router.go(-1)
      }
    },
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
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  {
    path: '/CommuWrite',
    name: 'CommuWrite',
    component: CommuWrite,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  {
    path: '/AdWrite',
    name: 'AdWrite',
    component: AdWrite,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else if (sessionStorage.getItem('coolRank') == 1) {
        alert('잘못된 접근입니다.')
        router.go(-1)
      } else {
        next()
      }
    },
  },
  {
    path: '/MarketUpdate',
    name: 'MarketUpdate',
    component: MarketUpdate,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  {
    path: '/CommuUpdate',
    name: 'CommuUpdate',
    component: CommuUpdate,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  {
    path: '/AdUpdate',
    name: 'AdUpdate',
    component: AdUpdate,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else if (sessionStorage.getItem('coolRank') == 1) {
        alert('잘못된 접근입니다.')
        router.go(-1)
      } else {
        next()
      }
    },
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
    component: MsgList,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  {
    path: '/MsgDetail',
    name: 'MsgDetail',
    component: MsgDetail,
    beforeEnter: (to, from, next) => {
      if (sessionStorage.getItem('coolUserNo') == null) {
        alert('로그인 해주세요')
        router.push('/Login')
      } else {
        next()
      }
    },
  },
  { path: '/*', component: NotFoundComponent },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
