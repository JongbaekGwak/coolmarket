<template>
    <div class="wrap">
        <div class="header-title">
            <p class="m-0">쪽지함</p>
            <div class="close-btn" v-on:click="MoveMyPage">
                <i class="fa fa-times fs-4" aria-hidden="true"></i>
            </div>
        </div>
        <div class="message-card" v-on:click="MoveMsgDetail">
            <div class="left-section">
                <div class="sender-info">
                    <div class="sender-img">
                        <i class="fa fa-user" aria-hidden="true"></i>
                    </div>
                    <div class="sender-text">
                        <div class="sender-name">
                            <p class="m-0">수영구주민</p>
                        </div>
                        <div class="sender-region">
                            <p class="m-0">부산광영시 수영구</p>
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

        }
    },
    methods: {
        MoveMyPage() {
            this.$router.push({ name: 'MyPage' });
        },
        MoveMsgDetail() {
            this.$router.push({ name: 'MsgDetail' });
        }
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
    .receive-date {

        display: flex;
        /* justify-self: end; */
    }

    .left-section {
        display: flex;
        align-items: center;
    }

    .receive-date {
        font-size: 13px;
    }
</style>