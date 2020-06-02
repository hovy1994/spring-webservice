import Vue from 'vue'
import Vuex from 'vuex'

import ServiceViewMainPage from './components/main/service_tab/main_page/ServiceViewMainPage.vue';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    pageNameStack: ["BeBlet"],
    pageStack: [ServiceViewMainPage],
    items: [
      {
        idx: 0,
        korName: '아이패드 프로 11"',
        engName: "ipad_pro_11",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 800,
        priceStr: "800원 / 시간",
      },
      {
        idx: 1,
        korName: '아이패드 프로 12.9"',
        engName: "ipad_pro_12",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 700,
        priceStr: "700원 / 시간",
      },
      {
        idx: 2,
        korName: "아이패드 에어",
        engName: "ipad_air",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 600,
        priceStr: "600원 / 시간",
      },
      {
        idx: 3,
        korName: "아이패드 7세대",
        engName: "ipad_air",
        maker: "apple",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 500,
        priceStr: "500원 / 시간"
      },
      {
        idx: 4,
        korName: "갤럭시탭 S6",
        engName: "galaxy_tab_s6",
        maker: "samsung",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 600,
        priceStr: "600원 / 시간"
      },
      {
        idx: 5,
        korName: "갤럭시탭 S6 Lite",
        engName: "galaxy_tab_s6",
        maker: "samsung",
        // remaining: [100, 7, 12, 5, 4, 1, 0, 22, 10, 7, 9, 7, 8, 8],
        price: 500,
        priceStr: "500원 / 시간"
      },
    ],
    selectedItemIdx: null,
  },
  mutations: {
    pageStackPush: function(state, payload) {
      state.pageStack.push(payload.page);
      state.selectedItemIdx = payload.selectedItemIdx;
    },
    pageStackPop(state) {
      if (state.pageStack.length > 1) {
        state.pageStack.pop();
      }
      // var currentPageName = state.pageStack[state.pageStack.length - 1].name;
      if (state.pageStack.length < 2) {
        state.selectedItemIdx = null;
      }
    },
  },
  getters: {
    selectedItem: function (state) {
      return state.items[state.selectedItemIdx];
    }
  }
});
