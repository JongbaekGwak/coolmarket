<template>
  <div>
    <div class="border bg-light py-4">
      <h3 class="text-center">개인정보</h3>
      <b-row>
        <b-col md="6" class="mx-auto">
          <p>이름 : {{ user.name }}</p>
          <p>별명 : {{ user.nickName }}</p>
          <p>등급 : {{ rank }}</p>
          <p>연락처 : {{ user.tel }}</p>
          <p>주소 : {{ user.addr1 }} {{ user.addr2 }} {{ user.addr3 }}</p>
          <div>
            <b-button-group class="d-flex">
              <b-button v-on:click="premium" pill variant="success" class="mx-1"
                >프리미엄 신청</b-button
              ><b-button v-on:click="msg" pill variant="info" class="mx-1"
                >쪽지함</b-button
              ><b-button v-on:click="logOut" pill variant="warning" class="mx-1"
                >로그아웃</b-button
              >
            </b-button-group>
          </div>
        </b-col>
      </b-row>
    </div>
    <div class="mt-3">
      <b-tabs content-class="mt-3" fill>
        <!-- 판매목록 -->
        <b-tab title="판매목록" active>
          <div class="mt-5">
            <div>
              <div>
                <h3 class="text-center" v-if="saleList == ''">
                  등록하신 목록 없습니다.
                </h3>
              </div>
              <div v-if="saleList != ''">
                <b-row>
                  <b-col
                    sm="4"
                    class="mx-auto"
                    v-for="item in saleList"
                    :key="item.marNo"
                    v-on:click="MarketDetail(item.marNo)"
                  >
                    <b-card
                      :img-src="imgPath(item.imgList)"
                      img-alt="Image"
                      img-top
                      class="me-2 border-0 cursor"
                      img-height="250"
                    >
                      <b-card-text>
                        <p>{{ item.marTitle }}</p>
                        <p>{{ won(item.marPrice) }} 원</p>
                        <p>
                          {{ item.marAddr1 }}{{ item.marAddr2
                          }}{{ item.marAddr3 }}
                        </p>
                        <p>
                          <span>
                            <fai :icon="['fas', 'heart']"></fai>
                          </span>
                          {{ item.marLike }}
                          <span>
                            <fai :icon="['fas', 'comment']"></fai>
                          </span>
                          {{ item.marTalkCnt }}
                        </p>
                      </b-card-text>
                    </b-card>
                  </b-col>
                </b-row>
              </div>
            </div>
          </div>
        </b-tab>

        <!-- 구매목록 -->
        <b-tab title="구매목록">
          <div class="mt-5">
            <div>
              <div>
                <h3 class="text-center" v-if="buyList == ''">
                  구매하신 목록 없습니다.
                </h3>
              </div>
              <div v-if="buyList != ''">
                <b-row>
                  <b-col
                    sm="4"
                    class="mx-auto"
                    v-for="item in buyList"
                    :key="item.marNo"
                    v-on:click="MarketDetail(item.marNo)"
                  >
                    <b-card
                      :img-src="imgPath(item.imgList)"
                      img-alt="Image"
                      img-top
                      class="me-2 border-0 cursor"
                      img-height="250"
                    >
                      <b-card-text>
                        <p>{{ item.marTitle }}</p>
                        <p>{{ won(item.marPrice) }} 원</p>
                        <p>
                          {{ item.marAddr1 }}{{ item.marAddr2
                          }}{{ item.marAddr3 }}
                        </p>
                        <p>
                          <span>
                            <fai :icon="['fas', 'heart']"></fai>
                          </span>
                          {{ item.marLike }}
                          <span>
                            <fai :icon="['fas', 'comment']"></fai>
                          </span>
                          {{ item.marTalkCnt }}
                        </p>
                      </b-card-text>
                    </b-card>
                  </b-col>
                </b-row>
              </div>
            </div>
          </div>
        </b-tab>

        <!-- 찜 목록 -->
        <b-tab title="찜 목록">
          <div class="mt-5">
            <div v-if="wishList == ''">
              <h3 class="text-center">찜 목록이 없습니다.</h3>
            </div>
            <div v-if="wishList != ''">
              <b-row>
                <b-col
                  sm="4"
                  class="mx-auto"
                  v-for="item in wishList"
                  :key="item.marNo"
                  v-on:click="MarketDetail(item.marNo)"
                >
                  <b-card
                    :img-src="imgPath(item.imgList)"
                    img-alt="Image"
                    img-top
                    class="me-2 border-0 cursor"
                    img-height="250"
                  >
                    <b-card-text>
                      <p>{{ item.marTitle }}</p>
                      <p>{{ won(item.marPrice) }} 원</p>
                      <p>
                        {{ item.marAddr1 }}{{ item.marAddr2
                        }}{{ item.marAddr3 }}
                      </p>
                      <p>
                        <span>
                          <fai :icon="['fas', 'heart']"></fai>
                        </span>
                        {{ item.marLike }}
                        <span>
                          <fai :icon="['fas', 'comment']"></fai>
                        </span>
                        {{ item.marTalkCnt }}
                      </p>
                    </b-card-text>
                  </b-card>
                </b-col>
              </b-row>
            </div>
          </div>
        </b-tab>

        <!-- 광고 목록 -->
        <b-tab title="광고 목록" v-if="rank == 2">
          <div class="mt-5">
            <div>
              <div>
                <h3 class="text-center" v-if="adList == ''">
                  등록하신 목록 없습니다.
                </h3>
              </div>
              <div v-if="adList != ''">
                <b-row>
                  <b-col
                    sm="4"
                    class="mx-auto"
                    v-for="item in adList"
                    :key="item.adNo"
                    v-on:click="AdDetail(item.adNo)"
                  >
                    <b-card
                      :img-src="imgPath(item.imgList)"
                      img-alt="Image"
                      img-top
                      class="me-2 border-0 cursor"
                      img-height="250"
                    >
                      <b-card-text>
                        <p>{{ item.adTitle }}</p>
                        <p>{{ won(item.adPrice) }} 원</p>
                        <p>
                          {{ item.adAddr1 }}{{ item.adAddr2 }}{{ item.adAddr3 }}
                        </p>
                        <p>
                          <span>
                            <fai :icon="['fas', 'heart']"></fai>
                          </span>
                          {{ item.adLike }}
                          <span>
                            <fai :icon="['fas', 'comment']"></fai>
                          </span>
                          {{ item.adTalkCnt }}
                        </p>
                      </b-card-text>
                    </b-card>
                  </b-col>
                </b-row>
              </div>
            </div>
          </div>
        </b-tab>

        <!-- 커뮤니티 등록 목록 -->
        <b-tab title="커뮤니티 등록 목록">
          <div class="mt-5">
            <div>
              <h3 class="text-center" v-if="commuList == ''">
                등록하신 글이 없습니다.
              </h3>
            </div>
            <div v-if="commuList != ''">
              <div
                class="board-card"
                v-for="item in commuList"
                :key="item.comNo"
                v-on:click="CommuDetail(item.comNo)"
              >
                <b-card
                  :img-src="imgPath(item.imgList)"
                  img-alt="Card image"
                  img-right
                  img-height="150px"
                  class="m-2 bg-light cursor"
                >
                  <b-card-text>
                    <p class="badge rounded-pill bg-secondary">
                      {{ item.comCate }}
                    </p>
                    <h4>{{ item.comTitle }}</h4>
                    <p>
                      {{ item.comCreaNickName }}
                      <span> | </span>
                      {{ item.comAddr1 }}
                      {{ item.comAddr2 }}
                      {{ item.comAddr3 }}
                      <span> | </span>
                      {{ item.comCreaDt }}
                    </p>
                  </b-card-text>
                </b-card>
              </div>

              <div class="d-flex justify-content-center">
                <nav aria-label="Page navigation example">
                  <ul class="pagination">
                    <li class="page-item">
                      <a
                        class="page-link"
                        aria-label="Previous"
                        v-on:click="previous"
                      >
                        <span aria-hidden="true">&laquo;</span>
                      </a>
                    </li>
                    <li
                      class="page-item"
                      v-for="page in commuNav.navigatepageNums"
                      :key="page"
                    >
                      <a
                        class="page-link"
                        v-on:click="movePage(page)"
                        v-text="page"
                      ></a>
                    </li>
                    <li class="page-item">
                      <a class="page-link" aria-label="Next" v-on:click="next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                  </ul>
                </nav>
              </div>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: [],
      rank: "",
      saleList: [],
      buyList: [],
      wishList: [],
      commuList: [],
      commuNav: [],
      adList: [],
    };
  },
  beforeCreate() {
    if (this.$session.get("coolUserNo") == null) {
      this.$router.push("/Login");
    } else if (this.$session.get("coolRank") == 0) {
      this.$router.push("ManagerPage");
    }
  },
  mounted() {
    this.$axios
      .get("http://localhost:9000/myList", {
        params: { userNo: this.$session.get("coolUserNo") },
      })
      .then((res) => {
        this.saleList = res.data.saleList;
        this.buyList = res.data.buyList;
        this.wishList = res.data.wishList;
      })
      .catch((err) => {
        console.log(err);
      });

    this.$axios
      .get("http://localhost:9000/myCommuList", {
        params: {
          userNo: this.$session.get("coolUserNo"),
        },
      })
      .then((res) => {
        this.commuList = res.data.list;
        this.commuNav = {
          navigatepageNums: res.data.navigatepageNums,
          prePage: res.data.prePage,
          nextPage: res.data.nextPage,
        };
      })
      .catch((err) => {
        console.log(err);
      });

    this.$axios
      .get("http://localhost:9000/me", {
        params: {
          userNo: this.$session.get("coolUserNo"),
        },
      })
      .then((res) => {
        this.user = res.data;
        if (this.user.rank == 1) {
          this.rank = "일반";
        } else if (this.user.rank == 2) {
          this.rank = "프리미엄";
        }
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/myAdList", {
        params: { adUserNo: this.$session.get("coolUserNo") },
      })
      .then((res) => {
        this.adList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    premium() {
      let rank = this.rank;
      if (rank == 2) {
        alert("이미 프리미엄 회원이십니다.");
      } else {
        this.$axios
          .get("http://localhost:9000/creaPremium", {
            params: { userNo: this.$session.get("coolUserNo") },
          })
          .then((res) => {
            if (res.data == true) {
              alert("프리미엄 신청 완료 \n 24시간 안에 적용 됩니다.");
            } else {
              alert("이미 신청하였습니다.");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    msg() {
      this.$router.push({
        name: "MsgList",
        query: { userNo: this.$session.get("coolUserNo") },
      });
    },
    logOut() {
      this.$session.clear();
      alert("로그아웃 되었습니다.");
      this.$router.push("/");
    },
    movePage(num) {
      this.$axios
        .get("http://localhost:9000/myCommuList", {
          params: {
            userNo: this.$session.get("coolUserNo"),
            pageNo: num,
          },
        })
        .then((res) => {
          this.commuList = res.data.list;
          this.commuNav = {
            navigatepageNums: res.data.navigatepageNums,
            prePage: res.data.prePage,
            nextPage: res.data.nextPage,
          };
        })
        .catch((err) => {
          console.log(err);
        });
    },
    previous() {
      if (this.commuNav.prePage == 0) {
        alert("첫번째 페이지 입니다.");
      } else {
        this.$axios
          .get("http://localhost:9000/myCommuList", {
            params: {
              userNo: this.$session.get("coolUserNo"),
              pageNo: this.commuNav.prePage,
            },
          })
          .then((res) => {
            this.commuList = res.data.list;
            this.commuNav = {
              navigatepageNums: res.data.navigatepageNums,
              prePage: res.data.prePage,
              nextPage: res.data.nextPage,
            };
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    next() {
      if (this.commuNav.nextPage == 0) {
        alert("마지막 페이지 입니다.");
      } else {
        this.$axios
          .get("http://localhost:9000/myCommuList", {
            params: {
              userNo: this.$session.get("coolUserNo"),
              pageNo: this.commuNav.nextPage,
            },
          })
          .then((res) => {
            this.commuList = res.data.list;
            this.commuNav = {
              navigatepageNums: res.data.navigatepageNums,
              prePage: res.data.prePage,
              nextPage: res.data.nextPage,
            };
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    CommuDetail(num) {
      this.$router.push({ name: "CommuDetail", query: { comNo: num } });
    },
    MarketDetail(num) {
      this.$router.push({ name: "MarketDetail", query: { marNo: num } });
    },
    AdDetail(num) {
      this.$router.push({ name: "AdDetail", query: { adNo: num } });
    },
    won(num) {
      if (num != null) {
        return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    imgPath(src) {
      if (src == "") {
        return "images/noImg.png";
      } else {
        return src[0].storedImgPath;
      }
    },
  },
};
</script>
<style scoped>
.cursor {
  cursor: pointer;
}
</style>