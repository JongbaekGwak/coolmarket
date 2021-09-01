<template>
  <div>
    <h2 class="mt-3 pb-3 border-bottom">마켓 물건 수정하기</h2>

    <div class="d-lg-flex justify-content-between d-block">
      <div class="mt-3">
        <span class="me-1">카테고리 :</span>
        <b-form-select
          v-model="marCate"
          :options="cate"
          class="btn btn-sm btn-warning"
        ></b-form-select>
      </div>
      <div class="mt-3">
        <span class="me-1">주소 : </span>
        <b-form-select
          v-on:change="addre2"
          v-model="address1"
          :options="addr1"
          class="btn btn-sm btn-primary"
        ></b-form-select>
        <b-form-select
          v-on:change="addre3"
          v-model="address2"
          :options="addr2"
          class="btn btn-sm btn-info"
        ></b-form-select>
        <b-form-select
          v-model="address3"
          :options="addr3"
          class="btn btn-sm btn-success"
        ></b-form-select>
      </div>
    </div>

    <b-row class="mt-3">
      <b-col>
        <div class="input-group">
          <div class="input-group-text">제목</div>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            placeholder="제목을 입력하세요"
            v-model="mar.marTitle"
          />
        </div>
      </b-col>

      <b-col>
        <div class="input-group">
          <div class="input-group-text">가격</div>
          <input
            type="text"
            class="form-control"
            id="price"
            name="price"
            placeholder="가격을 입력하세요"
            v-model="mar.marPrice"
          />
        </div>
      </b-col>
    </b-row>

    <div class="mt-3">
      <input
        type="file"
        class="form-control"
        multiple
        accept="image/*"
        @change="up"
      />
    </div>

    <div class="mt-2">
      <b-input-group v-for="item in mar.imgList" :key="item.imgNo">
        <b-input-group-text> 등록된 이미지 </b-input-group-text>
        <b-input readonly :value="imgName(item)"></b-input>
        <b-input-group-text
          class="btn btn-danger"
          v-on:click="imgDelete(item.imgNo)"
        >
          삭제
        </b-input-group-text>
      </b-input-group>
    </div>

    <div class="form-group mt-3">
      <textarea
        name="contents"
        id="contents"
        rows="10"
        class="form-control"
        v-model="mar.marContents"
      ></textarea>
    </div>
    <div class="d-flex justify-content-end mt-3">
      <button
        type="button"
        class="btn btn-primary mx-1"
        v-on:click="moveMarketList"
      >
        목록으로
      </button>
      <button
        type="button"
        class="btn btn-success mx-1"
        v-on:click="marketUpdate"
      >
        수정하기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      marNo: "",
      mar: [],
      images: "",
      address1: "",
      address2: "",
      address3: "",
      marCate: "",
      addr1: [],
      addr2: [{ value: "", text: "시/군/구" }],
      addr3: [{ value: "", text: "동" }],
      cate: [],
    };
  },
  beforeCreate() {
    if (this.$session.get("coolUserNo") == null) {
      alert("로그인 해주세요");
      this.$router.push("/Login");
    }
  },
  mounted() {
    this.marNo = this.$route.query.marNo;
    this.$axios
      .get("http://localhost:9000/addr1")
      .then((res) => {
        this.addr1 = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/marCate")
      .then((res) => {
        this.cate = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/marketDetail", {
        params: { marNo: this.marNo },
      })
      .then((res) => {
        this.mar = res.data.marketDetail;
        if (this.mar.marUserNo != this.$session.get("coolUserNo")) {
          alert("수정권한이 없습니다.");
          this.$router.push("/");
        } else {
          this.address1 = res.data.marketDetail.marAddr1;
          this.marCate = res.data.marketDetail.marCate;
          this.addre2();
          this.address2 = res.data.marketDetail.marAddr2;
          this.addre3();
          this.address3 = res.data.marketDetail.marAddr3;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    addre2() {
      this.address2 = "";
      this.address3 = "";
      this.$axios
        .get("http://localhost:9000/addr2", {
          params: {
            addr1: this.address1,
          },
        })
        .then((res) => {
          if (res.data.length == 1) {
            this.addr2 = [
              { value: "", text: "시/군/구" },
              { value: "없음", text: "없음" },
            ];
          } else {
            this.addr2 = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addre3() {
      this.address3 = "";
      this.$axios
        .get("http://localhost:9000/addr3", {
          params: {
            addr1: this.address1,
            addr2: this.address2,
          },
        })
        .then((res) => {
          if (res.data.length == 1) {
            this.addr3 = [
              { value: "", text: "동" },
              { value: "없음", text: "없음" },
            ];
          } else {
            this.addr3 = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveMarketList() {
      this.$router.push("/MarketList");
    },
    marketUpdate() {
      if (this.title == "") {
        alert("제목을 입력해 주세요");
      } else if (this.price == "") {
        alert("가격을 입력해 주세요");
      } else if (this.marCate == "") {
        alert("카테고리를 선택해 주세요");
      } else if (this.address1 == "") {
        alert("주소를 선택해 주세요");
      } else {
        let marprice = parseInt(this.mar.marPrice);
        if (isNaN(marprice)) {
          alert("가격은 숫자만 입력할 수 있습니다.");
        } else {
          this.$axios
            .put("http://localhost:9000/marketUpdate", {
              marNo: this.marNo,
              marTitle: this.mar.marTitle,
              marPrice: this.mar.marPrice,
              marContents: this.mar.marContents,
              marAddr1: this.address1,
              marAddr2: this.address2,
              marAddr3: this.address3,
              marCate: this.marCate,
            })
            .then(() => {
              if (this.images != "") {
                let fromData = new FormData();
                for (let i = 0; i < this.images.length; i++) {
                  fromData.append("image", this.images[i]);
                }
                this.$axios
                  .post("http://localhost:9000/imgInsert", fromData, {
                    params: { adNo: "", marNo: this.marNo, comNo: "" },
                    headers: {
                      "Content-Type": "multipart/form-data",
                    },
                  })
                  .then(() => {
                    alert("수정완료");
                    this.$router.push({
                      name: "MarketDetail",
                      query: { marNo: this.marNo },
                    });
                  })
                  .catch((err) => {
                    console.log(err);
                  });
              } else {
                alert("수정완료");
                this.$router.push({
                  name: "MarketDetail",
                  query: { marNo: this.marNo },
                });
              }
            })
            .catch((err) => {
              console.log(err);
            });
        }
      }
    },
    up(file) {
      this.images = file.target.files;
    },
    imgName(img) {
      if (img == null) {
        return "이미지가 없습니다.";
      } else {
        return img.oriImgName;
      }
    },
    imgDelete(num) {
      this.$axios
        .get("http://localhost:9000/marImgDelete", {
          params: { marNo: this.marNo, imgNo: num },
        })
        .then((res) => {
          this.mar.imgList = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
</style>