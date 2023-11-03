//////////////////////--------------component variables

const pswdrecover_pg_loadingModel = $('#pswrdrecover-pg-loading-model');

const pswdrecover_pg_alertModelError = $('#pswdrecover-pg-alert-model-error');
const pswdrecover_pg_alertModelError_title =  $('#pswdrecover-pg-alert-model-title-error');
const pswdrecover_pg_alertModelError_content = $('#pswdrecover-pg-alert-model-content-error');

const pswdrecover_pg_alertModelDone = $('#pswdrecover-pg-alert-model-done');
const pswdrecover_pg_alertModelDone_title =  $('#pswdrecover-pg-alert-model-title-done');
const pswdrecover_pg_alertModelDone_content = $('#pswdrecover-pg-alert-model-content-done');

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');

const pswd_recovery_login_otp_txtfld = $('#pswd_recovery_login_otp_txtfld');

const pswdrecover_pg_forgot_pswd_get_otp_container = $('#forgot_pswd_get_otp_container');
const pswdrecover_pg_forgot_pswd_get_username_container = $('#forgot_pswd_get_username_container');

const pswdrecover_pg_pswdadding_model = $('#pswrdrecover-pg-pswdadding-model');

let pswd_recovery_login_username_txtfld_value;

const pswd_recovery_login_not_confirm_pswd_txtfld = $('#pswrdrecover-pg-pswdadding-model-new-pswd-txtfld');
const pswd_recovery_login_confirmed_pswd_txtfld = $('#pswrdrecover-pg-pswdadding-model-confiremed-pswd-txtfld');

//////--------------------------password recovery username

function pswdRecoveryUsernameSearchBtnClicked(){
    pswd_recovery_login_username_txtfld_value = pswd_recovery_login_username_txtfld.val();

    //show loading model
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
        url: 'http://localhost:1010/main/user/ischeck-username-and-send-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld_value),
        processData: false,
        contentType: false,
        success: function (data) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {
                    if (data.rspd_code === RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED) {
                        pswdrecover_pg_alertModelDone_title.text("OTP sent to you'r Mail...");
                        pswdrecover_pg_alertModelDone_content.text("Please check your e-mail account for the OTP we just sent you and enter that ...");
                        pswdrecover_pg_alertModelDone.modal('show');

                        setTimeout(function () {
                            pswdrecover_pg_alertModelDone.modal('hide');

                            pswdrecover_pg_forgot_pswd_get_username_container.css('display','none');
                            pswdrecover_pg_forgot_pswd_get_otp_container.css('display','block');

                        }, 3000); // delay
                    } else {
                        pswdrecover_pg_alertModelError_title.text("Not Found!");
                        pswdrecover_pg_alertModelError_content.text("This User not Registerd yet.Try again or register please!");
                        pswdrecover_pg_alertModelError.modal('show');

                        setTimeout(function () {
                            location.reload(); //reload
                        }, 2800); // delay
                    }

                }, 100); // delay

            }, 500); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("Try Again!");
                    pswdrecover_pg_alertModelError_content.text("Register or Type another one ...!");
                    pswdrecover_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        location.reload(); //reload
                    }, 2800); // 2000 milliseconds

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds
        }
    });
}

///-------OTP varification
function pswdRecoveryLoginOTOCheckBtnClicked(){
    pswd_recovery_login_otp_txtfld_value = pswd_recovery_login_otp_txtfld.val();

    //show loading model
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
        url: 'http://localhost:1010/main/user/isverify-username-with-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld_value)+'&otp='+encodeURIComponent(pswd_recovery_login_otp_txtfld_value),
        processData: false,
        contentType: false,
        success: function (data) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {
                    if (data.rspd_code === RespondCodes.Respond_USERNAME_AND_OTP_VERIFIED) {
                        pswdrecover_pg_alertModelDone_title.text("OTP Matched...");
                        pswdrecover_pg_alertModelDone_content.text("Create a new password and you can login here ...");
                        pswdrecover_pg_alertModelDone.modal('show');

                        setTimeout(function () {
                            //open to enter password
                            pswdrecover_pg_pswdadding_model.modal('show');
                            pswdrecover_pg_alertModelDone.modal('hide');

                        }, 1000); // delay
                    } else {
                        pswdrecover_pg_alertModelError_title.text("OTP Not Matched!");
                        pswdrecover_pg_alertModelError_content.text("Type correct OTP and Try again...!");
                        pswdrecover_pg_alertModelError.modal('show');
                    }

                }, 100); // delay

            }, 500); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("OTP Not Matched!");
                    pswdrecover_pg_alertModelError_content.text("Type correct OTP and Try again...!");
                    pswdrecover_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        pswdrecover_pg_alertModelError.modal('hide');

                    }, 1000); // delay

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds
        }
    });
}

//----------create new password and login to system
function pswdRecoveryCreatePswdAndLogin(){
    //show loading model
    pswdrecover_pg_pswdadding_model.modal('hide');
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
        url: 'http://localhost:1010/main/user/user-login-with-recoverd-password?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld_value)+'&otp='+encodeURIComponent(pswd_recovery_login_otp_txtfld_value)+'&password='+encodeURIComponent(pswd_recovery_login_confirmed_pswd_txtfld.val()),
        processData: false,
        contentType: false,
        success: function (data) {
            setTimeout(function () {

                setTimeout(function () {
                    if (data.rspd_code === RespondCodes.Respond_NEW_PASSWORD_CREATED_AND_LOGIN_SUCCEED) {

                        setTimeout(function () {

                            //save tokens on local localStorage
                            localStorage.setItem("secure_data_username", data.token.access_username);
                            localStorage.setItem("secure_data_access_token", data.token.access_jwt_token);
                            localStorage.setItem("secure_data_refresh_token", data.token.access_refresh_token);

                            if(data.data === RoleTypes.ROLE_CLIENT){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                setTimeout(function () {
                                    pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                    pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                    pswdrecover_pg_alertModelDone.modal('show');

                                    setTimeout(function () {
                                        //open to enter password
                                        pswdrecover_pg_pswdadding_model.modal('hide');
                                        pswdrecover_pg_alertModelDone.modal('hide');

                                        window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/client_main_page.html?_ijt=lgk33b09l42ffpce17ruvb5qhv&_ij_reload=RELOAD_ON_SAVE';
                                    }, 2500); // delay

                                }, 100); // delay

                            }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_USER){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                setTimeout(function () {

                                    pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                    pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                    pswdrecover_pg_alertModelDone.modal('show');

                                    setTimeout(function () {
                                        //open to enter password
                                        pswdrecover_pg_pswdadding_model.modal('hide');
                                        pswdrecover_pg_alertModelDone.modal('hide');

                                        window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/user_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                                    }, 3000); // delay

                                }, 100); // delay

                            }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                setTimeout(function () {

                                    pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                    pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                    pswdrecover_pg_alertModelDone.modal('show');

                                    setTimeout(function () {
                                        //open to enter password
                                        pswdrecover_pg_pswdadding_model.modal('hide');
                                        pswdrecover_pg_alertModelDone.modal('hide');

                                        window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/travelpackage_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                                    }, 3000); // delay

                                }, 100); // delay

                            }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_HOTEL){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                    setTimeout(function () {

                                        pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                        pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                        pswdrecover_pg_alertModelDone.modal('show');

                                        setTimeout(function () {
                                            //open to enter password
                                            pswdrecover_pg_pswdadding_model.modal('hide');
                                            pswdrecover_pg_alertModelDone.modal('hide');

                                            window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/hotel_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                                        }, 3000); // delay

                                    }, 100); // delay

                            }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_GUIDE){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                    setTimeout(function () {

                                        pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                        pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                        pswdrecover_pg_alertModelDone.modal('show');

                                        setTimeout(function () {
                                            //open to enter password
                                            pswdrecover_pg_pswdadding_model.modal('hide');
                                            pswdrecover_pg_alertModelDone.modal('hide');

                                            window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/guide_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                                        }, 3000); // delay

                                    }, 100); // delay

                            }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE){
                                //hide loading model
                                pswdrecover_pg_loadingModel.modal('hide');

                                setTimeout(function () {

                                    pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                                    pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                                    pswdrecover_pg_alertModelDone.modal('show');

                                    setTimeout(function () { //open to enter password
                                        pswdrecover_pg_pswdadding_model.modal('hide');
                                        pswdrecover_pg_alertModelDone.modal('hide');

                                        window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/vehicle_admin_main_page.html?_ijt=lfe5pib42qssa1523t2ctc28hn&_ij_reload=RELOAD_ON_SAVE';
                                    }, 3000); // delay

                                }, 100); // delay

                            }
                        }, 600); // delay
                    } else {
                        pswdrecover_pg_alertModelError_title.text("Eror has occurd!");
                        pswdrecover_pg_alertModelError_content.text("Try again...!");
                        pswdrecover_pg_alertModelError.modal('show');

                        setTimeout(function () {
                            pswdrecover_pg_alertModelError.modal('hide');

                        }, 1000); // delay
                    }

                }, 1000); // delay

            }, 2000); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("Eror has occurd!");
                    pswdrecover_pg_alertModelError_content.text("Type correct OTP and password Try again...!");
                    pswdrecover_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        pswdrecover_pg_alertModelError.modal('hide');

                    }, 1000); // delay

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds
        }
    });
}

//-------------- validations ----------------------------------
//usernam txt fld validation
function iisUsernameCheckedRegexLogin(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
pswd_recovery_login_username_txtfld.on('keyup', function () {
    const wtinvalidFeedback = pswd_recovery_login_username_txtfld.siblings('.invalid-feedback');
    if (iisUsernameCheckedRegexLogin(pswd_recovery_login_username_txtfld.val())) {
        pswd_recovery_login_username_txtfld.removeClass('is-invalid');
        wtinvalidFeedback.hide();
        $('#pswd_recovery_login_search_btn').prop("disabled", false);
    } else {
        pswd_recovery_login_username_txtfld.addClass('is-invalid');
        wtinvalidFeedback.show();
        $('#pswd_recovery_login_search_btn').prop("disabled", true);
    }
});

//usernam txt fld validation
function isUsernameOTPRegexLogin(username) {
    const regex = /^\b\d{4}\b$/;
    return regex.test(username);
}
pswd_recovery_login_otp_txtfld.on('keyup', function () {
    const wtinvalidFeedback = pswd_recovery_login_otp_txtfld.siblings('.invalid-feedback');
    if (isUsernameOTPRegexLogin(pswd_recovery_login_otp_txtfld.val())) {
        pswd_recovery_login_otp_txtfld.removeClass('is-invalid');
        wtinvalidFeedback.hide();
        $('#pswd_recovery_login_otp_btn').prop("disabled", false);
    } else {
        pswd_recovery_login_otp_txtfld.addClass('is-invalid');
        wtinvalidFeedback.show();
        $('#pswd_recovery_login_otp_btn').prop("disabled", true);
    }
});

//password txt fld validation
function isUsernamepswdRegexLogin(pswd) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(pswd);
}

pswd_recovery_login_not_confirm_pswd_txtfld.on('keyup', function () {
    const tinvalidFeedback = pswd_recovery_login_not_confirm_pswd_txtfld.siblings('.invalid-feedback');
    const tvalidFeedback = pswd_recovery_login_not_confirm_pswd_txtfld.siblings('.valid-feedback');
    if (isUsernamepswdRegexLogin(pswd_recovery_login_not_confirm_pswd_txtfld.val())
    ) {
        pswd_recovery_login_not_confirm_pswd_txtfld.removeClass('is-invalid');
        pswd_recovery_login_not_confirm_pswd_txtfld.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();
        $('#recover_pg_login_btn').prop("disabled", false);
    } else {
        pswd_recovery_login_not_confirm_pswd_txtfld.removeClass('is-valid');
        pswd_recovery_login_not_confirm_pswd_txtfld.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();
        $('#recover_pg_login_btn').prop("disabled", true);
    }
});
pswd_recovery_login_confirmed_pswd_txtfld.on('keyup', function () {
    const tinvalidFeedback = pswd_recovery_login_confirmed_pswd_txtfld.siblings('.invalid-feedback');
    const tvalidFeedback = pswd_recovery_login_confirmed_pswd_txtfld.siblings('.valid-feedback');
    if (isUsernamepswdRegexLogin(pswd_recovery_login_confirmed_pswd_txtfld.val())
    && pswd_recovery_login_not_confirm_pswd_txtfld.val() === pswd_recovery_login_confirmed_pswd_txtfld.val()
    ) {
        pswd_recovery_login_confirmed_pswd_txtfld.removeClass('is-invalid');
        pswd_recovery_login_confirmed_pswd_txtfld.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();
        $('#recover_pg_login_btn').prop("disabled", false);
    } else {
        pswd_recovery_login_confirmed_pswd_txtfld.removeClass('is-valid');
        pswd_recovery_login_confirmed_pswd_txtfld.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();
        $('#recover_pg_login_btn').prop("disabled", true);
    }
});