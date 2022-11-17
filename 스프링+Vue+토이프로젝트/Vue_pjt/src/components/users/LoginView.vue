<template>
  <div class="container">
    <h2>로그인 페이지</h2><br><br>
    <form>
    <div class="input-group mb-3">
      <span class="input-group-text" id="inputGroup-sizing-default" style="width: 90px;"> 아이디 </span>
      <input
        type="text"
        class="form-control"
        aria-label="Sizing example input"
        aria-describedby="inputGroup-sizing-default"
        v-model="logininfo.id"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text" id="inputGroup-sizing-default" style="width: 90px;"> 비밀번호 </span>
      <input
        type="text"
        class="form-control"
        aria-label="Sizing example input"
        aria-describedby="inputGroup-sizing-default"
        v-model="logininfo.password"
      />
    </div><br>
    <button type="submit" @click.prevent="loginrequest" class="btn btn-outline-primary" style="width: 120px;">로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      logininfo: {
      id: "",
      password: "",},

    }
  },
  methods:{
    loginrequest(){
      if(this.logininfo.id===""||this.logininfo.password===""){
        alert("아이디와 비밀번호를 모두 입력해 주세요!")
        return;
      }

      this.$store.dispatch("loginRequest", this.logininfo);
    }
  }
};
</script>

<style scoped>
  .container{
    padding-top: 30px;
    text-align: center;
    width: 500px;
    height: 500px;
    margin: 0 auto; 
    display: block;
  }
</style>