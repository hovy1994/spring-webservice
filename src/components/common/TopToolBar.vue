<template>
  <v-ons-toolbar class="top_tool_bar">
    <div class="left logo">
      <i class="fas fa-arrow-left" v-if="this.pageStack.length > 1" v-on:click="popPage"></i>
      {{ pageName }}
    </div>
  </v-ons-toolbar>
</template>

<script>
export default {
  data: function () {
    return {
      query: ''
    }
  },
  methods:{
    popPage: function () {
      this.$store.commit("pageStackPop");
    }
  },
  computed: {
    index: function () {
    },
    pageStack: function () {
      // return this.$store.state.pageNameStack[this.$store.state.pageNameStack.length - 1];
      return this.$store.state.pageStack;
    },
    pageName: function () {
      return this.pageStack[this.pageStack.length - 1].toolBarName;
    }
  }
}
</script>

<style>
.center {
  text-align: center;
}

.logo {
  position: absolute;
  left: 5%;
  top: 10%;
}

i.fa-arrow-left:active {
  color: #0076FF;
}

.search_area {
  margin-top: 10px;
}
</style>