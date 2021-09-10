<template>
  <div>
    <b-row>
      <b-col md="8" class="mx-auto border">
        <b-row class="mt-2">
          <b-col md="10" class="mx-auto my-5">
            <b-form-group label="이메일 : " label-for="userId">
              <b-form-input
                id="userId"
                v-model="userId"
                trim
                placeholder="이메일을 입력해 주세요."
                type="text"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="패스워드 : " label-for="userPw" class="mt-3">
              <b-form-input
                id="userPw"
                v-model="userPw"
                trim
                placeholder="패스워드를 압력해 주세요."
                v-on:keyup.enter="login"
                type="password"
              ></b-form-input>
            </b-form-group>
            <p class="mt-3 text-info cursor" v-on:click="pwFind">
              패스워드를 잊어 버리셧나요?
            </p>
            <b-button-group vertical class="d-flex">
              <b-button
                size="lg"
                pill
                block
                variant="primary"
                class="mt-3"
                v-on:click="login"
                >로그인</b-button
              >
              <b-button
                size="lg"
                pill
                block
                variant="success"
                class="mt-2"
                v-on:click="signUp"
                >회원가입</b-button
              >
            </b-button-group>

            <!-- 비밀번호 찾기 -->
            <div class="mt-5" v-if="findShow == true">
              <hr />
              <h3 class="text-center">비밀번호 찾기</h3>
              <b-form-group label="이메일 : " label-for="userId">
                <b-form-input
                  id="findId"
                  v-model="findId"
                  trim
                  placeholder="가입하신 이메일을 입력해 주세요."
                  type="text"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름 : " label-for="findName">
                <b-form-input
                  id="findName"
                  v-model="findName"
                  trim
                  placeholder="가입하신 이름을 입력해 주세요."
                  type="text"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="연락처 : " label-for="findTel">
                <b-form-input
                  id="findTel"
                  v-model="findTel"
                  trim
                  placeholder="가입하신 연락처를 입력해 주세요."
                  type="text"
                ></b-form-input>
              </b-form-group>
              <b-button-group vertical class="d-flex">
                <b-button
                  size="lg"
                  pill
                  block
                  variant="info"
                  class="mt-3"
                  v-on:click="find"
                  >비밀번호 찾기</b-button
                >
              </b-button-group>
            </div>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userId: "",
      userPw: "",
      findShow: false,
      findId: "",
      findName: "",
      findTel: "",
    };
  },
  methods: {
    login() {
      if (this.userId == "") {
        alert("아이디를 입력하세요");
      } else if (this.userPw == "") {
        alert("비밀번호를 입력하세요");
      } else {
        this.$axios
          .post("http://coolmarket.link/login", null, {
            params: {
              email: this.userId,
              password: this.userPw,
            },
          })
          .then((res) => {
            if (res.data == false) {
              alert("아이디, 비밀번호를 확인해 주세요");
            } else {
              sessionStorage.setItem("coolUserNo", res.data.userNo);
              sessionStorage.setItem("coolEmail", res.data.email);
              sessionStorage.setItem("coolRank", res.data.rank);
              sessionStorage.setItem("coolNickName", res.data.nickName);
              if (
                this.$route.query.toSignUp != 1 ||
                this.$route.query.toSignUp == undefined
              ) {
                this.$router.go(-1);
              } else {
                this.$router.push("/");
              }
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    signUp() {
      this.$router.push("SignUp");
    },
    pwFind() {
      this.findShow = true;
    },
    find() {
      this.$axios
        .post("http://coolmarket.link/find", {
          email: this.findId,
          name: this.findName,
          tel: this.findTel,
        })
        .then((res) => {
          if (res.data == "") {
            alert("입력하신 정보의 사용자가 없습니다. \n 다시 입력해 주세요.");
          } else {
            alert("비밀번호 : " + res.data + " 입니다.");
            this.findId = "";
            this.findName = "";
            this.findTel = "";
            this.findShow = false;
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
</style>