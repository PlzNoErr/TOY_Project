<template>
<header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <router-link to="/" class="nav-link px-2" id="ttitle">SSAFIT</router-link>
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#" class="nav-link px-2 text-white">PAGE1</a></li>
          <li><a href="#" class="nav-link px-2 text-white">PAGE2</a></li>
          <li><a href="#" class="nav-link px-2 text-white">PAGE3</a></li>
        </ul>

        <div class="text-end" v-if="LoginNickname">
          <span style="margin-right: 15px; position: relative; top: 2px;">{{LoginNickname}}님 환영합니다!</span>
          <button type="button" @click="logoutrequest" class="btn btn-outline-info me-2">로그아웃</button>
          <router-link to="/user/videolist" type="button" class="btn btn-outline-light me-2">MY 찜한영상</router-link>
          <router-link to="/user/follow" type="button" class="btn btn-outline-light me-2">MY 팔로우</router-link>
        </div>
        <div class="text-end" v-else>
          <router-link to="/user/login" type="button" class="btn btn-outline-light me-2">로그인   
          </router-link>
          <router-link to="/user/signup" type="button" class="btn btn-outline-warning">회원가입   
          </router-link>
        </div>
      </div>
    </div>
 </header>
</template>

<script>
import { mapState } from 'vuex';
export default {
  computed:{
    ...mapState([
    'LoginNickname',
    ]),
  },
  methods:{
    logoutrequest(){
      this.$store.commit("LOGOUTREQUEST")
      this.$router.go();
    }
  }
}
</script>

<style scoped>
#ttitle{
  font-size: 25px;
  color: rgb(213, 210, 249);
  margin-left: 5px;
}
</style>