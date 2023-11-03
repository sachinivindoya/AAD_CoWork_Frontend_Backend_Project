//////////////////////--------------component variables

const login_container = $('#login_container');

const intrologin_pg_loadingModel = $('#intro-login-pg-loading-model');

const intrologin_pg_alertModelError = $('#intro-login-pg-alert-model-error');
const intrologin_pg_alertModelError_title =  $('#intro-login-pg-alert-model-title-error');
const intrologin_pg_alertModelError_content = $('#intro-login-pg-alert-model-content-error');

const intrologin_pg_alertModelDone = $('#intro-login-pg-alert-model-done');
const intrologin_pg_alertModelDone_title =  $('#intro-login-pg-alert-model-title-done');
const intrologin_pg_alertModelDone_content = $('#intro-login-pg-alert-model-content-done');

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');
const pswd_recovery_login_username_container = $('#forgot_pswd_get_username_container');

////////---------- login operation
//fields variables
let loginUsername =  $('#login_Username');
let loginPassword =  $('#login_Password');
const  user_login_btn = $('#user_login_btn');

let logintxtfld1=false;
let logintxtfld2=false;

function isUsernameCheckedRegexLogin(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
function isSignUpPasswordCheckedRegexLogin(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}

//check regax action signup_password
loginUsername.on('keyup', function () {
    const tinvalidFeedback = loginUsername.siblings('.invalid-feedback');
    const tvalidFeedback = loginUsername.siblings('.valid-feedback');
    if (isUsernameCheckedRegexLogin(loginUsername.val())) {
        loginUsername.removeClass('is-invalid');
        loginUsername.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        logintxtfld1=true;

        readyToEnableLoginBtn();//trigger to enable
    } else {
        loginUsername.removeClass('is-valid');
        loginUsername.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        logintxtfld1=false;

        readyToEnableLoginBtn();//trigger to enable
    }
});

//check regax action signup_password
loginPassword.on('keyup', function () {
    const tinvalidFeedback = loginPassword.siblings('.invalid-feedback');
    const tvalidFeedback = loginPassword.siblings('.valid-feedback');
    if (isSignUpPasswordCheckedRegexLogin(loginPassword.val())) {
        loginPassword.removeClass('is-invalid');
        loginPassword.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        logintxtfld2=true;

        readyToEnableLoginBtn();//trigger to enable
    } else {
        loginPassword.removeClass('is-valid');
        loginPassword.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        logintxtfld2=false;

        readyToEnableLoginBtn();//trigger to enable
    }
});

function readyToEnableLoginBtn() {
    if (logintxtfld1 === true && logintxtfld2 === true ) {
        // All conditions are true, enable the signup button
        user_login_btn.prop("disabled", false);
    }
}


function loginFormBtnClicked() {

    //show loading model
    intrologin_pg_loadingModel.modal('show');

    console.log("success");

    $.ajax({
        method:"GET",
        contentType:"application/json",
        url:"http://localhost:1010/main/user/user-login",
        async:false,
        data: {
            username: loginUsername.val(),
            password: loginPassword.val()
        },
        success:function (data){
            if(data.rspd_code === RespondCodes.Respond_PASSWORD_MATCHED){

                if(data.data === RoleTypes.ROLE_CLIENT){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_client_username", data.token.access_username);
                    localStorage.setItem("secure_data_client_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_client_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {
                        s
                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/client_main_page.html?_ijt=lgk33b09l42ffpce17ruvb5qhv&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_USER){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_user_admin_username", data.token.access_username);
                    localStorage.setItem("secure_data_user_admin_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_user_admin_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {

                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/user_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_travelpackage_admin_username", data.token.access_username);
                    localStorage.setItem("secure_data_travelpackage_admin_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_travelpackage_admin_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {

                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/travelpackage_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_HOTEL){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_hotel_admin_username", data.token.access_username);
                    localStorage.setItem("secure_data_hotel_admin_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_hotel_admin_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {

                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/hotel_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_GUIDE){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_guide_admin_username", data.token.access_username);
                    localStorage.setItem("secure_data_guide_admin_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_guide_admin_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {

                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/guide_admin_main_page.html?_ijt=1mvkd5bqbnv72hebvfm2dsuead&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }else if(data.data === RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE){

                    //save tokens on local localStorage - user admin
                    localStorage.setItem("secure_data_vehicle_admin_username", data.token.access_username);
                    localStorage.setItem("secure_data_vehicle_admin_access_token", data.token.access_jwt_token);
                    localStorage.setItem("secure_data_vehicle_admin_refresh_token", data.token.access_refresh_token);

                    setTimeout(function () {

                        //hide loading model
                        intrologin_pg_loadingModel.modal('hide');

                        setTimeout(function () {

                            intrologin_pg_alertModelDone_title.text("Welcome Again!");
                            intrologin_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                            intrologin_pg_alertModelDone.modal('show');

                            setTimeout(function () {
                                window.location.href = 'http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/pages/vehicle_admin_main_page.html?_ijt=lfe5pib42qssa1523t2ctc28hn&_ij_reload=RELOAD_ON_SAVE';
                            }, 1000); // delay

                        }, 100); // delay

                    }, 500); // delay

                }
            }else if(data.rspd_code === RespondCodes.Respond_PASSWORD_NOT_MATCHED){

                setTimeout(function () {

                    //hide loading model
                    intrologin_pg_loadingModel.modal('hide');

                    setTimeout(function () {

                        intrologin_pg_alertModelError_title.text("Username or Password Mismatched!");
                        intrologin_pg_alertModelError_content.text("Try again!");
                        intrologin_pg_alertModelError.modal('show');

                        setTimeout(function () {
                            location.reload(); //reload
                        }, 1000); // 2000 milliseconds

                    }, 100); // 2000 milliseconds

                }, 500); // 2000 milliseconds

            }

        },
        error: function (xhr,exception){
            setTimeout(function () {

                //hide loading model
                intrologin_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    intrologin_pg_alertModelError_title.text("Not Found!");
                    intrologin_pg_alertModelError_content.text("This user is not registered. Please register and try again!");
                    intrologin_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        location.reload(); //reload
                    }, 1000); // 2000 milliseconds

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds

        }
    })
}

///---------------open username add form for recover password
function openRecoveryPasswordUsernameAddPage(){
    //show username adding container and hide login container
    pswd_recovery_login_username_container.css('display','block');
    login_container.css('display','none');
}

//////--------------------------password recovery username

function pswdRecoveryLoginSearchBtnClicked(){
    //show loading model
    intrologin_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: false,
        url: 'http://localhost:1010/main/user/search-user-send-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld.val()),
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.rspd_code === RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED) {
                console.log("Done! Checked Username and sent otp to your mail...");
                resolve(true);
            } else {
                console.log("Error occured!");
                resolve(false);
            }
        },
        error: function (xhr, exception) {
            intrologin_pg_loadingModel.modal('hide');
            throw exception;
        }
    });
}
