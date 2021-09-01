<template>
  <div class="wrap">
    <!-- CommuList Header start -->
    <div class="content-header mb-5">
      <!-- CommuList Title start -->
      <div class="content-title">
        <h1 class="text-center fs-2 fw-bold mt-5">
          <span>{{ addr1 }}</span> <span>{{ addr2 }}</span>
          <span>{{ addr3 }}</span> 동네&nbsp;이야기
        </h1>
      </div>
      <!-- CommuList Title end -->

      <!-- CommuList Select Form start -->
      <div class="content-select">
        <select
          class="select-box select1"
          v-model="selected"
          v-on:change="onSelect()"
        >
          <option disabled value="">카테고리</option>
          <option v-for="item in cate" v-bind:key="item.cateNo">
            {{ item.commuCateName }}
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
      <!-- CommuList Select Form end -->
    </div>
    <!-- CommuList Header end -->

    <div class="board-wrap">
      <div
        class="board-card"
        v-on:click="MoveCommuDetail(item.comNo)"
        v-for="item in items"
        v-bind:key="item.comNo"
      >
        <div class="board-info">
          <span class="tag">{{ item.comCate }}</span>
          <div class="board-title mt-2">
            <h2 class="m-0">{{ item.comTitle }}</h2>
          </div>
          <div class="writer-info mt-2">
            <div class="writer-name me-3">
              <p class="m-0">{{ item.comCreaNickName }}</p>
            </div>
            <div class="writer-addr">
              <p class="m-0">{{ item.comAddr1 }} {{ item.comAddr2 }}</p>
            </div>
            <div class="like-comment">
              <div class="like-icon">
                <i class="fa fa-heart-o hidden-icon" aria-hidden="true"
                  ><span class="count"> {{ item.comLike }}</span></i
                >
              </div>
              <div class="comment-icon">
                <i class="fa fa-comment-o hidden-icon" aria-hidden="true"
                  ><span class="count"> {{ item.comTalkCnt }}</span></i
                >
              </div>
            </div>
          </div>
        </div>

        <div class="board-img-section">
          <div class="board-img">
            <img :src="imgPath(item.imgList)" alt="게시판이지미" />
          </div>
        </div>
      </div>
    </div>

    <div class="btn-section">
      <div class="page-message" v-if="this.items.length < this.comTotalNum">
        <p>마지막 페이지입니다.</p>
      </div>
      <div class="my-btn paging-btn" v-on:click="IncrementList">
        <span>더보기</span>
      </div>
      <div
        class="my-btn paging-btn"
        v-on:click="DecrementList"
        v-if="this.items.length > 10"
      >
        <span>접기</span>
      </div>
      <div class="write-btn-section" v-if="this.user.userNo != null">
        <div class="my-btn write-btn" v-on:click="MoveCommuWrite">
          <span>글쓰기</span>
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
      cate: [],
      selected1: [],
      selected2: [{ value: "", text: "시/군/구" }],
      selected3: [{ value: "", text: "동" }],
      items: [],

      comStartNum: 0,
      comTotalNum: 10,
    };
  },
  beforeCreate() {
    this.$axios
      .get("http://localhost:8000/me", {
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
  },
  mounted() {
    let obj = this;
    obj.$axios
      .get("http://localhost:8000/getCommuOption")
      .then(function (res) {
        console.log("Select Cate Get Succcess");
        obj.cate = res.data;
      })
      .catch(function (err) {
        console.log("Select Cate Get Fail");
        console.log(err);
      });
    this.$axios
      .get("http://localhost:8000/addr1")
      .then((res) => {
        this.selected1 = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    obj.$axios
      .get("http://localhost:8000/getCommuList", {
        params: {
          selected: this.selected,
          addr1: this.addr1,
          addr2: this.addr2,
          comStartNum: this.comStartNum,
          comTotalNum: this.comTotalNum,
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
  methods: {
    won() {
      if (this.items.marPrice != null) {
        return this.items.marPrice.replace(
          /\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
          ","
        );
      }
    },
    Getaddr2() {
      this.addr2 = "";
      this.addr3 = "";
      this.$axios
        .get("http://localhost:8000/addr2", {
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
        .get("http://localhost:8000/addr3", {
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
        .get("http://localhost:8000/getCommuList", {
          params: {
            selected: this.selected,
            addr1: this.addr1,
            addr2: this.addr2,
            comStartNum: (this.comStartNum = 0),
            comTotalNum: (this.comTotalNum = 10),
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
      (this.comStartNum += 10),
        console.log(
          this.$data.selected +
            "\n" +
            this.$data.addr1 +
            "\n" +
            this.$data.addr2
        );
      let obj = this;
      obj.$axios
        .get("http://localhost:8000/getCommuList", {
          params: {
            selected: this.selected,
            addr1: this.addr1,
            addr2: this.addr2,
            comStartNum: this.comStartNum,
            comTotalNum: this.comTotalNum,
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
      this.$data.selected2 = [];
      this.$data.addr2 = "";
      this.onSelect();
    },
    DecrementList() {
      (this.comStartNum = 0), (this.comTotalNum -= 10), this.onSelect();
    },
    MoveCommuDetail(comNo) {
      this.$router.push({ name: "CommuDetail", query: { comNo: comNo } });
    },
    MoveCommuWrite() {
      this.$router.push({ name: "CommuWrite" });
    },
    imgPath(src) {
      if (src == null) {
        return "/images/noImg.png";
      } else {
        return src[0].storedImgPath;
      }
    },
  },
};
</script>

<style scoped>
@media (max-width: 380px) {
  .writer-info {
    flex-direction: column;
    align-items: unset !important;
  }
  .like-comment {
    margin-left: 0 !important;
  }
}
@media (max-width: 550px) {
  .content-select {
    flex-direction: column !important;
    justify-content: center;
    align-items: center;
  }
  .board-wrap {
    padding: 5px 15px;
  }
}
@media (max-width: 990px) {
  .wrap {
    width: 100% !important;
    /* padding: 0 24px; */
  }
  .content-title h1 {
    font-size: 21px !important;
    transition: 0.2s;
  }
  .content-select {
    /* display: flex; */
    justify-content: center !important;
    transition: 0.2s;
  }
  .select-box {
    font-size: 12px;
    margin-bottom: 10px;
    width: 100px;
    padding: 0 !important;
    height: 30px !important;
    transition: 0.2s;
  }
  .board-img-section {
    display: none !important;
    transition: 0.2s;
  }
}
.wrap {
  width: 80%;
  margin: 0 auto;
  padding: 0 15px;
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

.content-wrap {
  width: 100%;
}
.board-card {
  width: 100%;
  /* height: 120px; */
  margin-bottom: 20px;
  padding: 8px;
  border-radius: 15px;
  display: flex;
  justify-content: space-between;
  cursor: pointer;
  border-bottom: 1px solid rgb(243, 243, 243);
}
.board-card:hover {
  box-shadow: 1px 2px 12px 1px rgb(243, 243, 243);
  transform: scale(0.99);
}
.board-card:active {
  transform: scale(0.99);
}
.board-info {
  display: flex;
  flex-direction: column;
  width: auto;
}
.tag {
  width: fit-content;
  font-size: 11px;
  font-weight: bold;
  padding: 3px;
  border-radius: 15px;
  background-color: rgb(224, 224, 224);
}
.writer-info {
  display: flex;
  align-items: center;
}
.like-comment {
  display: flex;
  margin-left: 15px;
  /* width: 60px; */
  justify-content: space-between;
}
.comment-icon {
  margin-left: 8px;
}

.count {
  font-size: 12px;
}
.board-title h2 {
  font-size: 16px;
  font-weight: bold;
}

.writer-name {
  font-weight: bold;
}
.writer-name p {
  font-size: 12px;
}

.writer-addr p {
  margin: 0;
  font-size: 10px;
}
.board-img-section {
  display: flex;
  width: 120px;
  height: 100%;
  align-items: center;
  justify-content: center;
}
.board-img {
  width: 100px;
  height: 100px;
  overflow: hidden;
}
.board-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
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

.page-message {
  text-align: center;
  font-size: 13px;
  color: rgb(243, 75, 75);
}
</style>
