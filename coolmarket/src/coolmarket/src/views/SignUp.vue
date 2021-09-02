<template>
  <div>
    <b-row>
      <b-col md="8" class="mx-auto border py-5">
        <h1 class="text-center mt-3">회원가입하기</h1>
        <b-row class="mt-2">
          <b-col md="10" class="mx-auto my-5">
            <!-- 아이디 -->
            <b-form-group label="이메일 : " label-for="userId">
              <b-input-group>
                <b-form-input
                  id="userId"
                  v-model="userId"
                  trim
                  placeholder="이메일을 입력해 주세요."
                  type="email"
                ></b-form-input>
                <b-input-group-text v-on:click="overlapCheck" type="button"
                  >중복확인</b-input-group-text
                >
              </b-input-group>
            </b-form-group>

            <!-- 비밀번호 -->
            <b-row>
              <b-col>
                <b-form-group
                  label="패스워드 : "
                  label-for="userPw"
                  class="mt-3"
                >
                  <b-form-input
                    id="userPw"
                    v-model="userPw"
                    trim
                    placeholder="패스워드를 입력해 주세요."
                    type="password"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group
                  label="패스워드 확인 : "
                  label-for="userPw2"
                  class="mt-3"
                >
                  <b-form-input
                    id="userPw2"
                    v-model="userPw2"
                    trim
                    placeholder="패스워드를 입력해 주세요."
                    type="password"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <small class="m-0 text-info"
                >비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야
                합니다.</small
              >
            </b-row>

            <!-- 이름, 닉네임 -->
            <b-row>
              <b-col>
                <b-form-group label="이름 : " label-for="userName" class="mt-3">
                  <b-form-input
                    id="userName"
                    v-model="userName"
                    trim
                    placeholder="이름을 입력해 주세요."
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group
                  label="별명 : "
                  label-for="userNickName"
                  class="mt-3"
                >
                  <b-input-group>
                    <b-form-input
                      id="userNickName"
                      v-model="userNickName"
                      trim
                      placeholder="별명을 입력해 주세요."
                      type="text"
                    ></b-form-input>
                    <b-input-group-text v-on:click="overlapCheck2" type="button"
                      >중복확인</b-input-group-text
                    >
                  </b-input-group>
                </b-form-group>
              </b-col>
            </b-row>

            <!-- 역락처 -->
            <b-form-group label="연락처 : " label-for="userTel" class="mt-3">
              <b-input-group>
                <b-form-input
                  id="userTel"
                  v-model="userTel"
                  trim
                  placeholder="연락처를 입력해 주세요. ( - 는 빼고 입력해주세요.)"
                  type="text"
                ></b-form-input>
              </b-input-group>
            </b-form-group>

            <!-- 주소 -->
            <div class="mt-3 d-flex jistify-content-between">
              <span class="me-auto">주소 : </span>
              <b-form-select
                v-on:change="addre2"
                v-model="address1"
                :options="addr1"
                class="btn btn-sm btn-light me-auto"
              ></b-form-select>
              <b-form-select
                v-on:change="addre3"
                v-model="address2"
                :options="addr2"
                class="btn btn-sm btn-light me-auto"
              ></b-form-select>
              <b-form-select
                v-model="address3"
                :options="addr3"
                class="btn btn-sm btn-light"
              ></b-form-select>
            </div>

            <!-- 버튼 -->
            <b-button-group vertical class="d-flex mt-2">
              <b-button
                size="lg"
                pill
                block
                variant="primary"
                class="mt-2"
                v-on:click="signUp"
                >가입하기</b-button
              >
            </b-button-group>
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
      userPw2: "",
      userName: "",
      userNickName: "",
      userTel: "",
      address1: "",
      address2: "",
      address3: "",
      addr1: [],
      addr2: [{ value: "", text: "시/군/구" }],
      addr3: [{ value: "", text: "동" }],
      check: false,
      check2: false,
    };
  },
  beforeCreate() {
    if (this.$session.get("coolUserNo") != null) {
      if (this.$session.get("coolRank") == 0) {
        this.$router.push("ManagerPage");
      } else {
        this.$router.push("/MyPage");
      }
    }
  },
  mounted() {
    this.$axios
      .get("http://localhost:9000/addr1")
      .then((res) => {
        this.addr1 = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    validEmail(email) {
      let re =
        /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
      return re.test(email);
    },
    validPassword(email, password) {
      if (!/^[a-zA-Z0-9]{8,20}$/.test(password)) {
        alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
        return false;
      }
      let chk_num = password.search(/[0-9]/g);
      let chk_eng = password.search(/[a-z]/gi);
      if (chk_num < 0 || chk_eng < 0) {
        alert("비밀번호는 숫자와 영무자를 혼용하여야 합니다.");
        return false;
      }
      if (/(\w)\1\1\1/.test(password)) {
        alert("비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.");
        return false;
      }
      if (password.search(email) > -1) {
        alert("ID가 포함된 비밀번호는 사용하실 수 없습니다.");
        return false;
      }
      return true;
    },
    validTel(tel) {
      let re = /^((01[1|6|7|8|9])[0-9]+[0-9]{6,7})|(010[0-9][0-9]{7})$/;
      return re.test(tel);
    },
    overlapCheck() {
      if (!this.validEmail(this.userId)) {
        alert("이메일형식을 확인해 주세요");
        this.check = false;
      } else {
        this.$axios
          .get("http://localhost:9000/overlabCheck", {
            params: {
              email: this.userId,
            },
          })
          .then((res) => {
            if (res.data == true) {
              alert("사용할 수 있는 아이디 입니다.");
              this.check = true;
            } else {
              alert("사용할 수 없는 아이디 입니다.");
              this.check = false;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    overlapCheck2() {
      this.$axios
        .get("http://localhost:9000/overlabCheck2", {
          params: { nickName: this.userNickName },
        })
        .then((res) => {
          if (res.data == true) {
            alert("사용할 수 있는 별명 입니다.");
            this.check2 = true;
          } else {
            alert("사용할 수 없는 별명 입니다.");
            this.check2 = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    sign() {
      if (!this.check) {
        alert("이메일 중복확인 해주세요");
        return false;
      } else {
        if (this.userPw == "") {
          alert("비밀번호를 입력해 주세요");
          return false;
        } else {
          if (this.userPw2 == "") {
            alert("비밀번호 확인을 입력해 주세요");
            return false;
          } else {
            if (this.userPw != this.userPw2) {
              alert("비밀번호 확인을 잘못 입력하였습니다.");
              return false;
            } else {
              if (!this.validPassword(this.userId, this.userPw)) {
                return false;
              } else {
                if (this.userName == "") {
                  alert("이름을 입력해 주세요");
                  return false;
                } else {
                  if (this.userNickName == "") {
                    alert("별명을 입력해 주세요");
                  } else {
                    if (!this.check2) {
                      alert("별명 중복확인 해주세요");
                      return false;
                    } else {
                      if (this.userTel == "") {
                        alert("연락처를 입력해 주세요");
                        return false;
                      } else {
                        if (!this.validTel(this.userTel)) {
                          alert("전화번호 형식을 확인해 주세요");
                          return false;
                        } else {
                          if (this.address1 == "") {
                            alert("주소를 선택해 주세요");
                            return false;
                          } else {
                            return true;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    },
    signUp() {
      if (this.sign()) {
        if (this.address2 == "없음") {
          this.address2 == "";
        }
        if (this.address3 == "없음") {
          this.address3 == "";
        }
        this.$axios
          .post("http://localhost:9000/signUp", {
            email: this.userId,
            password: this.userPw,
            name: this.userName,
            nickName: this.userNickName,
            tel: this.userTel,
            addr1: this.address1,
            addr2: this.address2,
            addr3: this.address3,
          })
          .then(() => {
            alert("회원가입 완료");
            this.$router.push({
              name: "Login",
              query: { toSignUp: "1" },
            });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
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
  },
};
</script>
<style>
</style>