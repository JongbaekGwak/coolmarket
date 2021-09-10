<template>
  <div>
    <h2 class="mt-3 pb-3 border-bottom">광고 수정하기</h2>
    <div class="d-lg-flex justify-content-end d-block">
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

    <div class="mt-3">
      <b-col>
        <div class="input-group">
          <div class="input-group-text">제목</div>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            placeholder="제목을 입력하세요"
            v-model="ad.adTitle"
          />
        </div>
      </b-col>
    </div>

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
      <b-input-group v-for="item in ad.imgList" :key="item.imgNo">
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
        v-model="ad.adContents"
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
      <button type="button" class="btn btn-success mx-1" v-on:click="adUpdate">
        수정하기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      adNo: "",
      ad: [],
      images: "",
      address1: "",
      address2: "",
      address3: "",
      addr1: [],
      addr2: [{ value: "", text: "시/군/구" }],
      addr3: [{ value: "", text: "동" }],
    };
  },
  mounted() {
    this.adNo = this.$route.query.adNo;
    this.$axios
      .get("http://coolmarket.link/addr1")
      .then((res) => {
        this.addr1 = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://coolmarket.link/adDetail", {
        params: { adNo: this.adNo },
      })
      .then((res) => {
        this.ad = res.data;
        if (this.ad.adUserNo != sessionStorage.getItem("coolUserNo")) {
          alert("수정권한이 없습니다.");
          this.$router.push("/");
        } else {
          this.address1 = res.data.adAddr1;
          this.addre2();
          this.address2 = res.data.adAddr2;
          this.addre3();
          this.address3 = res.data.adAddr3;
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
        .get("http://coolmarket.link/addr2", {
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
        .get("http://coolmarket.link/addr3", {
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
    adUpdate() {
      if (this.title == "") {
        alert("제목을 입력해 주세요");
      } else if (this.address1 == null) {
        alert("주소를 선택해 주세요");
      } else {
        if (this.images != "") {
          console.log(this.images);
        }
        this.$axios
          .put("http://coolmarket.link/adUpdate", {
            adNo: this.adNo,
            adTitle: this.ad.adTitle,
            adContents: this.ad.adContents,
            adAddr1: this.address1,
            adAddr2: this.address2,
            adAddr3: this.address3,
          })
          .then(() => {
            if (this.images != "") {
              let fromData = new FormData();
              for (let i = 0; i < this.images.length; i++) {
                fromData.append("image", this.images[i]);
              }
              this.$axios
                .post("http://coolmarket.link/imgInsert", fromData, {
                  params: { adNo: this.ad.adNo, marNo: "", comNo: "" },
                  headers: {
                    "Content-Type": "multipart/form-data",
                  },
                })
                .then(() => {
                  alert("수정완료");
                  this.$router.push({
                    name: "AdDetail",
                    query: { adNo: this.adNo },
                  });
                })
                .catch((err) => {
                  console.log(err);
                });
            } else {
              alert("수정완료");
              this.$router.push({
                name: "AdDetail",
                query: { adNo: this.adNo },
              });
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    up(file) {
      this.images = file.target.files;
    },
    imgName(img) {
      return img.oriImgName;
    },
    imgDelete(num) {
      this.$axios
        .get("http://coolmarket.link/adImgDelete", {
          params: { adNo: this.adNo, imgNo: num },
        })
        .then((res) => {
          this.ad.imgList = res.data;
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