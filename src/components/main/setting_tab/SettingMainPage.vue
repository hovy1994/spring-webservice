<template id="page1">
  <v-ons-page>
    <v-ons-list>
      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item tappable>
        <i class="fas fa-bullhorn"></i> 공지사항
      </v-ons-list-item>
      <v-ons-list-item tappable>
        <i class="fas fa-headset"></i> 문의하기
      </v-ons-list-item>
      <v-ons-list-item tappable>
        <i class="fas fa-won-sign"></i> 결제내역
      </v-ons-list-item>

      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item modifier="chevron" tappable>앱 설정</v-ons-list-item>

      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item>
        <div class="left">
          <img class="list-item__thumbnail" src="http://placekitten.com/g/40/40">
        </div>
        <div class="center">
          <span class="list-item__title">유저 닉네임</span><span class="list-item__subtitle">유저 정보</span>
        </div>
      </v-ons-list-item>

      <v-ons-list-header></v-ons-list-header>
      <v-ons-list-item tappable v-on:click="logout" title="로그아웃">
        <i class="fas fa-sign-out-alt"></i> 로그아웃
      </v-ons-list-item>

    </v-ons-list>
  </v-ons-page>
</template>

<script>
import DetailSettingPage from './DetailSettingPage.vue';

export default {
  key: "SettingPage",
  name: "Setting",
  toolBarName: "BeBlet",
  methods: {
    push: function() {
      this.$emit('push-page', DetailSettingPage);
    },
    logout: function () {
      var options = {
        title: '로그아웃'
      };
      var logoutCallBack = function () {
        window.localStorage.removeItem("id");
        window.localStorage.removeItem("pubKey");
        window.localStorage.removeItem("nickname");
        window.location.reload();
      }

      this.$ons.notification.confirm('정말로 로그아웃 하시겠습니까?', options).then(logoutCallBack);
    }
  }
}
</script>

<style>
i {
  margin-right: 10px;
}
</style>