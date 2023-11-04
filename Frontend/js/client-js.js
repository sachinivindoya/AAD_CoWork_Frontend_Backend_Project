
//Guides array
let GuideObjsLocalDB = [];

//Hotels array
let HotelObjsLocalDB = [];

const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_alert_model_unauthorise_error = $('#guide_admin_main_pg_alert-model-unauthorise-error');

const guide_admin_main_pg_alert_model_error = $('#guide_admin_main_pg_alert-model-error');
const guide_admin_main_pg_alert_model_title_error =  $('#guide_admin_main_pg_alert-model-title-error');
const guide_admin_main_pg_alert_model_content_error = $('#guide_admin_main_pg_alert-model-content-error');

//load profile image
$(document).ready(function(){


    loadAdminProfileData();

    loadGuidesData();

    loadHotelsData();



});

function loadAdminProfileData(){
    console.log(localStorage.getItem("secure_data_client_username"));
    console.log(localStorage.getItem("secure_data_client_access_token"));
    console.log(localStorage.getItem("secure_data_client_refresh_token"));

    //show loading model
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user/user-get-profile-data',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_client_username"),
            access_jwt_token: localStorage.getItem("secure_data_client_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_client_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_client_username", data.token.access_username);
                localStorage.setItem("secure_data_client_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_client_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data

                // guide_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //fill setting form
                // p_s_id.text(data.data.id);
                // p_s_username.text(data.data.name);
                // p_s_email.val(data.data.email);
                // p_s_nameinitial.val(data.data.name_with_initial);
                // p_s_nic.val(data.data.nic_or_passport);
                // p_s_address.val(data.data.address);
                $('#profile_image').attr('src', data.data.profile_image);



                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    setTimeout(function () {
                        $('#alert').show();
                        setTimeout(function () {
                            $('#alert').hide();
                        }, 1000); // delay
                    }, 100); // delay

                }, 1000); // delay

            } else {
                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    guide_admin_main_pg_alert_model_content_error.text("Try Again!");
                    guide_admin_main_pg_alert_model_error.modal('show');

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 401){
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_unauthorise_error.modal('show');

                }, 1000); // delay
            }else {
                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    guide_admin_main_pg_alert_model_content_error.text("Try Again!");
                    guide_admin_main_pg_alert_model_error.modal('show');

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        }
    });
}

function loadGuidesData(){
//show loading model
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/guide-service/guides-getall',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_client_username"),
            access_jwt_token: localStorage.getItem("secure_data_client_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_client_refresh_token")
        },
        success: function(data) {
            data.data.forEach(function (guide){
                GuideObjsLocalDB.push([new GuideDTO(
                    guide.id,
                    guide.name,
                    guide.address,
                    guide.nic,
                    guide.tell,
                    guide.experience,
                    guide.dob,
                    guide.perday_fee,
                    guide.remarks,
                    guide.gender,
                    guide.image,
                    guide.nic_front_view,
                    guide.nic_rear_view
                )]);
            });


//load cards
            loadGuideDataintoCards();
            //hide loading model
            setTimeout(function () {
                guide_admin_main_pg_loading_model.modal('hide');
                setTimeout(function () {
                    $('#alert').show();
                    setTimeout(function () {
                        $('#alert').hide();
                    }, 1000); // delay
                }, 100); // delay

            }, 1000); // delay

        },
        error: function(xhr, status, error) {
            if (xhr.status === 401){
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_unauthorise_error.modal('show');

                }, 1000); // delay
            }else {

                console.log("fail to logout exception");
            }
        }
    });
}

function loadGuideDataintoCards() {
    console.log("@@@@####array###start");
    console.log(GuideObjsLocalDB);
    GuideObjsLocalDB.forEach(function (guide) {
        console.log("in Guide ID: " ); // Accessing the ID at index 0
        guide.forEach(function (guided) {
            console.log("in Guide ID: " + guided.getGuideID()); // Accessing the ID at index 0

            const card = $('<div>').addClass('col-lg-4 col-md-6 wow fadeInUp').attr('data-wow-delay', '0.1s');
            const cardContent = `
        <div class="room-item shadow rounded overflow-hidden " style="background-color: rgba(168,168,168,0.47)">
            <div class="position-relative">
                <img class="img-fluid" src="${guided.getGuideProfileimage()}" alt="" style="width: 100%"> 
                <small class="position-absolute start-0 top-100 translate-middle-y bg-success text-white rounded py-1 px-3 ms-lg-4">Rs.${guided.getGuidePerdayfee()}/Day</small>
            </div>
            <div class="p-4 mt-2">
                <div class="d-flex justify-content-between mb-3">
                    <h4 class="mb-0">${guided.getGuideName()}</h4>
          
                </div>
                <p class="text-body mb-3">* Remarks : ${guided.getGuideRemarks()}</p>
                <p class="text-body mb-3">* Experience : ${guided.getGuideExperience()}</p>
                <p class="text-body mb-3">* Gender : ${guided.getGuideGender()}</p>
                <p class="text-body mb-3">* DOB : ${guided.getGuideDob()}</p>
                <p class="text-body mb-3">* Address : ${guided.getGuideAddress()}</p>
     
                <div class="d-flex justify-content-between">
                    <a class="btn btn-sm btn-dark rounded py-2 px-4" href="">Book Now</a>
                </div>
            </div>
        </div>
    `;

            card.html(cardContent);
            $('#Guide_card_container').append(card);

        });
    });
    console.log("@@@####array###end");


}


// function loadGuideDataintoCards() {
//     console.log("@@@@####array###start");
//     console.log(GuideObjsLocalDB);
//     GuideObjsLocalDB.forEach(function (guide) {
//         console.log("in Guide ID: " ); // Accessing the ID at index 0
//         guide.forEach(function (guided) {
//             console.log("in Guide ID: " + guided.getGuideID()); // Accessing the ID at index 0
//
//             const card = $('<div>').addClass('col-lg-4 col-md-6 wow fadeInUp').attr('data-wow-delay', '0.1s');
//             const cardContent = `
//         <div class="room-item shadow rounded overflow-hidden " style="background-color: rgba(168,168,168,0.47)">
//             <div class="position-relative">
//                 <img class="img-fluid" src="${guided.getGuideProfileimage()}" alt="">
//                 <small class="position-absolute start-0 top-100 translate-middle-y bg-info text-white rounded py-1 px-3 ms-4">$${guided.getGuidePerdayfee()}/Day</small>
//             </div>
//             <div class="p-4 mt-2">
//                 <div class="d-flex justify-content-between mb-3">
//                     <h5 class="mb-0">${guided.getGuideName()}</h5>
//                     <div class="ps-2">
//                         <small class="fa fa-star text-primary"></small>
//                         <small class="fa fa-star text-primary"></small>
//                         <small class="fa fa-star text-primary"></small>
//                         <small class="fa fa-star text-primary"></small>
//                         <small class="fa fa-star text-primary"></small>
//                     </div>
//                 </div>
//                 <div class="d-flex mb-3">
//                     <small class="border-end me-3 pe-3"><i class="fa fa-bed text-primary me-2"></i>${guided.getGuideExperience()} Bed</small>
//                     <small class="border-end me-3 pe-3"><i class="fa fa-bath text-primary me-2"></i>${guided.getGuideExperience()} Bath</small>
//                     <small><i class="fa fa-wifi text-primary me-2"></i>Wifi</small>
//                 </div>
//                 <p class="text-body mb-3">${guided.getGuideRemarks()}</p>
//                 <div class="d-flex justify-content-between">
//                     <a class="btn btn-sm btn-primary rounded py-2 px-4" href="">View Detail</a>
//                     <a class="btn btn-sm btn-dark rounded py-2 px-4" href="">Book Now</a>
//                 </div>
//             </div>
//         </div>
//     `;
//
//             card.html(cardContent);
//             $('#Guide_card_container').append(card);
//
//         });
//     });
//     console.log("@@@####array###end");
//
//
// }

function loadHotelsData(){
//show loading model
    console.log("HHotel @@@@####array###start");
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/hotel-service/hotels-getall',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_client_username"),
            access_jwt_token: localStorage.getItem("secure_data_client_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_client_refresh_token")
        },
        success: function(data) {
            data.data.forEach(function (hotel){
                console.log("***************night :"+hotel.is_pet_allowed);

                HotelObjsLocalDB.push([new HotelDTO(
                    hotel.id,
                    hotel.name,
                    hotel.location,
                    hotel.location_coordinates,
                    hotel.remarks,
                    hotel.referenceLink,
                    hotel.email,
                    hotel.tell1,
                    hotel.tell2,
                    hotel.categoryStar,
                    hotel.is_pet_allowed,
                    hotel.perday_hotel_fee_for_days,
                    hotel.perday_hotel_fee_for_nights,
                    hotel.cancellationFee,
                    hotel.image,
                    hotel.option_1_description,
                    hotel.option_1_name,
                    hotel.option_1_fee,
                    hotel.option_2_description,
                    hotel.option_2_name,
                    hotel.option_2_fee,
                    hotel.option_3_description,
                    hotel.option_3_name,
                    hotel.option_3_fee,
                    hotel.option_4_description,
                    hotel.option_4_name,
                    hotel.option_4_fee
                )]);
            });


//load cards
            loadHotelDataintoCards();

            //hide loading model
            setTimeout(function () {
                guide_admin_main_pg_loading_model.modal('hide');
                setTimeout(function () {
                    $('#alert').show();
                    setTimeout(function () {
                        $('#alert').hide();
                    }, 1000); // delay
                }, 100); // delay

            }, 1000); // delay

        },
        error: function(xhr, status, error) {
            if (xhr.status === 401){
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_unauthorise_error.modal('show');

                }, 1000); // delay
            }else {

                console.log("fail to logout exception");
            }
        }
    });
}

function loadHotelDataintoCards() {
    console.log("Hotelloading##start");
    console.log(HotelObjsLocalDB);
    HotelObjsLocalDB.forEach(function (hotel) {
        console.log("in Hotel ID: " ); // Accessing the ID at index 0
        hotel.forEach(function (hoteld) {

            console.log(" mapppp           :   "+hoteld.getHotelLocationMapLink());

            const card = $('<div>').addClass('col-lg-4 col-md-6 wow fadeInUp').attr('data-wow-delay', '0.1s');
            const cardContent = `
        <div class="room-item shadow rounded overflow-hidden " style="background-color: rgba(168,168,168,0.47)">
            <div class="position-relative">
                <img class="img-fluid" src="${hoteld.getHotelImage()}" alt="" style="width: 100%">
                <small class="position-absolute start-0 top-100 translate-middle-y bg-success text-white rounded py-1 px-3 ms-lg-4">Rs.${hoteld.getHotelPerdayHotelFee()}/Day & Rs.${hoteld.getHotelPernightHotelFee()}/Night</small>
            </div>
            <div class="p-4 mt-2">
                <div class="d-flex justify-content-between mb-3">
                    <h4 class="mb-0">${hoteld.getHotelName()}</h4>

                </div>
                <p class="text-body mb-3">* Location : ${hoteld.getHotelLocation()}</p>
                <a href="${hoteld.getHotelLocationMapLink()}" style="color: blue">* Click to open Map</a<br><br>
               <p class="text-body mb-3">* Remarks : ${hoteld.getHotelRemarks()}</p>
               <p class="text-body mb-3">* Is Pet Allowed? : ${hoteld.getHotelIsPetAllowed()}</p>
               
                <div class="d-flex justify-content-between">
                    <a class="btn btn-sm btn-dark rounded py-2 px-4" href="">Book Now</a>
                </div>
            </div>
        </div>
    `;

            card.html(cardContent);
            $('#Hotel_card_container').append(card);

        });
    });
    console.log("@@@####array###end");


}