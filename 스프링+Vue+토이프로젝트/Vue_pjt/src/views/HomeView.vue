<template>
  <div class="container py-3">
    <video-row :videos="videosByView" :title="'조회수별 영상'"></video-row>
    <video-row :videos="videosByFollow" :title="'추천 영상'"></video-row>
  </div>
</template>

<script>
import VideoRow from "@/components/videos/VideoRow.vue";
import $ from "@/util/http-common";
import { mapState } from "vuex";

export default {
  name: "HomeView",
  components: {
    VideoRow,
  },
  data() {
    return {
      videosByView: [],
      videosByFollow: [],
    };
  },
  created() {
    if (!this.LoginNickname) {
      this.$router.push({ name: "LoginView" });
      return;
    }
    const API_URL = "/api/videos/search";
    $.get(API_URL, {
      headers: { "access-token": sessionStorage.getItem("access-token") },
    })
      .then((res) => {
        this.videosByView = res.data;
        this.videosByFollow = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapState(["LoginNickname"]),
  },
};
</script>

<style scoped>
.container {
  width: 960px;
}
</style>
