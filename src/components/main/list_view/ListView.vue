<template>
  <v-ons-page>
    <v-ons-navigator class="content"
      v-bind:page-stack="this.$store.state.listPageStack">
    </v-ons-navigator>
  </v-ons-page>
</template>

<script>
import ListViewMainPage from "./ListViewMainPage.vue";

export default {
  data: function () {
    return {
      get tabletFromList() {
				return window.sessionStorage.getItem("tablet");
      },
      get penFromList() {
				return window.sessionStorage.getItem("pen");
      },
      get keyboardFromList() {
				return window.sessionStorage.getItem("keyboard");
      },
    }
  },

}
</script>

<style>

</style>