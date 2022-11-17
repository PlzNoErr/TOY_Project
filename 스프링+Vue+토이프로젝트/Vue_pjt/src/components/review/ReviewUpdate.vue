<template>
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">리뷰 수정</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="updateReview">
            <div class="form-floating my-3">
              <input
                type="text"
                class="form-control"
                id="title"
                v-model="title"
              />
              <label for="title">제목</label>
            </div>
            <div class="form-floating my-3">
              <textarea class="form-control" id="content" v-model="content" />
              <label for="content">내용</label>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            취소하기
          </button>
          <button
            type="button"
            class="btn btn-primary"
            @click.prevent="updateReview"
          >
            수정하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from "bootstrap";

export default {
  name: "ReviewUpdate",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  props: {
    review: {
      type: Object,
    },
  },
  methods: {
    updateReview() {
      let data = {
        content: this.content,
        title: this.title,
        reviewId: this.$route.params.reviewId,
      };
      this.$store.dispatch("updateReview", data);
      Modal.getInstance(document.querySelector("#exampleModal")).hide();
    },
  },
  watch: {
    review() {
      this.title = this.review.title;
      this.content = this.review.content;
    },
  },
};
</script>

<style>
#content {
  height: 200px;
}
</style>
