import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //存储data
    token: '',
    userInfo: JSON.stringify(sessionStorage.getItem('userInfo'))
  },
  mutations: {
    //set
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem("token",token)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },

    //移除信息,退出时调用
    REMOVE_INFO: (state) => {
      state.token = ''
      state.userInfo = ''
      localStorage.setItem("token","")
      sessionStorage.setItem("userInfo",JSON.stringify(''))
      sessionStorage.setItem('isLogin',JSON.stringify(false))
    }

  },
  getters: {
    //get
    getUser: state => {
      return state.userInfo
    }
  },
  actions: {
  },
  modules: {
  }
})
