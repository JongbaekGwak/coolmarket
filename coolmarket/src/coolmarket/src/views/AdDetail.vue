<template>
  <div class="wrap">
    <div id="carouselExampleIndicators" class="carousel slide">
      <ol class="carousel-indicators">
        <li
          data-target="#carouselExampleIndicators"
          data-slide-to="0"
          class="active"
        ></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active" data-interval="3000">
          <img src="" class="w-100" alt="..." />
        </div>
        <div class="carousel-item" data-interval="3000">
          <img src="" class="w-100" alt="..." />
        </div>
        <div class="carousel-item" data-interval="3000">
          <img src="" class="w-100" alt="..." />
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>

    <div class="user-info-container">
      <div class="left-section">
        <div class="user-info">
          <div class="user-img">
            <i class="fa fa-user" aria-hidden="true"></i>
          </div>
          <div class="user-text">
            <div class="user-name" v-on:click="MoveMsgDetail">
              <p class="m-0 pb-1">수영구주민</p>
            </div>
            <div class="user-region">
              <p class="m-0">부산광영시 수영구</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="content-container">
      <div class="content-title-box">
        <div class="content-title">
          <h2 class="m-0">
            냉장고 팝니다 거의 새 것 구매한지 1년 정도 됐습니다.
          </h2>
        </div>
        <div class="created-date">
          <p class="m-0">2021.08.21</p>
        </div>
        <div class="ad-text">
          <p class="m-0">지역광고</p>
        </div>
      </div>

      <div class="content">
        <p class="m-0">
          안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것안녕하세요 물건 팝니다 이것
          저것안녕하세요 물건 팝니다 이것 저것
        </p>
      </div>
    </div>

    <div class="btn-container">
      <div class="top-btn-box">
        <div class="like-comment">
          <div class="left-btn">
            <div class="like-icon">
              <span>관심 ' </span><span class="count"> 21</span>
            </div>
            <div class="comment-icon">
              <span>댓글 ' </span><span class="count"> 5</span>
            </div>
          </div>
          <div class="board-report-btn">
            <span class="report-text">게시글 신고</span
            ><i
              class="fa fa-bullhorn"
              aria-hidden="true"
              v-on:click="MoveReport"
            ></i>
          </div>
        </div>
      </div>

      <div class="bottom-btn-box">
        <div class="left-btn">
          <div class="like-btn">
            <i class="fa fa-heart-o hidden-icon" aria-hidden="true"></i>
          </div>
          <p class="m-0 text">추천</p>
        </div>
        <div class="msg-btn my-btn" type="button" v-on:click="MoveMsgDetail">
          문의 하기
        </div>
      </div>
      <div class="bottom-btn-box">
        <div
          class="rewrite-btn my-btn"
          type="button"
          vo-on:click="MoveAdUpdate"
        >
          수정
        </div>
        <div class="delete-btn my-btn" type="button">삭제</div>
      </div>
      <div class="list-btn" type="button" v-on:click="MoveMarketList">
        목록으로
      </div>
    </div>

    <div class="comment-container">
      <div class="user-info-box comment-user-info-box">
        <div class="user-info comment-user-info">
          <div class="user-img comment-user-img">
            <i class="fa fa-user" aria-hidden="true"></i>
          </div>
          <div class="user-text comment-user-text">
            <div class="user-name comment-user-name" v-on:click="MoveMsgDetail">
              <p class="m-0 pb-1">수영구주민</p>
            </div>
            <div class="user-region comment-user-region">
              <p class="m-0">부산광영시 수영구</p>
              <p class="m-0">&nbsp;2021.08.22</p>
            </div>
          </div>
        </div>
      </div>
      <div class="comment-box">
        <div class="comment">
          <p class="m-0">안녕하세요 여기 맛있어요 ~</p>
        </div>
      </div>
    </div>

    <div class="menu-container">
      <div class="send-input-left">
        <div class="file-btn">
          <input type="file" name="" id="file" accept=".jpg,.jpeg,.png,.gif" />
          <label for="file"
            ><i class="fa fa-folder-o" aria-hidden="true"></i
          ></label>
        </div>
      </div>
      <div class="report-btn">
        <i
          class="fa fa-bullhorn"
          aria-hidden="true"
          v-on:click="MoveReport"
        ></i>
      </div>
    </div>

    <div class="send-input-container">
      <div class="text-input">
        <input
          type="text"
          name="send-msg"
          id="send-msg"
          autocomplete="off"
          v-on:keyup.enter="send"
          v-model="msg"
        />
      </div>
      <div class="send-btn" type="button" v-on:click="send">
        <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
      </div>

      <!-- <div class="text-input">
                <input type="text" name="send-msg" id="send-msg" autocomplete='off' v-on:keyup.enter="send" v-bind:value="msg" v-on:input="inputMsg">
            </div>
            <div class="send-btn" type="button" v-on:click="send">
                <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
            </div> -->
      <!-- 테스트 -->
      {{ msg }}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: "",
      adNo: "",
      adDetail: {},
    };
  },
  beforeCreate() {
    if (this.$route.query.adNo == null) {
      this.$router.go(-1);
    }
  },
  mounted() {
    this.adNo = this.$route.query.adNo;
    this.$axios
      .get("http://localhost:9000/adDetail", { params: { adNo: this.adNo } })
      .then((res) => {
        this.adDetail = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    MoveReport() {
      this.$router.push({ name: "Report" });
    },
    MoveMsgDetail() {
      this.$router.push({ name: "MsgDetail" });
    },
    MoveMarketList() {
      this.$router.push({ name: "MarketList" });
    },
    MoveAdUpdate() {
      this.$router.push({ name: "AdUpdate" });
    },
    send() {
      if (this.msg == "") return;
    },
  },
};
</script>

<style scoped>
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

.comment-user-info-box {
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