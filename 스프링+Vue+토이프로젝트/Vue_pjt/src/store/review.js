import $ from "@/util/http-common";
import router from "@/router";

export default {
  state: {
    reviewList: [],
    review: {},
  },
  getters: {},
  mutations: {
    GET_REVIEW_LIST(state, data) {
      state.reviewList = data;
    },
    GET_REVIEW(state, data) {
      state.review = data;
    },
  },
  actions: {
    getReviewList({ commit }, videoId) {
      const API_URL = `/api/reviews/video/${videoId}`;
      $.get(API_URL, {
        headers: { "access-token": sessionStorage.getItem("access-token") },
      })
        .then((res) => {
          commit("GET_REVIEW_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createReview(context, data) {
      const API_URL = `/api/reviews/`;
      $.post(API_URL, data, {
        headers: { "access-token": sessionStorage.getItem("access-token") },
      })
        .then(() => {
          context.dispatch("getReviewList", data.videoId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateReview(context, data) {
      const API_URL = `/api/reviews/`;
      $.put(API_URL, data, {
        headers: { "access-token": sessionStorage.getItem("access-token") },
      })
        .then(() => {
          context.dispatch("getReview", data.reviewId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getReview({ commit }, reviewId) {
      const API_URL = `/api/reviews/${reviewId}`;
      $.get(API_URL, {
        headers: { "access-token": sessionStorage.getItem("access-token") },
      })
        .then((res) => {
          commit("GET_REVIEW", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteReview(context, review) {
      const API_URL = `/api/reviews/${review.reviewId}`;
      $.delete(API_URL, {
        headers: { "access-token": sessionStorage.getItem("access-token") },
      })
        .then(() => {
          router.push({
            name: "VideoDetail",
            params: { videoId: review.videoId },
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
