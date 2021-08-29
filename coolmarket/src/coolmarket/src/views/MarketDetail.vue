<template>
  <div>
    <!-- 판매자 정보 -->
    <div class="d-flex w-100 border-bottom">
      <div class="d-flex justify-content-center m-1 p-2">
        <fai :icon="['far', 'user']" class="fs-1 m-auto"></fai>
      </div>
      <div class="mt-3 pb-3">
        <div class="cursor" v-on:click="MoveMsgDetail">
          <h3 class="m-0 pb-1">{{ marketDetail.marCreaNickName }}</h3>
        </div>
        <p class="m-0">
          {{ marketDetail.marAddr1 }} {{ marketDetail.marAddr2 }}
          {{ marketDetail.marAddr3 }}
        </p>
      </div>
    </div>

    <div class="my-3">
      <small>
        <span class="badge rounded-pill bg-primary">
          {{ marketDetail.marCate }}
        </span>
        <span
          class="badge rounded-pill bg-secondary"
          v-if="marketDetail.marBuyUserNo != ''"
        >
          판매 완료
        </span>
      </small>
      <h1>{{ marketDetail.marTitle }}</h1>
      <p class="m-0 fw-bold fs-4">
        {{ won(marketDetail.marPrice) }}<span>원</span>
      </p>
      <p class="m-0 w-100">
        {{ marketDetail.marCreaDt }}
        <span class="float-end"> 조회수 : {{ marketDetail.marView }} </span>
      </p>

      <pre class="my-3">{{ marketDetail.marContents }}</pre>

      <div class="col-md-8 mx-auto">
        <div v-if="marketDetail.imgList != ''" class="text-center">
          <div v-for="item in marketDetail.imgList" :key="item.imgNo">
            <img :src="item.storedImgPath" class="my-1" />
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-between border-bottom">
        <p>
          관심 ' {{ marketDetail.marLike }} 대화 ' {{ marketDetail.marTalkCnt }}
        </p>

        <p class="text-danger cursor" v-on:click="decInsert">
          게시글 신고 <fai :icon="['fas', 'bullhorn']"></fai>
        </p>
      </div>

      <div class="d-flex pb-5">
        <p class="btn me-auto">
          <span v-if="myWishSear == true" v-on:click="wish"
            ><fai :icon="['far', 'heart']"></fai> 찜하기</span
          >
          <span v-if="myWishSear == false" v-on:click="wishCancel"
            ><fai :icon="['fas', 'heart']"></fai> 찜하기 취소</span
          >
        </p>
        <div class="d-flex justify-content-end">
          <b-button
            class="m-1"
            variant="warning"
            v-on:click="MoveMsgDetail"
            v-if="marketDetail.marUserNo != myUserNo"
          >
            채팅으로 구매하기
          </b-button>
          <b-button
            class="m-1"
            variant="warning"
            v-on:click="setSale1"
            v-if="
              marketDetail.marUserNo == myUserNo &&
              marketDetail.marBuyUserNo == ''
            "
          >
            판매완료 등록
          </b-button>
          <b-button class="m-1" variant="primary" v-on:click="MoveMarketList">
            목록으로
          </b-button>

          <b-button
            class="m-1"
            variant="info"
            v-on:click="MoveMarketUpdate"
            v-if="myUserNo == marketDetail.marUserNo || myRank == 0"
          >
            수정
          </b-button>
          <b-button
            class="m-1"
            variant="danger"
            v-on:click="marDelete"
            v-if="myUserNo == marketDetail.marUserNo || myRank == 0"
            >삭제</b-button
          >
        </div>
      </div>

      <div class="my-3" v-if="setSale == true">
        <div class="d-flex">
          <b-input-group>
            <b-input-group-text>구매자 닉네임</b-input-group-text>
            <b-input v-model="marBuyUser"></b-input>
            <b-input-group-text
              class="ms-2 bg-warning rounded cursor"
              v-on:click="setSale2"
              >판매완료</b-input-group-text
            >
          </b-input-group>
        </div>
      </div>
    </div>

    <!--인기 상품 -->
    <div class="popular-list-container">
      <div class="popular-list-header">
        <div class="popular-list-title">
          <h3 class="m-0">중고 거래 인기 상품</h3>
        </div>
      </div>
      <div class="popular-list-content">
        <div
          class="card-section sale-card-section"
          v-for="item in hotMarket"
          :key="item.marNo"
          v-on:click="MoveDetail(item.marNo)"
        >
          <div class="card-box">
            <div class="card-img-box" v-if="item.imgList != ''">
              <img :src="path(item.imgList.storedImgPath)" alt="광고이미지" />
            </div>
            <div class="card-img-box" v-if="item.imgList == ''">
              <img src="https://placekitten.com/g/300/450" alt="광고이미지" />
            </div>
            <div class="card-body">
              <div class="card-title">
                <h2>{{ item.marTitle }}</h2>
              </div>
              <div style="font-size: 14px; font-weight: bold">
                {{ won(item.marPrice) }}<span>원</span>
              </div>
              <div class="card-region" style="font-size: 12px">
                {{ item.marAddr1 }} {{ item.marAddr2 }} {{ item.marAddr3 }}
              </div>
              <div class="like-comment">
                <div class="like-icon">
                  <fai :icon="['fas', 'heart']"></fai>
                  <span class="count">{{ item.marLike }}</span>
                </div>
                <div class="comment-icon">
                  <fai :icon="['fas', 'comment']"></fai>
                  <span class="count">{{ item.marTalkCnt }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      marNo: "",
      marketDetail: [],
      hotMarket: [],
      myUserNo: "",
      myRank: "",
      myWishSear: true,
      marBuyUser: "",
      setSale: false,
    };
  },
  beforeCreate() {
    if (this.$route.query.marNo == null) {
      this.$router.push("/MarketList");
    }
  },
  mounted() {
    window.scrollTo(0, 0);
    if (this.$session.get("coolUserNo") != null) {
      this.myUserNo = this.$session.get("coolUserNo");
      this.myRank = this.$session.get("coolRank");
    }
    this.marNo = this.$route.query.marNo;
    this.$axios
      .get("http://localhost:9000/marketDetail", {
        params: { marNo: this.marNo },
      })
      .then((res) => {
        if (res.data == "") {
          alert("삭제된 게시믈 입니다.");
          this.$router.push("/MarketList");
        }
        this.marketDetail = res.data.marketDetail;
        this.hotMarket = res.data.hotMarket;
      })
      .catch((err) => {
        console.log(err);
      });

    if (this.$session.get("coolUserNo") != null) {
      this.$axios
        .get("http://localhost:9000/myWishSearch", {
          params: {
            wishUserNo: this.$session.get("coolUserNo"),
            wishMarNo: this.marNo,
          },
        })
        .then((res) => {
          this.myWishSear = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
  methods: {
    MoveMsgDetail() {
      if (this.$session.get("coolUserNo") == null) {
        alert("로그인 해주세요");
      } else {
        this.$router.push({
          name: "MsgDetail",
          query: {
            marUserNo: this.marketDetail.marUserNo,
            userNo: this.myUserNo,
          },
        });
      }
    },
    MoveMarketList() {
      this.$router.push("/MarketList");
    },
    MoveMarketUpdate() {
      this.$router.push({
        name: "MarkeUpdate",
        query: { marNo: this.marketDetail.marNo },
      });
    },
    MoveDetail(num) {
      this.$router.push({ name: "MarketDetail", query: { marNo: num } });
    },
    won(num) {
      if (num != null) {
        return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    wish() {
      if (this.$session.get("coolUserNo") == null) {
        alert("로그인 해주세요");
      } else {
        this.$axios
          .get("http://localhost:9000/wish", {
            params: {
              wishUserNo: this.$session.get("coolUserNo"),
              wishMarNo: this.marketDetail.marNo,
            },
          })
          .then(() => {
            alert("찜목록에 등록 되었습니다.");
            this.$router.go();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    wishCancel() {
      this.$axios
        .get("http://localhost:9000/wishCancel", {
          params: {
            wishUserNo: this.$session.get("coolUserNo"),
            wishMarNo: this.marketDetail.marNo,
          },
        })
        .then(() => {
          alert("찜목록에서 삭제 되었습니다.");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    marDelete() {
      this.$axios
        .get("http://localhost:9000/marDelete", {
          params: { marNo: this.marNo },
        })
        .then(() => {
          alert("삭제완료");
          this.$router.go(-1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    decInsert() {
      this.$axios
        .get("http://localhost:9000/decInsert", {
          params: { marNo: this.marNo, comNo: 0 },
        })
        .then(() => {
          alert("신고 하였습니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setSale1() {
      this.setSale = true;
    },
    setSale2() {
      this.$axios
        .get("http://localhost:9000/setSale", {
          params: { marNo: this.marNo, nickName: this.marBuyUser },
        })
        .then((res) => {
          if (res.data == true) {
            alert("판매 완료 등록");
            this.$router.go();
          } else {
            alert("구매자 닉네임을 잘못 입력하셧습니다.");
          }
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

.popular-list-header {
  padding: 24px 0 12px;
  margin-bottom: 15px;
  border-top: 1px solid rgb(241, 241, 241);
  display: flex;
  justify-content: space-between;
}
.popular-list-title h3 {
  font-size: 18px;
  font-weight: bold;
}

.popular-list-content {
  display: flex;
  /* justify-content: space-between; */
  flex-wrap: wrap;
}

.card-section {
  margin-bottom: 50px;
  border-radius: 8px;
}
.card-section:hover {
  box-shadow: 4px 4px 15px 1px #f1f0f0;
  transform: scale(0.99);
  transition: 0.1s;
}

.card-box {
  width: 150px;
  cursor: pointer;
  /* margin-bottom: 20px; */
}
.card-img-box {
  width: 150px;
  height: 150px;
  margin-bottom: 10px;
  overflow: hidden;
  border-radius: 8px;
  border: 1px solid lightgrey;
}
.card-img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.card-body {
  padding: 0;
}
.card-body h2 {
  font-size: 16px;
  height: 20px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.like-comment {
  width: 100%;
  display: flex;
  align-items: center;
  font-size: 15px;
}

.like-icon {
  margin-right: 10px;
}

.count {
  font-size: 14px;
}

.sale-section {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  justify-content: space-around;
}

.sale-card-section {
  /* display: flex; */
  width: 150px;
  justify-content: center;
  margin: 0 auto 20px;
}
</style>