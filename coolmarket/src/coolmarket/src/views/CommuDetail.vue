<template>
  <b-row>
    <b-col md="8" class="mx-auto">
      <div class="d-flex w-100 border-bottom">
        <div class="d-flex justify-content-center m-1 p-2">
          <fai :icon="['far', 'user']" class="fs-1 m-auto"></fai>
        </div>
        <div class="mt-3 pb-3">
          <div class="cursor">
            <h3 class="m-0 pb-1">{{ commuDetail.comCreaNickName }}</h3>
          </div>
          <p class="m-0">
            {{ commuDetail.comAddr1 }} {{ commuDetail.comAddr2 }}
            {{ commuDetail.comAddr3 }}
          </p>
        </div>
      </div>

      <div class="my-3">
        <small>
          <span class="badge rounded-pill bg-secondary">
            {{ commuDetail.comCate }}
          </span>
        </small>
        <h1>{{ commuDetail.comTitle }}</h1>
        <p class="m-0">
          {{ commuDetail.comCreaDt }}
          <span class="float-end"> 조회수 : {{ commuDetail.comView }}</span>
        </p>

        <div class="col-md-8 mx-auto mt-3">
          <div v-if="commuDetail.imgList != ''" class="text-center">
            <div v-for="item in commuDetail.imgList" :key="item.imgNo">
              <img
                :src="item.storedImgPath"
                class="my-1"
                style="max-width: 300px"
              />
            </div>
          </div>
        </div>
        <pre class="my-3 pre">{{ commuDetail.comContents }}</pre>

        <div class="d-flex justify-content-between border-bottom">
          <p>좋아요 : {{ commuDetail.comLike }}</p>
          <p class="text-danger cursor" v-on:click="decInsert">
            게시글 신고 <fai :icon="['fas', 'bullhorn']"></fai>
          </p>
        </div>

        <div class="d-flex pb-5">
          <p class="btn me-auto">
            <span v-on:click="like"
              ><fai :icon="['far', 'heart']"></fai> 좋아요</span
            >
          </p>
          <div class="d-flex justify-content-end">
            <b-button class="m-1" variant="primary" v-on:click="moveComList"
              >목록으로</b-button
            >
            <b-button
              class="m-1"
              variant="info"
              v-on:click="moveComUpdate"
              v-if="myUserNo == commuDetail.comUserNo || myRank == 0"
              >수정</b-button
            >
            <b-button
              class="m-1"
              variant="danger"
              v-on:click="comDelete"
              v-if="myUserNo == commuDetail.comUserNo || myRank == 0"
              >삭제</b-button
            >
          </div>
        </div>
      </div>

      <!-- 댓글 -->
      <div>
        <CommuComent />
      </div>
    </b-col>
  </b-row>
</template>

<script>
import CommuComent from "./CommuComent.vue";
export default {
  data() {
    return {
      commuDetail: [],
      myUserNo: "",
      comNo: "",
      myRank: "",
    };
  },
  components: {
    CommuComent,
  },
  beforeCreate() {
    if (this.$route.query.comNo == null) {
      this.$router.push("/CommuList");
    }
  },
  mounted() {
    window.scrollTo(0, 0);
    this.myUserNo =
      sessionStorage.getItem("coolUserNo") != null
        ? sessionStorage.getItem("coolUserNo")
        : "";
    this.myRank =
      sessionStorage.getItem("coolRank") != null
        ? sessionStorage.getItem("coolRank")
        : "";
    this.comNo = this.$route.query.comNo;
    this.$axios
      .get("http://coolmarket.link/commuDetail", {
        params: { comNo: this.comNo },
      })
      .then((res) => {
        if (res.data == "") {
          alert("삭제된 게시물입니다.");
          this.$router.push("/CommuList");
        } else {
          this.commuDetail = res.data.commuDetail;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    like() {
      if (this.myUserNo == "") {
        alert("로인후 이용해 주세요");
      } else {
        this.$axios
          .get("http://coolmarket.link/likeUp", {
            params: { comNo: this.commuDetail.comNo },
          })
          .then(() => {
            this.commuDetail.comLike += 1;
            alert("좋아요!!");
          });
      }
    },
    decInsert() {
      this.$axios
        .get("http://coolmarket.link/decInsert", {
          params: { marNo: 0, comNo: this.comNo },
        })
        .then(() => {
          alert("신고 하였습니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveComList() {
      this.$router.push("/CommuList");
    },
    moveComUpdate() {
      this.$router.push({ name: "CommuUpdate", query: { comNo: this.comNo } });
    },
    comDelete() {
      this.$axios
        .get("http://coolmarket.link/comDelete", {
          params: { comNo: this.comNo },
        })
        .then(() => {
          alert("삭제 완료");
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
  word-break: break-all;
}
</style>