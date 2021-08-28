<template>
  <div class="row bg-light fixed-top p-2">
    <div class="col-md-10 mx-auto">
      <b-navbar toggleable="lg" type="light" variant="light">
        <b-navbar-brand href="/" class="fs-1">쿨거래</b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <div class="d-flex justify-content-between w-100">
            <b-navbar-nav class="fs-3">
              <b-nav-item v-on:click="MarketList">마켓</b-nav-item>
              <b-nav-item v-on:click="CommuList">커뮤니티</b-nav-item>
            </b-navbar-nav>
            <b-navbar-nav>
              <b-button
                variant="light"
                size="lg"
                type="button"
                v-on:click="user"
              >
                <fai :icon="['fas', 'user-circle']" class="fs-1"></fai>
              </b-button>
            </b-navbar-nav>
          </div>
        </b-collapse>
      </b-navbar>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    MarketList() {
      this.$router.push("/MarketList");
    },
    CommuList() {
      this.$router.push("/CommuList");
    },
    user() {
      if (this.$session.get("coolUserNo") == null) {
        this.$router.push("/Login");
      } else {
        if (this.$session.get("coolRank") == 0) {
          this.$router.push("/ManagerPage");
        } else {
          this.$router.push("/MyPage");
        }
      }
    },
  },
};
</script>
<style scoped>
</style>