<template>
  <v-ons-page>
    <top-tool-bar></top-tool-bar>
    <v-ons-tabbar position="auto" v-on:postchange="tabbarChange"
      :tabs="tabs"
      :visible="true"
      :index.sync="activeIndex"
    >
    </v-ons-tabbar>
  </v-ons-page>
</template>

<script>
import ServiceView from './service_tab/ServiceView.vue'
import MypageView from './mypage_tab/MypageView.vue'
import SettingView from './setting_tab/SettingView.vue'
import TopToolBar from '../common/TopToolBar.vue';
import ListView from "./list_view/ListView.vue";

let listCount = 0;


export default {
  data: function() {
    return {

      activeIndex: 0,
      tabs: [
        {
          icon: 'fa-link',
          label: '서비스',
          page: ServiceView,
          key: "ServiceView",
        },        
        {
          icon: "fa-clipboard-list",
          label: '리스트',
          badge: listCount,
          page: ListView,
          key: "ListView"
        },
        {
          icon: 'fa-user',
          label: '마이페이지',
          badge: 3,
          page: MypageView,
          key: "myPageView"
        },
        {
          icon: 'fa-cog',
          label: '설정',
          page: SettingView,
          key: "settingView"
        },

      ],
    };
  },
  methods : {
    tabbarChange: function() {
      this.$store.commit('pageIndexChange', this.activeIndex);
    }
  },
  beforeCreate() {
    if(window.sessionStorage.getItem("tablet") !== null) {
      listCount += 1;
    }
    if(window.sessionStorage.getItem("pen") !== null) {
      listCount += 1;
    }
    if(window.sessionStorage.getItem("keyboard") !== null) {
      listCount += 1;
    }
  },
  components: {
    ServiceView,
    SettingView,
    MypageView,
    TopToolBar,

  }
}
</script>

<style>

</style>