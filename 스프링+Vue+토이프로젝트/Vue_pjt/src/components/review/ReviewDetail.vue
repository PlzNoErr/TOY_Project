<template>
  <div>
    <h3>{{ myReview.title }}</h3>
    <hr />
    <p>{{ myReview.content }}</p>
    <button
      type="button"
      data-bs-toggle="modal"
      data-bs-target="#exampleModal"
      class="btn btn-success me-3"
      v-if="auth"
    >
      수정
    </button>
    <button
      v-if="auth"
      @click.prevent="deleteReview"
      class="btn btn-danger me-3"
    >
      삭제
    </button>
    <router-link
      :to="{
        name: 'VideoDetail',
        params: { videoId: myReview.videoId },
      }"
      class="btn btn-primary me-3"
      >돌아가기</router-link
    >
    <review-update :review="myReview"></review-update>
  </div>
</template>

<script>
import ReviewUpdate from "./ReviewUpdate.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ReviewDetail",
  data() {
    return {};
  },
  computed: {
    ...mapState(["review"]),
    myReview() {
      return this.review.review;
    },
    auth() {
      return this.myReview.userSeq == sessionStorage.getItem("userSeq");
    },
  },
  methods: {
    ...mapActions(["getReview"]),
    deleteReview() {
      this.$store.dispatch("deleteReview", this.review.review);
    },
  },
  created() {
    this.getReview(this.$route.params.reviewId);
  },
  components: { ReviewUpdate },
};
</script>

<style></style>
