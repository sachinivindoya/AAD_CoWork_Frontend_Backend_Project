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
