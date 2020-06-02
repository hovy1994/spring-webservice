<template>
  <v-ons-list-item tappable>
    <div class="left">
      <img class="list-item__thumbnail" v-bind:src="thumbRoot + Item.user + '_' + Item.engName + '_thumb.jpg'">
    </div>
    <div class="center">
      <span class="list-item__title">{{ Item.korName }}</span><span class="list-item__subtitle">{{ Item.dueDate }} {{ Item.dueTime }} 까지</span>
    </div>
  </v-ons-list-item>
</template>

<script>
export default {
  data: function() {
    return {
      thumbRoot: './src/assets/images/items/'
    }
  },
  props: ['Item']
}
</script>

<style>

</style>