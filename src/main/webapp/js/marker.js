/**
 * Created by schug2 on 11/26/2015.
 */
$( document ).ready(function() {
    console.log("ready!");



    $("#reload").click(function(){ initMap()});


});


function initMap() {

    var pinColor = "FE7569";
    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0,0),
        new google.maps.Point(10, 34));
    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
        new google.maps.Size(40, 37),
        new google.maps.Point(0, 0),
        new google.maps.Point(12, 35));

    $.get("/getGeoAll", function (data) {
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
                title: response[i].id.toString(),
                icon: pinImage,
                shadow: pinShadow
            });
        }


    });

    window.setTimeout(
        initMap,60000);
    //var myLatLng  = response[i].latitude

}