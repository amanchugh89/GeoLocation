/**
 * Created by schug2 on 11/26/2015.
 */
$( document ).ready(function() {
    console.log("ready!");



    $("#reload").click(function(){ initMap()});


});

window.setTimeout(
    initMap,5000);

function initMap() {

    $.get("getGeoAll", function (data) {
        var response = data;
        var myLatLng1 = {lat: 28.4469743, lng: 76.9864378};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12,
            center: myLatLng1,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });


        for (i = 0; i < response.length; i++) {
            var myLatLng = {'lat': response[i].latitude, 'lng': response[i].longitude};

            var marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                title: response[i].id.toString()
            });
        }


    });


    //var myLatLng  = response[i].latitude

}