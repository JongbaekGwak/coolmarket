<template>
  <div>
    <div class="mt-3">
      <div class="d-flex border" v-for="item in coment" :key="item.comentNo">
        <div class="border-end" style="width: 20%">
          <div class="mb-2">
            {{ item.comentNickName }}
            <b-button
              class="ms-auto"
              size="sm"
              variant="danger"
              v-if="item.comentUserNo == myUserNo || myRank == 0"
              v-on:click="comentDelete(item.comentNo)"
              >삭제</b-button
            >
          </div>
          <div>
            {{ item.comentCreaDt }}
          </div>
        </div>

        <pre
          class="p-0 m-0 pre"
          style="width: 80%"
          v-text="item.comentContents"
        ></pre>
      </div>
    </div>
    <b-input-group class="mt-3">
      <b-textarea
        v-model="insert"
        placeholder="댓글 입력"
        rows="3"
        max-rows="6"
        class="pre"
      ></b-textarea>
      <b-input-group-text class="btn bg-info" v-on:click="comentInsert">
        입력
      </b-input-group-text>
    </b-input-group>
  </div>
</template>
<script>
export default {
  data() {
    return {
      coment: [],
      insert: "",
      myUserNo: "",
      marNo: "",
    };
  },
  mounted() {
    this.myUserNo = this.$session.get("coolUserNo");
    this.myRank = this.$session.get("coolRank");
    this.$axios
      .get("http://localhost:9000/coment", {
        params: { comentComNo: this.$route.query.comNo },
      })
      .then((res) => {
        this.coment = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    comentInsert() {
      this.$axios
        .get("http://localhost:9000/comentInsert", {
          params: {
            comentComNo: this.$route.query.comNo,
            comentUserNo: this.$session.get("coolUserNo"),
            comentNickName: this.$session.get("coolNickName"),
            comentContents: this.insert,
          },
        })
        .then((res) => {
          this.coment = res.data;
          this.insert = "";
        })
        .catch((err) => {
          console.log(err);
        });
    },
    comentDelete(num) {
      this.$axios
        .get("http://localhost:9000/comentDelete", {
          params: { comentNo: num, comentComNo: this.$route.query.comNo },
        })
        .then((res) => {
          this.coment = res.data;
          alert("삭제완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
.cursor {
  cursor: pointer;
}
.pre {
  white-space: pre-wrap;
}
</style>