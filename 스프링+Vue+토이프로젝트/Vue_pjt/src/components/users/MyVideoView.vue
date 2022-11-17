<template>
  <div class="container">
    <h2>나의 좋아요 영상목록</h2><br><br>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">영상제목</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(video, index) in videolists" :key="index">
          <th scope="row">{{ index + 1 }}</th>
          <td>
            <router-link :to="`/video/${video.videoId}`">
            {{video.title}}</router-link>
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
      videolists : {},
    }
  },
  
  created() {
    const URL = `/api/videos/like/${sessionStorage.getItem("userSeq")}`
    $({
        url: URL,
        method: "GET",
      })
        .then((res) => {
          this.videolists = res.data
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