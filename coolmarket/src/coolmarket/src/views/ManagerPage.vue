<template>
  <div>
    <div class="my-3 d-flex justify-content-end">
      <b-button v-on:click="logOut">로그아웃</b-button>
    </div>
    <b-tabs content-class="mt-3" fill>
      <b-tab title="마켓 신고" active>
        <div class="mt-5">
          <div class="d-flex justify-content-end m-2">
            <b-button variant="danger" v-on:click="marAllDel"
              >일괄삭제</b-button
            >
          </div>
          <div v-if="marDec == ''">
            <h3 class="text-center">신고된 게시물이 없습니다.</h3>
          </div>
          <div v-if="marDec != ''">
            <b-row>
              <b-col
                sm="4"
                class="mx-auto"
                v-for="item in marDec"
                :key="item.marNo"
                v-on:click="MarketDetail(item.marNo)"
              >
                <b-card
                  v-if="item.imgList != ''"
                  :img-src="item.imgList[0].storedImgPath"
                  img-alt="Image"
                  img-top
                  class="me-2 border-0 cursor"
                  img-height="250"
                >
                  <b-card-text>
                    <p>{{ item.marTitle }}</p>
                    <p>{{ won(item.marPrice) }} 원</p>
                    <p>
                      {{ item.marAddr1 }}{{ item.marAddr2 }}{{ item.marAddr3 }}
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

                <b-card
                  v-if="item.imgList == ''"
                  img-src="https://placekitten.com/g/300/450"
                  img-alt="Image"
                  img-top
                  class="me-2 border-0 cursor"
                  img-height="250"
                >
                  <b-card-text>
                    <p>{{ item.marTitle }}</p>
                    <p>{{ won(item.marPrice) }} 원</p>
                    <p>
                      {{ item.marAddr1 }}{{ item.marAddr2 }}{{ item.marAddr3 }}
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

      <b-tab title="커뮤니티 신고">
        <div class="mt-5">
          <div class="d-flex justify-content-end m-2">
            <b-button variant="danger" v-on:click="comAllDel"
              >일괄삭제</b-button
            >
          </div>
          <div v-if="comDec == ''">
            <h3 class="text-center">신고된 게시물이 없습니다.</h3>
          </div>
          <div v-if="comDec != ''">
            <div
              class="board-card"
              v-for="item in comDec"
              :key="item.comNo"
              v-on:click="CommuDetail(item.comNo)"
            >
              <b-card
                v-if="item.imgList != ''"
                :img-src="item.imgList[0].storedImgPath"
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

              <b-card
                v-if="item.imgList == ''"
                img-src="https://placekitten.com/300/300"
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
          </div>
        </div>
      </b-tab>

      <b-tab title="프리미엄 신청자">
        <div class="mt-5">
          <div class="d-flex justify-content-end m-2">
            <b-button variant="info" v-on:click="preAllUp">일괄등업</b-button>
          </div>
          <div
            class="border p-3 m-2 rounded"
            v-for="item in premiumWait"
            :key="item.userNo"
          >
            <b-button
              variant="primary"
              class="float-end"
              v-on:click="preUp(item.userNo)"
              >등업</b-button
            >
            <p>이메일 : {{ item.email }}</p>
            <p>신청날짜 : {{ item.creaDt }}</p>
            <p>이름 : {{ item.name }}</p>
            <p>별명 : {{ item.nickName }}</p>
          </div>
        </div>
      </b-tab>

      <b-tab title="프리미엄 회원">
        <div class="mt-5">
          <table class="table table-hover">
            <colgroup>
              <col width="10%" />
              <col width="25%" />
              <col width="15%" />
              <col width="15%" />
              <col width="*" />
            </colgroup>
            <thead>
              <tr>
                <th span="col">No</th>
                <th span="col">이메일</th>
                <th span="col">이름</th>
                <th span="col">별명</th>
                <th span="col">유지기간</th>
              </tr>
            </thead>
            <tbody>
              <tr v-if="premiumUser == ''">
                <td colspan="5" class="text-center">
                  프리미엄 회원이 없습니다.
                </td>
              </tr>
              <tr v-for="item in premiumUser" :key="item.userNo">
                <td>{{ item.userNo }}</td>
                <td>{{ item.email }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.nickName }}</td>
                <td>{{ item.preDt }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </b-tab>
    </b-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      marDec: [],
      comDec: [],
      premiumWait: [],
      premiumUser: [],
    };
  },
  mounted() {
    this.$axios
      .get("http://coolmarket.link/managerDataList")
      .then((res) => {
        this.marDec = res.data.marDec;
        this.comDec = res.data.comDec;
        this.premiumWait = res.data.premiumWait;
        this.premiumUser = res.data.premiumUser;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    logOut() {
      sessionStorage.clear();
      alert("로그아웃 되었습니다.");
      this.$router.push("/");
    },
    CommuDetail(num) {
      this.$router.push({ name: "CommuDetail", query: { comNo: num } });
    },
    MarketDetail(num) {
      this.$router.push({ name: "MarketDetail", query: { marNo: num } });
    },
    marAllDel() {
      this.$axios
        .get("http://coolmarket.link/marAllDel")
        .then(() => {
          alert("삭제 완료");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    comAllDel() {
      this.$axios
        .get("http://coolmarket.link/comAllDel")
        .then(() => {
          alert("삭제 완료");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    preAllUp() {
      let arr = [];
      for (let i = 0; i < this.premiumWait.length; i++) {
        arr[i] = this.premiumWait[i].userNo;
      }
      this.$axios
        .get("http://coolmarket.link/preAllUp", {
          params: { userNoList: arr },
          paramsSerializer(paramObj) {
            const params = new URLSearchParams();
            for (const key in paramObj) {
              params.append(key, paramObj[key]);
            }
            return params.toString();
          },
        })
        .then(() => {
          alert("일괄등업 완료");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    preUp(num) {
      this.$axios
        .get("http://coolmarket.link/preUp", { params: { userNo: num } })
        .then(() => {
          alert("등업 완료");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    won(num) {
      if (num != null) {
        return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
    },
    path(str) {
      return "../../../../" + str;
    },
  },
};
</script>
<style scoped>
.cursor {
  cursor: pointer;
}
</style>