<template>
  <div>
    <!-- 광고 크라쉘 -->
    <div>
      <b-carousel
        id="carousel"
        style="text-shadow: 0px 0px 2px #000"
        indicators
        controls
      >
        <b-carousel-slide
          v-for="item in newAdList"
          :key="item.adNo"
          :text="item.adTitle"
          :img-src="imgPath(item.imgList)"
          style="width: 100%; height: 500px"
          v-on:click="moveAdDetail(item.adNo)"
        >
        </b-carousel-slide>
      </b-carousel>
    </div>

    <hr />

    <!-- 마켓 최신글 -->
    <div class="mt-5">
      <h3 class="my-2 cursor" v-on:click="MarketList">마켓 최신글</h3>
      <div v-if="newMarketList != ''">
        <b-card-group>
          <!-- [0] -->
          <b-card
            :img-src="imgPath(newMarketList[0].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[0].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[0].marTitle }}</p>
              <p>{{ won(newMarketList[0].marPrice) }}원</p>
              <p>
                {{ newMarketList[0].marAddr1 }}{{ newMarketList[0].marAddr2
                }}{{ newMarketList[0].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[0].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[0].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>

          <!-- [1] -->
          <b-card
            :img-src="imgPath(newMarketList[1].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[1].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[1].marTitle }}</p>
              <p>{{ won(newMarketList[1].marPrice) }}원</p>
              <p>
                {{ newMarketList[1].marAddr1 }}{{ newMarketList[1].marAddr2
                }}{{ newMarketList[1].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[1].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[1].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>

          <!-- [2] -->
          <b-card
            :img-src="imgPath(newMarketList[2].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[2].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[2].marTitle }}</p>
              <p>{{ won(newMarketList[2].marPrice) }}원</p>
              <p>
                {{ newMarketList[2].marAddr1 }}{{ newMarketList[2].marAddr2
                }}{{ newMarketList[2].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[2].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[2].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>
        </b-card-group>

        <b-card-group>
          <!-- [3] -->
          <b-card
            :img-src="imgPath(newMarketList[3].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[3].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[3].marTitle }}</p>
              <p>{{ won(newMarketList[3].marPrice) }}원</p>
              <p>
                {{ newMarketList[3].marAddr1 }}{{ newMarketList[3].marAddr2
                }}{{ newMarketList[3].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[3].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[3].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>

          <!-- [4] -->
          <b-card
            :img-src="imgPath(newMarketList[4].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[4].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[4].marTitle }}</p>
              <p>{{ won(newMarketList[4].marPrice) }}원</p>
              <p>
                {{ newMarketList[4].marAddr1 }}{{ newMarketList[4].marAddr2
                }}{{ newMarketList[4].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[4].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[4].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>

          <!-- [5] -->
          <b-card
            :img-src="imgPath(newMarketList[5].imgList)"
            img-alt="Image"
            img-top
            class="me-2 border-0 cursor"
            img-height="250"
            v-on:click="MarketDetail(newMarketList[5].marNo)"
          >
            <b-card-text>
              <p>{{ newMarketList[5].marTitle }}</p>
              <p>{{ won(newMarketList[5].marPrice) }}원</p>
              <p>
                {{ newMarketList[5].marAddr1 }}{{ newMarketList[5].marAddr2
                }}{{ newMarketList[5].marAddr3 }}
              </p>
              <p>
                <span>
                  <fai :icon="['fas', 'heart']"></fai>
                </span>
                {{ newMarketList[5].marLike }}
                <span>
                  <fai :icon="['fas', 'comment']"></fai>
                </span>
                {{ newMarketList[5].marTalkCnt }}
              </p>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
    </div>

    <hr />

    <!-- 커뮤니티 최신글 -->
    <div class="mt-5">
      <h3 class="my-2 cursor" v-on:click="CommuList">커뮤니티 최신글</h3>
      <div v-if="newCommuList != ''">
        <div class="board-card" v-for="item in newCommuList" :key="item.comNo">
          <!-- 이미지 yes -->
          <b-card
            :img-src="imgPath(item.imgList)"
            img-alt="Card image"
            img-right
            img-height="150px"
            class="m-2 bg-light cursor"
            v-on:click="CommuDetail(item.comNo)"
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      newAdList: [],
      newMarketList: [],
      newCommuList: [],
    };
  },
  mounted() {
    this.$axios
      .get("http://localhost:9000/newAdList")
      .then((res) => {
        this.newAdList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/newMarketList")
      .then((res) => {
        this.newMarketList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    this.$axios
      .get("http://localhost:9000/newCommuList")
      .then((res) => {
        this.newCommuList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    MarketList() {
      this.$router.push("/MarketList");
    },
    CommuList() {
      this.$router.push("/CommuList");
    },
    CommuDetail(num) {
      this.$router.push({ name: "CommuDetail", query: { comNo: num } });
    },
    MarketDetail(num) {
      this.$router.push({ name: "MarketDetail", query: { marNo: num } });
    },
    moveAdDetail(num) {
      this.$router.push({ name: "AdDetail", query: { adNo: num } });
    },
    won(num) {
      if (num != null) {
        return num.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
      }
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
.cursor {
  cursor: pointer;
}
</style>