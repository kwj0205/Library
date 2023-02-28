var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.5000, 126.9975),
        level: 3
    };

var map = new kakao.maps.Map(mapContainer, mapOption);
var markerPosition  = new kakao.maps.LatLng(37.4978, 127.0029);
var marker = new kakao.maps.Marker({
    position: markerPosition
});
marker.setMap(map);

map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);