import Vue from "vue";
import Vuex from "vuex";
import $ from "@/util/http-common";
import router from "@/router";
import Review from "./review";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    LoginNickname: "",
  },
  getters: {},
  mutations: {
    LOGIN_CHECK(state, nickName) {
      state.LoginNickname = nickName;
    },
    LOGOUTREQUEST(state) {
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("userId");
      sessionStorage.removeItem("nickName");
      sessionStorage.removeItem("userSeq");
      state.LoginNickname = "";
    },
  },
  actions: {
    loginCheck({ commit }) {
      let data = sessionStorage.getItem("nickName");
      if (data) {
        commit("LOGIN_CHECK", data);
      }
    },

    loginRequest({ commit }, payload) {
      const URL = `/api/users/login`;
      $({
        url: URL,
        method: "POST",
        data: {
          userId: payload.id,
          password: payload.password,
        },
      })
        .then((res) => {
          if (res.data.userId) {
            sessionStorage.setItem("access-token", res.data["access-token"]);
            sessionStorage.setItem("userId", res.data.userId);
            sessionStorage.setItem("nickName", res.data.nickName);
            sessionStorage.setItem("userSeq", res.data.userSeq);
            alert("로그인 성공!");
            commit("LOGIN_CHECK", res.data.nickName);
            router.push("/");
            return;
          }
          alert("아이디와 비밀번호를 확인해 주세요!");
        })
        .catch((err) => console.log(err));
    },

    sinupRegist({ commit }, payload) {
      const URL = `/api/users`;
      $({
        url: URL,
        method: "POST",
        data: {
          userId: payload.id,
          password: payload.password,
          email: payload.email,
          nickName: payload.nickname,
        },
      })
        .then(() => {
          alert("회원가입 성공!");
          router.push("/");
          commit;
          return;
        })
        .catch((err) => console.log(err));
    },
  },
  modules: {
    review: Review,
  },
});
