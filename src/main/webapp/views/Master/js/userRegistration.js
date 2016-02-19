(function ($, window, document, RSSB, undefined) {
    RSSB.RegisterUser = (function () {
        function _registerUser() {
            var thisViewModel,
                model = {
                    "id": "",
                    "name": "",
                    "badge": "",
                    "mobile": "",
                    "company": "",
                    "model": "",
                    "mobileType": "",
                    "vehicleNumber" :"",
                    "area":"",
                    "center":""

                };


            this.init = function () {

                ko.applyBindings(new viewModel());


                $("#datepicker").datepicker();
            };




            var viewModel = function () {
                var self = this;

                /**
                 * Basic Details
                 **/
                self.addressList = ko.observableArray();

                self.model = model;
                self.model.id = ko.observable();
                self.model.name = ko.observable();
                self.model.badge = ko.observable();
                self.model.mobile = ko.observable();
                self.model.company = ko.observable();
                self.model.mobileType = ko.observable();
                self.model.vehicleNumber = ko.observable();
                self.model.area = ko.observable();
                self.model.center = ko.observable();
                self.model.model = ko.observable();

                self.areaList = ko.observableArray();
                self.centerList = ko.observableArray();


             /*   self.model.area.subscribe(function(areaVal){
                    if(areaVal!= null && areaVal != undefined && areaVal != "")
                        RSSB.serviceCall('POST', 'rest/getCentreList/'+areaVal, "", getCenterList);
                })*/;



                self.saveDetails = function () {
                    save();
                };



                self.resetForm = function () {
                    self.model.id("");
                    self.model.name("");
                    self.model.badge("");
                    self.model.mobile("");
                    self.model.company("");
                    self.model.mobileType("");
                    self.model.vehicleNumber("");
                    self.model.area("");
                    self.model.center("");
                    self.model.model("");


                }

                function save() {
                    var data = ko.mapping.toJS(self.model),
                        isValid = RSSB.reusables.validateForm($('#user-reg'));
                    if (isValid) {

                        RSSB.serviceCall('POST', 'rest/register', JSON.stringify(data), register);
                    }
                }

                function register(status, response) {
                    if (status === "success"){
						$('.general-errors').addClass('hidden');
						self.resetForm();
						self.model.id(response.id);
						RSSB.reusables.notify('success', 'User has been successfully registered. User ID is: ' + response.id, false);
					}else if(status === "error"){
						$(window).scrollTop(0);
						$('.general-errors p').html(response.hxr.responseJSON.message).attr('data-errorId', response.hxr.responseJSON.errorCode).parents('.general-errors').removeClass('hidden');
					}
				};

                function getAreaList(type, result){
                    if (type == "success"){
                        self.areaList( result.GetAreasResult);
                        self.areaList.sort( function(a,b){
                            if( a.AreaName <b.AreaName) return -1;
                            if( a.AreaName >b.AreaName) return 1;
                            return 0;

                        });
                    }else{
                        // Error.
                    }
                };

                function getCenterList(type, result){
                    if (type == "success"){
                        self.centerList ( result.GetCentresResult);
                        self.centerList.sort( function(a,b){
                            if( a.CentreName <b.CentreName) return -1;
                            if( a.CentreName >b.CentreName) return 1;
                            return 0;

                        });
                    }else{
                        // Error.
                    }
                };

            //    RSSB.serviceCall('POST', 'rest/getAreaList', "", getAreaList);



            };

            return this;
        };

        return new _registerUser();
    }());

    $(function () {
        RSSB.RegisterUser.init();
    });
})(jQuery, this, this.document, RSSB);	// IIFE