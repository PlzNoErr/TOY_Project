<template>
  <div>
    <h3>{{ title }}</h3>
    <div class="d-flex justify-content-between align-items-center">
      <button>◁</button>
      <div class="d-flex justify-content-between">
        <router-link
          v-for="(video, index) in videos"
          :key="index"
          :to="{ name: 'VideoDetail', params: { videoId: video.videoId } }"
          class="main-col"
        >
          <img
            :src="`https://img.youtube.com/vi/${video.videoUrl}/mqdefault.jpg`"
            alt="videoImage"
            class="main-col-img"
          />
          <div>
            <p>
              {{ video.title }}
            </p>
            <p>조회수 : {{ video.viewCount }}</p>
          </div>
        </router-link>
      </div>
      <button>▷</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
    },
    videos: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.main-col {
  color: black;
  text-decoration: none;
  width: 300px;
}
.main-col-img {
  width: 300px;
}
</style>
