<template>
  <div>
    <p>댓글 : {{ coment.length }}</p>
    <div
      class="comment-container border-bottom"
      v-for="item in coment"
      :key="item.adComentNo"
    >
      <div class="user-info-box comment-user-info-box">
        <div class="user-info comment-user-info">
          <div class="user-img comment-user-img">
            <fai :icon="['far', 'user']"></fai>
          </div>
          <div class="user-text comment-user-text">
            <div class="user-name comment-user-name">
              <p class="m-0 pb-1">{{ item.adComentNickName }}</p>
            </div>
            <div class="user-region comment-user-region">
              <p class="m-0">{{ item.adComentCreaDt }}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="comment-box">
        <div class="comment">
          <pre class="m-0 pre">{{ item.adComentContents }}</pre>
        </div>
        <b-button
          class="float-end"
          size="sm"
          variant="danger"
          v-if="item.adComentUserNo == myUserNo || myRank == 0"
          v-on:click="comentDelete(item.adComentNo)"
          >삭제</b-button
        >
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
      myRank: "",
    };
  },
  mounted() {
    this.myUserNo = this.$session.get("coolUserNo");
    if (this.$session.get("coolUserNo") != null) {
      this.myUserNo = this.$session.get("coolUserNo");
      this.myRank = this.$session.get("coolRank");
    }
    this.$axios
      .get("http://localhost:9000/adComent", {
        params: { adComentAdNo: this.$route.query.adNo },
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
      if (this.myUserNo == "") {
        alert("로그인 해주세요");
      } else {
        this.$axios
          .get("http://localhost:9000/adComentInsert", {
            params: {
              adComentAdNo: this.$route.query.adNo,
              adComentUserNo: this.$session.get("coolUserNo"),
              adComentNickName: this.$session.get("coolNickName"),
              adComentContents: this.insert,
            },
          })
          .then((res) => {
            this.coment = res.data;
            this.insert = "";
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    comentDelete(num) {
      if (this.myUserNo == "") {
        alert("로그인 해주세요");
      } else {
        this.$axios
          .get("http://localhost:9000/adComentDelete", {
            params: { adComentNo: num, adComentAdNo: this.$route.query.adNo },
          })
          .then((res) => {
            this.coment = res.data;
            alert("삭제완료");
          })
          .catch((err) => {
            console.log(err);
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