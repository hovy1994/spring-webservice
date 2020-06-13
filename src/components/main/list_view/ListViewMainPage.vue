<template>
  <v-ons-page class="tablet_page" v-on:deviceBackButton="popPage($event)">
    <item-list-area></item-list-area>
  </v-ons-page>
</template>

<script>
import ItemListArea from './ItemListArea.vue';


export default {
  key: "TabletPage",
  name: "CartList",
  toolBarName: "리스트",
  data: function () {
    return {
    }
  },
  methods: {
    popPage: function ($event) {
      $event.preventDefault();
      this.$store.commit("pageStackPop");
    },
    pushPageReq($event) {
      this.$emit("push-page", $event);
    }
  },
  components: {
    ItemListArea
  }

}
</script>

<style>
.tablet_page {
  width: 100%;
}

</style>