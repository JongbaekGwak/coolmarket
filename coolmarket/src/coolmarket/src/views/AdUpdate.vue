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
            v-model="title"
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
    <div class="form-group mt-3">
      <textarea
        name="contents"
        id="contents"
        rows="10"
        class="form-control"
        v-model="contents"
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
      adNo:"",
      title: "",
      images: "",
      files: [],
      contents: "",
      address1: "",
      address2: "",
      address3: "",
      addr1: [],
      addr2: [{ value: "", text: "시/군/구" }],
      addr3: [{ value: "", text: "동" }],
    };
  },
  beforeCreate() {
    if (this.$session.get("coolUserNo") == null) {
      alert("로그인 해주세요");
      this.$router.push("/Login");
    }
  },
  mounted() {
    this.adNo = this.$route.query.adNo;
    this.$axios
      .get("http://localhost:9000/addr1")
      .then((res) => {
        this.addr1 = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/adDetail", { 
        params: { adNo: this.adNo } 
        })
      .then((res) => {
        this.title = res.data.adTitle;
        this.contents = res.data.adContents;
        this.address1 = res.data.adAddr1;
        this.addre2();
        this.address2 = res.data.adAddr2;
        this.addre3();
        this.address3 = res.data.adAddr3;
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
          .get("http://localhost:9000/adUpdate", {
            params: {
              adNo : this.adNo,
              adTitle: this.title,
              adContents: this.contents,
              adAddr1: this.address1,
              adAddr2: this.address2,
              adAddr3: this.address3
            },
          })
          .then((res) => {
            if (this.images != "") {
              let fromData = new FormData();
              for (let i = 0; i < this.images.length; i++) {
                fromData.append("image", this.images[i]);
              }
              this.$axios
                .post("http://localhost:9000/imgInsert", fromData, {
                  params: { adNo: res.data, marNo: "", comNo: "" },
                  headers: {
                    "Content-Type": "multipart/form-data",
                  },
                })
                .then(() => {
                  alert("작성완료");
                  this.$router.push({
                    name: "AdDetail",
                    query: { adNo: res.data },
                  });
                })
                .catch((err) => {
                  console.log(err);
                });
            } else {
              alert("작성완료");
              this.$router.push({
                name: "AdDetail",
                query: { adNo: res.data },
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
  },
};
</script>

<style scoped>
</style>