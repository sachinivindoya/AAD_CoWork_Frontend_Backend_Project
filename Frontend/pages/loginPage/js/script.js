///////////////////--------------component variables

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


//------------------ login ----------------
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
