<template>
  <div>
    <h2>영상 상세 보기</h2>
    <div class="iframe-container mx-auto my-3">
      <iframe
        width="800"
        height="480"
        :src="`https://www.youtube.com/embed/${video.videoUrl}`"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
      ></iframe>
    </div>
    <div class="d-flex justify-content-between align-items-center">
      <div>
        <h3>{{ video.title }}</h3>
        <p>{{ video.description }}</p>
        <p>조회수 : {{ video.viewCount }}</p>
      </div>
      <button class="btn btn-warning" @click="likechange" v-if="alreadylike">
        좋아요취소
      </button>
      <button class="btn btn-danger" @click="likechange" v-else>좋아요♡</button>
    </div>
    <hr />
    <review-list :reviews="review.reviewList"></review-list>
  </div>
</template>

<script>
import ReviewList from "@/components/videos/ReviewList.vue";
import $ from "@/util/http-common";
import { mapState } from "vuex";

export default {
  name: "VideoDetail",
  data() {
    return {
      video: {},
      reviews: [],
      videolists: [],
      alreadylike: false,
    };
  },

  methods: {
    likechange() {
      const API_URL1 = `/api/videos/like`;
      $.post(
        API_URL1,
        {
          userSeq: sessionStorage.getItem("userSeq"),
          videoId: this.video.videoId,
        },
        {
          headers: { "access-token": sessionStorage.getItem("access-token") },
        }
      )
        .then(() => {
          if (this.alreadylike) {
            this.alreadylike = false;
          } else {
            this.alreadylike = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  created() {
    this.$store.dispatch("getReviewList", this.$route.params.videoId);
    const API_URL = `/api/videos/${this.$route.params.videoId}`;
    $.get(API_URL, {
      headers: { "access-token": sessionStorage.getItem("access-token") },
    })
      .then((res) => {
        this.video = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    const API_URL2 = `/api/reviews/video/${this.$route.params.videoId}`;
    $.get(API_URL2, {
      headers: { "access-token": sessionStorage.getItem("access-token") },
    })
      .then((res) => {
        this.reviews = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    const API_URL3 = `/api/videos/like/${sessionStorage.getItem("userSeq")}`;
    $({
      url: API_URL3,
      method: "GET",
    })
      .then((res) => {
        this.videolists = res.data;
        for (let index = 0; index < this.videolists.length; index++) {
          if (this.videolists[index].videoId === this.video.videoId) {
            this.alreadylike = true;
            break;
          }
        }
      })
      .catch((err) => console.log(err));
  },
  computed: {
    ...mapState(["review"]),
  },

  components: {
    ReviewList,
  },
};
</script>

<style scoped>
.iframe-container {
  width: 800px;
}
</style>
