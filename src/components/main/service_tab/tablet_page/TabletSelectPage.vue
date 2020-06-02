<template>
  <v-ons-page v-on:deviceBackButton="popPage($event)">
    <div class="picture_area">
      <img v-bind:src="'./src/assets/images/tablets/' + selectedTablet.engName + '.png'" alt="">
    </div>
    <div class="item_name">
      {{ selectedTablet.korName }}
    </div>
    <div class="date_pick">
      <HotelDatePicker 
        format="YYYY-MM-DD" 
        v-bind:maxNights="14" 
        v-bind:hoveringTooltip="true" 
        v-bind:i18n="datePickerOption" 
        v-bind:currentDateStyle="{border: '1px solid #0076ff'}"
        v-on:closed="checkTime($event)"
        ></HotelDatePicker>
    </div>
    <i class="far fa-calendar-alt cal_icon"></i>

  </v-ons-page>
</template>

<script>
import HotelDatePicker from 'vue-hotel-datepicker'

// var popProcess = async function() {

// }

export default {
  key: "TabletSelectPage",
  name: "TabletSelect",
  toolBarName: "태블릿 선택",
  data: function () {
    return {
      datePickerOption: {
        night: 'Night',
        nights: 'Nights',
        'day-names': ['Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat'],
        'check-in': '대여일',
        'check-out': '반납일',
        'month-names': ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      },
      startTime: "",
    }
  },
  methods: {
    popPage: function ($event) {
      $event.preventDefault();
      this.$store.commit("pageStackPop");
    },
    checkTime: function ($event) {
      let dateObj = new Date($event._data.checkIn);
      let year = dateObj.getFullYear();
      let month = dateObj.getMonth() + 1;
      let day = dateObj.getDay();
      let dateStr = year + "-";
      if (month < 10) {
        month = "0" + month;
      }
      if (date < 10) {
        date = "0" + date;
      }
      dateStr += month;
      dateStr += "-";
      dateStr += date;

      if(dateStr === this.todayDate) {
        let todayObj = new Date();
        let curHour = todayObj.getHours();
        
        curHour += 1;

        if (curHour > 24) {
          curHour = 0;
        }
      }
    }
  },
  computed: {
    selectedTablet: function () {
      return this.$store.getters.selectedItem;
    },
    todayDate: function () {
      let todayObj = new Date();
      let year = todayObj.getFullYear();
      let month = todayObj.getMonth() + 1;
      let date = todayObj.getDate();
      let dateStr = year + "-";
      if (month < 10) {
        month = "0" + month;
      }
      if (date < 10) {
        date = "0" + date;
      }
       dateStr += month;
      dateStr += "-";
      dateStr += date;

      console.log(dateStr);

      return dateStr;
    }
  },
  components: {
    HotelDatePicker
  }
}
</script>

<style>
.picture_area {
  height: 300px;
  text-align: center;
  position: relative;
}
.picture_area img {
  position: relative;
  width: 60%;
}

.item_name {
  position: relative;
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 23px;
  top: 0;
}

.date_pick {
  position: relative;
  top: 5%;
}

.cal_icon {
  position: relative;
  color: #0076ff;
  font-size: 20px;
  left: 7%;
  bottom: 15px;
}



/*
  date Picker
 */
.datepicker__month-day--first-day-selected, .datepicker__month-day--last-day-selected, .datepicker__month-day--selected {
  background-color: #0076FF;
}
.datepicker__close-button {
  color: #0076FF;
}

.datepicker__dummy-wrapper--is-active {
  border: #0076FF;
}

.datepicker__input {
  color: #0076FF;
}

.datepicker__month-day--first-day-selected, .datepicker__month-day--last-day-selected {
  background-color: #0076ff;
}

.datepicker__clear-button {
  margin-top: 9px;
}

.datepicker__dummy-wrapper {
  border: none;
}

.datepicker__input:first-child {
  background: transparent url(http://localhost:8080/src/assets/images/dateImg/ic-arrow-right-datepicker.regular.svg) no-repeat 100%/8px;
}
</style>