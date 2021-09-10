<template>
  <div>
    <div class="d-flex w-100 border-bottom">
      <div class="d-flex justify-content-center m-1 p-2">
        <fai :icon="['far', 'user']" class="fs-1 m-auto"></fai>
      </div>
      <div class="mt-3 pb-3">
        <div>
          <h3 class="m-0 pb-1">{{ adDetail.adCreaNickName }}</h3>
        </div>
        <p class="m-0">
          {{ adDetail.adAddr1 }} {{ adDetail.adAddr2 }}
          {{ adDetail.adAddr3 }}
        </p>
      </div>
    </div>

    <div class="content-container">
      <div class="content-title-box">
        <div class="content-title">
          <h2 class="m-0">
            {{ adDetail.adTitle }}
          </h2>
        </div>
        <div class="created-date">
          <p class="m-0">
            {{ adDetail.adCreaDt
            }}<span class="float-end"> 조회수 : {{ adDetail.adView }} </span>
          </p>
        </div>
        <div class="ad-text">
          <p class="m-0">{{ adDetail.adCate }}</p>
        </div>
      </div>

      <div class="content">
        <pre class="m-0 pre">{{ adDetail.adContents }}</pre>
      </div>

      <div class="col-md-8 mx-auto mt-3">
        <div v-if="adDetail.imgList != ''" class="text-center">
          <div v-for="item in adDetail.imgList" :key="item.imgNo">
            <img
              :src="item.storedImgPath"
              class="my-1"
              style="max-width: 300px"
            />
          </div>
        </div>
      </div>
    </div>

    <div class="btn-container border-bottom">
      <div class="top-btn-box">
        <div class="like-comment">
          <div class="left-btn">
            <div class="like-icon">
              <span>관심 ' </span
              ><span class="count"> {{ adDetail.adLike }}</span>
            </div>
          </div>
          <div class="board-report-btn">
            <span class="report-text">게시글 신고</span>
            <fai :icon="['fas', 'bullhorn']"></fai>
          </div>
        </div>
      </div>

      <div class="bottom-btn-box">
        <div class="left-btn" v-on:click="likeUp" v-if="!like">
          <div class="like-btn">
            <fai :icon="['far', 'heart']"></fai>
          </div>
          <p class="m-0 text">추천</p>
        </div>
        <div class="left-btn" v-on:click="likeDown" v-else>
          <div class="like-btn">
            <fai :icon="['fas', 'heart']"></fai>
          </div>
          <p class="m-0 text">추천 취소</p>
        </div>
      </div>
      <div class="d-flex justify-content-end">
        <b-button class="m-1" variant="primary" v-on:click="MoveMarketList">
          목록으로
        </b-button>
        <b-button
          class="m-1"
          variant="info"
          v-on:click="MoveAdUpdate"
          v-if="myUserNo == adDetail.adUserNo || myRank == 0"
        >
          수정
        </b-button>
        <b-button
          class="m-1"
          variant="danger"
          v-on:click="adDelete"
          v-if="myUserNo == adDetail.adUserNo || myRank == 0"
          >삭제</b-button
        >
      </div>
    </div>

    <!-- 댓글 -->
    <AdComents />
  </div>
</template>

<script>
import AdComents from "./AdComents.vue";
export default {
  components: {
    AdComents,
  },
  data() {
    return {
      adNo: "",
      adDetail: {},
      myUserNo: "",
      myRank: "",
      like: false,
    };
  },
  beforeCreate() {
    if (this.$route.query.adNo == null) {
      this.$router.push("/MarketList");
    }
  },
  mounted() {
    this.adNo = this.$route.query.adNo;
    if (sessionStorage.getItem("coolUserNo") != null) {
      this.myUserNo = sessionStorage.getItem("coolUserNo");
      this.myRank = sessionStorage.getItem("coolRank");
      this.$axios
        .post("http://coolmarket.link/adLike", null, {
          params: { adNo: this.adNo, userNo: this.myUserNo },
        })
        .then((res) => {
          this.like = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    }
    this.$axios
      .get("http://coolmarket.link/adDetail", { params: { adNo: this.adNo } })
      .then((res) => {
        if (res.data == "") {
          alert("삭제된 게시물입니다.");
          this.$router.push("/MarketList");
        } else {
          this.adDetail = res.data;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    MoveMarketList() {
      this.$router.push({ name: "MarketList" });
    },
    MoveAdUpdate() {
      this.$router.push({ name: "AdUpdate", query: { adNo: this.adNo } });
    },
    adDelete() {
      this.$axios
        .get("http://coolmarket.link/adDelete", {
          params: { adNo: this.adNo },
        })
        .then(() => {
          alert("삭제완료");
          this.$router.push("/MarketList");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    likeUp() {
      if (this.myUserNo == "") {
        alert("로그인 해주세요");
      } else {
        this.$axios
          .get("http://coolmarket.link/adLikeUp", {
            params: { adNo: this.adNo, userNo: this.myUserNo },
          })
          .then(() => {
            alert("좋아요!!");
            this.$router.go();
          });
      }
    },
    likeDown() {
      if (this.myUserNo == "") {
        alert("로그인 해주세요");
      } else {
        this.$axios
          .get("http://coolmarket.link/adLikeDown", {
            params: { adNo: this.adNo, userNo: this.myUserNo },
          })
          .then(() => {
            alert("좋아요 취소");
            this.$router.go();
          });
      }
    },
  },
};
</script>

<style scoped>
.pre {
  white-space: pre-wrap;
  word-break: break-all;
}
.wrap {
  max-width: 650px;
  margin: 30px auto;
  padding: 0 32px;
}

#carouselExampleIndicators {
  border-radius: 10px;
  width: 100%;
  height: 450px;
  overflow: hidden;
}

.carousel-inner {
  height: 100%;
  width: 100%;
  overflow: hidden;
}
.carousel-item img {
  object-fit: cover !important;
  width: 100%;
  height: 100%;
}

.carousel-item {
  height: 100%;
  width: 100%;
  /* object-fit: cover; */
  /* align-items: center; */
  /* overflow: hidden; */
}

.user-info-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  border-bottom: 1px solid rgb(241, 241, 241);
}

.left-section {
  display: flex;
  align-items: center;
  margin-top: 15px;
  padding-bottom: 15px;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-name:hover {
  cursor: pointer;
  text-decoration: underline;
  text-underline-position: under;
  text-decoration-color: rgb(161, 161, 161) !important;
}

.user-img {
  width: 65px;
  height: 65px;
  border-radius: 180px;
  border: 1px solid rgb(243, 243, 243);
  background-color: #ffff;
  text-align: center;
  font-size: 32px;
}
.user-text {
  height: 50px;
  display: flex;
  flex-direction: column;
  margin: 0 15px;
  justify-content: space-between;
}
.user-name {
  font-size: 14px;
  font-weight: bold;
}
.user-region {
  font-size: 11px;
}

.content-container {
  width: 100%;
  padding: 32px 0;
}

.content-title-box {
  display: flex;
  flex-direction: column;
  margin-bottom: 30px;
}

.content-title {
  word-break: keep-all;
}
.content-title h2 {
  font-size: 21px;
  font-weight: bold;
}

.created-date {
  font-size: 13px;
}

.ad-text {
  font-size: 16px;
  font-weight: bold;
  color: rgba(255, 94, 0, 0.959);
}

.btn-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  /* border-bottom: 1px solid rgb(241, 241, 241); */
}

.left-btn {
  display: flex;
}

.like-comment {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 18px;
  padding-bottom: 10px;
  border-bottom: 1px solid rgb(241, 241, 241);
}

.like-icon {
  margin-right: 10px;
  font-size: 13px;
}

.comment-icon {
  font-size: 13px;
}
.count {
  font-size: 13px;
}

.board-report-btn {
  font-size: 18px;
  color: rgba(255, 0, 0, 0.897);
  cursor: pointer;
}
.board-report-btn:hover {
  transform: scale(1.1);
}

.report-text {
  font-size: 12px;
  margin-right: 5px;
  opacity: 0.8;
}

.bottom-btn-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 0;
}

.like-btn {
  font-size: 28px;
  line-height: 1;
  border-right: 1px solid rgb(216, 216, 216);
  padding-right: 5px;
  margin-right: 5px;
  cursor: pointer;
}
.text {
  /* line-height: 1.8; */
  align-self: center;
  font-size: 13px;
  color: rgb(87, 87, 87);
}

.my-btn {
  height: fit-content;
  font-size: 13px;
  font-weight: bold;
  color: #ffff;
  background: rgba(255, 94, 0, 0.959);
  padding: 8px 16px;
  border-radius: 8px;
}
.delete-btn {
  background-color: red;
}
.my-btn:hover {
  transform: scale(1.05);
  transition: 0.12s;
}

.list-btn {
  align-self: flex-end;
  font-size: 15px;
}
.list-btn:hover {
  /* border-bottom: 1px solid rgb(187, 187, 187); */
  text-decoration: underline;
  text-underline-position: under;
  text-decoration-color: rgb(161, 161, 161);
}

.comment-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 16px 0;
  border-top: 1px solid rgb(241, 241, 241);
}

.comment-user-img {
  width: 40px;
  height: 40px;
  font-size: 18px;
  line-height: 2;
}
.comment-user-text {
  justify-content: center;
}
.comment-user-name {
  font-size: 12px;
}
.comment-user-region {
  display: flex;
  font-size: 10px;
}

.comment {
  padding-left: 54px;
  font-size: 14px;
  font-weight: bold;
}

.menu-container {
  display: flex;
  width: 100%;
}

.file-btn {
  width: 30px;
  height: 30px;
  margin-right: 10px;
  font-size: 20px;
  font-weight: bold;
  color: #dfddddfd;
  text-align: center;
  cursor: pointer;
}
.file-btn:hover {
  transform: scale(1.1);
}
#file {
  /* width: 50px;
        position: absolute;
        top: 3px;
        left: 8px;
        opacity: 0; */
  display: none;
}
label {
  width: 30px;
  height: 30px;
}
label:hover {
  cursor: pointer;
}

.report-btn {
  width: 30px;
  height: 30px;
  font-size: 20px;
  font-weight: bold;
  color: #dfddddfd;
  text-align: center;
  cursor: pointer;
}
.report-btn:hover {
  transform: scale(1.1);
}

.send-input-container {
  display: flex;
  width: 100%;
  padding: 3px 32px;
  /* background-color: #fafafafa; */
  /* align-items: center; */
  justify-content: space-between;
  border: 1px solid rgb(241, 241, 241);
}
.send-input-container:hover {
  box-shadow: 1px 1px 10px 1px rgb(241, 241, 241);
}

.text-input {
  width: 90%;
}

#send-msg {
  font-size: 14px;
  height: 40px;
  border: none;
  outline: none;
  width: 100%;
}

.send-btn {
  font-size: 25px;
  color: rgb(0, 174, 255);
  text-align: center;
}

.send-btn:hover {
  transform: scale(0.9);
  transition: 0.1s;
}
</style>