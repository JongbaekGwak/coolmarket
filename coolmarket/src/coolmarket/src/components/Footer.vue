<template>
  <div class="mt-3">
    <footer>
      <div class="row bg-secondary">
        <div class="col-10 mx-auto">
          <p class="text-dark m-2">
            공지
            <span>
              <small>
                {{ title }}
              </small>
            </span>
          </p>
        </div>
      </div>
      <div class="row">
        <div class="col-10 mx-auto">
          <p class="text-center">
            프로젝트 명 : 지역교류를 활성화 시키는 '쿨거래'
          </p>
          <p class="text-center">팀원 : 곽종백, 김현준, 김민수, 허재웅</p>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "",
    };
  },
};
</script>
<style scoped>
</style>