<template>
  <div class="container">
    <h2>회원가입</h2>
    <br /><br />
    <form>
      <div class="input-group mb-3">
        <span
          class="input-group-text"
          id="inputGroup-sizing-default"
          style="width: 90px"
        >
          아이디
        </span>
        <input
          type="text"
          class="form-control"
          aria-label="Sizing example input"
          aria-describedby="inputGroup-sizing-default"
          v-model="signupinfo.id"
        />
      </div>
      <div class="input-group mb-3">
        <span
          class="input-group-text"
          id="inputGroup-sizing-default"
          style="width: 90px"
        >
          비밀번호
        </span>
        <input
          type="text"
          class="form-control"
          aria-label="Sizing example input"
          aria-describedby="inputGroup-sizing-default"
          v-model="signupinfo.password"
        />
      </div>
      <div class="input-group mb-3">
        <span
          class="input-group-text"
          id="inputGroup-sizing-default"
          style="width: 90px"
        >
          닉네임
        </span>
        <input
          type="text"
          class="form-control"
          aria-label="Sizing example input"
          aria-describedby="inputGroup-sizing-default"
          v-model="signupinfo.nickname"
        />
      </div>
      <div class="input-group mb-3">
        <span
          class="input-group-text"
          id="inputGroup-sizing-default"
          style="width: 90px"
        >
          이메일
        </span>
        <input
          type="text"
          class="form-control"
          aria-label="Sizing example input"
          aria-describedby="inputGroup-sizing-default"
          v-model="signupinfo.email"
        />
      </div>
      <br />
      <button
        type="submit"
        @click.prevent="signupcheck"
        class="btn btn-outline-primary"
        style="width: 120px"
      >
        회원가입
      </button>
    </form>
  </div>
</template>

<script>
import $ from "@/util/http-common";

export default {
  data() {
    return {
      signupinfo: {
        id: "",
        password: "",
        email: "",
        nickname: "",
      },
    };
  },
  methods: {
    signupcheck() {
      if (
        this.signupinfo.id === "" ||
        this.signupinfo.password === "" ||
        this.signupinfo.email === "" ||
        this.signupinfo.nickname === ""
      ) {
        alert("회원정보를 빠짐 없이 모두 입력해 주세요!");
        return;
      }

      const URL = `/api/users/checksignup`;
      $({
        url: URL,
        method: "POST",
        data: {
          userId: this.signupinfo.id,
          nickName: this.signupinfo.nickname,
        },
      })
        .then((res) => {
          if (res.data.userId) {
            alert("중복된 아이디 입니다! 새로운 아이디를 입력해 주세요!");
            return;
          }
          if (res.data.nickName) {
            alert("중복된 닉네임 입니다! 새로운 닉네임을 입력해 주세요!");
            return;
          }

          this.$store.dispatch("sinupRegist", this.signupinfo);
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style scoped>
.container {
  padding-top: 30px;
  text-align: center;
  width: 500px;
  height: 500px;
  margin: 0 auto;
  display: block;
}
</style>