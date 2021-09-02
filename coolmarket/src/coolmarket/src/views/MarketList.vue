<template>
  <div class="wrap">
    <div class="content-header mb-5">
      <div class="content-title">
        <h1 class="text-center fs-2 fw-bold mt-5">
          <span>{{ addr1 }}</span>
          <span>{{ addr2 }}</span> 중고&nbsp;거래&nbsp;매물
        </h1>
      </div>

      <div class="content-select">
        <select class="select-box" v-model="selected" v-on:change="onSelect()">
          <option selected disabled value="">카테고리</option>
          <option v-for="item in cate" v-bind:key="item.marCateNo">
            {{ item }}
          </option>
        </select>

        <b-form-select
          v-on:change="Getaddr2(), onSelect(), onFlush()"
          v-model="addr1"
          :options="selected1"
          class="select-box"
        ></b-form-select>

        <b-form-select
          v-on:change="Getaddr3(), onSelect()"
          v-model="addr2"
          :options="selected2"
          class="select-box"
        ></b-form-select>

        <b-form-select
          v-on:change="onSelect()"
          v-model="addr3"
          :options="selected3"
          class="select-box"
        ></b-form-select>
      </div>
    </div>

    <div class="content-body">
      <div class="sale-section">
        <div
          class="card-section ad-card-section"
          v-for="(item, idx) in items"
          v-bind:key="idx"
        >
          <div
            class="card-box"
            v-if="item.adCate != null"
            v-on:click="MoveAdDetail(item.adNo)"
          >
            <div class="card-img-box">
              <img :src="imgPath(item.imgList)" alt="광고이미지" />
            </div>
            <div class="card-body">
              <div class="card-title">
                <h2>{{ item.adTitle }}</h2>
              </div>
              <div class="price" style="font-size: 14px; font-weight: bold">
                {{ item.adCate }}
                <span class="price-tag" v-if="item.marPrice">원</span>
              </div>
              <div class="card-addr" style="font-size: 12px">
                {{ item.adAddr1 }} {{ item.adAddr2 }}
              </div>

              <div class="like-comment">
                <div class="like-icon">
                  <fai :icon="['fas', 'heart']"></fai>
                  <span class="count"> {{ item.adLike }}</span>
                </div>
                <div class="comment-icon">
                  <fai :icon="['fas', 'comment']"></fai>
                  <span class="count"> {{ item.adView }}</span>
                </div>
              </div>
            </div>
          </div>
          <div
            class="card-box"
            v-if="item.adCate == null"
            v-on:click="MoveMarketDetail(item.marNo)"
          >
            <div class="card-img-box">
              <img :src="imgPath(item.imgList)" alt="광고이미지" />
            </div>
            <div class="card-body">
              <div class="card-title">
                <h2>{{ item.marTitle }}</h2>
              </div>
              <div class="price" style="font-size: 14px; font-weight: bold">
                {{ item.marPrice }}
                <span class="price-tag" v-if="item.marPrice">원</span>
              </div>
              <div class="card-addr" style="font-size: 12px">
                {{ item.marAddr1 }} {{ item.marAddr2 }}
              </div>

              <div class="like-comment">
                <div class="like-icon">
                  <fai :icon="['fas', 'heart']"></fai>
                  <span class="count"> {{ item.marLike }}</span>
                </div>
                <div class="comment-icon">
                  <fai :icon="['fas', 'comment']"></fai>
                  <span class="count"> {{ item.marView }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="btn-section">
        <div
          class="my-btn paging-btn"
          v-on:click="IncrementList"
          v-bind:disabled="this.items.length < 3"
        >
          <span>더보기</span>
        </div>
        <div
          class="my-btn paging-btn"
          v-on:click="DecrementList"
          v-if="this.items.length > 12"
        >
          <span>접기</span>
        </div>
        <div class="write-btn-section" v-if="this.user.userNo != null">
          <div class="my-btn sale-btn" v-on:click="MoveMarketWrite">
            <span>판매 등록</span>
          </div>
          <div
            class="my-btn ad-btn"
            v-on:click="MoveAdtWrite"
            v-if="this.user.rank != 1"
          >
            <span>광고 등록</span>
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
      user: [],
      addr1: "",
      addr2: "",
      addr3: "",
      selected: "",
      cate: "",
      selected1: [],
      selected2: [{ value: "", text: "시/군/구" }],
      selected3: [{ value: "", text: "동" }],
      items: [],

      marStartNum: 0,
      adStartNum: 0,
      marTotalNum: 9,
      adTotalNum: 3,
    };
  },
  mounted() {
    if (this.$session.get("coolUserNo") != null) {
      this.$axios
        .get("http://localhost:9000/addr1")
        .then((res) => {
          this.selected1 = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      this.$axios
        .get("http://localhost:9000/userAddr", {
          params: { userNo: this.$session.get("coolUserNo") },
        })
        .then((res) => {
          this.addr1 = res.data.addr1;
          this.Getaddr2();
          if (res.data.addr2 == "") {
            this.addr2 = "";
          } else {
            this.addr2 = res.data.addr2;
            this.Getaddr3();
          }
          this.onSelect();
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      let obj = this;
      obj.$axios
        .get("http://localhost:9000/getMarOption")
        .then(function (res) {
          console.log("Select Cate Get Succcess");
          obj.cate = res.data;
        })
        .catch(function (err) {
          console.log("Select Cate Get Fail");
          console.log(err);
        });
      this.$axios
        .get("http://localhost:9000/addr1")
        .then((res) => {
          this.selected1 = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      obj.$axios
        .get("http://localhost:9000/getMarketList", {
          params: {
            selected: this.selected,
            addr1: this.addr1,
            addr2: this.addr2,
            marStartNum: this.marStartNum,
            marTotalNum: this.marTotalNum,
            adStartNum: this.adStartNum,
            adTotalNum: this.adTotalNum,
          },
        })
        .then(function (res) {
          console.log("BoardList Get Succcess");
          obj.items = res.data;
        })
        .catch(function (err) {
          console.log("BoardList Get Fail");
          console.log(err);
        });
    }
  },
  methods: {
    sessioncheck() {
      this.session.get("userNo" != null);
    },
    Getaddr2() {
      this.addr2 = "";
      this.addr3 = "";
      this.$axios
        .get("http://localhost:9000/addr2", {
          params: {
            addr1: this.addr1,
          },
        })
        .then((res) => {
          if (res.data.length == 1) {
            this.selected2 = [
              { value: "", text: "시/군/구" },
              { value: "없음", text: "없음" },
            ];
          } else {
            this.selected2 = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    Getaddr3() {
      this.addr3 = "";
      this.$axios
        .get("http://localhost:9000/addr3", {
          params: {
            addr1: this.addr1,
            addr2: this.addr2,
          },
        })
        .then((res) => {
          if (res.data.length == 1) {
            this.selected3 = [
              { value: "", text: "동" },
              { value: "없음", text: "없음" },
            ];
          } else {
            this.selected3 = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onSelect() {
      console.log(
        this.$data.selected + "\n" + this.$data.addr1 + "\n" + this.$data.addr2
      );
      let obj = this;
      obj.$axios
        .get("http://localhost:9000/getSelectBoardList", {
          params: {
            selected: this.selected,
            addr1: this.addr1,
            addr2: this.addr2,
            marStartNum: (this.marStartNum = 0),
            marTotalNum: (this.marTotalNum = 9),
            adStartNum: (this.adStartNum = 0),
            adTotalNum: (this.adTotalNum = 3),
          },
        })
        .then(function (res) {
          console.log("Select BoardList Get Succcess");
          obj.items = res.data;
        })
        .catch(function (err) {
          console.log("Select BoardList Get Fail");
          console.log(err);
        });
    },
    IncrementList() {
      this.marStartNum += 9;
      this.adStartNum += 3;
      console.log(
        this.$data.selected + "\n" + this.$data.addr1 + "\n" + this.$data.addr2
      );
      let obj = this;
      obj.$axios
        .get("http://localhost:9000/getSelectBoardList", {
          params: {
            selected: this.selected,
            addr1: this.addr1,
            addr2: this.addr2,
            marStartNum: this.marStartNum,
            marTotalNum: this.marTotalNum,
            adStartNum: this.adStartNum,
            adTotalNum: this.adTotalNum,
          },
        })
        .then(function (res) {
          console.log("Select BoardList Get Succcess");
          for (let i = 0; i < res.data.length; i++) {
            obj.items.push(res.data[i]);
          }
        })
        .catch(function (err) {
          console.log("Select BoardList Get Fail");
          console.log(err);
        });
    },
    onFlush() {
      console.log(this.$data.addr1 + "\n" + this.$data.addr2);
      this.$data.addrList2 = [];
      this.$data.addr2 = "";
      this.onSelect();
    },
    DecrementList() {
      (this.marStartNum -= 9), (this.adStartNum -= 3), this.onSelect();
    },
    MoveAdDetail(adNo) {
      this.$router.push({ name: "AdDetail", query: { adNo: adNo } });
    },
    MoveMarketDetail(marNo) {
      this.$router.push({ name: "MarketDetail", query: { marNo: marNo } });
    },
    MoveMarketWrite() {
      this.$router.push({ name: "MarketWrite" });
    },
    MoveAdtWrite() {
      this.$router.push({ name: "AdWrite" });
    },
    imgPath(src) {
      if (src == "") {
        return "/images/noImg.png";
      } else {
        return src[0].storedImgPath;
      }
    },
  },
};
</script>

<style scoped>
@media (max-width: 550px) {
  .content-select {
    flex-direction: column !important;
    justify-content: center;
    align-items: center;
  }
}
@media (max-width: 990px) {
  .wrap {
    width: 100% !important;
    padding: 0 24px;
  }
  .content-title h1 {
    font-size: 21px !important;
    transition: 0.2s;
  }
  .content-select {
    display: flex;
    justify-content: center !important;
    transition: 0.2s;
    /* flex-direction: column; */
  }

  .select-box {
    font-size: 12px;
    width: 100px;
    padding: 0 !important;
    margin-bottom: 10px;
    height: 30px !important;
    transition: 0.2s;
  }
}

.wrap {
  max-width: 950px;
  width: 80%;
  margin: 0 auto;
}
.content-header {
  width: 100%;
}
.content-title {
  word-break: keep-all;
}
.content-select {
  display: flex;
  justify-content: flex-end;
  flex-wrap: wrap;
  /* width: 100%; */
  margin-top: 25px;
}
.select-box {
  font-size: 15px;
  margin-left: 10px;
  height: 40px;
  padding: 8px 16px;
  border-radius: 8px;
  border: 1px solid rgb(230, 230, 230);
  width: 145px;
}
.select-box:focus {
  outline: none;
}

.content-body {
  display: flex;
  flex-wrap: wrap;
  /* justify-content: space-between; */
  width: 100%;
  margin-bottom: 50px;
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

.ad-card-section {
  width: 200px;
  justify-content: center;
}

.card-box {
  width: 200px;
  cursor: pointer;
}
.card-img-box {
  width: 200px;
  height: 200px;
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
.card-title {
  font-weight: 12px;
  font-weight: bold;
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
  max-width: 950px;
  justify-content: space-around;
}

.btn-section {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.my-btn {
  padding: 8px 16px;
  margin-bottom: 20px;
  text-align: center;
  font-size: 15px;
  color: rgb(170, 170, 170);
  background-color: #ffff;
  align-self: center;
  border: 1px solid lightgray;
  cursor: pointer;
  font-weight: bold;
}
.my-btn:hover {
  background-color: #b3b3b3;
  color: #ffff;
  font-weight: bold;
}

.write-btn-section {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.write-btn {
  /* width: 90px; */
  padding: 8px 16px;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  color: rgb(170, 170, 170);
  background-color: #ffff;
  align-self: center;
  border: 1px solid lightgray;
  cursor: pointer;
}

.ad-btn {
  margin-left: 10px;
}

.page-message {
  text-align: center;
  font-size: 13px;
  color: rgb(243, 75, 75);
}
</style>
