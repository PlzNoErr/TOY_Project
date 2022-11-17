<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view />
    <common-footer></common-footer>
  </div>
</template>
<script>
import HeaderNav from "./components/common/HeaderNav.vue";
import CommonFooter from "./components/common/CommonFooter.vue";
export default {
  name: "APP",
  components: { HeaderNav, CommonFooter },
  created(){
    this.$store.dispatch("loginCheck");
  }



};
</script>
<style>
</style>
