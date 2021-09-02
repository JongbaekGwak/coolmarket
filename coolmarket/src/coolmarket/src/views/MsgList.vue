<template>
    <div class="wrap">
        <div class="header-title">
            <p class="m-0">쪽지함</p>
            <div class="close-btn" v-on:click="MoveMyPage">
                <i class="fa fa-times fs-4" aria-hidden="true"></i>
            </div>
        </div>
        <div class="message-card" v-for="item in roomList" v-bind:key="item.roomNo">
            <div class="left-section" v-on:click="MoveMsgDetail(item.roomNo, item.marNo, item.buyerNo, item.nickName)">
                <div class="sender-info">
                    <div class="sender-img">
                        <i class="fa fa-user" aria-hidden="true"></i>
                    </div>
                    <div class="sender-text">
                        <div class="sender-name">
                            <p class="m-0">{{item.nickName}}</p>
                        </div>
                        <div class="sender-region">
                            <p class="m-0">{{item.addr1}} {{item.addr2}}</p>
                        </div>
                    </div>
                </div>
                <div class="receive-text">
                    <p class="m-0">메세지를 확인하세요 </p>
                </div>
            </div>
            <div class="receive-date">
                <p class="m-0">16:25</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            user: [],
            roomList: [],
            myUserNo: ""
        }
    },
    beforeCreate() {
        this.$axios
        .get("http://localhost:9000/me", {
        params: {
            userNo: this.$session.get("coolUserNo"),
        },
        })
        .then((res) => {
            this.user = res.data;
            this.user.userNo = this.$session.get("coolUserNo")
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
        this.myUserNo = this.$session.get("coolUserNo");
        let obj = this
        obj.axios.get("http://localhost:9000/getRoomList", {
            params: {
                buyerNo: this.$session.get("coolUserNo"),
            }
        })
        .then(function (res) {
            console.log("RoomList Get Success");
            obj.roomList = res.data
        })
        .catch(function (err) {
            console.log("RoomList Get Fail");
            console.log(err);
        })
    },
    methods: {
        MoveMyPage() {
            this.$router.push({ name: 'MyPage' });
        },
        MoveMsgDetail(roomNo, marNo, buyerNo, nickName) {
            this.$router.push({ name: 'MsgDetail', 
                query: {
                    roomNo: roomNo,
                    marNo: marNo,
                    buyerName: this.user.nickName,
                    buyerNo: this.myUserNo,
                    sellerNo: buyerNo,
                    sellerName: nickName
                } 
            });
        },
    }
}
</script>

<style scoped>
    @media (max-width: 990px) {
        .wrap {
            width: 100% !important; 
        }
        .message-card {
            height: 110px !important;
            padding: 0 !important;
        }
        .sender-info {
            flex-direction: column;
            min-width: 115px;
        }
        .sender-text {
            height: auto !important;
            justify-content: center !important;
            text-align: center;
        }
        .receive-text {
            margin-left: 0 !important;
        }
        .receive-date {
            padding-right: 30px;
        }
    }
    .wrap {
        width: 70%;
        margin: 0 auto;
        background-color: #fbfbfb;
        border: 1px solid rgb(194, 194, 194);
        box-shadow: 1px 1px 10px 1px lightgrey;
    }
    .header-title {
        display: flex;
        width: 100%;
        height: 60px;
        padding: 8px 32px;
        align-items: center;
        border-bottom: 1px solid rgb(243, 243, 243);
        justify-content: space-between;
    }
    .header-title p {
        font-size: 18px;
        font-weight: bold;
    }
    .close-btn {
        cursor: pointer;
    }

    .message-card {
        display: flex;
        width: 100%;
        height: 100px;
        padding: 0 32px;
        border-bottom: 1px solid rgb(243, 243, 243);
        align-items: center;
        justify-content: space-between;
        cursor: pointer;
    }

    .message-card:hover {
        background-color: #ffff;
    }
    .sender-info {
        display: flex;
        align-items: center;
    }
    .sender-img {
        width: 65px;
        height: 65px;
        border-radius: 180px;
        border: 1px solid rgb(243, 243, 243);
        background-color: #ffff;
        text-align: center;
        font-size: 32px;
    }
    .sender-text {
        height: 50px;
        display: flex;
        flex-direction: column;
        margin: 0 15px;
        justify-content: space-between;
    }
    .sender-name {
        font-size: 14px;
        font-weight: bold;
    }
    .sender-region {
        font-size: calc(.4rem + .3vw);
    }
    .receive-text {
        margin-left: 15px;
        min-width: 150px;
    }
    
  

    .left-section {
        display: flex;
        align-items: center;
    }

    .receive-date {
        font-size: 13px;
    }
</style>