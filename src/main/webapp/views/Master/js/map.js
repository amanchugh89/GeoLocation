var TRACKER = (function () {
    var map, markers = [],
        infoBoxes = [],
        keepOpen = [],
        selectedOption = [],
        isFilter = false,
        allBusJson;

    var initMap = function () {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 11,
            center: {lat: 28.441193, lng: 77.199203}
        });

        var marker = new google.maps.Marker({
            position: {lat: 28.441193, lng: 77.199203},
            map: map,
            title: "Bhati",
            icon: {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 5,
                strokeColor: "#689f38"
            }
        });

        getBusLoc();
        getUserList();
        attachEvents();
        setInterval(function () {
            getBusLoc();
        }, 30 * 1000);
    };

    var attachEvents = function () {
        $(document).on('change', '.map-option', function () {
            var $selectedOption = $('.' + this.value + '-wrap'),
                $allOptions = $('#side-bar .options');

            $allOptions.addClass('hidden');
            if (this.value == "filter") {
                $selectedOption.removeClass('hidden')
            } else if (this.value == "single") {
                $selectedOption.removeClass('hidden')
                $('.filter-wrap li input:checked').attr('checked', false);
                setMarkerOnMap(null);
                setMarkerOnMap(map);
                selectedOption = [];
                isFilter = false;
                closeAllInfobox();


            } else {
                $('.filter-wrap li input:checked').attr('checked', false);
                setMarkerOnMap(null);
                setMarkerOnMap(map);
                selectedOption = [];
                isFilter = false;
                closeAllInfobox();
            }
        });

        $(document).on('change', '.filter-wrap li input', function (evt) {
            var $this = $(this),
                checked = this.checked,
                id = $this.attr('data-id');

            // clear all markers for first checkbox checked.
            (!isFilter) && (setMarkerOnMap(null));

            // if Checked, set the isFilter flag true.
            if (checked) {
                selectedOption.push(id);
                isFilter = true;
            } else {
                var index = selectedOption.indexOf(id);
                if (index > -1) {
                    selectedOption.splice(index, 1);
                }
            }

            for (aj = 0, ajLen = allBusJson.length; aj < ajLen; aj++) {
                var thisBus = allBusJson[aj];

                if (thisBus.userDetails && thisBus.userDetails.id == id) {
                    if (checked) {
                        markers[aj].setMap(map)
                        infoBoxes[aj].open(map, markers[aj]);
                    } else {
                        infoBoxes[aj].close(map, markers[aj]);
                        markers[aj].setMap(null);
                    }
                }
            }

            // If all the checkboxes are unchecked, show all the buses.
            if ($('.filter-wrap li input:checked').length == 0) {
                isFilter = false;
                setMarkerOnMap(map);
            }
        });

        $(document).on('change', '.single-wrap select.transport', function (evt) {

        });
    };

    var getBusLoc = function () {
        $.get("rest/getGeoAll/12", function (data) {
            //$.get("test.json", function(data){
            //var response = JSON.parse(data);
            var response = (data);

            clearMarkers();

            for (i = 0; i < response.length; i++) {
                if (response[i].userDetails) {
                    var dt = new Date(response[i].timestamp),
                        time = ((dt.getHours() > 10) ? dt.getHours() : ("0" + dt.getHours())) + ":" + ((dt.getMinutes() > 10) ? dt.getMinutes() : ("0" + dt.getMinutes())),
                        contentString = "<p style='margin: 0;'>" + response[i].userDetails.center + "</p><p style='margin: 0;'>" + response[i].userDetails.vehicleNumber + "</p><p style='margin: 0;'>" + time + "</p>";

                    markers[i] = new google.maps.Marker({
                        position: {'lat': response[i].latitude, 'lng': response[i].longitude},
                        title: response[i].userId + ". Last updated at : " + time + " for User " + ((response[i].userDetails && response[i].userDetails.name) ? response[i].userDetails.name : ""),
                        icon: {
                            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
                            scale: 3
                        }
                    });

                    infoBoxes[i] = new google.maps.InfoWindow({
                        content: contentString
                    });

                    if (!isFilter || selectedOption.indexOf(response[i].userDetails.id.toString()) >= 0) {
                        markers[i].setMap(map);
                        (isFilter) && (infoBoxes[i].open(map, markers[i]));
                    }

                    (function (i) {
                        /* markers[i].addListener('click', function() {
                         infoBoxes[i].open(map, markers[i]);
                         }); */

                        markers[i].addListener('mouseover', function () {
                            infoBoxes[i].open(map, markers[i]);
                        });

                        markers[i].addListener('mouseout', function () {
                            infoBoxes[i].close(map, markers[i]);
                        });
                    })(i);
                }
                ;
            }

            allBusJson = response;
        });
    };

    var trackUser = function (id, from, to) {
        $.get("rest/trackUser/" + id + "/" + from + "/" + to, function (data) {

            clearMarkers();
            var wps = [];
            var rendererOptions = {map: map};
            var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
            for (i = 0; i < data.length; i++) {
                wps.push({location: new google.maps.LatLng(data[i].latitude, data[i].longitude)});

            }
            var org = new google.maps.LatLng(-33.89192157947345, 151.13604068756104);
            var dest = new google.maps.LatLng(-33.69727974097957, 150.29047966003418);
            var request = {
                origin: org,
                destination: dest,
                waypoints: wps,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };
            var directionsService = new google.maps.DirectionsService();
            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
                else
                    alert('failed to get directions');
            });
        })
    };

    var getUserList = function () {
        $.get("rest/getAllUsers", function (data) {
            //$.get("user.json", function(data){
            //	var response = JSON.parse(data);
            var response = data;
            // populate filter list
            var $filterWrap = $('.filter-wrap'),
                $transportSelect = $('.transport'),
                filterStr = "",
                selectBoxStr = "<option>Select from below</option>";
            for (var ul = 0, ulLen = response.length; ul < ulLen; ul++) {
                var user = response[ul],
                    html = '<li><input type="checkbox" id="user-' + user.id + '" name="user-' + user.id + '" data-id="' + user.id + '"><label for="user-' + user.id + '">' + user.name + ', ' + user.center + '</label>',
                    optionStr = '<option value="' + user.id + '">' + user.name + ', ' + user.center + '</option>'

                filterStr += html;
                selectBoxStr += optionStr;
            }
            $filterWrap[0].innerHTML = filterStr;
            $transportSelect[0].innerHTML = selectBoxStr;
        });


    }

    var setMarkerOnMap = function (map) {
        for (var i = 0; i < markers.length; i++) {
            if(markers[i] != undefined) markers[i].setMap(map);
        }
    }

    var clearMarkers = function () {
        closeAllInfobox();
        setMarkerOnMap(null);
        markers = [];
        infoBoxes = [];
    }

    var closeAllInfobox = function () {
        for (var ib = 0, ibLen = infoBoxes.length; ib < ibLen; ib++) {
           if(infoBoxes[ib]!= undefined) infoBoxes[ib].close(map, markers[ib]);
        }
    }


    return {
        initMap: initMap
    }
})();