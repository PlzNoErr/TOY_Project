<template>
  <div class="container">
    <h2>팔로우 목록</h2><br><br>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">닉네임</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(follow, index) in followlists" :key="index">
          <th scope="row">{{ index + 1 }}</th>
          <td>
            <router-link :to="`/product/detail/${follow.nickName}`">{{
              follow.nickName
            }}</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import $ from "@/util/http-common";
export default {
  data() {
    return {
      followlists : {},
    }
  },
  
  created() {
    const URL = `/api/users/followlist/${sessionStorage.getItem("userSeq")}`
    $({
        url: URL,
        method: "GET",
      })
        .then((res) => {
          this.followlists = res.data
        })
        .catch((err) => console.log(err));
  },

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