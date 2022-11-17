<template>
  <div>
    <h3>리뷰 목록</h3>
    <div>
      <table class="table">
        <tr>
          <th>제목</th>
          <th>작성자</th>
          <th>작성시간</th>
        </tr>
        <tr v-for="(review, index) in computed_reviews" :key="index">
          <td>
            <router-link
              :to="{
                name: 'ReviewDetail',
                params: { reviewId: review.reviewId },
              }"
            >
              {{ review.title }}
            </router-link>
          </td>
          <td>{{ review.nickname }}</td>
          <td>{{ review.createTimeStr }}</td>
        </tr>
      </table>

      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-outline-success"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
      >
        리뷰 생성하기
      </button>

      <!-- Modal -->
      <review-create></review-create>
    </div>
  </div>
</template>

<script>
import ReviewCreate from "@/components/review/ReviewCreate.vue";

export default {
  components: { ReviewCreate },
  props: {
    reviews: {
      type: Array,
    },
  },
  computed: {
    computed_reviews() {
      let result = this.reviews.map((e) => {
        e.createTimeStr = new Date(e.createTime).toLocaleString();
        return e;
      });
      return result;
    },
  },
};
</script>

<style></style>
