<template>
  <div id="app">
    <Header></Header>
    <b-row id="main">
      <b-col md="8" class="mx-auto">
        <router-view :key="$route.fullPath"></router-view>
      </b-col>
    </b-row>
    <Footer></Footer>
  </div>
</template>
<script>
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
export default {
  components: {
    Header,
    Footer,
  },
};
</script>
<style scoped>
#main {
  margin-top: 120px;
}
</style>