<template>
    <div class="wrap">
        <div class="item-info-card" >
            <div class="item-left-section" v-on:click="MoveMarketDetail">
                <div class="contact-item">
                    <img :src="imgPath(contactItem.imgList)" alt="마켓이미지" />
                </div>
                <div class="item-info">
                    <h2>{{contactItem.marTitle}}</h2>
                    <p>{{contactItem.marPrice}} 원</p>
                </div>
            </div>
            <div class="close-btn" v-on:click="MoveMsgList">
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
  <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
</svg>
            </div>
        </div>

        <div class="msg-container">
            <div class="msg-content"  ref="recvList">
            <div  v-for="(item, idx) in recvList" v-bind:key="idx">

                <div class="receive-box" v-if="item.buyerNo != userNo">
                    <div class="recieve-sender-img-box">
                        <div class="sender-img recieve-sender-img">
                            <fai :icon="['far', 'user']" class="fs-3 m-auto"></fai>
                        </div>
                    </div>
                    <div class="receive-data">
                        <div class="receive-name">
                            <p class="m-0">{{item.buyerName}}</p>
                        </div>
                        <div class="receive-msg">
                            <p class="m-0 msg">{{item.message}}</p>
                        </div>
                    </div>
                    <div class="receive-time">
                        <p class="m-0">{{item.sendDt}}</p><span><i class="fa fa-check mx-1" aria-hidden="true"></i></span>
                    </div>
                </div>
                
                <div class="send-box"  v-if="item.buyerNo == userNo">
                    <div class="receive-time">
                        <span><i class="fa fa-check mx-1" aria-hidden="true"></i></span>
                        <p class="m-0">{{item.sendDt}}</p>
                    </div>
                    <div class="send-data">
                        <div class="my-name">
                            <p class="m-0">나</p>
                        </div>
                        <div class="send-msg">
                            <p class="m-0 my-msg">{{item.message}}</p>
                        </div>
                    </div>
                </div>
            </div>
            </div>

            <div class="menu-container">
                <div class="send-input-left">
                    <div class="input-file-btn">
                        <input type="file" name="" id="file"  accept=".jpg,.jpeg,.png,.gif">
                        <label for="file"><i class="fa fa-folder-o" aria-hidden="true"></i></label>
                    </div>
                </div>
                <div class="input-report-btn">
                    <i class="fa fa-bullhorn" aria-hidden="true" v-on:click="MoveReport"></i>
                </div>
            </div>

            <div class="send-input-container">
                <div class="text-input">
                    <input type="text" name="send-msg" id="send-msg" autocomplete='off' v-on:keyup="sendMessage" v-model="message">
                </div>
                <div class="send-btn" type="button" v-on:click="send">
                    <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    data() {
        return {
            user: [],
            message: "",
            recvList: [],
            // marketDetail: [],
            // chatRoom: [],
            roomNo: "",
            marNo: "",
            buyerNo: "",
            sellerNo: "",
            sellerName: "",
            userNo: "",
            contactItem: "",
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
    created() {
        // this.marketDetail= this.$route.query.marketDetail;
        // this.chatRoom= this.$route.query.chatRoom;
        this.roomNo = this.$route.query.roomNo,
        this.marNo = this.$route.query.marNo,
        this.buyerNo = this.$route.query.buyerNo,
        this.sellerNo = this.$route.query.sellerNo,
        this.sellerName = this.$route.query.sellerName
        this.connect()
        
    },
    mounted() {
        this.userNo = this.$session.get("coolUserNo")
        let obj = this
        obj.axios.get("http://localhost:9000/getChatList", {
            params: {
                roomNo: obj.roomNo
            }
        })
        .then (function (res){
            console.log("Message Get Success");
            obj.recvList = res.data;
        })
        .catch (function (err) {
            console.log("Message Get Fail");
            console.log(err);
        });
        obj.axios.get("http://localhost:9000/getContactItem", {
            params: {
                marNo: obj.marNo
            }
        })
        .then (function (res) {
            console.log("ContactItem Get Success");
            obj.contactItem = res.data;
        })
        .catch (function (err) {
            console.log("ContactItem Get Success");
            console.log(err)
        })
    },
    watch: {
        recvList() {
            this.$nextTick(() => {
                let recvList = this.$refs.recvList;
                recvList.scrollTo({ top: recvList.scrollHeight, behavior: 'smooth' });
            });
        },
    },
    methods: {
        sendMessage (e) {
            if(e.keyCode === 13 && this.message !== ''){
                this.send()
                this.message = ''
            }
        },
        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const message = { 
                    'roomNo': this.roomNo,
                    'marNo': this.marNo,
                    'buyerNo': this.buyerNo,
                    'buyerName': this.user.nickName,
                    'sellerNo': this.sellerNo,
                    'sellerName': this.sellerName,
                    'message': this.message
                };
                this.stompClient.send("/app/chat/send", JSON.stringify(message), {});
            }
        },    
        connect() {
            const serverURL = "http://localhost:9000/chat"
            let socket = new SockJS(serverURL);

            this.stompClient = Stomp.over(socket);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)

            let obj = this;
            this.stompClient.connect(
                {},
                frame => {
                    // 소켓 연결 성공
                    obj.connected = true;
                    console.log('소켓 연결 성공', frame);
                    obj.stompClient.subscribe("/topic/" + obj.roomNo, res => {
                    // 연결 성공했으면 /send를 subscribe(구독)해 메세지 요청
                    console.log('구독으로 받은 메시지 입니다.', res.body);  
                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줌
                    obj.recvList.push(JSON.parse(res.body))
                    });
                },
                error => {
                    // 소켓 연결 실패
                    console.log('소켓 연결 실패', error);
                    obj.connected = false;
                }
            );
        },
        imgPath(src) {
            if (src == "") {
                return "/images/noImg.png";
            } else {
                return src[0].storedImgPath;
            }
        },

        MoveMsgList() {
            this.$router.push({ name: 'MsgList' });
        },
        MoveMarketDetail() {
            this.$router.push({ name: 'MarketDetail' });
        },
        MoveReport() {
            this.$router.push({ name: 'Report' });
        },
    }
}

</script>

<style scoped>
    @media (max-width: 550px) {
        .sender-img {
            width: 45px !important;
            height: 45px !important;
            transition: .2s;
        }
        .send-box {
            min-width: 270px !important;
        }
    }
    @media (max-width: 990px) {
        .wrap {
            width: 100% !important;
        }
        .sender-info {
            flex-direction: column;
            min-width: 135px;
        }
        .sender-text {
            height: auto !important;
            justify-content: center !important;
            text-align: center;
        }
        .item-info {
            overflow: hidden;
        }
        .item-info h2 {
            white-space:nowrap;
        }
    }
    .wrap {
        width: 70%;
        margin: 0 auto;
        background-color: #fbfbfb;
        border: 1px solid rgb(194, 194, 194);
        box-shadow: 1px 1px 10px 1px lightgrey;
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

    .close-btn {
        cursor: pointer;
    }
    .close-btn:hover {
        color: rgb(0, 153, 255);
    }

    .item-info-card {
        display: flex;
        width: 100%;
        height: 100px;
        padding: 0 32px;
        border-bottom: 1px solid rgb(243, 243, 243);
        align-items: center;
        justify-content: space-between;
    }

    .item-left-section {
        display: flex;
        align-items: center;
        cursor: pointer;
    }

    .contact-item {
        width: 65px;
        min-width: 65px;
        height: 65px;
        overflow: hidden;
    }


    .contact-item img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .item-info {
        display: flex;
        flex-direction: column;
        margin: 0 15px;
        height: 55px;
        /* align-items: stretch; */
        justify-content: space-between;
    }
    .item-info h2{
        font-size: 14px;
        margin: 0;
    }

    .item-info p {
        font-size: 12px;
        font-weight: bold;
        margin: 0;
    }
    .msg-container {
        display: flex;
        flex-direction: column;
    }
    .msg-content {
        width: 100%;
        height: 50vh;
        padding: 24px 32px;
        overflow: auto; 
        background-color: rgb(159, 195, 250);
    }

    /* 스크롤바 전체 */
    .msg-content::-webkit-scrollbar {
        width: 8px;
    }
    /* 스크롤 막대 */
    .msg-content::-webkit-scrollbar-thumb {
        background-color: #8f8f8f;
        border-radius: 10px;
        background-clip: padding-box;
        border: 2px solid transparent;
    }
    /* 스크롤 막대 라인 */
    .msg-box::-webkit-scrollbar-track {
        background-color: none;

    }


    .msg {
        width: auto;
        position:relative;
        padding: 2px 7px;
        background:#fafafafa;
        border-radius: 10px;
        font-size: 14px;
    }
    .msg:after {
        border-top:12px solid #fafafafa;
        border-left: 15px solid transparent;
        border-right: 0px solid transparent;
        border-bottom: 0px solid transparent;
        content:"";
        position:absolute;
        top:8px;
        left:-12px;
    }

    .receive-box {
        display: flex;
        height: fit-content;
        margin-bottom: 10px;
    }
    .recieve-sender-img-box {
        display: flex;
        align-items: center;
    }
    .recieve-sender-img {
        display: flex;
        width: 50px;
        height: 50px;
        align-items: center;
    }

    .receive-data {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-left: 15px;
        /* min-width: 120px; */
    }

    .receive-name {
        margin-bottom: 10px;
        font-size: 14px;
        font-weight: bold;
    }

    .receive-msg {
        margin-left: 10px;
    }

    .receive-time {
        display: flex;
        padding: 0 5px;
        margin-bottom: 5px;
        font-size: 13px;
        align-self: flex-end;
    }

    .send-box {
        display: flex;
        /* height: 65px; */
        min-width: 120px;
        justify-content: flex-end !important;
        margin-bottom: 10px;
    }

    .send-data {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
    .my-name {
        font-size: 14px;
        text-align: right;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .send-msg {
        margin-right: 15px;
    }
    
    
    .my-msg {
        position:relative;
        padding: 2px 7px;
        background:#fafafafa;
        border-radius: 10px;
        font-size: 14px;
    }
    .my-msg:after {
        /* border-top:12px solid #fafafafa;
        border-left: 15px solid transparent;
        border-right: 0px solid transparent;
        border-bottom: 0px solid transparent; */
        border-top:12px solid #fafafafa;
        border-left: 0px solid transparent;
        border-right: 15px solid transparent;
        border-bottom: 0px solid transparent;
        content:"";
        position:absolute;
        top:8px;
        right:-10px;
    }


    .menu-container {
        justify-content: flex-end;
        display: flex;
        width: 100%;
        padding: 0 32px;
        background-color: rgb(159, 195, 250);
    }


    .file-btn {
        width: 50px;
        height: 50px;
        margin-right: 10px;
        font-size: 30px;
        font-weight: bold;
        color: #dfddddfd;
        text-align: center;
        cursor: pointer;
    }
    .file-btn:hover {
        transform: scale(1.1);
    }
    #file {
        /* width: 50px;
        position: absolute;
        top: 3px;
        left: 8px;
        opacity: 0; */
        display: none;
    }
    label {
        width: 50px;
        height: 50px;
    }
    label:hover {
        cursor: pointer;
    }

    .report-btn {
        width: 50px;
        height: 50px;
        width: 50px;
        height: 50px;
        font-size: 30px;
        font-weight: bold;
        color: #dfddddfd;
        text-align: center;
        cursor: pointer;
    }
    .report-btn:hover {
        transform: scale(1.1);
    }

    .send-input-container {
        display: flex;
        width: 100%;
        padding: 3px 32px;
        background-color: #ffff;
        /* align-items: center; */
        justify-content: space-between;
    }
    .send-input-container:hover {
        box-shadow: 1px 1px 10px 1px rgb(241, 241, 241);
    }

    .text-input {
        width:90%;
    }
    
    #send-msg {
        font-size: 14px;
        height: 40px;
        border: none;
        outline: none;
        width: 100%;
    }

    .send-btn {
        font-size: 25px;
        color: rgb(0, 174, 255);
        text-align: center;
    }

    .send-btn:hover {
        transform: scale(.9);
        transition: .1s;
    }
</style>
