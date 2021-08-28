<template>
  <div></div>
</template>
<script>
export default {
  beforeCreate() {
    alert("존재하지 않는 페이지 입니다.");
    this.$router.push("/");
  },
};
</script>