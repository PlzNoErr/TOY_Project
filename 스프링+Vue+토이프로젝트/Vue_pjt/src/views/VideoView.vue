<template>
  <div class="container py-3">
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoVIew",
  created() {
    if (!this.LoginNickname) {
      this.$router.push({ name: "LoginView" });
      return;
    }
  },
  computed: {
    ...mapState(["LoginNickname"]),
  },
};
</script>

<style>
.container {
  width: 960px;
}
</style>
