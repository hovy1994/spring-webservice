<template>
    <div ref="map"></div>
</template>

<script>
/* global kakao */

export default {
    name: 'KakaoMap',
    props: {
        appkey: {
            type: String,
            required: true
        },
        width: {
            type: Number,
            default: 100
        },
        height: {
            type: Number,
            default: 100
        },
        center: {
            type: Array,
            required: true
        },
        level: {
            type: Number
        },
        isShowing: {
            type: Boolean
        }
    },

    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a04f05666032a9ee976b653738fe4b63';
            document.head.appendChild(script);
        }
    },

    data() {
      return {
        map: null,
        mapCenter: this.center,
        cabinetLatLng: [
          {
            korName: "1호관",
            engName: "1st",
            lat: 37.449366,
            lng: 126.654386
          },
          {
            korName: "2호관",
            engName: "2nd",
            lat: 37.450340,
            lng: 126.655694
          },
          {
            korName: "3호관",
            engName: "3rd",
            lat: 37.450920,
            lng: 126.654242
          },
          {
            korName: "4호관",
            engName: "4th",
            lat: 37.450390,
            lng: 126.655207
          },
          {
            korName: "5호관",
            engName: "5th",
            lat: 37.451213,
            lng: 126.653141
          },
          {
            korName: "6호관",
            engName: "6th",
            lat: 37.447740,
            lng: 126.655893
          },
          {
            korName: "비룡플라자",
            engName: "Bilyong",
            lat: 37.449674,
            lng: 126.656570
          },
          {
            korName: "9호관",
            engName: "9th",
            lat: 37.447510,
            lng: 126.655400
          },
          {
            korName: "하이테크",
            engName: "HITECH",
            lat: 37.450620,
            lng: 126.657353
          },
          {
            korName: "정석학술정보관",
            engName: "JeongSuk",
            lat: 37.449461,
            lng: 126.652536
          },
          {
            korName: "서호관",
            engName: "Seoho",
            lat: 37.451284,
            lng: 126.651431
          },
          {
            korName: "법학전문대학원",
            engName: "LawSchool",
            lat: 37.448397,
            lng: 126.651914
          },
        ]
        }
    },

    watch: {
        isShowing(val) {
            if (val) {
                this.relayout();
            }
        },
        width(val) {
            this.$refs.map.style.width = val + '%';
            this.relayout();
        },
        height(val) {
            this.$refs.map.style.height = val + '%';
            this.relayout();
        }
    },

    methods: {
        initMap() {
            const container = this.$refs.map;
            container.style.width = this.width + '%';
            container.style.height = this.height + '%';

            const options = {
              center: new kakao.maps.LatLng(...this.mapCenter)
            };

            const map = new kakao.maps.Map(container, options);
            map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
            kakao.maps.event.addListener(map, 'center_changed', this.onCenterChanged);

            
            for(let i = 0; i < this.cabinetLatLng.length; i++) {
                var markerPosition  = new kakao.maps.LatLng(this.cabinetLatLng[i].lat, this.cabinetLatLng[i].lng); 

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    position: markerPosition
                });
                // 마커가 지도 위에 표시되도록 설정합니다
                marker.setMap(map);
            }
            this.map = map;
        },
        relayout() {
            kakao.maps.event.removeListener(this.map, 'center_changed', this.onCenterChanged);
            this.map.relayout();
            const latlng = new kakao.maps.LatLng(...this.mapCenter);
            this.map.setCenter(latlng);
            kakao.maps.event.addListener(this.map, 'center_changed', this.onCenterChanged);
        },
        onCenterChanged() {
            const center = this.map.getCenter();
            this.mapCenter = [center.getLat(), center.getLng()];
        }
    }
}
</script>